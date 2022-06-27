package com.example.binding_adapters

import android.util.Log
import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.share.DiskShare


class TestConnection {
    companion object{

        fun getData(){
            //code 3
        val client = SMBClient()
        client.connect("25.70.83.232").use { connection ->
            Log.v("cooneeected:",connection.isConnected.toString())
        val ac =
            AuthenticationContext("", "".toCharArray(), "")
        val session = connection.authenticate(ac)
        var share=session.connectShare("wwwroot") as DiskShare
            for (f in share.list("Pictures")) {
                println("File : " + f.fileName)
            }

    }
}

    }

}