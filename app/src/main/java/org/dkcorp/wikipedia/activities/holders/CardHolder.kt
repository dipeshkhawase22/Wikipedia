package org.dkcorp.wikipedia.activities.holders

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.dkcorp.wikipedia.R
import org.dkcorp.wikipedia.activities.activities.ArticleDetailActivity
import org.dkcorp.wikipedia.activities.models.WikiPage

/**
 * Created by saurabh on 18/3/18.
 */
class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage){
        currentPage = page

        titleTextView.text = page.title

        //load image with picasso
        if (page.thumbnail != null)
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
    }
}