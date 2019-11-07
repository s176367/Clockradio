package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class StateSleep extends StateAdapter {

    private ContextClockradio context;
    ArrayList<String> sleeplist = new ArrayList<String>();
    int i = 0;
    private static final long START_TIME_IN_MILLIS = 5000;

    private CountDownTimer mCountdowntimer;
    private boolean mTimerRunning;
    private long mtTimeLeftInMillis = START_TIME_IN_MILLIS;



    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        this.context = context;
        resetTimer();
        startTimer();
        context.ui.turnOffLED(1);
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(3);
        context.ui.turnOffLED(4);
        context.ui.turnOffLED(5);
        sleeplist.add(0,"120");
        sleeplist.add(1,"90");
        sleeplist.add(2,"60");
        sleeplist.add(3,"30");
        sleeplist.add(4,"15");
        sleeplist.add(5, "Off");
        context.ui.setDisplayText(String.valueOf(sleeplist.get(i)));

    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        if (i == 5){
            context.ui.turnOffLED(3);

        }else{
            context.ui.turnOnLED(3);
        }

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {
        super.onClick_Snooze(context);
        context.ui.turnOffLED(1);
        context.setState(new StateStandby(context.getTime()));

    }
    @Override
    public void onClick_Power(ContextClockradio context)  {
        super.onClick_Power(context);
        context.setState(new StateOnFM());
    }
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateOnAM());
    }
    private void startTimer(){
        mCountdowntimer = new CountDownTimer(mtTimeLeftInMillis, 500) {
            @Override
            public void onTick(long l) {
                mtTimeLeftInMillis = l;

            }

            @Override
            public void onFinish() {
            mTimerRunning = false;
            context.setState(new StateStandby(context.getTime()));

            }
        }.start();
        mTimerRunning = true;
    }

    private void resetTimer(){
        mtTimeLeftInMillis = START_TIME_IN_MILLIS;
    }

    @Override
    public void onClick_Sleep(final ContextClockradio context) {
        //Burde reset timer ved hvert on click. Men Crasher pga. nullpointer
        resetTimer();
        startTimer();
        if(sleeplist.size()>0) {
            i++;
            if (i > sleeplist.size() - 1) {
                i = 0;

            }
            context.ui.setDisplayText(String.valueOf(sleeplist.get(i)));

        }


    }
}
