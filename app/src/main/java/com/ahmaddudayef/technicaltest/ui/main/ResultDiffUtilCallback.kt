package com.ahmaddudayef.technicaltest.ui.main

import android.support.v7.util.DiffUtil
import com.ahmaddudayef.technicaltest.data.network.model.Value

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
class ResultDiffUtilCallback(
    private val oldData: MutableList<Value>,
    private val newData: MutableList<Value>
): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition) == newData.get(newItemPosition)
    }

}