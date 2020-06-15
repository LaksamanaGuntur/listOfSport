package co.id.listofleague.ui.main;

import java.util.List;

import co.id.listofleague.data.ResultData;
import co.id.listofleague.network.ApiResponse;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class MainContract {

    public interface View{
        void setAdapter(List<ResultData> resultData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void openDetail(ResultData resultData);
    }

    public interface UserActionListener{
        void getData();
        void saveData(ApiResponse apiResponse);
    }
}
