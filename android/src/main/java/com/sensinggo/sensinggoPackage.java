package com.sensinggo;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class sensinggoPackage implements ReactPackage {
  @NotNull
  public List createNativeModules(@NotNull ReactApplicationContext reactContext) {
    Intrinsics.checkParameterIsNotNull(reactContext, "reactContext");
    List var10000 = Arrays.asList((NativeModule)(new sensinggoModule(reactContext)));
    Intrinsics.checkExpressionValueIsNotNull(var10000, "Arrays.asList<NativeModu…nggoModule(reactContext))");
    return var10000;
  }

  @NotNull
  public List createViewManagers(@NotNull ReactApplicationContext reactContext) {
    Intrinsics.checkParameterIsNotNull(reactContext, "reactContext");
    return CollectionsKt.emptyList();
  }
}
