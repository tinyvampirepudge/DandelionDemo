package com.tinytongtong.dandelion.biz.grouplist

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean


/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/4 8:22 PM
 * @Version TODO
 */

class ApkGroupsListAdapterKt(context: Context, mutableList: MutableList<ApkGroupsListBean.DataBean.ListBean>) : RecyclerView.Adapter<ApkGroupsListAdapterKt.ViewHolder>() {
    var mContext: Context? = null
    var mList: MutableList<ApkGroupsListBean.DataBean.ListBean>? = null

    init {
        mContext = context
        mList = mutableList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_apk_groups_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = mList!![position]
        Glide.with(this.mContext!!).load("https://www.pgyer.com/image/view/app_icons/" + bean.buildIcon).into(holder?.ivIcon)
        holder.tvName.setText(bean.buildName)
        holder.tvType.setText(getBuildType(bean.buildType))
    }

    private fun getBuildType(type: Int): String {
        return if (1 == type) {
            "IOS"
        } else if (2 == type) {
            "Android"
        } else {
            type.toString()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView
        var tvName: TextView
        var tvType: TextView

        init {
            ivIcon = itemView.findViewById(R.id.iv_icon) as ImageView
            tvName = itemView.findViewById(R.id.tv_name) as TextView
            tvType = itemView.findViewById(R.id.tv_type) as TextView
        }
    }
}