package org.dkcorp.wikipedia.activities.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.dkcorp.wikipedia.R
import org.dkcorp.wikipedia.activities.holders.CardHolder
import org.dkcorp.wikipedia.activities.holders.ListItemHolder
import org.dkcorp.wikipedia.activities.models.WikiPage


/**
 * Created by saurabh on 18/3/18.
 */
class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults : ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size //temporary
    }

    override fun onBindViewHolder(holder: ListItemHolder?, position: Int) {
        // update viewto
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }
}