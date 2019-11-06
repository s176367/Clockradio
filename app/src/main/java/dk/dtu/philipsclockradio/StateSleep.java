package dk.dtu.philipsclockradio;

public class StateSleep extends StateAdapter {

    private ContextClockradio context;

    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        context.ui.turnOffLED(1);
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(3);
        context.ui.turnOffLED(4);
        context.ui.turnOffLED(5);
        context.ui.setDisplayText(" ");
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
}
