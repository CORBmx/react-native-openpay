//
//  RNOpenpay.h
//  RNOpenpay
//
//  Created by Cesar Landeros Delgado on 9/5/16.
//  Copyright © 2016 corb. All rights reserved.
//

#import "RCTBridgeModule.h"

@interface RNOpenpayManager : NSObject <RCTBridgeModule>

@property (readonly) NSString *deviceSessionId;

@end
