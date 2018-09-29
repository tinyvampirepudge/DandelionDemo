package com.tinytongtong.dandelion.biz.detail

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tinytongtong.dandelion.R

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/29 4:55 PM
 * @Version TODO
 */
class AppDetailScreenShotAdapter(layoutResId: Int, data: List<String>?) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        val img = helper?.getView<ImageView>(R.id.iv_screen_shot)

        img?.let { Glide.with(mContext).load("https://www.pgyer.com/image/view/app_screenshots/${item}").into(it) }
    }

}