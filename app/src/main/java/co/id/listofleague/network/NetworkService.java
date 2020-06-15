package co.id.listofleague.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public interface NetworkService {
    @GET("all_sports.php")
    Flowable<ApiResponse> getData();

}
