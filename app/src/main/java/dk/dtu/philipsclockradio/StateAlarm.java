package dk.dtu.philipsclockradio;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.Calendar;
import java.util.Date;

public class StateAlarm extends StateAdapter {
    private ContextClockradio context;
    Date mTimeAlarm;
    int i=0;
    String time;
    Date alarmTime;
    String display;


    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        this.context = context;
        mTimeAlarm = context.getTime();
        context.updateDisplayTime();
        context.ui.turnOnTextBlink();
        Calendar timeAlarm = Calendar.getInstance();
        timeAlarm.set(2019,01,01,12,01 );
        alarmTime = timeAlarm.getTime();
        time = alarmTime.toString().substring(11,16);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        super.onClick_Hour(context);
        this.context = context;
        alarmTime.setTime(alarmTime.getTime() + 3600000);
        updateDisplay(alarmTime);
        time = alarmTime.toString().substring(11,16);
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        this.context = context;
        alarmTime.setTime(alarmTime.getTime() + 60000);
        updateDisplay(alarmTime);
        time = alarmTime.toString().substring(11,16);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        this.context = context;
        context.ui.turnOffTextBlink();
    }
    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onClick_Power(context);
        context.setState(new StateOnFM());
    }
    @Override
    public void onClick_AL1(final ContextClockradio context) {
        super.onLongClick_AL1(context);
        context.ui.turnOnLED(2);
        context.ui.turnOffTextBlink();
        context.updateDisplayTime();
        class Asynctask1 extends AsyncTask{


            @Override
            protected Object doInBackground(Object[] objects) {
                boolean run = true;
                while (run) {

                    String currentTime = context.getTime().toString().substring(11, 16);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (time.equals(currentTime)) {
                        run = false;
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                context.setState(new StateAlarmOn());
            }

        }
        new Asynctask1().execute();
    }
    public void updateDisplay(Date date){
        display = alarmTime.toString().substring(11,16);
        context.ui.setDisplayText(display);
    }

    @Override
    public void onClick_AL2(final ContextClockradio context) {
        context.ui.turnOnLED(2);

        class Asynctask1 extends AsyncTask{

            @Override
            protected Object doInBackground(Object[] objects) {
                boolean run = true;
                while (run) {

                    String currentTime = context.getTime().toString().substring(11, 16);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (time.equals(currentTime)) {
                        run = false;
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                context.setState(new StateAlarmOn());
            }

        }
        new Asynctask1().execute();
    }
    }

