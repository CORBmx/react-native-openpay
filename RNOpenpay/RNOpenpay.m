//
//  RNOpenpay.m
//  RNOpenpay
//
//  Created by Cesar Landeros Delgado on 9/5/16.
//  Copyright © 2016 corb. All rights reserved.
//

#import "Openpay.h"
#import "RNOpenpay.h"

@interface RNOpenpayManager() {
    Openpay *_openpayAPI;
}

@end

@implementation RNOpenpayManager

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setup:(NSString *)merchantId
             withApiKey:(NSString *)apiKey
          forProduction:(BOOL)isProduction)
{
    _openpayAPI = [[Openpay alloc] initWithMerchantId:merchantId
                                               apyKey:apiKey
                                     isProductionMode:isProduction];
}

RCT_EXPORT_METHOD(createCardToken:(NSDictionary *)cardJson
                         resolver:(RCTPromiseResolveBlock)resolve
                         rejecter:(RCTPromiseRejectBlock)reject)
{
    OPCard *card = [[OPCard alloc] init];
    card.holderName = cardJson[@"holder_name"];
    card.number = cardJson[@"holder_name"];
    card.expirationMonth = cardJson[@"holder_name"];
    card.expirationYear = cardJson[@"holder_name"];
    card.cvv2 = cardJson[@"holder_name"];


    [_openpayAPI createTokenWithCard:card
        success:^(OPToken *token) {
            resolve(token.id);
        } failure:^(NSError *error) {
            NSString *codeWithDomain = [NSString stringWithFormat:@"E%@%zd", error.domain.uppercaseString, error.code];
            reject(codeWithDomain, error.localizedDescription, error);
    }];
}

RCT_EXPORT_METHOD(getDeviceSessionId:(RCTPromiseResolveBlock)resolve
                            rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve([_openpayAPI createDeviceSessionId]);
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@end
