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
        context.ui.setDisplayText(String.valueOf(list.get(i)));
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
    public void onClick_Min(ContextClockradio context) {
        i++;
        if(i>list.size()-1){
            i=0;
        }
        context.ui.setDisplayText(String.valueOf(list.get(i)));
        System.out.println(list.get(i));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        i--;
        if (i<0){
            i = list.size()-1;
        }
        context.ui.setDisplayText(String.valueOf(list.get(i)));

    }

}
