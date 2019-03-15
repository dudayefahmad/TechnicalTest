package com.ahmaddudayef.technicaltest.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.ahmaddudayef.technicaltest.R
import com.ahmaddudayef.technicaltest.data.network.model.Value
import com.ahmaddudayef.technicaltest.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView, MainAdapter.Callback {

    @Inject
    lateinit var presenter: MainMvpPresenter<MainMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var mainAdapter: MainAdapter

    var isLoading = false
    var listSize: Int = 0
    var dataList: MutableList<Value> = mutableListOf()
    var dataAllList: MutableList<Value> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        mainAdapter.setCallback(this)
        init()
    }

    private fun init() {
        refreshList()
        recyclerView()
        presenter.getJokesRandom()
    }

    private fun refreshList() {
        swipe_refresh.setOnRefreshListener {
            dataList.clear()
            mainAdapter.clearItems()
            presenter.getJokesRandom()
        }
    }

    private fun recyclerView() {
        rv_list_jokes.layoutManager = linearLayoutManager
        rv_list_jokes.setHasFixedSize(true)
        rv_list_jokes.adapter = mainAdapter
    }

    override fun updateJokesRandom(listData: MutableList<Value>) {
        if (mainAdapter.itemCount > 0)
            mainAdapter.removeItem()

        if (listData.size > 0) {
            listSize = 3
            dataAllList.clear()
            dataAllList.addAll(listData)
            dataList.clear()
            dataList.addAll(listData.subList(0, listSize))
            mainAdapter.addItems(dataList)
            mainAdapter.notifyDataSetChanged()
            if (dataList.size < 5) {
                mainAdapter.addItem(
                    Value(
                        type = "LOADING"
                    )
                )
                isLoading = true
            }

            swipe_refresh.isRefreshing = false
        }
    }

    override fun onArrowClick(data: Value, position: Int) {
        val setToPositionFirst = dataList[position]
        dataList.removeAt(position)
        mainAdapter.remove(position)
        dataList.reverse()
        mainAdapter.reverseItem()
        dataList.add(setToPositionFirst)
        mainAdapter.addItem(setToPositionFirst)
        dataList.reverse()
        mainAdapter.reverseItem()
        mainAdapter.notifyDataSetChanged()
    }

    override fun onLoadMore() {
        mainAdapter.clearItems()
        dataList.add(dataAllList[listSize++])
        mainAdapter.addItems(dataList)
        mainAdapter.notifyDataSetChanged()
        if (dataList.size < 5) {
            mainAdapter.addItem(
                Value(
                    type = "LOADING"
                )
            )
            isLoading = true
        }
    }

    override fun onOtherClick(data: Value, position: Int) {
        alert(data.joke!!) {
            positiveButton("Nice Joke!") {  }
            negativeButton("Not Bad") {  }
        }.show()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
