package com.arora.ringtoneshuffler;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ahsanferoz on 18/06/15.
 */
public class RingtoneHelper {

//    fetches the list of all available ringtones
    public static List<Ringtone> fetchAvailableRingtones(Context context) {

        List<Ringtone> ringtones = new ArrayList<>();
        RingtoneManager mgr = new RingtoneManager(context);
        mgr.setType(RingtoneManager.TYPE_RINGTONE);

        int n = mgr.getCursor().getCount();
        for (int i = 0; i < n; i++) {
            ringtones.add(mgr.getRingtone(i));
        }

        return ringtones;
    }

//    this method is responsible for changing the ringtone of the device.
    public static void changeRingtone(Context context) {

        SharedPreferences preferences = context.getSharedPreferences("shuffler", Context.MODE_PRIVATE);
        if(!preferences.getBoolean("active", false))
            return;

        RingtoneManager mgr = new RingtoneManager(context);
        Random random = new Random(System.currentTimeMillis());

        int n = random.nextInt(mgr.getCursor().getCount());

        RingtoneManager.setActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_RINGTONE, mgr.getRingtoneUri(n));

    }
}
