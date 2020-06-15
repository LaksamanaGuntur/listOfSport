package co.id.listofleague.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.listofleague.R;
import co.id.listofleague.data.ResultData;
import co.id.listofleague.helper.Constant;
import co.id.listofleague.ui.main.MainPresenter;


/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private List<ResultData> mResultData;
    private List<ResultData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(List<ResultData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_activity, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultData resultData = mFilteredList.get(position);

        holder.resultData = resultData;
        holder.mTxtTitle.setText(resultData.getStrSport());

        Picasso.with(mContext)
                .load(resultData.getStrSportThumb())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mResultData;
                } else {
                    ArrayList<ResultData> filteredList = new ArrayList<>();
                    for (ResultData resultData : mResultData) {
                        if (resultData.getStrSport().toLowerCase().contains(charString)) {
                            filteredList.add(resultData);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ResultData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_thumbnail)
        ImageView mImageView;
        @BindView(R.id.txt_title)
        TextView mTxtTitle;

        ResultData resultData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new MainPresenter().getView().openDetail(resultData);
        }
    }
}
