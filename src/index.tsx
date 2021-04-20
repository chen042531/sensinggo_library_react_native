import { NativeModules } from 'react-native';

type SensinggoType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Sensinggo } = NativeModules;

export default Sensinggo as SensinggoType;
