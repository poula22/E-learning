package com.example.binding_adapters

import android.util.Log
import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation
import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.share.DiskEntry
import com.hierynomus.smbj.share.DiskShare
import com.hierynomus.smbj.share.File
import com.hierynomus.smbj.share.Open


class TestConnection {
    companion object{
        private val client = SMBClient()
        private val auth=client.connect("25.70.83.232")
        fun getData(filePath:String): FileIdBothDirectoryInformation? {
            //code 3
        auth.use { connection ->
            Log.v("cooneeected:",connection.isConnected.toString())
        val ac =
            AuthenticationContext("", "".toCharArray(), "")
        val session = connection.authenticate(ac)
        var share=session.connectShare("wwwroot") as DiskShare
            Log.v("Pathhhhhh:",filePath)
            for (f in share.list(filePath)) {
                return f
            }

    }
            return null
}

    }

}