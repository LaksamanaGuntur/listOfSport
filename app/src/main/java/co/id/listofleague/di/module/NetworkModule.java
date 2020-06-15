package co.id.listofleague.di.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.id.listofleague.helper.Constant;
import co.id.listofleague.network.NetworkService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(Constant.URL_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    public NetworkService provideOchaService(Retrofit restAdapter) {
        return restAdapter.create(NetworkService.class);
    }
}
