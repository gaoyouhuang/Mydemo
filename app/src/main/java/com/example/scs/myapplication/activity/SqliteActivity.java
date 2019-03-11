package com.example.scs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.bean.EventbusMessage;
import com.example.scs.myapplication.sql.SqlStudent;
import com.example.scs.myapplication.sql.Student;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_showSql;
    //    SqlBean sqlBean;
    Button btn_saveBean, btn_getListBean, btn_seachBean, btn_deleBean, btn_deleteAll;

    SqlStudent sqlStudent;

    protected void onDestroy() {
        super.onDestroy();
//        https://blog.csdn.net/WBST5/article/details/81089710
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void change_TextView(EventbusMessage message){
        tv_showSql.setText(message.getMsg());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
//        sqlBean = SqlBean.getInstance();
        EventBus.getDefault().register(this);

        sqlStudent = SqlStudent.getInstance();

        tv_showSql = (TextView) findViewById(R.id.tv_showSql);
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
//        Student student = new Student();
//        student.setActivity(this);

//        final Bean bean = new Bean();
//        bean.setName("hahaha");
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                synchronized (bean) {
//                    try {
//                        for (int i = 0; i < 10; i++) {
//                            Date date = new Date();
//                            System.out.println(bean.getName() + " A " + i+" "+date.getTime());
//                            sleep(100);
//                            if (i == 5) {
//                               sticky = false bean.wait();
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        };
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                synchronized (bean) {
//                    for (int i = 0; i < 10; i++) {
//                        Date date = new Date();
//                        System.out.println(bean.getName() + " B " + i+" "+date.getTime());
//                        try {
//                            sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
////                        if (i == 5) {
////                            bean.notify();
////                        }
//                    }
//
//                }
//            }
//        };
//
//        thread.start();
//        thread1.start();
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data 十六进制char[]
     * @return byte[]
     * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * 16进制转10进制
     *
     * @param hex
     * @return
     */
    public int hex2decimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

    /**
     * 16进制转2进制
     *
     * @param hex
     * @return
     */
    public String hexStringToByte(String hex) {
        int i = Integer.parseInt(hex, 16);
        String str2 = Integer.toBinaryString(i);
        return str2;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch    十六进制char
     * @param index 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_saveBean:
                String data = "F3 F5 0F 33 00 65 00 01 00 00 00 02 02 00 02 15 13 1B F1 F3 FB";
                data = data.replaceAll(" ", "");
//                System.out.println("16-->2 string "+hexStringToByte(data));
//                System.out.println("16-->10 int "+hex2decimal(data));
                System.out.println("16-->2 byte " + decodeHex(data.toCharArray()) + " " + (byte) Integer.parseInt("02", 16));
                System.out.println(" " + ((byte) Integer.parseInt("02", 16) & 0xFF));
//                Bean bean = new Bean();
//                bean.setName("11111");
//                sqlBean.saveBean(bean);
//
//                List<Bean> list = sqlBean.getListBean();
//                StringBuffer stringBuffer = new StringBuffer();
//                if (list.size() == 0) {
//                    stringBuffer.append("no list");
//                } else {
//                    for (Bean bean1 : list) {
//                        stringBuffer.append("id " + bean1.getId() + " name " + bean1.getName());
//                    }
//                }
//                tv_showSql.setText(stringBuffer);
                ArrayList<Student> studentArrayList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    studentArrayList.add(new Student("name" + i, i));
                }
                sqlStudent.add(studentArrayList);

                break;
            case R.id.btn_getListBean:
//                List<Bean> list2 = sqlBean.getListBean();
//
//                StringBuffer stringBuffer2 = new StringBuffer();
//                if (list2.size() == 0) {
//                    stringBuffer2.append("no list");
//                } else {
//                    for (Bean bean1 : list2) {
//                        stringBuffer2.append("id " + bean1.getId() + " name " + bean1.getName());
//                    }
//                }
//                tv_showSql.setText(stringBuffer2);
                sqlStudent.seach("", true);
                break;
            case R.id.btn_seachBean:
//                Bean bean3 = sqlBean.seachBean("11111");
//
//                if (bean3 != null) {
//                    tv_showSql.setText("seach id = " + bean3.getId());
//                } else {
//                    tv_showSql.setText("no find");
//                }
                sqlStudent.seach("name1", false);
                sqlStudent.update("name3", new Student("namenamename3333", 3333));

                break;
            case R.id.btn_deleBean:
//                sqlBean.deleBean("11111");
//
//                List<Bean> list4 = sqlBean.getListBean();
//                StringBuffer stringBuffer4 = new StringBuffer();
//                if (list4.size() == 0) {
//                    stringBuffer4.append("no list");
//                } else {
//                    for (Bean bean1 : list4) {
//                        stringBuffer4.append("id " + bean1.getId() + " name " + bean1.getName());
//                    }
//                }
//                tv_showSql.setText(stringBuffer4);
                sqlStudent.dele("name2", false);
                break;
            case R.id.btn_deleteAll:
//                sqlBean.deleteAll();
//
//                List<Bean> list1 = sqlBean.getListBean();
//                StringBuffer stringBuffer1 = new StringBuffer();
//                if (list1.size() == 0) {
//                    stringBuffer1.append("no list");
//                } else {
//                    for (Bean bean1 : list1) {
//                        stringBuffer1.append("id " + bean1.getId() + " name " + bean1.getName());
//                    }
//                }
//                tv_showSql.setText(stringBuffer1);
                sqlStudent.dele("", true);
                EventBus.getDefault().post(new EventbusMessage("EventBus_send_msg",1));
                break;
        }
    }
}
