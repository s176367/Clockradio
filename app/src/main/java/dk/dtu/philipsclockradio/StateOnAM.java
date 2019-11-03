package dk.dtu.philipsclockradio;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class StateOnAM extends StateAdapter {

    private ContextClockradio mContext;
    private Context view;

    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        mContext = context;
        context.ui.turnOnLED(2);
        context.ui.displayToastAM(view);

    }
    

    @Override
    public void onClick_Power(ContextClockradio context) {
        super.onClick_Power(context);
        context.setState(new StateOnFM(1));

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
}
