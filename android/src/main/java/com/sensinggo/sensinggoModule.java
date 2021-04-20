package com.sensinggo;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.sensinggo.sensing.Data.DataListener;
import com.sensinggo.sensing.FileMaker.writeFile;
import com.sensinggo.sensing.FileSender.sendData;
import com.sensinggo.sensing.Measurement.BatteryState;
import com.sensinggo.sensing.Measurement.CellularInfo;
import com.sensinggo.sensing.Measurement.LocationInfo;
import com.sensinggo.sensing.Measurement.NetworkState;
import com.sensinggo.sensing.Measurement.PhoneInfo;
import com.sensinggo.sensing.Measurement.PhoneState;
import com.sensinggo.sensing.Measurement.ScreenState;
import com.sensinggo.sensing.Measurement.SensorInfo;
import com.sensinggo.sensing.Measurement.WiFiInfo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class sensinggoModule extends ReactContextBaseJavaModule {
  private CellularInfo cellularInfo;
  private LocationInfo gps;
  private NetworkState networkState;
  private PhoneInfo phoneInfo;
  private BatteryState batteryState;
  private ScreenState screenState;
  private PhoneState phoneState;
  private SensorInfo sensorInfo;
  private WiFiInfo wifiInfo;

  private  ReactApplicationContext reactContext;

  @NotNull
  public String getName() {
    return "Sensinggo";
  }


  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @ReactMethod
  public final void WiFiInfo(@NotNull Promise promise) {
    wifiInfo = new WiFiInfo(reactContext);
    wifiInfo.getWiFiInfo();
//    Log.i("dggggghaha",WiFiInfo.servingBSSID);
    WritableMap params = Arguments.createMap();
    params.putString("servingBSSID", String.valueOf(WiFiInfo.servingBSSID));
    params.putString("servingChan", String.valueOf(WiFiInfo.servingChan));
    params.putString("servingFreq", String.valueOf(WiFiInfo.servingFreq));
    params.putString("servingIP", String.valueOf(WiFiInfo.servingIP));
    params.putString("servingLevel", String.valueOf(WiFiInfo.servingLevel));
    params.putString("servingMAC", String.valueOf(WiFiInfo.servingMAC));
    params.putString("servingSSID", String.valueOf(WiFiInfo.servingSSID));
    params.putString("servingSpeed", String.valueOf(WiFiInfo.servingSpeed));

    promise.resolve(params);

  }

  @ReactMethod
  public final void networkState(@NotNull Promise promise) {

    networkState = new NetworkState(reactContext);

//    Log.i("dggggghaha",networkState.getNetworkType());

    WritableMap params = Arguments.createMap();
    params.putString("NetworkType", String.valueOf(networkState.getNetworkType()));
    params.putString("isNetworkAvailable", String.valueOf(networkState.isNetworkAvailable()));
    promise.resolve(params);

  }
  @ReactMethod
  public final void phoneInfo(@NotNull Promise promise) throws IOException {

    WritableMap params = Arguments.createMap();
    params.putString("CPU", String.valueOf(PhoneInfo.cpuInfo()));
    promise.resolve(params);

  }

  @ReactMethod
  public final void multiply(int a, int b, @NotNull Promise promise) {
    Intrinsics.checkParameterIsNotNull(promise, "promise");
    promise.resolve(a * b + 300);

  }

  @ReactMethod
  public final void write_(String fileName, String s) {
    writeFile write_file = null;
    Log.i("writefile test_time_gg ", String.valueOf(s));
    try {
      write_file = new writeFile(reactContext);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      write_file.write_testTime(fileName, s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @ReactMethod
  public final void send_() {
    sendData s = new sendData(reactContext);
    s.setUserID("time_time.csv");
    s.execute();
  }
  @ReactMethod
  public final void location() {
    gps = new LocationInfo(reactContext);
    gps.startNetwork_location();
  }

  @ReactMethod
  public final void location_stop() {
    gps.stopNetwork_location();
  }

  @ReactMethod
  public final void locationGPS() {
    gps = new LocationInfo(reactContext);
    gps.startGPS_location();
  }
  @ReactMethod
  public final void locationGPS_stop() {
    gps.stopGPS_location();
  }
  @ReactMethod
  public final void phoneState() {
    phoneState = new PhoneState(reactContext);
    phoneState.start();
  }
  @ReactMethod
  public final void phoneState_stop() {
    phoneState.stop();
  }
  @ReactMethod
  public final void batteryState() {
    batteryState = new BatteryState(reactContext);
    batteryState.start();
  }
  @ReactMethod
  public final void batteryState_stop(){
    batteryState.stop();
  }
  @ReactMethod
  public final void screenState() {
    screenState = new ScreenState(reactContext);
    screenState.start();
  }
  @ReactMethod
  public final void screenState_stop(){
    screenState.stop();
  }

  @ReactMethod
  public final void cellularInfo() {
    cellularInfo = new CellularInfo(reactContext);
    cellularInfo.start();
  }
  @ReactMethod
  public final void cellularInfo_stop() {
    cellularInfo.stop();
  }
  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @ReactMethod
  public final void acc() {
    sensorInfo =  new SensorInfo(reactContext);
    sensorInfo.startService_acc();
  }
  @ReactMethod
  public final void mag() {
    sensorInfo =  new SensorInfo(reactContext);
    sensorInfo.startService_MAG();
  }
  @ReactMethod
  public final void light() {
    sensorInfo =  new SensorInfo(reactContext);
    sensorInfo.startService_LIGHT();
  }
  @ReactMethod
  public final void pressure() {
    sensorInfo =  new SensorInfo(reactContext);
    sensorInfo.startService_PRESSURE();
  }
  @ReactMethod
  public final void proximity() {
    sensorInfo =  new SensorInfo(reactContext);
    sensorInfo.startService_Proximity();
  }
  @ReactMethod
  public final void stop_sensorInfo() {
    sensorInfo.stop();

  }
  public sensinggoModule(@NotNull ReactApplicationContext reactContext) {
    super(reactContext);
    Intrinsics.checkParameterIsNotNull(reactContext, "reactContext");

    this.reactContext = reactContext;

  }
  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }
}
