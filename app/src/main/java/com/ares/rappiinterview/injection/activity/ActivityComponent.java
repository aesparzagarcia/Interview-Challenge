package com.ares.rappiinterview.injection.activity;

import android.content.Context;

import com.ares.rappiinterview.injection.application.ApplicationComponent;

import dagger.Component;

/**
 * Created by ares on 2019-05-27
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Context context();
}
