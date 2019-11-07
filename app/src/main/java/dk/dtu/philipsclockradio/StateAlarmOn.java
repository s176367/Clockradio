package dk.dtu.philipsclockradio;

import android.os.AsyncTask;

public class StateAlarmOn extends StateAdapter {
private ContextClockradio context;

    @Override
    public void onEnterState(ContextClockradio context) {
        this.context = context;
        context.ui.setDisplayText("ALARM");
        context.ui.turnOnTextBlink();
        context.ui.turnOnLED(2);

    }

    @Override
    public void onClick_Snooze(final ContextClockradio context) {
        context.ui.turnOffTextBlink();
        context.ui.turnOnLED(4);
        context.setState(new StateStandby(context.getTime()));

        class Asynctask2 extends AsyncTask{
            @Override
            protected Object doInBackground(Object[] objects) {
                //540000  9 minutter
                try {
                    Thread.sleep(540000);
                    context.setState(new StateAlarmOn());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }new Asynctask2().execute();
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
        context.ui.turnOffTextBlink();
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(4);

    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
        context.ui.turnOffTextBlink();
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(4);
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateOnFM());
        context.ui.turnOffTextBlink();
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(4);
    }
}
