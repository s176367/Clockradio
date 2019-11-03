package dk.dtu.philipsclockradio;

import android.content.Context;
import android.os.Handler;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class StateOn extends StateAdapter {
    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    //radio frequencies
    private String[] radiofz = {"0","1","2","3","4","5","6","7","8","9","10"};
    private State StateOn;


    StateOn(int radiofz){}

    

    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        mContext = context;
        context.updateDisplayTime();
        context.ui.setDisplayText(Arrays.toString(new String[]{radiofz[1]}));
        context.ui.turnOffLED(3);
        context.ui.turnOffLED(4);
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(5);
        context.ui.turnOnLED(1);

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {
        super.onClick_Snooze(context);
        context.ui.turnOffLED(1);
        context.setState(new StateStandby(context.getTime()));

    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        super.onClick_Hour(context);


        }

}
