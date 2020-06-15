package co.id.listofleague.ui.detail;

import co.id.listofleague.model.DataModel;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class DetailPresenter implements DetailContract.UserActionListener {
    private DetailContract.View mView;
    private DataModel mDataModel;

    public DetailPresenter(DataModel dataModel) {
        mDataModel = dataModel;
    }

    public void setView(DetailContract.View view){
        mView = view;
    }

}
