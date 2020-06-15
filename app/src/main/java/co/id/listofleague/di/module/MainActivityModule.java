package co.id.listofleague.di.module;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.id.listofleague.CoreApp;
import co.id.listofleague.data.DaoMaster;
import co.id.listofleague.data.DaoSession;
import co.id.listofleague.di.scope.ActivityScope;
import co.id.listofleague.helper.Constant;
import co.id.listofleague.model.DataModel;
import co.id.listofleague.network.NetworkService;
import co.id.listofleague.repository.MainRepository;
import co.id.listofleague.ui.detail.DetailActivity;
import co.id.listofleague.ui.detail.DetailPresenter;
import co.id.listofleague.ui.main.MainActivity;
import co.id.listofleague.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Module
public class MainActivityModule {
    private MainActivity mainActivity;
    private DetailActivity detailActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivityModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(NetworkService networkService) {
        return new MainRepository(networkService);
    }

    @Provides
    @ActivityScope
    DaoSession provideDaoSession() {
        String DbName = Constant.DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(CoreApp.get(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        Log.d("DB PATH", db.getPath());
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


    @Provides
    @ActivityScope
    DataModel provideDataModel(DaoSession daoSession){
        return new DataModel(daoSession);
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new MainPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    DetailPresenter provideDetailPresenter(DataModel dataModel) {
        return new DetailPresenter(dataModel);
    }
}
