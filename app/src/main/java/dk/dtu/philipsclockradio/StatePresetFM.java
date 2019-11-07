package dk.dtu.philipsclockradio;

import android.content.Context;

public class StatePresetFM extends StateAdapter{

    ContextClockradio context;
    Context view;
    StateOnFM statefm = new StateOnFM();
    int i =0;
    int preseti = 0;



    @Override
    public void onEnterState(ContextClockradio context) {
        statefm.list.add(0,10);
        statefm.list.add(1,20);
        statefm.list.add(2,30);
        statefm.list.add(3, 40);
        statefm.list.add(4,50);
        context.ui.setDisplayText(String.valueOf(statefm.list.get(i)));
        context.ui.turnOnTextBlink();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        context.ui.turnOffLED(i+1);

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        if (context.presetFm.size()<4){
            context.presetFm.add(statefm.list.get(i));
            preseti++;
            context.setState(new StateOnFM());
        } else{
            context.ui.displayToastFilledFM(view);
            context.setState(new StateOnFM());
        }
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        context.presetFm.clear();
        context.ui.fmToastClear(view);
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        i++;
        if(i>statefm.list.size()-1){
            i=0;

        }
        context.ui.setDisplayText(String.valueOf(statefm.list.get(i)));


    }

}
