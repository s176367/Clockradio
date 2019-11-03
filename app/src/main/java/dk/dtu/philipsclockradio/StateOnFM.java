package dk.dtu.philipsclockradio;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class StateOnFM extends StateAdapter {
    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    //radio frequencies
    public int[] radiofz = {1,2,3,4,5,6,7,8,9,10};
    private State StateOn;


    StateOnFM(int radiofz){}



    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        mContext = context;
        context.updateDisplayTime();
        context.ui.setDisplayText(Arrays.toString(radiofz));
        context.ui.turnOnLED(1);

    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        context.ui.turnOffLED(1);
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

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateOnAM());
    }
}
