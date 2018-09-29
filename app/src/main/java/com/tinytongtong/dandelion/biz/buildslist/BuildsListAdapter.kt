package com.tinytongtong.dandelion.biz.buildslist

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.appKey
import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.detail.AppDetailActivity
import com.tinytongtong.dandelion.buildKey
import com.tinytongtong.dandelion.common.util.CommonUtils

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/4 9:57 PM
 * @Version TODO
 */
class BuildsListAdapter(layoutResId: Int, data: MutableList<BuildsListBean.DataBean.ListBean>?, appKey: String?) : BaseQuickAdapter<BuildsListBean.DataBean.ListBean, BaseViewHolder>(layoutResId, data) {
    val myAppKey = appKey;
    override fun convert(helper: BaseViewHolder?, item: BuildsListBean.DataBean.ListBean?) {
        val titleLayout = helper?.getView<RelativeLayout>(R.id.rl_title_layout)
        val imageView = helper?.getView<ImageView>(R.id.iv_icon)
        val tvName = helper?.getView<TextView>(R.id.tv_name)
        val tvType = helper?.getView<TextView>(R.id.tv_type)
        val tvVersion = helper?.getView<TextView>(R.id.tv_version)
        val tvBuildVersion = helper?.getView<TextView>(R.id.tv_build_version)
        val tvSize = helper?.getView<TextView>(R.id.tv_size)
        val tvDuwnloadCount = helper?.getView<TextView>(R.id.tv_download_count)
        val tvCreateTime = helper?.getView<TextView>(R.id.tv_create_time)

        // 应用的Icon图标key，访问地址为 https://www.pgyer.com/image/view/app_icons/[应用的Icon图标key]
        Glide.with(mContext).load("https://www.pgyer.com/image/view/app_icons/" + item?.buildIcon).into(imageView!!)
        tvName?.text = item?.buildName
        tvType?.text = getBuildType(item?.buildType!!)
        tvVersion?.text = item.buildVersion
        tvBuildVersion?.text = item.buildBuildVersion
        tvSize?.text = CommonUtils.getFileSizeDescription(item.buildFileSize)
        tvDuwnloadCount?.text = item.buildDownloadCount.toString() + "次"
        tvCreateTime?.text = item.buildCreated

        titleLayout?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(mContext, AppDetailActivity::class.java)
                intent.putExtra("name", item.buildName)
                intent.putExtra(appKey, myAppKey)
                intent.putExtra(buildKey, item.buildKey)
                mContext.startActivity(intent)
            }
        })
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
}