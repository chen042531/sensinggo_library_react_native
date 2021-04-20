//
//  sensorInfo.swift
//  sensor
//
//  Created by ycchen on 2020/7/25.
//  Copyright © 2020 ycchen. All rights reserved.
//

import Foundation
import CoreMotion
import NMSSH
class t{
    var manager: CMMotionManager = CMMotionManager()
   
    init() {
    
    }
    
    public func getAccelerometerValues (values: ((Double, Double, Double) -> ())? ){
        print("fuckrrrrrrrrrr")
            var valX: Double!
            var valY: Double!
            var valZ: Double!
            
        if manager.isAccelerometerAvailable {
            manager.accelerometerUpdateInterval = 0.5
            manager.startAccelerometerUpdates(to: OperationQueue(), withHandler: {
                    (data, error) in
                    
                    
                    valX = data!.acceleration.x
                    valY = data!.acceleration.y
                    valZ = data!.acceleration.z
    //                print(valX)
                
                    if values != nil{
                        values!( valX,valY, valZ)
//                        sensorInfo.notify(X: valX, ev: self.eve)
                    }
                
//                    self.delegate?.retrieveAccelerometerValues(x: valX, y: valY, z: valZ)
                })
            } else {
                print("The Accelerometer is not available")
            }
//        self.eve("valX")
        }
}
