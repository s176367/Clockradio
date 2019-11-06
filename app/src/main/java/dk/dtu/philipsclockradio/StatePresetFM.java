package dk.dtu.philipsclockradio;

public class StatePresetFM extends StateAdapter{

    ContextClockradio context;
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
        context.ui.turnOnLED(1);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        if (context.presetFm.length<3){
            context.presetFm[preseti] = statefm.list.get(i);
            preseti++;
        }
        //context.setState(new StateOnFM());

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        i++;
        if(i>statefm.list.size()-1){
            i=0;
            context.ui.turnOffLED(1);
            context.ui.turnOffLED(2);
            context.ui.turnOffLED(3);
            context.ui.turnOffLED(4);
            context.ui.turnOffLED(5);
        }
        context.ui.setDisplayText(String.valueOf(statefm.list.get(i)));
        context.ui.turnOnLED(i+1);
        context.ui.turnOffLED(i);


    }

}
