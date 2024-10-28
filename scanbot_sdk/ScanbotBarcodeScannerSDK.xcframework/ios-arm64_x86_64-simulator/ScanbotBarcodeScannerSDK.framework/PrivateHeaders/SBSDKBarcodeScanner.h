//
//  SBSDKBarcodeScanner.h
//  ScanbotSDK
//
//  Created by Andrew Petrus on 17.01.19.
//  Copyright © 2019 doo GmbH. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>
#import <CoreGraphics/CoreGraphics.h>

#ifdef FLAVOUR_ALL
#import <ScanbotSDK/SBSDKBaseScannerFrame.h>
#endif

#ifdef FLAVOUR_BARCODES
#import <ScanbotBarcodeScannerSDK/SBSDKBaseScannerFrame.h>
#endif

@class SBSDKBarcodeScannerResult;
@class SBSDKBarcodeAdditionalParameters;
@class SBSDKBarcodeType;
@class SBSDKBarcodeDocumentRootType;

@interface _SBSDKBarcodeScanner : NSObject

- (nonnull instancetype)init;

- (nullable instancetype)initWithTypes:(nonnull NSArray<SBSDKBarcodeType *> *)barCodeTypes;

- (nullable instancetype)initWithTypes:(nonnull NSArray<SBSDKBarcodeType *> *)barCodeTypes
                             liveMode:(BOOL)useLiveMode;

- (nullable NSArray<SBSDKBarcodeScannerResult *> *)detectBarCodesOnImage:(nonnull UIImage *)image
                                                                  inRect:(CGRect)rect;

- (nullable NSArray<SBSDKBarcodeScannerResult *> *)detectBarCodesOnImage:(nonnull UIImage *)image
                                                             orientation:(AVCaptureVideoOrientation)videoOrientation
                                                                  inRect:(CGRect)rect;

- (nullable NSArray<SBSDKBarcodeScannerResult *> *)detectBarCodesOnSampleBuffer:(nonnull CMSampleBufferRef)sampleBuffer
                                                                    orientation:(AVCaptureVideoOrientation)videoOrientation
                                                                         inRect:(CGRect)rect;

- (nonnull NSArray<SBSDKBarcodeScannerResult *> *)detectBarCodesOnFrame:(nonnull _SBSDKBaseScannerFrame *)frame
                                                                 inRect:(CGRect)rect;

@property (nonatomic, strong, nonnull) NSArray<SBSDKBarcodeType *> *acceptedBarcodeTypes;

@property (nonatomic, strong, nonnull) NSArray<SBSDKBarcodeDocumentRootType *> *acceptedDocumentTypes;

@property (nonatomic, strong, nullable) NSString *regularExpressionPattern;

@property (nonatomic, assign) NSInteger extensionFilter;

@property (nonatomic, strong, nonnull) SBSDKBarcodeAdditionalParameters *additionalParameters;

@property (nonatomic, assign) NSInteger engineMode;

@property (nonatomic, assign) BOOL useLiveMode;

@end
