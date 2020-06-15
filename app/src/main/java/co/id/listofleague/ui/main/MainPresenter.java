package co.id.listofleague.ui.main;

import java.lang.ref.WeakReference;

import co.id.listofleague.data.ResultData;
import co.id.listofleague.model.DataModel;
import co.id.listofleague.network.ApiResponse;
import co.id.listofleague.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class MainPresenter implements MainContract.UserActionListener {
    private static WeakReference<MainContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public MainPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public MainPresenter() {

    }

    public void setView(MainContract.View view) {
        mView = new WeakReference<>(view);
    }

    public MainContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        mDataModel.deleteDataList();
        getView().showProgressBar();
        mMainRepository.getData()
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        getView().hideProgressBar();
                        getView().setAdapter(apiResponse.getResults());
                        saveData(apiResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        getView().hideProgressBar();
                        getView().setAdapter(mDataModel.getAllData());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveData(ApiResponse apiResponse) {
        for(ResultData resultData : apiResponse.getResults()){
            mDataModel.insertData(resultData);
        }
    }
}
