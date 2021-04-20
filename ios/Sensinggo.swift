@objc(Sensinggo)
class Sensinggo: RCTEventEmitter {
 
    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b+200)
    }
    var sen = SensorInfo()
    
    var phinfo = PhoneInfo()
    
    var loc = Location()
    var wifi = WiFiInfo()
    var cell = CellularInfo()
    var file_maker = fileMaker()
    var send_Data = sendData()
    @objc
    func location() {
        loc.getLocation(action: { (a, b) in
                    self.sendEvent(withName: "loc", body: ["lon":a,"lat":b])

                    })
    }
    @objc
    func location_stop() {
        loc.stop_loc()
    }
    @objc(write_:)
    func write_(info:NSString) {
        file_maker.makeFile(val1: info as String)
    }
    @objc
    func write_blank() {
        file_maker.makeFile_blank(val1: "")
    }
    @objc
    func send() {
        send_Data.send()
    }
    @objc
    func getAccelerometer() {
        sen.getAccelerometerValues() { (X, Y, Z) -> () in
                    self.sendEvent(withName: "acc", body: ["X":X,"y":Y,"z":Z])
                    }
    }
    @objc
    func getMagnetometer() {
        sen.getMagnetometerValues() { (X, Y, Z) -> () in
                    self.sendEvent(withName: "mag", body: ["X":X,"y":Y,"z":Z])

                    }
    }
    @objc
    func getAltitude() {
        sen.getAltitudeValues() { (alt, press) -> () in
                    self.sendEvent(withName: "alt", body: ["alt":alt,"press":press])
        
                    }
    }
    @objc
    func getGyroscope() {
        sen.getGyroscopeValues() { (X, Y, Z) -> () in
                    self.sendEvent(withName: "gyro", body: ["X":X,"y":Y,"z":Z])
                    }
    }
    @objc
    func stopAcc(){
        sen.stopAcc()
    }
    @objc
    func stopMag(){
        sen.stopMag()
    }
    @objc
    func stopAlt(){
        sen.stopAlt()
    }
    @objc
    func stopGyro(){
        sen.stopGyro()
    }
    @objc
    func getNetworkType(_ resolve: RCTPromiseResolveBlock,
                        rejecter reject: RCTPromiseRejectBlock) -> Void {
        if #available(iOS 12.0, *) {
            let netstate = NetworkState()
            resolve(netstate.getNetworkType())
        } else {
            // Fallback on earlier versions
        }

    }
   
    @objc
    func getNetworkStatus(_ resolve: RCTPromiseResolveBlock,
                          rejecter reject: RCTPromiseRejectBlock) -> Void {
        if #available(iOS 12.0, *) {
            let netstate = NetworkState()
            resolve(netstate.status())
        } else {
            // Fallback on earlier versions
        }
    }
    @objc
    func getSSID(_ resolve: RCTPromiseResolveBlock,
                 rejecter reject: RCTPromiseRejectBlock) -> Void {
        resolve(wifi.getWiFiSsid())
    }
    @objc
    func getBattery(_ resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock
                    ) -> Void {
        resolve(String(phinfo.receiveBatteryLevel()))
    }
//
//    @objc
//    func getBattery() -> Float {
//        print("batt batt",phinfo.receiveBatteryLevel())
//        return phinfo.receiveBatteryLevel()
//    }
    
    @objc
    func getMNC(_ resolve: RCTPromiseResolveBlock,
                rejecter reject: RCTPromiseRejectBlock) -> Void {
//        file_maker.makeFile()
//
//        send_Data.send()
        resolve(cell.getMNC())
    }
    @objc
    func getCarrierName(_ resolve: RCTPromiseResolveBlock,
                        rejecter reject: RCTPromiseRejectBlock) -> Void {
        resolve(cell.getCarrierName())
    }
    @objc
    func getMobileCountryCode(_ resolve: RCTPromiseResolveBlock,
                              rejecter reject: RCTPromiseRejectBlock) -> Void {
        resolve(cell.getMobileCountryCode())
    }
    @objc
    func getNanoSecond(_ resolve: RCTPromiseResolveBlock,
                              rejecter reject: RCTPromiseRejectBlock) -> Void {
        resolve("20")
    }
    // we need to override this method and
      // return an array of event names that we can listen to
  override func supportedEvents() -> [String]! {
    return ["sensor","acc","mag","alt","gyro","loc"]
  }
}
