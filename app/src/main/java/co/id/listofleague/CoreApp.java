package co.id.listofleague;

import android.app.Application;

import co.id.listofleague.di.component.AppComponent;
import co.id.listofleague.di.component.DaggerAppComponent;
import co.id.listofleague.di.module.AppModule;
import co.id.listofleague.di.module.NetworkModule;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class CoreApp extends Application {
    private AppComponent appComponent = createAppComponent();

    private static CoreApp instance;

    public static CoreApp get() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    protected AppComponent createAppComponent() {
        return appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return  appComponent;
    }
}
