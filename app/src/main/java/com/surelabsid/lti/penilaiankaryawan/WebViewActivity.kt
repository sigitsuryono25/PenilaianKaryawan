package com.surelabsid.lti.penilaiankaryawan

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintManager
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private var url: String? = null
    private var data: String? = null
    private lateinit var binding :ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        url = intent.getStringExtra("url")
        data = intent.getStringExtra("data")

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true

        binding.webView.webChromeClient =
            object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)


                }
            }

        binding.webView.webViewClient =
            object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                }
            }

        if (url?.isNotEmpty() == true) {
            binding.webView.loadUrl(url.toString())
        } else {
            binding.webView.loadDataWithBaseURL(null, data.toString(), "text/html", "UTF-8", null)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /**
         * @var menu.add(param1, param2, param3, param4)
         * @param1 item group
         * @param2 itemId
         * @param3 order
         * @param4 title
         */
//        menu?.add(0, 1, 0, getString(R.string.forward))
//        menu?.add(0, 2, 0, getString(R.string.back))
//        if (url?.isNotEmpty() == true)
//            menu?.add(0, 3, 0, getString(R.string.open_with_chrome))
//
//        menu?.add(0, 4, 0, getString(R.string.web_page_print))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                if (binding.webView.canGoForward()) {
                    binding.webView.goForward()
                }
            }
            2 -> {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                }
            }
            3 -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.setPackage("com.android.chrome")
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse(url)
                startActivity(i)
            }
            4 -> {
                createWebPrintJob(this, binding.webView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else
            super.onBackPressed()
    }

    @Suppress("DEPRECATION")
    private fun createWebPrintJob(context: Context, webView: WebView) {
        // Get a PrintManager instance
        val printManager =
            context.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName: String = webView.title + " Document"

        // Get a print adapter instance
        val printAdapter: PrintDocumentAdapter
        printAdapter =
            webView.createPrintDocumentAdapter(jobName)

        // Create a print job with name and adapter instance
        printManager.print(
            jobName, printAdapter,
            PrintAttributes.Builder().build()
        )

        // Save the job object for later status checking
//        mPrintJobs.add(printJob)
    }

}