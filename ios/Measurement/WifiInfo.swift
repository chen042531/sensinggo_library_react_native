//
//  WiFiInfo.swift
//  sensinggo_lib
//
//  Created by ycchen on 2020/11/28.
//

import Foundation
import UIKit
import SystemConfiguration.CaptiveNetwork

public class WiFiInfo: NSObject {
    public func getWiFiSsid() -> String? {
        var ssid: String?
        if let interfaces = CNCopySupportedInterfaces() as NSArray? {
            for interface in interfaces {
                if let interfaceInfo = CNCopyCurrentNetworkInfo(interface as! CFString) as NSDictionary? {
                    ssid = interfaceInfo[kCNNetworkInfoKeySSID as String] as? String
                    break
                }
            }
        }
        return ssid
    }
}
