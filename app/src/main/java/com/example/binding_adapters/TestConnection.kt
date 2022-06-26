package com.example.binding_adapters

import android.util.Log
import jcifs.UniAddress
import jcifs.smb.NtlmPasswordAuthentication
import jcifs.smb.SmbFile
import jcifs.smb.SmbSession
import java.util.*

class TestConnection {
    companion object{
        var filesInfo = TreeMap<Date, String>()
        var auth: NtlmPasswordAuthentication? = null
        var dc = UniAddress.getByName("25.70.83.232", true);
        fun getData(){
            auth =  NtlmPasswordAuthentication("" + ";" + "" + ":" + "");
            SmbSession.logon(dc, auth);
            var file: SmbFile = SmbFile("wwwroot", auth)
            var files = file.listFiles()
            for (i in 0..files.size - 1) {
                var fileName:String = files [i].getName()
                var extension:String = fileName . substring(fileName.lastIndexOf(".") + 1)
                var fileTime:Date = Date(files[i].date)
                Log.v("fileName", fileName)

            }
        }

    }

}