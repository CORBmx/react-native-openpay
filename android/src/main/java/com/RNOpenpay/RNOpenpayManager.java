package com.RNOpenpay;

import com.RNOpenpay.openpay.OperationCallBack;
import com.RNOpenpay.openpay.OperationResult;
import com.RNOpenpay.openpay.exceptions.OpenpayServiceException;
import com.RNOpenpay.openpay.exceptions.ServiceUnavailableException;
import com.RNOpenpay.openpay.model.Token;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.RNOpenpay.openpay.Openpay;
import com.RNOpenpay.openpay.model.Card;

public class RNOpenpayManager extends ReactContextBaseJavaModule {

    public ReactApplicationContext reactContext;
    private Openpay openpay;

    public RNOpenpayManager(ReactApplicationContext _reactContext) {
        super(_reactContext);

        reactContext = _reactContext;
    }

    @Override
    public String getName() {
        return "RNOpenpayManager";
    }

    @ReactMethod
    public void setup(String merchantId, String apiKey, Boolean forProduction) {
        this.openpay = new Openpay(merchantId, apiKey, forProduction);
    }

    @ReactMethod
    public void createCardToken(ReadableMap cardJson, final Promise promise) {
        Card card = new Card();
        card.holderName(cardJson.getString("holder_name"));
        card.cardNumber(cardJson.getString("card_number"));
        card.expirationMonth(Integer.parseInt(cardJson.getString("expiration_month")));
        card.expirationYear(Integer.parseInt(cardJson.getString("expiration_year")));
        card.cvv2(cardJson.getString("cvv2"));

        this.openpay.createToken(card, new OperationCallBack<Token>() {

            @Override
            public void onError(OpenpayServiceException error) {
                promise.reject(error);
            }

            @Override
            public void onCommunicationError(ServiceUnavailableException error) {
                promise.reject(error);
            }

            @Override
            public void onSuccess(OperationResult<Token> operationResult) {
                Token token = operationResult.getResult();
                promise.resolve(token.getId());
            }
        });
    }

    @ReactMethod
    public void getDeviceSessionId(Promise promise) {
        String deviceSessionId = openpay.getDeviceCollectorDefaultImpl()
            .setup(reactContext.getCurrentActivity());

        promise.resolve(deviceSessionId);
    }

}
