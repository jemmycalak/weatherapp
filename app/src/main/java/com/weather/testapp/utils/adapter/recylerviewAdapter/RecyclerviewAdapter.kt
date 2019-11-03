package com.weather.testapp.utils.adapter.recylerviewAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Constructor

abstract class RecyclerviewAdapter<M, VH : RecyclerView.ViewHolder>
    (val layout: Int, var mData: ArrayList<M>?, val mModel: Class<M>, val mViewModel: Class<VH>) :
    RecyclerView.Adapter<VH>() {

    init {
        if (mData == null) {
            mData = ArrayList()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewGroup = LayoutInflater.from(parent.context).inflate(layout, parent, false) as ViewGroup
        try {
            val constructor = mViewModel.getConstructor(View::class.java) as Constructor<VH>
            return constructor.newInstance(viewGroup)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e.message + " onCreatViewHolder")
        }

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val model = mData!![position]
        bindView(holder, model, position)
    }

    protected abstract fun bindView(holder: VH, model: M, position: Int)

    override fun getItemCount(): Int {
        return mData!!.size
    }

    fun onUpdate(dataUpdate: ArrayList<M>) {
        mData!!.clear()
        for (model in dataUpdate) {
            mData!!.add(model)
            notifyDataSetChanged()
        }
    }

    fun onReplace(data: ArrayList<M>) {
        mData!!.clear()
        mData = data
    }
}