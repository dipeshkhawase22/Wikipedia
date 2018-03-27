package org.dkcorp.wikipedia.activities

import android.app.Application
import org.dkcorp.wikipedia.activities.Providers.ArticleDataProvider
import org.dkcorp.wikipedia.activities.managers.WikiManager
import org.dkcorp.wikipedia.activities.repositories.ArticleDatabaseOpenHelper
import org.dkcorp.wikipedia.activities.repositories.FavoritesRepository
import org.dkcorp.wikipedia.activities.repositories.HistoryRepository

/**
 * Created by saurabh on 24/3/18.
 */
class WikiApplication: Application() {
    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}