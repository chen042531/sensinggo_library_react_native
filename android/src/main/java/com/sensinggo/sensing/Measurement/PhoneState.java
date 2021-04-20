package com.sensinggo.sensing.Measurement;


import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class PhoneState extends PhoneStateListener {
    private Context mContext;
    public static String phoneState = "IDLE"; //IDLE, OFFHOOK, RINGING
    public static String callState = "IDLE"; //Callout, ""Callin"", RINGING
    public static String callID = "null";
    public static String startCallTime, endCallTime;

    public static int callNum = 0, callExcessNum = 0;
    public static long callStartAt = 0, callHoldingTime = 0, excessLife = 0;
    public static double avgCallHoldTime = 0, avgExcessLife = 0;
    public static double ttlCallHoldTime = 0, ttlExcessLife = 0;
    public static boolean FirstCallCell = false;

    private TelephonyManager teleManger;
    public PhoneState(Context context) {
      initBfRun();
      this.mContext = context;
      teleManger = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }
    public void start() {
      teleManger.listen(this, LISTEN_CALL_STATE);
    }
    public void stop() {
      teleManger.listen(this, LISTEN_NONE);
    }
    @Override
    public void onCallStateChanged(final int state, String incomingNumber) {
      super.onCallStateChanged(state, incomingNumber);
      switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
          // OFFHOOK: At least one call exists that is dialing, active, or on hold, and no calls are ringing or waiting.
          if (phoneState.equals("OFFHOOK")) {
            callHoldingTime = System.currentTimeMillis() - callStartAt;
            ttlCallHoldTime = ttlCallHoldTime + callHoldingTime;
          }
          phoneState = "IDLE";
          phoneState = "IDLE";
          callState = "IDLE";
          FirstCallCell = false;
          break;

        case TelephonyManager.CALL_STATE_OFFHOOK:
          if (phoneState.equals("IDLE")) {
            callState = "Callout";
            Log.i("phoneState","Callout");
          }
          if (phoneState.equals("RINGING")) {
            callState = "Callin";
            Log.i("phoneState","Callin");
          }
          callNum = callNum + 1;
          callStartAt = System.currentTimeMillis();
          FirstCallCell = true;
          phoneState = "OFFHOOK";
          break;

        case TelephonyManager.CALL_STATE_RINGING:
          phoneState = "RINGING";
          callState = "RINGING";
          break;
      }
      WritableMap PhoneStateMap = Arguments.createMap();
      PhoneStateMap.putString("phoneState", String.valueOf(PhoneState.phoneState));
      PhoneStateMap.putString("callState", String.valueOf(PhoneState.callState));
      PhoneStateMap.putString("callID", String.valueOf(PhoneState.callID));
      PhoneStateMap.putString("startCallTime", String.valueOf(PhoneState.startCallTime));
      PhoneStateMap.putString("endCallTime", String.valueOf(PhoneState.endCallTime));
      PhoneStateMap.putString("callNum", String.valueOf(PhoneState.callNum));
      PhoneStateMap.putString("callExcessNum", String.valueOf(PhoneState.callExcessNum));
      PhoneStateMap.putString("callStartAt", String.valueOf(PhoneState.callStartAt));
      PhoneStateMap.putString("callHoldingTime", String.valueOf(PhoneState.callHoldingTime));
      PhoneStateMap.putString("avgCallHoldTime", String.valueOf(PhoneState.avgCallHoldTime));
      PhoneStateMap.putString("avgExcessLife", String.valueOf(PhoneState.avgExcessLife));
      PhoneStateMap.putString("ttlExcessLife", String.valueOf(PhoneState.ttlExcessLife));
      PhoneStateMap.putString("ttlCallHoldTime", String.valueOf(PhoneState.ttlCallHoldTime));
      PhoneStateMap.putString("FirstCallCell", String.valueOf(PhoneState.FirstCallCell));
      sendEvent((ReactContext) mContext, "phoneState", PhoneStateMap);

    }

    public static void calculateAvg() {
        if (callNum != 0) {
            avgCallHoldTime = ttlCallHoldTime / callNum;
        }
        if (callExcessNum != 0) {
            avgExcessLife = ttlExcessLife / callExcessNum;
        }
    }

    private void initBfRun() {
        avgCallHoldTime = 0;
        avgExcessLife = 0;
        callNum = 0;
        callExcessNum = 0;
        callStartAt = 0;
        callHoldingTime = 0;
        excessLife = 0;
        ttlCallHoldTime = 0;
        ttlExcessLife = 0;
        FirstCallCell = false;
    }


//
//    public void startService(DataListenerInterface dataListener) {
//        initBfRun();
//
//        teleManger.listen(this, LISTEN_CALL_STATE); // idle, ringing, offhook
//        dataListenerInterface = dataListener;
//    }
//    public void stopService() {
//
//        teleManger.listen(this,LISTEN_NONE);
//    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           WritableMap params) {
      reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, params);
    }

}
