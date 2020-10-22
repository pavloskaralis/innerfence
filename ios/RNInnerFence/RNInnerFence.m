//
//  ViewController.m
//  ChargeDemo
//
//  Created by Ryan D Johnson on 10/5/15.
//  Copyright © 2015 Inner Fence. All rights reserved.
//

#import "RNInnerFence.h"

#import "React/RCTBridge.h"
#import "React/RCTConvert.h"

#import "IFChargeRequest.h"
#import "IFChargeResponse.h"

@interface RNInnerFence ()

@property (copy) RCTPromiseResolveBlock resolve;
@property (copy) RCTPromiseRejectBlock reject;

@end

@implementation RNInnerFence

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(paymentRequest, params:(NSDictionary *)params resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject){

    //resolve(@"HELLO FROM INNERFENCE IOS MODULE");
    IFChargeRequest* chargeRequest = [IFChargeRequest new];

    // Include my record_id so it comes back with the response
    [chargeRequest
        setReturnURL:@"com-innerfence-ChargeDemo://chargeResponse"
        withExtraParams:@{ @"record_id": @"123" }
    ];

    chargeRequest.amount        = @"50.00";
    chargeRequest.description   = @"Test transaction";
    chargeRequest.invoiceNumber = @"321";

    // Include a tax rate if you want Credit Card terminal to calculate
    // sales tax. If you pass in @"default", we'll use the default sales
    // tax preset by the user. If you leave it as nil, we’ll hide the
    // sales tax option from the user.
    chargeRequest.taxRate = @"8.5";

    [chargeRequest submit];
    resolve(@"HELLO FROM INNERFENCE IOS MODULE");
   // return @"hello";
}

@end