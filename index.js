import { NativeModules } from 'react-native';

const RNOpenpay = {
    getDeviceSessionId(merchantId, apiKey, production = false) {
        return NativeModules.RNOpenpayManager.getDeviceSessionId(merchantId, apiKey, production);
    }
}

export default RNOpenpay;
