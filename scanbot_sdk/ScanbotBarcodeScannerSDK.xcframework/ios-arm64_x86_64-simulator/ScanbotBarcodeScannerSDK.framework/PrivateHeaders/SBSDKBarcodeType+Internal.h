//
//  _SBSDKBarcodeType.h
//  ScanbotSDK
//
//  Created by Sebastian Husche on 05.09.19.
//  Copyright © 2019 doo GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SBSDKBarcodeType;

NS_ASSUME_NONNULL_BEGIN

@interface _SBSDKBarcodeType: NSObject

@property (nonatomic, readonly) NSString *name;

@property (nonatomic, readonly) NSInteger value;

- (instancetype)init NS_UNAVAILABLE;

+ (nullable _SBSDKBarcodeType *)internalTypeWithValue:(NSUInteger)value;

+ (_SBSDKBarcodeType *)aztec;
+ (_SBSDKBarcodeType *)codaBar;
+ (_SBSDKBarcodeType *)code25;
+ (_SBSDKBarcodeType *)code39;
+ (_SBSDKBarcodeType *)code93;
+ (_SBSDKBarcodeType *)code128;
+ (_SBSDKBarcodeType *)dataMatrix;
+ (_SBSDKBarcodeType *)ean8;
+ (_SBSDKBarcodeType *)ean13;
+ (_SBSDKBarcodeType *)itf;
+ (_SBSDKBarcodeType *)pdf417;
+ (_SBSDKBarcodeType *)qrCode;
+ (_SBSDKBarcodeType *)microQrCode;
+ (_SBSDKBarcodeType *)databar;
+ (_SBSDKBarcodeType *)databarExpanded;
+ (_SBSDKBarcodeType *)upcA;
+ (_SBSDKBarcodeType *)upcE;
+ (_SBSDKBarcodeType *)upcEanExtension;
+ (_SBSDKBarcodeType *)msiPlessey;
+ (_SBSDKBarcodeType *)iata2Of5;
+ (_SBSDKBarcodeType *)industrial2Of5;
+ (_SBSDKBarcodeType *)uspsIntelligentMail;
+ (_SBSDKBarcodeType *)royalMail;
+ (_SBSDKBarcodeType *)japanPost;
+ (_SBSDKBarcodeType *)royalTNTPost;
+ (_SBSDKBarcodeType *)australiaPost;
+ (_SBSDKBarcodeType *)databarLimited;
+ (_SBSDKBarcodeType *)gs1Composite;
+ (_SBSDKBarcodeType *)microPdf417;
+ (_SBSDKBarcodeType *)maxiCode;
+ (_SBSDKBarcodeType *)rmqrCode;
+ (_SBSDKBarcodeType *)code11;
+ (_SBSDKBarcodeType *)code32;
@end

NS_ASSUME_NONNULL_END
