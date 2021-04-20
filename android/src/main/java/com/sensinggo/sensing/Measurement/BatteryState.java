package com.sensinggo.sensing.Measurement;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;

public class BatteryState extends BroadcastReceiver {
  public static double electricity;
  public static int health = 0;
  public static int icon_small = 0;
  public static int level = 0;
  public static int lastLevel = -1;
  public static double lastElect = -1;
  public static int plugged = 0;
  public static boolean present = false;
  public static int scale = 0;
  public static int status = 0;
  public static String technology = "0";
  public static String tmp;
  public static double temperature = 0.0;
  public static int voltage = 0;



  public Context mContext;

  public BatteryState(Context mContext) {
    this.mContext = mContext;
  }
  public void start() {
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    mContext.registerReceiver(this, intentFilter);
  }
  public void stop() {
    mContext.unregisterReceiver(this);
  }
  @Override
  public void onReceive(Context context, Intent intent) {

    health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
    icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
    level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
    plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
//        present = Objects.requireNonNull(intent.getExtras()).getBoolean(BatteryManager.EXTRA_PRESENT);
    scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
    status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
    technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
    temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) * 0.1;
    tmp = String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) * 0.1);
    electricity = level / (double)scale * 100;
    voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 10);
    String action = intent.getAction();
    WritableMap PhoneInfoMap = Arguments.createMap();
    PhoneInfoMap.putString("health",String.valueOf(health));
    PhoneInfoMap.putString("level",String.valueOf(level));
    PhoneInfoMap.putString("plugged",String.valueOf(plugged));
    PhoneInfoMap.putString("status",String.valueOf(status));
    PhoneInfoMap.putString("technology",String.valueOf(technology));
    PhoneInfoMap.putString("temperature",String.valueOf(temperature));
    PhoneInfoMap.putString("electricity",String.valueOf(electricity));
    PhoneInfoMap.putString("voltage",String.valueOf(voltage));
    sendEvent((ReactContext) mContext, "acc", PhoneInfoMap);
  }
  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }
}
