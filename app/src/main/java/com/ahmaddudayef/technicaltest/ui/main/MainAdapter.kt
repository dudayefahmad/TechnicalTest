package com.ahmaddudayef.technicaltest.ui.main

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.ahmaddudayef.technicaltest.R
import com.ahmaddudayef.technicaltest.data.network.model.Value
import kotlinx.android.synthetic.main.item_joke.view.*
import kotlinx.android.synthetic.main.tv_list_footer.view.*

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
class MainAdapter(private val listDatas: MutableList<Value>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LOADING = -1
    private val TYPE_DATA = 1

    interface Callback {
        fun onArrowClick(data: Value, position: Int)
        fun onOtherClick(data: Value, position: Int)
        fun onLoadMore()
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun addItems(listData: MutableList<Value>) {
        val newData = ArrayList<Value>()
        newData.addAll(this.listDatas)
        newData.addAll(listDatas)

        val diffData = DiffUtil.calculateDiff(
            ResultDiffUtilCallback(this.listDatas,
                newData))
        this.listDatas.addAll(listData)
        diffData.dispatchUpdatesTo(this)
    }

    fun clearItems() {
        listDatas.clear()
        notifyDataSetChanged()
    }

    fun addItem(listData: Value){
        listDatas.add(listData)
        notifyItemInserted(listDatas.size - 1)
    }

    fun removeItem() {
        listDatas.removeAt(listDatas.size - 1)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        listDatas.removeAt(position)
    }

    fun reverseItem() {
        listDatas.reverse()
    }

    override fun getItemViewType(position: Int): Int {
        return if (listDatas.get(position).type == "LOADING") TYPE_LOADING else TYPE_DATA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOADING -> createLoadingViewHolder(parent)
            else -> createMainViewHolder(parent)
        }
    }

    private fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {

        val mainViewHolder = MainViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_joke,
                parent,
                false
            )
        )

        mainViewHolder.itemView.img_up_position.setOnClickListener {
            val result = getItem(mainViewHolder.adapterPosition)
            if (result != null){
                callback?.onArrowClick(listDatas[mainViewHolder.adapterPosition], mainViewHolder.adapterPosition)
            }
        }

        mainViewHolder.itemView.setOnClickListener {
            val result = getItem(mainViewHolder.adapterPosition)
            if (result != null){
                callback?.onOtherClick(listDatas[mainViewHolder.adapterPosition], mainViewHolder.adapterPosition)
            }
        }

        return mainViewHolder
    }

    private fun createLoadingViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val loadingViewHolder = LoadingViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.tv_list_footer,
                parent,
                false
            )
        )

        loadingViewHolder.itemView.btn_more.setOnClickListener {
            callback?.onLoadMore()
        }

//        if (loadingViewHolder.adapterPosition == 5)
//            loadingViewHolder.itemView.btn_more.visibility = GONE

        return loadingViewHolder
    }

    override fun getItemCount(): Int {
        return listDatas.size
    }

    fun getItem(position: Int): Value? {
        return if (position != RecyclerView.NO_POSITION)
            listDatas.get(position)
        else
            null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DATA -> (holder as MainViewHolder).bindItem(listDatas[position], position)
            TYPE_LOADING -> (holder as LoadingViewHolder).onBind()
        }
    }


    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(data: Value, position: Int){
            itemView.txt_joke.text = data.joke
            if (position == 0){
                itemView.img_up_position.visibility = GONE
                itemView.txt_status.visibility = VISIBLE
            } else {
                itemView.img_up_position.visibility = VISIBLE
                itemView.txt_status.visibility = GONE
            }
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
        }
    }
}