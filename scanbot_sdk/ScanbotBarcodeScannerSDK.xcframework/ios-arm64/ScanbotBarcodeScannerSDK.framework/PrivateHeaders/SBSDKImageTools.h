//
//  SBSDKImageTools.h
//  ScanbotSDK
//
//  Created by Sebastian Husche on 06.10.23.
//  Copyright © 2023 doo GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import <Metal/Metal.h>

#if defined(FLAVOUR_ALL) || defined(FLAVOUR_DOCUMENTS)
@class SBSDKLensCameraProperties;
@class SBSDKParametricFilter;
#endif

@class SBSDKAspectRatio;

@interface _SBSDKImageTools : NSObject

+ (nullable UIImage *)imageByStrippingAlphaFrom:(nonnull UIImage *)source;

+ (nullable UIImage *)imageByInverting:(nonnull UIImage *)source;

+ (nullable UIImage *)imageByScaling:(nonnull UIImage *)source toSize:(CGSize)size;

+ (nullable UIImage *)imageByCenterCroppingToAspectRatio:(nonnull UIImage *)source 
                                             aspectRatio:(nonnull SBSDKAspectRatio *)aspectRatio;

+ (nullable UIImage *)imageByGrayScaling:(nonnull UIImage *)source;

+ (nullable UIImage *)imageFromPixelBuffer:(nonnull CVPixelBufferRef)pixelBuffer;

+ (nullable UIImage *)imageFromSampleBuffer:(nonnull CMSampleBufferRef)sampleBuffer;

+ (NSInteger)rotationsForOrientation:(AVCaptureVideoOrientation)orientation;

+ (NSInteger)rotationsForDeviceOrientation:(UIDeviceOrientation)orientation;

+ (CGRect)rotatedCounterClockwiseFinderFrame:(CGRect)finderFrame
                                   imageSize:(CGSize)imageSize
                                       times:(NSUInteger)times;

+ (CGSize)sizeOfSampleBuffer:(nonnull CMSampleBufferRef)sampleBuffer;

#if defined(FLAVOUR_ALL) || defined(FLAVOUR_DOCUMENTS)

+ (nullable UIImage *)imageWarped:(nonnull UIImage *)image 
                        byPolygon:(nullable SBSDKPolygon *)polygon 
                       imageScale:(CGFloat)imageScale;

+ (nullable UIImage *)imageFiltered:(nonnull UIImage *)image
                          byFilters:(nonnull NSArray<SBSDKParametricFilter *> *)filters;

+ (nullable UIImage *)imageWarped:(nonnull UIImage *)image 
                        byPolygon:(nullable SBSDKPolygon *)polygon
             lensCameraProperties:(nullable SBSDKLensCameraProperties *)lensCameraProperties
                       imageScale:(CGFloat)imageScale;

+ (nullable UIImage *)imageWarped:(nonnull UIImage *)image 
                        byPolygon:(nullable SBSDKPolygon *)polygon
                    andFilteredBy:(nullable NSArray<SBSDKParametricFilter *> *)filter
                       imageScale:(CGFloat)imageScale;

+ (nullable UIImage *)imageWarped:(nonnull UIImage *)image 
                        byPolygon:(nullable SBSDKPolygon *)polygon
                    andFilteredBy:(nullable NSArray<SBSDKParametricFilter *> *)filters
             lensCameraProperties:(nullable SBSDKLensCameraProperties *)lensCameraProperties
                       imageScale:(CGFloat)imageScale;

+ (nullable UIImage *)imageByProcessing:(nonnull UIImage*) image
                              rotations:(NSInteger)times
                                polygon:(nullable SBSDKPolygon *)polygon
                                filters:(nullable NSArray<SBSDKParametricFilter *> *)filters
                            compression:(CGFloat)compression
                   lensCameraProperties:(nullable SBSDKLensCameraProperties *)lensCameraProperties
                             imageScale:(CGFloat)imageScale;

+ (nullable NSData *)imageDataByProcessing:(nonnull UIImage *)image 
                                 rotations:(NSInteger)times
                                   polygon:(nullable SBSDKPolygon *)polygon
                                   filters:(nullable NSArray<SBSDKParametricFilter *> *)filters
                               compression:(CGFloat)compression
                      lensCameraProperties:(nullable SBSDKLensCameraProperties *)lensCameraProperties
                                imageScale:(CGFloat)imageScale;

+ (nullable NSData *)imageData:(nonnull UIImage *)image 
             byApplyingFilters:(nullable NSArray<SBSDKParametricFilter *> *)filters
                   compression:(CGFloat)compression;

#endif

@end

