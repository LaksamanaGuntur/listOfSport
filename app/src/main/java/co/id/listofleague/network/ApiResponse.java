package co.id.listofleague.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.listofleague.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class ApiResponse {
    @SerializedName("sports")
    private List<ResultData> results;

    public List<ResultData> getResults() {
        return results;
    }

    public void setResults(List<ResultData> results) {
        this.results = results;
    }
}
