package co.id.listofleague.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Data
@Entity
public class ResultData implements Parcelable {
    @SerializedName("idSport")
    private String idSport;
    @SerializedName("strSport")
    private String strSport;
    @SerializedName("strFormat")
    private String strFormat;
    @SerializedName("strSportThumb")
    private String strSportThumb;
    @SerializedName("strSportThumbGreen")
    private String strSportThumbGreen;
    @SerializedName("strSportDescription")
    private String strSportDescription;

    protected ResultData(Parcel in) {
        idSport = in.readString();
        strSport = in.readString();
        strFormat = in.readString();
        strSportThumb = in.readString();
        strSportThumbGreen = in.readString();
        strSportDescription = in.readString();
    }

    @Generated(hash = 2011758719)
    public ResultData(String idSport, String strSport, String strFormat,
            String strSportThumb, String strSportThumbGreen,
            String strSportDescription) {
        this.idSport = idSport;
        this.strSport = strSport;
        this.strFormat = strFormat;
        this.strSportThumb = strSportThumb;
        this.strSportThumbGreen = strSportThumbGreen;
        this.strSportDescription = strSportDescription;
    }

    @Generated(hash = 1484394295)
    public ResultData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSport);
        dest.writeString(strSport);
        dest.writeString(strFormat);
        dest.writeString(strSportThumb);
        dest.writeString(strSportThumbGreen);
        dest.writeString(strSportDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getIdSport() {
        return this.idSport;
    }

    public void setIdSport(String idSport) {
        this.idSport = idSport;
    }

    public String getStrSport() {
        return this.strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrFormat() {
        return this.strFormat;
    }

    public void setStrFormat(String strFormat) {
        this.strFormat = strFormat;
    }

    public String getStrSportThumb() {
        return this.strSportThumb;
    }

    public void setStrSportThumb(String strSportThumb) {
        this.strSportThumb = strSportThumb;
    }

    public String getStrSportThumbGreen() {
        return this.strSportThumbGreen;
    }

    public void setStrSportThumbGreen(String strSportThumbGreen) {
        this.strSportThumbGreen = strSportThumbGreen;
    }

    public String getStrSportDescription() {
        return this.strSportDescription;
    }

    public void setStrSportDescription(String strSportDescription) {
        this.strSportDescription = strSportDescription;
    }

    public static final Creator<ResultData> CREATOR = new Creator<ResultData>() {
        @Override
        public ResultData createFromParcel(Parcel in) {
            return new ResultData(in);
        }

        @Override
        public ResultData[] newArray(int size) {
            return new ResultData[size];
        }
    };
}
