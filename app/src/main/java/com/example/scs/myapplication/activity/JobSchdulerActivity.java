package com.example.scs.myapplication.activity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.service.JobSchedulerService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JobSchdulerActivity extends FragmentActivity {
    private int jobId = hashCode();
    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            mJobScheduler.cancel(jobId);

        }
    };
    JobScheduler mJobScheduler;
    //https://www.aliyun.com/jiaocheng/15370.html
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_schduler);
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getPackageName(), JobSchedulerService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(jobId,
                componentName);

        //设置至少延迟多久后执行，单位毫秒.
//        builder.setMinimumLatency(2 * 1000);
        //设置最多延迟多久后执行，单位毫秒。
        builder.setOverrideDeadline(1 * 1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        int i = mJobScheduler.schedule(builder.build());
        System.out.println(i + "");

//        Intent intent = new Intent();
//        ComponentName componentName1 = new ComponentName(getPackageName(), JobSchedulerIntentService.class.getName());
//        JobSchedulerIntentService.enqueueWork(this,componentName1,jobId+1,intent);

//        handler.sendMessageDelayed(Message.obtain(),3000);

////设置任务的延迟执行时间(单位是毫秒)
//        builder.setMinimumLatency(Long.valueOf(delay) * 1000);
////设置任务最晚的延迟时间。如果到了规定的时间时其他条件还未满足,你的任务也会被启动。
//        builder.setOverrideDeadline(Long.valueOf(deadline) * 1000);
////让你这个任务只有在满足指定的网络条件时才会被执行
////        NETWORK_TYPE_ANY 网络状态联网就行setRequiredNetworkType
////        NETWORK_TYPE_NONE 默认的网络连接状态setRequiredNetworkType
////        NETWORK_TYPE_NOT_ROAMING 移动网络连接情况setRequiredNetworkType
////        NETWORK_TYPE_UNMETERED WIFI连接情况setRequiredNetworkType
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//
////你的任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务。
//        builder.setRequiresDeviceIdle(mCb_RequiresIdle.isChecked());
////告诉你的应用,只有当设备在充电时这个任务才会被执行。
//        builder.setRequiresCharging(mCb_RequiresCharging.isChecked());
//// 额外字段
//        PersistableBundle extras = new PersistableBundle();
//        String workDuration = mEt_DurationTime.getText().toString();
//        if (TextUtils.isEmpty(workDuration)) {
//            workDuration = "1";
//        }
//        extras.putLong(WORK_DURATION_KEY, Long.valueOf(workDuration) * 1000);
//        builder.setExtras(extras);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().build();
        Call call =  okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
