package com.benidict.android_mvp_boilerplate.example.itemmodel

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.benidict.android_mvp_boilerplate.R
import com.benidict.android_mvp_boilerplate.common.base.BaseEpoxyHolder
import com.benidict.domain.model.Article
import com.squareup.picasso.Picasso


@EpoxyModelClass(layout = R.layout.article_item_model)
abstract class ArticleItemModel: EpoxyModelWithHolder<ArticleItemModel.Holder>(){

    @EpoxyAttribute
    lateinit var article: Article


    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
        Picasso.get()
            .load(article.urlToImage)
            .into(holder.imgIcon)

    }

    class Holder : BaseEpoxyHolder(){
        val tvTitle by bind<TextView>(R.id.tvTitle)
        val tvDescription by bind<TextView>(R.id.tvDescription)
        val imgIcon by bind<ImageView>(R.id.imgIcon)
    }

}