package io.github.coswind.pulltorefreshlayout;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class MainActivity extends Activity implements PullToRefreshLayout.PullRefreshListener {
    PullToRefreshLayout pullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getList()));
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_refresh_layout);
        pullToRefreshLayout.setListView(listView);
        pullToRefreshLayout.setUpProgressBar((SmoothProgressBar) findViewById(R.id.ptr_progress_up));
        pullToRefreshLayout.setBottomProgressBar((SmoothProgressBar) findViewById(R.id.ptr_progress_bottom));
        pullToRefreshLayout.setOnPullRefreshListener(this);
    }

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add("test" + i);
        }
        return list;
    }

    @Override
    public void onRefreshingUp() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Notify PullToRefreshLayout that the refresh has finished
                pullToRefreshLayout.setRefreshUpEnd();
            }
        }.execute();
    }

    @Override
    public void onRefreshingBottom() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Notify PullToRefreshLayout that the refresh has finished
                pullToRefreshLayout.setRefreshingBottomEnd();
            }
        }.execute();
    }
}
