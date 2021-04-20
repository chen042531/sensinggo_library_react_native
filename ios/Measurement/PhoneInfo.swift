//
//  PhoneInfo.swift
//  sensinggo_lib
//
//  Created by ycchen on 2020/11/28.
//

import Foundation
import UIKit
public class PhoneInfo{
    public init(){}
   
    public func receiveBatteryLevel()->Float {
        let device = UIDevice.current
        device.isBatteryMonitoringEnabled = true
        let batteryLevel = UIDevice.current.batteryLevel*100
    
        print("batteryLevelhaha", batteryLevel)
        return batteryLevel
      
    }
}
