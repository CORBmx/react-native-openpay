//
//  RNOpenpay.m
//  RNOpenpay
//
//  Created by Cesar Landeros Delgado on 9/5/16.
//  Copyright © 2016 corb. All rights reserved.
//

#import "Openpay.h"
#import "RNOpenpay.h"

@implementation RNOpenpayManager

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getDeviceSessionId:(NSString *)merchantId
                          withApiKey:(NSString *)apiKey
                       forProduction:(BOOL)isProduction
                            resolver:(RCTPromiseResolveBlock)resolve
                            rejecter:(RCTPromiseRejectBlock)reject)
{
    Openpay *openpayAPI = [[Openpay alloc] initWithMerchantId:merchantId
                                                       apyKey:apiKey
                                             isProductionMode:isProduction];

    NSString *sessionId = [openpayAPI createDeviceSessionId];

    resolve(sessionId);
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@end
