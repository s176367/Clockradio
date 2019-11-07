package dk.dtu.philipsclockradio;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Date;

public class StateOnFM extends StateAdapter {
    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    Context view;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int i;


    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    //radio frequencies
    ArrayList<Integer> list = new ArrayList<>();


    private State StateOn;



    StateOnFM() {
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
        context.ui.setDisplayText("FM-"+String.valueOf(list.get(i)));
        context.ui.displayToastFM(view);

    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        context.ui.turnOffLED(1);
    }

    @Override
    public void onLongClick_Snooze(ContextClockradio context) {
        super.onLongClick_Snooze(context);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        super.onClick_Sleep(context);
        context.setState(new StateSleep());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateOnAM());
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
            i++;
            if(i>list.size()-1){
                i=0;
            }
            context.ui.setDisplayText("FM-"+String.valueOf(list.get(i)));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
            i--;
            if (i<0){
                i = list.size()-1;
            }
            context.ui.setDisplayText("FM-"+String.valueOf(list.get(i)));

        }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StatePresetFM());

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        if (context.presetFm.size()>0) {
            i++;
            if (i > context.presetFm.size()-1) {
                i = 0;

            }
                context.ui.setDisplayText("CH-" + context.presetFm.get(i));


        }else{
            context.ui.noPresets(view);

        }

    }
}

