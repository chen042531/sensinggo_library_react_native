//
//  NetworkState.swift
//  sensinggo_lib
//
//  Created by ycchen on 2020/11/28.
//

import Foundation
import Network
@available(iOS 12.0, *)
public class NetworkState {
    public  let monitor = NWPathMonitor()
    public init(){}
    public func status()->String{
        var state = "no connection"
        monitor.start(queue: DispatchQueue.global())
        monitor.pathUpdateHandler = { path in
            print( "wifi",path.usesInterfaceType(.wifi))
            print( "cellular",path.usesInterfaceType(.cellular))
            print("jsdj",path)
           if path.status == .satisfied {
              print("connected")
              state = "connected"
           } else {
              print("no connection")
            state = "no connection"
              
           }
           
        }
        return state
    }

    public func getNetworkType() -> String {
        var type:String = "other"
        monitor.start(queue: DispatchQueue.global())
        monitor.pathUpdateHandler = { path in
            print( "wifi")
            print( "cellular",path.usesInterfaceType(.cellular))
            print("jsdj",path)
           if path.usesInterfaceType(.wifi) {
              print("cnnected")
              type = "WIFI"
              
           } else {
              print("no connection")
              type = "CELLULAR"
           }

        }
        return type
    }

}
