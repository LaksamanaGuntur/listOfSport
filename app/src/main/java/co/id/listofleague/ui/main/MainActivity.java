package co.id.listofleague.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.listofleague.CoreApp;
import co.id.listofleague.R;
import co.id.listofleague.adapter.ListAdapter;
import co.id.listofleague.data.ResultData;
import co.id.listofleague.di.module.MainActivityModule;
import co.id.listofleague.helper.Constant;
import co.id.listofleague.ui.detail.DetailActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.et_search)
    EditText mSearchEditText;

    private MainContract.UserActionListener mUserActionListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = mainPresenter;
        mainPresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void setAdapter(List<ResultData> resultData) {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mListAdapter = new ListAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);

        setupOnFocusListener(mSearchEditText);
    }

    @Override
    public void initializeData() {
        mUserActionListener.getData();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void openDetail(ResultData resultData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.ITEM_DETAIL, resultData);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constant.ITEM_DETAIL, b);
        startActivity(intent);
    }

    private void setupOnFocusListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}
