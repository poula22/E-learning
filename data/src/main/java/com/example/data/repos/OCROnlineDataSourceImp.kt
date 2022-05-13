package com.example.data.repos

import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.data_classes.URLOCR
import com.example.data.model.convertTo
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.model.OCRResponseDTO
import com.example.domain.model.ReadOCRResponseDTO

class OCROnlineDataSourceImp(val webService:MicrosoftOCRWebService):OCROnlineDataSource {
    override suspend fun getTextFromImage(language:String,url:String): OCRResponseDTO {
       var u=URLOCR(url =url )
        var result= webService.getTextFromImage(language=language,url=u)
        return result.convertTo(OCRResponseDTO::class.java)
    }

    override suspend fun getTextFromImageReadApi(language: String?, url: String): ReadOCRResponseDTO {
        var opId=getHeader(language,url)
        var result=webService.getTextFromSource(opId)
        return result.convertTo(ReadOCRResponseDTO::class.java)
    }

    private suspend fun getHeader(language:String?=null,url:String):String {
        var u=URLOCR(url =url )
        var result= webService.getTextFromImageReadAPI(url=u).headers().get("Operation-Location")
        var index=result?.indexOf("analyzeResults/")
        return result?.substring(index?.plus(15)!!)!!
    }

}