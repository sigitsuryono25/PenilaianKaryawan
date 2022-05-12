package com.surelabsid.lti.penilaiankaryawan.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log


object DownloadManagerUtils {

    fun instance(context: Context, url: String) {
        Log.d(DownloadManagerUtils.javaClass.canonicalName, "instance: $url")
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setDescription("Some descrition")
        request.setTitle("Downloading Files")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            uri.lastPathSegment
        )
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}