package com.example.data.api.microsoft_api.ocr

import com.example.data.data_classes.URLOCR
import com.example.data.model.microsoft_apis.ocr.OCRResponse
import retrofit2.http.*

interface MicrosoftOCRWebService {
    @Headers("Content-Type: application/json"
        ,"Ocp-Apim-Subscription-Key: 801adcabb2984e64aab3c0c8fb883e49"
    )
    @POST("vision/v3.2/ocr")
    suspend fun getTextFromImage(@Query("language") language:String="unk",@Query("detectOrientation") detectOrentation:Boolean=true
                         ,@Query("model-version") model:String="latest"
                            ,@Body url:URLOCR ):OCRResponse

}