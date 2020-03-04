package com.benidict.android_mvp_boilerplate.example.itemmodel

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.benidict.domain.model.Article

class ArticleControllerItemModel : TypedEpoxyController<List<Article>>(){


    override fun buildModels(data: List<Article>) {
        for (i in data.indices){
            val art = data[i]
            articleItem {
                id(i)
                article(art)
            }
        }
    }
}