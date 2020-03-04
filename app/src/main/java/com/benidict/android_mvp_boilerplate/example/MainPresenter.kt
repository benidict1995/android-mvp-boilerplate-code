package com.benidict.android_mvp_boilerplate.example

import android.util.Log
import com.benidict.android_mvp_boilerplate.common.base.BasePresenter
import com.benidict.domain.interactor.article.LoadArticleByNameUseCase
import com.uber.autodispose.AutoDispose
import javax.inject.Inject

class MainPresenter

@Inject
constructor(
    private val loadArticleByNameUseCase: LoadArticleByNameUseCase
): BasePresenter<MainView>(){

    fun loadArticle(){
        loadArticleByNameUseCase.execute("bitcoin")
            .`as`(AutoDispose.autoDisposable(this))
            .subscribe({
                getView().setArticles(it)
            },{
                Log.d("rerequest-failed", "error:${it.localizedMessage}")
            })
    }

}