package com.example.data.api.microsoft_translate

import com.google.gson.*
import com.squareup.okhttp.*
import com.squareup.okhttp.MediaType.parse
import com.squareup.okhttp.RequestBody.create
import java.io.*
import java.net.*
import java.util.*

class MicrosoftTranslate {
    var url: HttpUrl = HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("from", "en")
        .addQueryParameter("to", "ar")
        .addQueryParameter("to", "it")
        .build()

    // Instantiates the OkHttpClient.
    var client = OkHttpClient()

    // This function performs a POST request.
    @Throws(IOException::class)
    fun Post(): String {
        val mediaType: MediaType = parse("application/json")
        val body: RequestBody = create(mediaType, "[{\"text\":\"Hello World!\"}]")
        val request: Request = Request.Builder().url(url).post(body)
            .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
            .addHeader("Ocp-Apim-Subscription-Region", location)
            .addHeader("Content-type", "application/json")
            .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    companion object {
        private const val subscriptionKey = "ae3e26dd12ac4ac5a44f059d4085f335"

        // Add your location, also known as region. The default is global.
        // This is required if using a Cognitive Services resource.
        private const val location = "eastus"

        // This function prettifies the json response.
        fun prettify(json_text: String?): String {
            val parser = JsonParser()
            val json = parser.parse(json_text)
            val gson = GsonBuilder().setPrettyPrinting().create()
            return gson.toJson(json)
        }

        @JvmStatic
        fun getTranslatedText() {
            try {
                val translateRequest = MicrosoftTranslate()
                val response = translateRequest.Post()
                println(prettify(response))
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}