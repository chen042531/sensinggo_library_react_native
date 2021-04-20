#import <React/RCTBridgeModule.h>
#import "React/RCTEventEmitter.h"
@interface RCT_EXTERN_MODULE(Sensinggo, RCTEventEmitter)


RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(write_blank)
RCT_EXTERN_METHOD(send)
RCT_EXTERN_METHOD(write_:(NSString)info)
RCT_EXTERN_METHOD(getNetworkType:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getNetworkStatus:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getSSID:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getBattery:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getMNC:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getCarrierName:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getMobileCountryCode:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getNanoSecond:(RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(start)
RCT_EXTERN_METHOD(getAccelerometer)
RCT_EXTERN_METHOD(getMagnetometer)
RCT_EXTERN_METHOD(getAltitude)
RCT_EXTERN_METHOD(getGyroscope)
RCT_EXTERN_METHOD(stopAcc)
RCT_EXTERN_METHOD(stopMag)
RCT_EXTERN_METHOD(stopAlt)
RCT_EXTERN_METHOD(stopGyro)
RCT_EXTERN_METHOD(location)
RCT_EXTERN_METHOD(location_stop)


@end
