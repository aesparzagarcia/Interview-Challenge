package com.ares.rappiinterview.injection.application;

import com.ares.rappiinterview.RappiApp;
import com.ares.rappiinterview.core.SessionManager;
import com.ares.rappiinterview.data.Repository;
import com.ares.rappiinterview.data.db.MovieRepository;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by ares on 2019-05-27
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(RappiApp application);

    RappiApp application();

    SessionManager sessionManager();

    Repository repository();

    MovieRepository dbRepository();

    OkHttpClient httpClient();

}
