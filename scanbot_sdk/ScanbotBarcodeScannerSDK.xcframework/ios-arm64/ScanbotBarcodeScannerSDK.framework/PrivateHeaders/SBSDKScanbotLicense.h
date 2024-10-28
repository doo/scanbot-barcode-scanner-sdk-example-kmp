//
//  SBSDKScanbotLicense.h
//  Scanbot SDK
//
//  Created by Sebastian Husche on 03.06.15.
//  Copyright (c) 2015 doo GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void (^_FailureHandler)(int32_t, int64_t, NSString * _Nullable);

@interface _SBSDKScanbotLicense : NSObject

+ (BOOL)installLicenseFromString:(NSString * _Nonnull)licenseString;

+ (BOOL)installLicenseFromFile:(NSString * _Nonnull)licenseFilePath;

+ (BOOL)autoInstallLicense;

+ (BOOL)isLicenseValid;

+ (int32_t)licenseStatus;

+ (BOOL)isFeatureEnabled:(int64_t)feature;

+ (void)setLicenseLoggingEnabled:(BOOL)enabled;

+ (void)setLicenseFailureHandler:(_FailureHandler _Nullable)handler;

+ (NSDate * _Nullable)licenseExpirationDate;

+ (NSString * _Nonnull)stringForFeature:(int64_t)feature;

@end

#ifdef __cplusplus
extern "C"{
#endif

#include <stdint.h>

bool _isFeatureEnabled(int64_t feature);

#ifdef __cplusplus
}
#endif
