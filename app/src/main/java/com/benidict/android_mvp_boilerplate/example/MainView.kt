package com.benidict.android_mvp_boilerplate.example

import com.benidict.android_mvp_boilerplate.common.base.BaseActivityView
import com.benidict.domain.model.Article

interface MainView : BaseActivityView {

    fun setArticles(article: List<Article>)
}