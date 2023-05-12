package com.example.data.api.microsoft_summarization
//
//import com.azure.ai.textanalytics.TextAnalyticsClientBuilder
//import com.azure.ai.textanalytics.models.*
//import com.azure.core.credential.AzureKeyCredential
//import java.util.function.Consumer
//
///**
// * Sample demonstrates how to synchronously execute an "Extractive Summarization" action in a batch of documents.
// */
//object AnalyzeExtractiveSummarization {
//    /**
//     * Main method to invoke this demo about how to analyze an "Extractive Summarization" action.
//     *
//     * @param args Unused arguments to the program.
//     */
//    @JvmStatic
//    fun mainFunc() {
//        val client = TextAnalyticsClientBuilder()
//            .credential(AzureKeyCredential("ba7ac8ba7e1e4fcaa764596e102a26e9"))
//            .endpoint("https://lampit-textanalytics.cognitiveservices.azure.com/")
//            .buildClient()
//        val documents: MutableList<String> = ArrayList()
//        documents.add(
//            "At Microsoft, we have been on a quest to advance AI beyond existing techniques, by taking a more holistic,"
//                    + " human-centric approach to learning and understanding. As Chief Technology Officer of Azure AI"
//                    + " Cognitive Services, I have been working with a team of amazing scientists and engineers to turn "
//                    + "this quest into a reality. In my role, I enjoy a unique perspective in viewing the relationship"
//                    + " among three attributes of human cognition: monolingual text (X), audio or visual sensory signals,"
//                    + " (Y) and multilingual (Z). At the intersection of all three, there’s magic—what we call XYZ-code"
//                    + " as illustrated in Figure 1—a joint representation to create more powerful AI that can speak, hear,"
//                    + " see, and understand humans better. We believe XYZ-code will enable us to fulfill our long-term"
//                    + " vision: cross-domain transfer learning, spanning modalities and languages. The goal is to have"
//                    + " pretrained models that can jointly learn representations to support a broad range of downstream"
//                    + " AI tasks, much in the way humans do today. Over the past five years, we have achieved human"
//                    + " performance on benchmarks in conversational speech recognition, machine translation, "
//                    + "conversational question answering, machine reading comprehension, and image captioning. These"
//                    + " five breakthroughs provided us with strong signals toward our more ambitious aspiration to"
//                    + " produce a leap in AI capabilities, achieving multisensory and multilingual learning that "
//                    + "is closer in line with how humans learn and understand. I believe the joint XYZ-code is a "
//                    + "foundational component of this aspiration, if grounded with external knowledge sources in "
//                    + "the downstream AI tasks."
//        )
//        val syncPoller = client.beginAnalyzeActions(
//            documents,
//            TextAnalyticsActions().setDisplayName("display_name")
//                .setExtractSummaryActions(
//                    ExtractSummaryAction().setMaxSentenceCount(4)
//                        .setOrderBy(SummarySentencesOrder.RANK)
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