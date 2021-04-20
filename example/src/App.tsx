import * as React from 'react';
import { NativeEventEmitter, StyleSheet, View, Text, TouchableOpacity, PermissionsAndroid } from 'react-native';
import Sensinggo from 'sensinggo';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  
  const requestCameraPermission = async () => {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        {
          title: "Cool Photo App Camera Permission",
          message:
          "Cool Photo App needs access to your camera " +
          "so you can take awesome pictures.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK"
        }
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log("You can use the camera");
      } else {
        console.log("Camera permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  };

  React.useEffect(() => {
    Sensinggo.multiply(11, 9).then(setResult);
  }, []);
  // Sensinggo.getMagnetometer();
  
  // Sensinggo.WiFiInfo().then(function (fulfilled) {
  //   console.log("ggg servingChan",fulfilled["servingChan"]);
  // })
  // .catch(function (error) {
  //   console.log(error.message)
  // })
  requestCameraPermission();
  console.log("ggg ggg");

  timesRun = 0;
  first_= true;

  let timer;
  type = 0;
  count = 0;
  ROUND = 10 ;
  // Sensinggo.location();
  timer = setInterval(() => { 
    
    if(type == 0){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.acc();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('acc', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 0){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.stop_sensorInfo();
          count = count+1;
        }
      });
      
    }
    if(type == 1){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.mag();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('mag', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 1){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.stop_sensorInfo();
          count = count+1;
        }
      });
      
    }
    if(type == 2){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.light();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('light', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 2){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.stop_sensorInfo();
          count = count+1;
        }
      });
    }
    if(type == 3){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.location();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('location', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 3){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.location_stop();
          count = count+1;
        }
      });
    }
    if(type == 4){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.phoneState();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('phoneState', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 4){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.phoneState_stop();
          count = count+1;
        }
      });
    }
    if(type == 5){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.phoneInfo();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('phoneInfo', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 5){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.phoneInfo_stop();
          count = count+1;
        }
      });
    }
    if(type == 6){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.cellularInfo();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('cellularInfo', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_ && type == 6){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.cellularInfo_stop();
          count = count+1;
        }
      });
    }
    if(type == 7){
      console.log("test_time_gg","type0");
      first_=true;
      start = Date.now();
      Sensinggo.proximity();
      const eventEmitter = new NativeEventEmitter(Sensinggo);
      this.eventListener = eventEmitter.addListener('proxi', (event) => {
        // console.log("test_time_gg",event.X);
        end = Date.now();
        if(count == ROUND){
          Sensinggo.write_("time_time","\n");
          console.log("type",type);
          type = type + 1;
          count = 0;
        }
        if(first_&& type ==7){   
          first_=false;
          // console.log("test_time_gg",(end-start));
          Sensinggo.write_("time_time",(end-start)+"");
          Sensinggo.write_("time_time",",");
          Sensinggo.stop_sensorInfo();
          count = count+1;
        }
      });
      
    }
      
    // }
    // if(type == 4){
    //   console.log("test_time_gg","type0");
    //   first_=true;
    //   start = Date.now();
    //   Sensinggo.pressure();
    //   const eventEmitter = new NativeEventEmitter(Sensinggo);
    //   this.eventListener = eventEmitter.addListener('press', (event) => {
    //     // console.log("test_time_gg",event.X);
    //     end = Date.now();
    //     if(count == ROUND){
    //       Sensinggo.write_("time_time","\n");
    //       console.log("type",type);
    //       type = type + 1;
    //       count = 0;
    //     }
    //     if(first_){   
    //       first_=false;
    //       // console.log("test_time_gg",(end-start));
    //       Sensinggo.write_("time_time",(end-start)+"");
    //       Sensinggo.write_("time_time",",");
    //       Sensinggo.stop_sensorInfo();
    //       count = count+1;
    //     }
    //   });
      
    // }
    
  }, 5000);
     
  setTimeout(()=>{
    clearInterval(timer);
    }, 100000);
 
  return (
    <View style={styles.container}>
      <Text>Restyy: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
