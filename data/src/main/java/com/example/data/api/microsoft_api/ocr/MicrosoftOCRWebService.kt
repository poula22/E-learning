package com.example.data.api.microsoft_api.ocr

import com.example.data.data_classes.URLOCR
import com.example.data.model.microsoft_apis.ocr.OCRResponse
import com.example.data.model.microsoft_apis.ocr.ReadOCRResponse
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface MicrosoftOCRWebService {
    @Headers(
        "Content-Type: application/json",
        "Ocp-Apim-Subscription-Key: 801adcabb2984e64aab3c0c8fb883e49"
    )
    @POST("vision/v3.2/ocr")
    suspend fun getTextFromImage(
        @Query("language") language: String = "unk",
        @Query("detectOrientation") detectOrentation: Boolean = true,
        @Query("model-version") model: String = "latest",
        @Body url: URLOCR
    ): OCRResponse

    @Headers(
        "Content-Type: application/json",
        "Ocp-Apim-Subscription-Key: 801adcabb2984e64aab3c0c8fb883e49"
    )
    @POST("vision/v3.2/read/analyze")
    suspend fun getTextFromImageReadAPI(
        @Query("language") language: String?=null,
        @Query("readingOrder") readingOrder: String? = null,
        @Query("pages") pages: String?= null,
        @Query("model-version") modelVersion: String?= null,
        @Body url: URLOCR
    ): Response<Void>
    @Headers(
        "Ocp-Apim-Subscription-Key: 801adcabb2984e64aab3c0c8fb883e49",
        "Content-Type: application/json; charset=utf-8"
        , "x-ms-logging-context: com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision getReadResult"
    )
    @GET("vision/v3.2/read/analyzeResults/{operationId}",)
    suspend fun getTextFromSource(@Path("operationId") opId:String,@Header("accept-language") acceptLanguage:String?=null, @Header("x-ms-parameterized-host")  parameterizedHost:String?=null, @Header("User-Agent") userAgent:String?=null)
    :ReadOCRResponse
}