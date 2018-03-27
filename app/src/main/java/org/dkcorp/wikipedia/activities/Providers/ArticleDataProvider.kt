package org.dkcorp.wikipedia.activities.Providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Handler
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import org.dkcorp.wikipedia.activities.models.Urls
import org.dkcorp.wikipedia.activities.models.WikiResult
import java.io.Reader

/**
 * Created by saurabh on 20/3/18.
 */
class ArticleDataProvider {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Pluralsight Wikipedia")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Urls.getSearchUrl(term, skip, take).httpGet()
                .responseObject(WikipediaDataDeserializer()) { _, response, result ->

                    if(response.httpStatusCode != 200){
                        throw Exception("Unable to get articles")
                    }
                    val(data, _) = result
                    responseHandler.invoke(data as WikiResult)
        }
    }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Urls.getRandomUrl(take).httpGet()
                .responseObject(WikipediaDataDeserializer()) { _, response, result ->

                    if (response.httpStatusCode != 200) {
                        throw Exception("Unable to get articles")
                    }
                    val (data, _) = result
                    responseHandler.invoke(data as WikiResult)
                }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)
        }
    }