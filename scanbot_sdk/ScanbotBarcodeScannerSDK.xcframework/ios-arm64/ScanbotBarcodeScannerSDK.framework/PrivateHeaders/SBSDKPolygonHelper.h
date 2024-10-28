//
//  SBSDKPolygonHelper.h
//  ScanbotSDK
//
//  Created by Seifeddine Bouzid on 28.09.23.
//  Copyright © 2023 doo GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface _SBSDKPolygonHelper : NSObject

+ (CGSize)sizeWhenApplyingToImageOfSize:(CGSize)size
                                 points:(NSArray<NSValue *> *)points
                             imageScale:(double)imageScale
                            focalLength:(double)focalLength
                            sensorWidth:(double)sensorWidth;
@end

NS_ASSUME_NONNULL_END
