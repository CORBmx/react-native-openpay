package com.RNOpenpay;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.RNOpenpay.openpay.Openpay;

public class RNOpenpayManager extends ReactContextBaseJavaModule {

    public ReactApplicationContext reactContext;

    public RNOpenpayManager(ReactApplicationContext _reactContext) {
        super(_reactContext);

        reactContext = _reactContext;
    }

    @Override
    public String getName() {
        return "RNOpenpayManager";
    }

    @ReactMethod
    public void getDeviceSessionId(String merchantId, String apyKey, Boolean forProduction, Promise promise) {
        Openpay openpay = new Openpay(merchantId, apyKey, forProduction);

        String deviceSessionId = openpay.getDeviceCollectorDefaultImpl()
            .setup(reactContext.getCurrentActivity());

        promise.resolve(deviceSessionId);
    }

}
