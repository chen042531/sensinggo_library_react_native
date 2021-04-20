//
//  CellularInfo.swift
//  sensinggo_lib
//
//  Created by ycchen on 2020/11/28.
//

import Foundation
import CoreTelephony
import UIKit
public class CellularInfo{
 
    var networkInfo:CTTelephonyNetworkInfo
    var carrier:CTCarrier

    public init(){
        networkInfo = CTTelephonyNetworkInfo()
        carrier = networkInfo.subscriberCellularProvider!
    }

    public func getMNC() -> String {
        if let mnc = carrier.mobileNetworkCode {
            return mnc
        }
        return "nil"
    }
    public func getCarrierName() -> String {
        if let carrierName = carrier.carrierName {
            return carrierName
        }
        return "nil"
    }
    public func getMobileCountryCode() -> String {
        if let mcc = carrier.mobileCountryCode {
            return mcc
        }
        return "nil"
    }
//    func getServiceSubscriberCellularProviders() -> String {
//        if let mnc = networkInfo.serviceSubscriberCellularProviders {
//            return mnc
//        }
//        return "nil"
//    }
}
