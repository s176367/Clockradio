package dk.dtu.philipsclockradio;

public class StatePresetAM extends StateAdapter {

    StateOnAM stateOnAM = new StateOnAM();
    int i = 0;
    int presetAMi = 0;



    @Override
    public void onEnterState(ContextClockradio context) {
        stateOnAM.list.add(0,10);
        stateOnAM.list.add(1,20);
        stateOnAM.list.add(2,30);
        stateOnAM.list.add(3, 40);
        stateOnAM.list.add(4,50);
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(String.valueOf(stateOnAM.list.get(i)));
        context.ui.turnOnLED(1);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        if (context.presetFm.length<3){
            context.presetFm[presetAMi] = stateOnAM.list.get(i);
            presetAMi++;
        }
        context.setState(new StateOnAM() );

    }
    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        i++;
        if(i>stateOnAM.list.size()-1){
            i=0;
            context.ui.turnOffLED(1);
            context.ui.turnOffLED(2);
            context.ui.turnOffLED(3);
            context.ui.turnOffLED(4);
            context.ui.turnOffLED(5);
        }
        context.ui.setDisplayText(String.valueOf(stateOnAM.list.get(i)));
        context.ui.turnOnLED(i+1);
        context.ui.turnOffLED(i);


    }

}
