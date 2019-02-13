package com.example.scs.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.scs.myapplication.BaseRecyclerAdapter;
import com.example.scs.myapplication.R;
import com.example.scs.myapplication.RecycleViewDivider;
import com.example.scs.myapplication.myinterface.OnLoadMore;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    SwipeRefreshLayout swr_layout;
    RecyclerView rv;
    int page = 1;
    ArrayList<String> service_list = new ArrayList<>();
    ArrayList<String> data_list = new ArrayList<>();
    DemoAdatper demoAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 23; i++) {
            service_list.add(i + "");
        }

        swr_layout = findViewById(R.id.swr_layout);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        swr_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getdata(page);
            }
        });
        demoAdatper = new DemoAdatper(getApplicationContext());
        demoAdatper.setDatas(data_list);
//        demoAdatper.setFooterView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.demo_rv_footer, null));
        demoAdatper.setOnLoadMoreListener(new OnLoadMore() {
            @Override
            public void onLoadMore(int start) {
                page++;
                getdata(page);

            }
        });
        RecycleViewDivider recycleViewDivider = new RecycleViewDivider(getApplicationContext(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.colorAccent));
        rv.addItemDecoration(recycleViewDivider);

        rv.setAdapter(demoAdatper);
        getdata(page);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getdata(int page) {
        swr_layout.setRefreshing(false);
        demoAdatper.setLoadingMore(false);

        ArrayList<String> list = new ArrayList<>();
        int start = (page - 1) * 10;
        int end = page * 10;
        if (start < service_list.size()) {
            end = end < service_list.size() ? end : service_list.size();
            for (int i = start; i < end; i++) {
                list.add(service_list.get(i));
            }
        }
        if (page == 1) {
            demoAdatper.setDatas(list);
        } else {
            demoAdatper.addDatas(list);
        }
        if (list.size() <= 0) {
            demoAdatper.setHasNextPage(false);
        } else {
            demoAdatper.setHasNextPage(true);
        }

    }

    public class DemoAdatper extends BaseRecyclerAdapter<String, DemoAdatper.DemoItem> {

        LayoutInflater inflater;

        public DemoAdatper(Context context) {
            super(context);
            inflater = LayoutInflater.from(context);
        }

//        @Override
//        public DemoItem onCreateFoot(View footView) {
//            return new DemoItem(footView);
//        }

        @Override
        public DemoItem onCreateHolder(ViewGroup parent, int viewType) {
            return new DemoItem(inflater.inflate(R.layout.demo_rv_item, parent, false));
        }

        @Override
        public void onBind(DemoItem viewHolder, int realPosition, String data) {
            viewHolder.tv.setText(data + "");
        }

        public class DemoItem extends RecyclerView.ViewHolder {
            TextView tv;

            public DemoItem(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
