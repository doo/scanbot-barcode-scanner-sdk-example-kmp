//
//  SBSDKBaseScannerFrameProcessor.h
//  ScanbotSDK
//
//  Created by Sebastian Husche on 06.04.22.
//  Copyright © 2022 doo GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

#ifdef FLAVOUR_ALL
#import <ScanbotSDK/SBSDKBaseScannerFrame.h>
#endif

#ifdef FLAVOUR_BARCODES
#import <ScanbotBarcodeScannerSDK/SBSDKBaseScannerFrame.h>
#endif

#ifdef FLAVOUR_DOCUMENTS
#import <ScanbotDocumentScannerSDK/SBSDKBaseScannerFrame.h>
#endif

NS_ASSUME_NONNULL_BEGIN

@interface _SBSDKBaseScannerFrameProcessorInput : NSObject
@property(nonatomic, strong) _SBSDKBaseScannerFrame *frame;
@property(nonatomic, assign) CGRect finderRect;
@property(nonatomic, assign) BOOL clipTofinderRect;
@property(nonatomic, assign) CGRect visibleRect;
@property(nonatomic, assign) BOOL clipToVisibleRect;
@property(nonatomic, assign) NSInteger flipMode;
@property(nonatomic, assign) BOOL cameraFlipped;

@property(nonatomic, assign) AVCaptureVideoOrientation videoOrientation;
@property(nonatomic, assign) UIDeviceOrientation deviceOrientation;
@property(nonatomic, assign) BOOL correctOrientation;
@property(nonatomic, strong) AVCaptureVideoPreviewLayer *previewLayer;
@end

@interface _SBSDKBaseScannerFrameProcessorOutput : NSObject
@property(nonatomic, strong) _SBSDKBaseScannerFrame *frame;
@property(nonatomic, assign) CGSize imageSize;
@property(nonatomic, assign) CGRect clipRect;
@property(nonatomic, assign) CGRect visibleRect;
@property(nonatomic, assign) CGRect finderRect;
@property(nonatomic, assign) NSInteger counterClockwiseRotations;
@property(nonatomic, assign) CGAffineTransform imageToViewSpaceTransform;
@property(nonatomic, assign) BOOL cameraFlipped;
@end

@interface _SBSDKBaseScannerFrameProcessor: NSObject

- (nullable _SBSDKBaseScannerFrameProcessorOutput *)convertInput:(_SBSDKBaseScannerFrameProcessorInput *)input;

@end


NS_ASSUME_NONNULL_END
