import ScanbotBarcodeScannerSDK.SBSDKUI2BarcodeItem
import ScanbotBarcodeScannerSDK.SBSDKUI2BarcodeScannerConfiguration
import ScanbotBarcodeScannerSDK.SBSDKUI2BarcodeScannerResult
import ScanbotBarcodeScannerSDK.SBSDKUI2BarcodeScannerViewController
import ScanbotBarcodeScannerSDK.sbsdk_attachViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
class BarcodeScannerViewController(val onBarcodeScanned: OnBarcodeScanned) : UIViewController(nibName = null, bundle = null) {

    @OptIn(ExperimentalForeignApi::class)
    private lateinit var scannerViewController: SBSDKUI2BarcodeScannerViewController

    override fun viewDidLoad() {
        super.viewDidLoad()

        val configuration = SBSDKUI2BarcodeScannerConfiguration()
        scannerViewController =
            SBSDKUI2BarcodeScannerViewController.createNewWithConfiguration(
                configuration = configuration,
                handler = object : (SBSDKUI2BarcodeScannerViewController?, Boolean, NSError?, SBSDKUI2BarcodeScannerResult?) -> Unit {
                    override fun invoke(
                        p1: SBSDKUI2BarcodeScannerViewController?,
                        p2: Boolean,
                        p3: NSError?,
                        result: SBSDKUI2BarcodeScannerResult?
                    ) {
                        if (result?.items()?.isNotEmpty() == true) {
                            val firstBarcode = result.items().first() as SBSDKUI2BarcodeItem
                            onBarcodeScanned.onBarcodeScanned(firstBarcode.text())
                        }
                    }
                }
            )

        this.sbsdk_attachViewController(scannerViewController, this.view)
    }
}