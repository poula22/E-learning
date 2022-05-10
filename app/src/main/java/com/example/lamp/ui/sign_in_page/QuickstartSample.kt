package com.example.lamp.ui.sign_in_page

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.api.gax.paging.Page
import com.google.api.services.storage.Storage
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.StorageOptions
import com.google.cloud.vision.v1.AnnotateImageRequest
import com.google.cloud.vision.v1.Feature
import com.google.cloud.vision.v1.Image
import com.google.cloud.vision.v1.ImageAnnotatorClient

import com.google.common.collect.Lists
import com.google.protobuf.ByteString
import com.google.protobuf.Descriptors
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


class QuickstartSample {

    companion object {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.

        var client = ImageAnnotatorClient.create()

        @Throws(IOException::class)
        fun authExplicit(jsonPath: String?) {
            // You can specify a credential file by providing a path to GoogleCredentials.
            // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
            val credentials: GoogleCredentials =
                GoogleCredentials.fromStream(FileInputStream(jsonPath))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"))
            val storage: com.google.cloud.storage.Storage? =
                StorageOptions.newBuilder().setCredentials(credentials).build().getService()
            println("Buckets:")
            val buckets: Page<Bucket> = storage?.list() as Page<Bucket>
            for (bucket in buckets.iterateAll()) {
                System.out.println(bucket.toString())
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun test() {


            // The path to the image file to annotate
            val fileName = "C:\\Users\\nvenr\\Downloads\\im_test.jfif"

            // Reads the image file into memory
            val path = Paths.get(fileName)

            val data = Files.readAllBytes(path)

            val imgBytes = ByteString.copyFrom(data)

            // Builds the image annotation request
            val requests: MutableList<AnnotateImageRequest> = ArrayList()
            val img = Image.newBuilder().setContent(imgBytes).build()

            val feat = Feature.newBuilder()
                .setType(Feature.Type.TEXT_DETECTION).build()
            val request = AnnotateImageRequest.newBuilder().addFeatures(feat)
                .setImage(img).build()
            requests.add(request)

            // Performs label detection on the image file
            val response =
                client.batchAnnotateImages(requests)
            val responses =
                response.responsesList
            for (res in responses) {
                if (res.hasError()) {
//                    System.out.format("Error: %s%n", res.error.message)
//                    Log.v("test", res.error.message)
                    break
                }
                for (annotation in res.labelAnnotationsList) {
                    annotation
                        .allFields
                        .forEach { (k: Descriptors.FieldDescriptor?, v: Any) ->
//                            System.out.format(
//                                "%s : %s%n",
//                                k,
//                                v.toString()
//                            )
//                            Log.v(
//                                "test", String.format(
//                                    "%s : %s%n",
//                                    k,
//                                    v.toString()
//                                )
//                            )
                        }
                }
            }
        }


    }

}