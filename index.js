import { NativeModules } from 'react-native';

const RNOpenpay = {
    setup(merchantId, apiKey, production = false) {
        NativeModules.RNOpenpayManager.setup(merchantId, apiKey, production)
    },
    createCardToken: NativeModules.RNOpenpayManager.createCardToken,
    getDeviceSessionId: NativeModules.RNOpenpayManager.getDeviceSessionId,
}

export default RNOpenpay;
