package android.print

import android.app.Activity
import android.print.PrintAttributes.Resolution
import android.webkit.WebView
import java.io.File

object PdfView {
    private const val REQUEST_CODE = 493

    /**
     * convert webview content into to pdf file
     *
     * @param activity  pass the current activity context
     * @param webView   webview
     * @param directory directory path where pdf file will be saved
     * @param fileName  name of the pdf file.
     */
    @JvmStatic
    fun createWebPrintJob(
        activity: Activity,
        webView: WebView,
        directory: File,
        fileName: String,
        callback: (path: String?) -> Unit
    ) {
        val jobName = "Document"
        val attributes: PrintAttributes?
        attributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS).build()
        val pdfPrint = PdfPrint(attributes)
        pdfPrint.print(webView.createPrintDocumentAdapter(jobName), directory, fileName) { path ->
            callback(path)
        }
    }
}