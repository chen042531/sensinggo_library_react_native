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

public class ScreenState extends BroadcastReceiver {


  public static String screen_state = "on";

  public Context mContext;

  public ScreenState(Context mContext) {
    this.mContext = mContext;
  }
  public void start() {
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
    intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
    mContext.registerReceiver(this, intentFilter);
  }
  public void stop() {
    mContext.unregisterReceiver(this);
  }
  @Override
  public void onReceive(Context context, Intent intent) {


    String action = intent.getAction();
    if (Intent.ACTION_SCREEN_ON.equals(action)) {
      screen_state = "on";
    }
    else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
      screen_state = "off";
    }
    WritableMap ScreenStateMap = Arguments.createMap();
    ScreenStateMap.putString("screen_state",String.valueOf(screen_state));

    sendEvent((ReactContext) mContext, "screen_state", ScreenStateMap);
  }
  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }
}
