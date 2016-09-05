import { NativeModules } from 'react-native';

const RNOpenpay = {
    getDeviceSessionId(merchantId, apyKey, production = false) {
        return NativeModules.RNOpenpayManager.getDeviceSessionId(merchantId, apyKey, production);
    }
}

export default RNOpenpay;
