package com.santosh.miniredditapp.di.component;

import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.di.module.AppModule;
import com.santosh.miniredditapp.di.module.BackendModule;
import com.santosh.miniredditapp.di.scope.ApplicationScope;
import com.santosh.miniredditapp.ui.RedditMainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * In the @Component annotation the modules are specified which are used to create the implementation of the component.
 */
@ApplicationScope
@Component(

        modules = {
                AppModule.class,
                BackendModule.class
        }
)

public interface AppComponent {

    void inject(MiniRedditApplication miniRedditApplication);

    void inject(RedditMainActivity redditMainActivity);
}
