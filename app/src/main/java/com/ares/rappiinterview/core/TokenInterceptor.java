package com.ares.rappiinterview.core;

import androidx.annotation.NonNull;

import com.ares.rappiinterview.BuildConfig;
import com.ares.rappiinterview.data.model.BaseResponse;
import com.ares.rappiinterview.data.model.GrantType;
import com.ares.rappiinterview.data.model.TokenResponse;
import com.ares.rappiinterview.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ares on 2019-05-27
 */
public class TokenInterceptor implements Interceptor {

    @NonNull
    private final SessionManager sessionManager;
    @NonNull
    private final OkHttpClient httpClient;

    public TokenInterceptor(@NonNull SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        httpClient = new OkHttpClient();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        // Platform request, here original request will be executed
        Response response = chain.proceed(request);

        if(response.code() == 400){
            Logger.i("Error Interceptor -> 400 ERROR");

            // Response body can only be read once
            String responseBody;

            // Save token of this request for future
            String token = sessionManager.getUserToken();

            //if unauthorized
            Gson gson = new GsonBuilder().create();
            BaseResponse baseResponse;

            try{
                responseBody = response.body().string();
                baseResponse = gson.fromJson(responseBody, BaseResponse.class);
            }catch (IOException ex){
                Logger.i("Error Interceptor -> JSON parse error");
                ex.printStackTrace();

                return response;
            }

            if(baseResponse != null && baseResponse.getCode() == Constants.API_BODY_ERROR_CODE_INVALID_TOKEN){
                String currentToken = sessionManager.getUserToken();

                // Compare current token with token that was stored before, if it was not updated -do update
                if(currentToken != null && currentToken.equals(token)){
                    // Refresh token
                    boolean success = refreshToken();

                    // If refresh token failed for some reason
                    if (!success) {
                        return response;
                    }
                }

                String newToken = sessionManager.getUserToken();

                // Retry requires new auth token,
                if (newToken != null && !newToken.equals(currentToken)) {
                    // Set auth token to updated
                    setAuthHeader(builder, newToken);
                    request = builder.build();

                    // Repeat request with new token
                    return chain.proceed(request);
                } else {
                    return response;
                }
            } else {
                response = response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), responseBody))
                        .build();
            }
        }

        return response;
    }

    private void setAuthHeader(Request.Builder builder, String newToken) {
        if (newToken != null) {
            //Add Auth token to each request if authorized
            builder.header(Constants.API_HEADER_AUTH, String.format("Bearer %s", newToken));
        }
    }

    private boolean refreshToken() throws IOException{
        // Refresh token, synchronously, save it, and return result code
        boolean success = false;
        String requestToken = sessionManager.getUserToken();

        if (requestToken != null && !requestToken.isEmpty()) {
            FormBody.Builder formBuilder = new FormBody.Builder()
                    .add(Constants.API_BODY_KEY_GRANT_TYPE, GrantType.REQUEST_TOKEN)
                    .add(Constants.API_BODY_KEY_REFRESH_TOKEN, requestToken);

            Request request = new Request.Builder()
                    .url(Constants.API_URL + "/request_token")
                    .addHeader(Constants.API_HEADER_KEY, Constants.API_KEY)
                    .addHeader(Constants.API_HEADER_CONTENT_TYPE, Constants.API_HEADER_CONTENT_TYPE_VALUE)
                    .addHeader(Constants.API_HEADER_AUTH, "Bearer" + BuildConfig.BEARER)
                    .post(formBuilder.build())
                    .build();

            Response response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Retrieve response body
            Gson gson = new GsonBuilder().create();
            TokenResponse tokenResponse = null;

            try {
                tokenResponse = gson.fromJson(response.body().string(), TokenResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (tokenResponse != null) {
                final String request_token = tokenResponse.getRequest_token();

                // Store new access token
                sessionManager.setUserToken(request_token);

                success = true;
            }
        }

        return success;
    }
}
