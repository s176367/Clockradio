package dk.dtu.philipsclockradio;

import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StateSleep extends StateAdapter {

    private ContextClockradio context;
    ArrayList<Integer> sleeplist = new ArrayList<Integer>();
    int i = 0;


    @Override
    public void onEnterState(ContextClockradio context) {
        super.onEnterState(context);
        context.ui.turnOffLED(1);
        context.ui.turnOffLED(2);
        context.ui.turnOffLED(3);
        context.ui.turnOffLED(4);
        context.ui.turnOffLED(5);
        sleeplist.add(0,120);
        sleeplist.add(1,90);
        sleeplist.add(2,60);
        sleeplist.add(3,30);
        sleeplist.add(4,15);
        context.ui.setDisplayText(String.valueOf(sleeplist.get(i)));

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

    @Override
    public void onClick_Sleep(final ContextClockradio context) {
        if(sleeplist.size()>0)

        {
            i++;
            if (i > sleeplist.size() - 1) {
                i = 0;

            }
            context.ui.setDisplayText(String.valueOf(sleeplist.get(i)));

        }


    }

    private class ActionEvent {
    }
}
