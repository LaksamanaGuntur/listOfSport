package co.id.listofleague.di.component;

import javax.inject.Singleton;

import co.id.listofleague.di.module.AppModule;
import co.id.listofleague.di.module.MainActivityModule;
import co.id.listofleague.di.module.NetworkModule;
import dagger.Component;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {
    MainComponent plus(MainActivityModule mainActivityModule);
}
