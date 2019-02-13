package com.example.scs.myapplication.service;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

public class JobSchedulerIntentService extends JobIntentService {
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        System.out.println("jobschedulerintentservice");
    }
}
