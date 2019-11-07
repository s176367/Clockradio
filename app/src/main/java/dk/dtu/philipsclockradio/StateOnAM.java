package dk.dtu.philipsclockradio;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class StateOnAM extends StateAdapter {

    private ContextClockradio mContext;
    private Context view;
    ArrayList<Integer> list = new ArrayList<>();
    int i;

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    StateOnAM(){

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
        context.ui.setDisplayText("AM-"+String.valueOf(list.get(i)));
        context.ui.displayToastAM(view);

    }
    

    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onClick_Power(context);
        context.setState(new StateOnFM());

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        super.onLongClick_Power(context);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        super.onClick_Sleep(context);
        context.setState(new StateSleep());
    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        context.ui.turnOffLED(2);
    }
    public void onClick_Min(ContextClockradio context) {
        i++;
        if(i>list.size()-1){
            i=0;
        }
        context.ui.setDisplayText("AM-"+String.valueOf(list.get(i)));
        System.out.println(list.get(i));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        i--;
        if (i<0){
            i = list.size()-1;
        }
        context.ui.setDisplayText("AM-"+String.valueOf(list.get(i)));

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StatePresetAM());
    }
    public void onClick_Preset(ContextClockradio context) {
        if (context.presetAM.size()>0) {
            i++;
            if (i > context.presetAM.size()-1) {
                i = 0;

            }
                 context.ui.setDisplayText("CH-" + context.presetAM.get(i));


        }else{
            context.ui.noPresets(view);

        }

    }

    @Override
    public void onLongClick_Snooze(ContextClockradio context) {
        super.onLongClick_Snooze(context);
        context.setState(new StateStandby(context.getTime()));
    }
}
