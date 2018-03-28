package com.example.scs.myapplication.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scs.myapplication.MyApplication;
import com.example.scs.myapplication.R;
import com.example.scs.myapplication.retrofit.util.RetrofitUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    EditText ed_user, ed_pass;
    MyApplication myApplication;
    TextView tv_msg;
    Button btn_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        btn_other = (Button) findViewById(R.id.btn_other);
        myApplication = (MyApplication) getApplication();

        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request_Interface request_interface = RetrofitUtils.getInstance().create(Request_Interface.class);
                request_interface.getUserScenes().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                tv_msg.setText(responseBody.string());
                            }
                        });
            }
        });
    }

    public void login(View view) {
        String user = ed_pass.getText().toString();
        String pass = ed_user.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("username", "15706844102");
        map.put("pushType", "1");
        map.put("password", "e10adc3949ba59abbe56e057f20f883e");
        map.put("deviceToken", "AteDBO4bsJC9QiZTpQIFvinCzhdBE770CzIqbHuHFxrQ");

        Request_Interface request_interface = RetrofitUtils.getInstance().create(Request_Interface.class);
        request_interface.login(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        if (jsonObject.getBoolean("Success")) {
                            btn_other.setVisibility(View.VISIBLE);
                        }
                        tv_msg.setText(responseBody);
                    }
                });


    }
}
