package com.kituri.tools;

import android.content.Context;
import android.content.Intent;

import com.kituri.wowrich.MIDlet;

/**
 * Created by kirijin on 2016/6/8.
 */
public class UIUtils {

    static public void sendUIButtonChange(Context context, String action, Boolean isShow){
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(MIDlet.BTN_STATUS, isShow);
        context.sendBroadcast(intent);
    }

}
