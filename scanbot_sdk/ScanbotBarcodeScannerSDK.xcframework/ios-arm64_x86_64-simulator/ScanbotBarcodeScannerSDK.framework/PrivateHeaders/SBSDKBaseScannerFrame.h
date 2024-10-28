//
//  SBSDKBaseScannerFrame.h
//  ScanbotSDK
//
//  Created by Sebastian Husche on 19.09.23.
//  Copyright © 2023 doo GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>


NS_ASSUME_NONNULL_BEGIN

@interface _SBSDKBaseScannerFrame: NSObject

@property (nullable, nonatomic, readonly) UIImage* image;

- (instancetype)initWithSampleBuffer:(CMSampleBufferRef)sampleBuffer;
- (instancetype)initWithSampleBuffer:(CMSampleBufferRef)sampleBuffer orientation:(AVCaptureVideoOrientation) videoOrientation;

- (instancetype)initWithImage:(UIImage *)image ignoreOrientation:(BOOL)ignoreOrientation;

@end

NS_ASSUME_NONNULL_END
