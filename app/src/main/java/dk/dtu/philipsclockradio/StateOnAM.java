package dk.dtu.philipsclockradio;

import android.content.Context;
import android.widget.Toast;

public class StateOnAM extends StateAdapter {

    private ContextClockradio mContext;

    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        mContext = context;
        context.ui.turnOnLED(2);
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onClick_Power(context);
        context.setState(new StateOnFM(1));

    }

    @Override
    public void onExitState(ContextClockradio context) {
        super.onExitState(context);
        context.ui.turnOffLED(2);
    }
}
