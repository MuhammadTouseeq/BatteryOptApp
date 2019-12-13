package com.softdev.batteryoptapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

if(isTimeAutomatic(this))
{
    Toast.makeText(this, "Automatic Time Enable", Toast.LENGTH_SHORT).show();
}
else {
    Toast.makeText(this, "Automatic Time Disable", Toast.LENGTH_SHORT).show();
            Intent battSaverIntent = new Intent();
        battSaverIntent.setAction(Settings.ACTION_DATE_SETTINGS);
        startActivityForResult(battSaverIntent, 0);
}

//        try {
//            startActivity(new Intent().setClassName("com.transsion.phonemaster",
//                    "com.transsion.phonemaster.SettingProtectedApps"));
//
//        } catch (Exception e) {
//            Log.d("tag", "e " + e.toString());
//        }

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Intent intent = new Intent();
//            String packageName = getPackageName();
//            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
//            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
//                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                intent.setData(Uri.parse("package:" + packageName));
//                startActivity(intent);
//            }
//        }

//
//        Intent battSaverIntent = new Intent();
//        battSaverIntent.setAction(Settings.ACTION_DATE_SETTINGS);
//        startActivityForResult(battSaverIntent, 0);


//        new AlertDialog.Builder(this)
//                .setTitle(Build.MANUFACTURER + " Protected Apps")
//                .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", getString(R.string.app_name)))
////                .setView(dontShowAgain)
//                .setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                        MainActivity.this.startActivityForResult(new Intent().setComponent(new ComponentName("com.transsion.phonemaster",
////                                "com.transsion.phonemaster.SettingProtectedApps")),1);
//
//                        ComponentName componetName = new ComponentName(
//                                "com.android.settings",
//                                "com.android.settings.applications.InstalledAppDetails");
//                        Intent intent= new Intent();
//                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                        intent.setData(Uri.parse("package:com.softdev.batteryoptapp"));
//                        intent.setComponent(componetName);
//                        startActivityForResult(intent,1);
//
//
////                        launchNewActivity(getApplicationContext(),"com.transsion.phonemaster");
//
////                        Intent inent = new Intent("com.transsion.phonemaster.SettingProtectedApps");
//
////                        startActivity(inent);
//
//                    }
//                })
//                .setNegativeButton(android.R.string.cancel, null)
//                .show();
        Utils.startPowerSaverIntent(this);


//        MainSettingGpActivity


    }

    public static boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void launchNewActivity(Context context, String packageName) {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        }
        if (intent == null) {
            try {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                context.startActivity(intent);
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

        }
    }


}
