package com.example.scs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.sql.Bean;
import com.example.scs.myapplication.sql.SqlBean;

import java.util.List;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_showSql;
    SqlBean sqlBean;
    Button btn_saveBean, btn_getListBean, btn_seachBean, btn_deleBean, btn_deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        tv_showSql = (TextView) findViewById(R.id.tv_showSql);
        sqlBean = SqlBean.getInstance();
        btn_saveBean = (Button) findViewById(R.id.btn_saveBean);
        btn_saveBean.setOnClickListener(this);
        btn_getListBean = (Button) findViewById(R.id.btn_getListBean);
        btn_getListBean.setOnClickListener(this);
        btn_seachBean = (Button) findViewById(R.id.btn_seachBean);
        btn_seachBean.setOnClickListener(this);
        btn_deleBean = (Button) findViewById(R.id.btn_deleBean);
        btn_deleBean.setOnClickListener(this);
        btn_deleteAll = (Button) findViewById(R.id.btn_deleteAll);
        btn_deleteAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_saveBean:
                Bean bean = new Bean();
                bean.setName("11111");
                sqlBean.saveBean(bean);

                List<Bean> list = sqlBean.getListBean();
                StringBuffer stringBuffer = new StringBuffer();
                if (list.size() == 0) {
                    stringBuffer.append("no list");
                } else {
                    for (Bean bean1 : list) {
                        stringBuffer.append("id " + bean1.getId() + " name " + bean1.getName());
                    }
                }
                tv_showSql.setText(stringBuffer);

                break;
            case R.id.btn_getListBean:
                List<Bean> list2 = sqlBean.getListBean();

                StringBuffer stringBuffer2 = new StringBuffer();
                if (list2.size() == 0) {
                    stringBuffer2.append("no list");
                } else {
                    for (Bean bean1 : list2) {
                        stringBuffer2.append("id " + bean1.getId() + " name " + bean1.getName());
                    }
                }
                tv_showSql.setText(stringBuffer2);

                break;
            case R.id.btn_seachBean:
                Bean bean3 = sqlBean.seachBean("11111");

                if (bean3 != null) {
                    tv_showSql.setText("seach id = " + bean3.getId());
                } else {
                    tv_showSql.setText("no find");
                }
                break;
            case R.id.btn_deleBean:
                sqlBean.deleBean("11111");

                List<Bean> list4 = sqlBean.getListBean();
                StringBuffer stringBuffer4 = new StringBuffer();
                if (list4.size() == 0) {
                    stringBuffer4.append("no list");
                } else {
                    for (Bean bean1 : list4) {
                        stringBuffer4.append("id " + bean1.getId() + " name " + bean1.getName());
                    }
                }
                tv_showSql.setText(stringBuffer4);
                break;
            case R.id.btn_deleteAll:
                sqlBean.deleteAll();

                List<Bean> list1 = sqlBean.getListBean();
                StringBuffer stringBuffer1 = new StringBuffer();
                if (list1.size() == 0) {
                    stringBuffer1.append("no list");
                } else {
                    for (Bean bean1 : list1) {
                        stringBuffer1.append("id " + bean1.getId() + " name " + bean1.getName());
                    }
                }
                tv_showSql.setText(stringBuffer1);
                break;
        }
    }
}
