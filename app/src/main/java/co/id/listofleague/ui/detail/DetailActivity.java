package co.id.listofleague.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.listofleague.CoreApp;
import co.id.listofleague.R;
import co.id.listofleague.data.ResultData;
import co.id.listofleague.di.module.MainActivityModule;
import co.id.listofleague.helper.Constant;


public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.desc)
    TextView mTextView;

    private DetailContract.UserActionListener mUserActionListener;
    private ResultData mResultData;
    private boolean mIsInFavorites = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = detailPresenter;
        detailPresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    public void initializeData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constant.ITEM_DETAIL)) {
                Bundle b = intent.getBundleExtra(Constant.ITEM_DETAIL);
                mResultData = b.getParcelable(Constant.ITEM_DETAIL);

                Picasso.with(this)
                        .load(mResultData.getStrSportThumb())
                        .into(mImage);

                mTextView.setText(mResultData.getStrSportDescription());
                toolbar.setTitle(mResultData.getStrSport());
                setSupportActionBar(toolbar);
                ActionBar ab = getSupportActionBar();
                ab.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
