package com.example.newplanner;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class add_schedule_dialog extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    private static final String TAG = "a";
    private static final String INTENT_ACTION = "aa";
    int AlarmYear, AlarmMonth, AlarmDay, AlarmHour, AlarmMinute;
    Toast toast;
    int alarmflag;
    Calendar calendar = Calendar.getInstance();
    Calendar tempcalendar = null;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.add_schedule_dialog);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("HH시 mm분");
        String formatDate = sdfNow.format(date);
        AlarmHour = date.getHours();
        AlarmMinute = date.getMinutes();

        TextView timetext = (TextView)findViewById(R.id.timetext);
        timetext.setText(formatDate);

        TextView datetext = (TextView)findViewById(R.id.datetext);
        Intent intent = getIntent();
        AlarmDay = intent.getIntExtra("day", 0);
        AlarmMonth = intent.getIntExtra("month",0)-1;
        AlarmYear = intent.getIntExtra("year", 0);
        datetext.setText(AlarmYear+"년 "+AlarmMonth+1+"월 "+AlarmDay+"일");






        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
                tempcalendar = calendar;
                switch(i) {
                    case 0:
                        alarmflag=0;
                        toast = Toast.makeText(add_schedule_dialog.this,"알림을 설정하지 않습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(false);
                        break;
                    case 1:
                        alarmflag = 1;
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                    case 2:
                        alarmflag=2;
                        tempcalendar.add(Calendar.MINUTE, -3);
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                    case 3:
                        alarmflag=3;
                        /*
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();

                         */
                        toast.makeText(add_schedule_dialog.this, calendar.get(Calendar.MINUTE)+"분에 알림이 설정되었습니다!", Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);

                        break;
                    case 4:
                        alarmflag=4;
                        calendar.setTimeInMillis(calendar.getTimeInMillis()-60*1000*10);
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                    case 5:
                        alarmflag=5;
                        calendar.setTimeInMillis(calendar.getTimeInMillis()-60*1000*15);
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                    case 6:
                        alarmflag=6;
                        calendar.setTimeInMillis(calendar.getTimeInMillis()-60*1000*30);
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                    case 7:
                        alarmflag=7;
                        calendar.setTimeInMillis(calendar.getTimeInMillis()-60*1000*60);
                        toast = Toast.makeText(add_schedule_dialog.this,AlarmMonth+"월 "+AlarmDay+"일 "+AlarmHour+"시 "+AlarmMinute+"분에 알람이 설정되었습니다!",Toast.LENGTH_SHORT);
                        toast.show();
                        checkBox.setChecked(true);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button alarmbutton =(Button)findViewById(R.id.notification);

    }

    public void dateselect(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(add_schedule_dialog.this, now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), null);

    }

    public void timeselect(View v){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(add_schedule_dialog.this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
        tpd.show(getFragmentManager(), null);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        TextView datetext = (TextView)findViewById(R.id.datetext);
        AlarmYear = year;
        AlarmMonth = monthOfYear;
        AlarmDay = dayOfMonth;
        int Month = monthOfYear + 1;
        int Monthend = monthOfYearEnd+1;
        if(year == yearEnd && monthOfYear == monthOfYearEnd && dayOfMonth ==dayOfMonthEnd) {
            datetext.setText(year + "년 " + Month + "월 " + dayOfMonth + "일");
        }
        else {
            datetext.setText(year + "년 " + Month + "월 " + dayOfMonth + "일 ~ "+yearEnd+"년 "+Monthend+"월 "+dayOfMonthEnd+"일");
        }
        calendar.set(AlarmYear, AlarmMonth, AlarmDay, AlarmHour, AlarmMinute, 0);

    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        TextView timetext = (TextView)findViewById(R.id.timetext);
        AlarmHour = hourOfDay;
        AlarmMinute = minute;
        if(hourOfDay==hourOfDayEnd&&minute==minuteEnd){
            timetext.setText(hourOfDay + "시 " + minute + "분");
        }
        else if(minute>minuteEnd||hourOfDay>hourOfDayEnd){
            timetext.setText(hourOfDay + "시 " + minute + "분");
        }
        else if(minute>=10&&minuteEnd>=10) {
            timetext.setText(hourOfDay + "시 " + minute + "분 ~ " + hourOfDayEnd + "시 " + minuteEnd + "분");
        }
        else if(minute<10&&minuteEnd>=10){
            timetext.setText(hourOfDay + "시 " +0+ minute + "분 ~ " + hourOfDayEnd + "시 " + minuteEnd + "분");
        }
        else if(minute<10&&minuteEnd<10){
            timetext.setText(hourOfDay + "시 " +0+ minute + "분 ~ " + hourOfDayEnd + "시 " +0+ minuteEnd + "분");
        }
        calendar.set(AlarmYear, AlarmMonth, AlarmDay, AlarmHour, AlarmMinute, 0);
    }


    public void exit(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
    }

    public void add(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if(alarmflag!=0) {
            new AlarmHATT(add_schedule_dialog.this).Alarm();
        }
        finish();
    }

    public void test(View v){
        new AlarmHATT(add_schedule_dialog.this).Alarm();
    }


    public class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(add_schedule_dialog.this, BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

            calendar = tempcalendar;

            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }

    }



}
