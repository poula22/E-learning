package com.example.data.api.microsoft_summarization
//
//import com.azure.ai.textanalytics.TextAnalyticsClient
//import com.azure.ai.textanalytics.TextAnalyticsClientBuilder
//import com.azure.ai.textanalytics.models.AnalyzeActionsOptions
//import com.azure.ai.textanalytics.models.AnalyzeActionsResult
//import com.azure.ai.textanalytics.models.ExtractSummaryAction
//import com.azure.ai.textanalytics.models.TextAnalyticsActions
//import com.azure.core.credential.AzureKeyCredential
//import java.util.function.Consumer
//
//
//object Example {
//    private const val KEY = "ba7ac8ba7e1e4fcaa764596e102a26e9"
//    private const val ENDPOINT = "https://lampit-textanalytics.cognitiveservices.azure.com/"
//    @JvmStatic
//    fun main() {
//        val client = authenticateClient(KEY, ENDPOINT)
//        summarizationExample(client)
//    }
//
//    // Method to authenticate the client object with your key and endpoint
//    fun authenticateClient(key: String?, endpoint: String?): TextAnalyticsClient {
//        return TextAnalyticsClientBuilder()
//            .credential(AzureKeyCredential(key))
//            .endpoint(endpoint)
//            .buildClient()
//    }
//
//    // Example method for summarizing text
//    fun summarizationExample(client: TextAnalyticsClient) {
//        val documents: MutableList<String> = ArrayList()
//        documents.add(
//            "The extractive summarization feature uses natural language processing techniques "
//                    + "to locate key sentences in an unstructured text document. "
//                    + "These sentences collectively convey the main idea of the document. This feature is provided as an API for developers. "
//                    + "They can use it to build intelligent solutions based on the relevant information extracted to support various use cases. "
//                    + "In the public preview, extractive summarization supports several languages. "
//                    + "It is based on pretrained multilingual transformer models, part of our quest for holistic representations. "
//                    + "It draws its strength from transfer learning across monolingual and harness the shared nature of languages "
//                    + "to produce models of improved quality and efficiency."
//        )
//        val syncPoller = client.beginAnalyzeActions(
//            documents,
//            TextAnalyticsActions().setDisplayName("{tasks_display_name}")
//                .setExtractSummaryActions(
//                    ExtractSummaryAction()
//                ),
//            "en",
//            AnalyzeActionsOptions()
//        )
//        syncPoller.waitForCompletion()
//        syncPoller.finalResult.forEach(Consumer { actionsResult: AnalyzeActionsResult ->
//            println("Extractive Summarization action results:")
//            for (actionResult in actionsResult.extractSummaryResults) {
//                if (!actionResult.isError) {
//                    for (documentResult in actionResult.documentsResults) {
//                        if (!documentResult.isError) {
//                            println("\tExtracted summary sentences:")
//                            for (summarySentence in documentResult.sentences) {
//                                System.out.printf(
//                                    "\t\t Sentence text: %s, length: %d, offset: %d, rank score: %f.%n",
//                                    summarySentence.text, summarySentence.length,
//                                    summarySentence.offset, summarySentence.rankScore
//                                )
//                            }
//                        } else {
//                            System.out.printf(
//                                "\tCannot extract summary sentences. Error: %s%n",
//                                documentResult.error.message
//                            )
//                        }
//                    }
//                } else {
//                    System.out.printf(
//                        "\tCannot execute Extractive Summarization action. Error: %s%n",
//                        actionResult.error.message
//                    )
//                }
//            }
//        })
//    }
//}