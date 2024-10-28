//
//  SBSDKBarCodeScannerDocumentFormat.h
//  ScanbotSDK
//
//  Created by Andrew Petrus on 20.05.19.
//  Copyright © 2019 doo GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class SBSDKGenericDocument;
@class SBSDKBarcodeType;

@interface _SBSDKBarcodeDocumentParser : NSObject

@property (nonatomic) BOOL parsedSuccessful;

@property (nullable, nonatomic, strong) SBSDKGenericDocument* document;

- (instancetype)initWithString:(NSString *)rawString;

+ (NSArray<SBSDKBarcodeType*>*)supportedBarcodeTypesForName:(NSString*)name;

@end

NS_ASSUME_NONNULL_END
