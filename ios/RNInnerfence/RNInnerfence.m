//
//  ViewController.m
//  ChargeDemo
//
//  Created by Ryan D Johnson on 10/5/15.
//  Copyright © 2015 Inner Fence. All rights reserved.
//

#import "RNInnerfence.h"

#import "React/RCTBridge.h"
#import "React/RCTConvert.h"

#import "../innerfence/IFChargeRequest.h"
#import "../innerfence/IFChargeResponse.h"

@implementation RNInnerfence

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(addEvent:(NSString *)name location:(NSString *)location)
{
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}

@end