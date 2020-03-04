package com.benidict.android_mvp_boilerplate.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benidict.android_mvp_boilerplate.R
import com.benidict.android_mvp_boilerplate.common.base.BaseActivity
import com.benidict.android_mvp_boilerplate.example.itemmodel.ArticleControllerItemModel
import com.benidict.domain.model.Article
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    lateinit var controllerItemModel: ArticleControllerItemModel

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getMvpView(): MainView = this

    override fun getMvpPresenter(): MainPresenter = presenter

    override fun setupDependencies() {}

    override fun setupViews() {
        initRecyclerView()
    }

    private fun initRecyclerView(){
        controllerItemModel = ArticleControllerItemModel()

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.setController( controllerItemModel )
    }

    override fun setArticles(article: List<Article>) {
        controllerItemModel.setData(article)
    }

    override fun loadContent() {
        presenter.loadArticle()
    }
}
