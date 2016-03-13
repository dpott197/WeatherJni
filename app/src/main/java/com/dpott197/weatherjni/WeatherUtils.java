package com.dpott197.weatherjni;

import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by darren on 3/13/16.
 */
public class WeatherUtils    {

    private static final String TAG = "WeatherUtils";

    public static Boolean sRooted;

    public static boolean isRooted() {
        if (sRooted == null) {
            sRooted = findBinary("su");
        }
        return sRooted;
    }

    public static boolean findBinary(String binaryName) {
        boolean found = false;

        String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (String where : places) {
            if (new File(where + binaryName).exists()) {
                found = true;
                break;
            }
        }

        return found;
    }


    public static void forcePermission() {
        if (!isRooted()) {
            Log.w(TAG, "Not running on rooted device, cannot remove system UI");
            return;
        }

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(
                    new String[]{
                            "su", "-c", "chmod 777 /dev/i2c-1"
                    }
            );
            process.waitFor();
        } catch (IOException e) {
            Log.e(TAG, "Failed to kill task bar (1).", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "Failed to kill task bar (2).", e);
        }
    }

}
