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
        timeAlarm.set(2019,01,01,12,00 );
        alarmTime = timeAlarm.getTime();
        time = alarmTime.toString().substring(11,16);
        System.out.println(time);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        super.onClick_Hour(context);
        this.context = context;
        alarmTime.setTime(alarmTime.getTime() + 3600000);
        updateDisplay(alarmTime);
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        super.onClick_Min(context);
        this.context = context;
        alarmTime.setTime(alarmTime.getTime() + 60000);
        updateDisplay(alarmTime);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        this.context = context;
        context.ui.turnOnLED(3);
        context.ui.turnOffTextBlink();
    }
    public void setAlarm() {
        i++;
        context.alarmlist.add(i-1, alarmTime.getTime());

    }
    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onClick_Power(context);
        context.setState(new StateOnFM());
    }
    @Override
    public void onClick_AL1(final ContextClockradio context) {
        super.onLongClick_AL1(context);
        class Asynctask1 extends AsyncTask{

            @Override
            protected Object doInBackground(Object[] objects) {
                String currentTime = context.getTime().toString().substring(11, 16);
                while (getStatus()== Status.RUNNING) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (time.equals(currentTime)) {
                    
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                context.ui.setDisplayText("ALARM");

            }

        }
        new Asynctask1().execute();
        context.setState(new StateStandby(context.getTime()));
    }
    public void updateDisplay(Date date){
        display = alarmTime.toString().substring(11,16);
        context.ui.setDisplayText(display);
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        super.onClick_AL2(context);
        context.setState(new StateStandby(context.getTime()));
    }
}

