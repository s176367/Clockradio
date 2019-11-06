package dk.dtu.philipsclockradio;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.google.common.primitives.Ints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class StateOnFM extends StateAdapter {
    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    Context view;
    int i;



    //radio frequencies
    ArrayList<Integer> list = new ArrayList<>();


    private State StateOn;



    StateOnFM(int radiofz) {
    }


    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        mContext = context;
        list.add(0,10);
        list.add(1,20);
        list.add(2,30);
        list.add(3, 40);
        list.add(4,50);
        context.updateDisplayTime();
        context.ui.setDisplayText(String.valueOf(list.get(i)));
        context.ui.turnOnLED(1);






        context.ui.displayToastFM(view);

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
    public void onClick_Sleep(ContextClockradio context) {
        super.onClick_Sleep(context);
        context.setState(new StateSleep());
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateOnAM());
    }

    //TODO: Make this method save the current radio station for a preset.

    @Override
    public void onClick_Min(ContextClockradio context) {
            i++;
            if(i>list.size()-1){
                i=0;
            }
            context.ui.setDisplayText(String.valueOf(list.get(i)));
        System.out.println(list.get(i));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
            i--;
            if (i<0){
                i = list.size()-1;
            }
            context.ui.setDisplayText(String.valueOf(list.get(i)));

        }

    }

