package com.example.keywordlist.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keywordlist.R;
import com.example.keywordlist.activities.adapters.KeywordAdapter;
import com.example.keywordlist.activities.presenters.MainActivityPresenter;
import com.example.keywordlist.activities.views.GetKeywordListView;
import com.example.keywordlist.models.Keyword;
import com.example.keywordlist.utils.ColorGenerator;
import com.example.keywordlist.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View mRootView;

    private View mKeywordListLayout;
    private TextView mTvTitle;
    private RecyclerView mRvItems;

    private KeywordAdapter mAdapter;

    private boolean mLoading = false;

    private MainActivityPresenter mPresenter;
    private GetKeywordListView mGetKeywordListView = new GetKeywordListView() {
        @Override
        public void onConnectionError() {
            mLoading = false;
            showSnackbar(getString(R.string.error_connection_error), getString(R.string.text_try_again), () -> fetchData());
        }

        @Override
        public void onConnectionTimeout() {
            mLoading = false;
            showSnackbar(getString(R.string.error_connection_timeout), getString(R.string.text_try_again), () -> fetchData());
        }

        @Override
        public void onSuccess(List<String> items) {
            mLoading = false;

            if (items != null && !items.isEmpty()) {
                new ProcessResultTask(MainActivity.this, items).execute();
            }
        }

        @Override
        public void onFailed(Throwable throwable) {
            mLoading = false;
            showToast(throwable == null ? null : throwable.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenter(mGetKeywordListView);

        initViews();
        fetchData();
    }

    private void initViews() {

        mRootView = findViewById(R.id.root_view_layout);
        mKeywordListLayout = findViewById(R.id.keyword_list_layout);
        mTvTitle = findViewById(R.id.tv_title);
        mRvItems = findViewById(R.id.rv_items);

        displayKeywordListView(false);

        mAdapter = new KeywordAdapter(MainActivity.this);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));

        mRvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvItems.addItemDecoration(divider);
        mRvItems.setAdapter(mAdapter);

    }

    private void fetchData() {
        if (!mLoading) {

            if (Utils.hasNetworkConnection(this)) {
                mLoading = true;
                mPresenter.getKeywordList();
            } else {
                mGetKeywordListView.onConnectionError();
            }

        }
    }

    private void showToast(String message) {
        if (message == null || message.isEmpty()) return;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(String message,
                              String buttonTitle,
                              Runnable buttonAction) {
        if (message == null || message.isEmpty()) return;

        Snackbar.make(mRootView, message, Snackbar.LENGTH_INDEFINITE).setAction(buttonTitle, v -> {
            if (buttonAction != null) buttonAction.run();
        }).show();
    }

    private void displayKeywordListView(boolean visible) {
        mKeywordListLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    static class ProcessResultTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<MainActivity> self;
        private List<String> items;
        private List<Keyword> objects;

        public ProcessResultTask(MainActivity self, List<String> items) {
            this.self = new WeakReference<>(self);
            this.items = items;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (self.get() == null || items == null || items.isEmpty()) {
                return null;
            }

            objects = new ArrayList<>();
            Iterator<String> iterator = items.iterator();

            while (iterator.hasNext()) {
                String item = iterator.next();
                if (item != null && !item.isEmpty()) {
                    objects.add(new Keyword(item, ColorGenerator.getInstance().getColor(item)));
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (self.get() != null) {
                self.get().mAdapter.setData(objects);
                self.get().displayKeywordListView(true);
            }

        }
    }

}
