package com.ares.rappiinterview.injection.application;

import com.ares.rappiinterview.BuildConfig;
import com.ares.rappiinterview.RappiApp;
import com.ares.rappiinterview.core.SessionManager;
import com.ares.rappiinterview.data.DataRepository;
import com.ares.rappiinterview.data.Repository;
import com.ares.rappiinterview.data.db.MovieRepository;
import com.ares.rappiinterview.utils.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import net.grandcentrix.tray.AppPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ares on 2019-05-27
 */
@Module
public class ApplicationModule {

    private final RappiApp application;

    // Define the Interceptor - Add API Key and user info
    private Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request()
                    .newBuilder()
                    .addHeader(Constants.API_HEADER_KEY, BuildConfig.PUBLIC_KEY)
                    .build();

            return chain.proceed(newRequest);
        }
    };

    public ApplicationModule( RappiApp application) {
        this.application = application;

        // Init Logger
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("Rappi Test")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        // Configure Logger for Debug or Release
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

    }

    @Provides
    @Singleton
    RappiApp providesApplication(){ return application; }

    @Provides
    @Singleton
    SessionManager providesSessionManager(){
        return new SessionManager(new AppPreferences(application), application);
    }

    @Provides
    @Singleton
    Repository provideDataRepository(DataRepository restDataSource) {
        return restDataSource;
    }

    @Provides
    @Singleton
    MovieRepository providesMovieRepository() {
        return new MovieRepository(application);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(SessionManager sessionManager){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        // Set log level for Debug or Release
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                //.addInterceptor(new TokenInterceptor(sessionManager))
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor());

        return builder.build();
    }
}
