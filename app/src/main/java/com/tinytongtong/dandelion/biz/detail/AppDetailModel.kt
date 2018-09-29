package com.tinytongtong.dandelion.biz.detail

import com.tinytongtong.dandelion.biz.detail.bean.AppDetailBean
import com.tinytongtong.dandelion.http.HttpHelper
import io.reactivex.Observer

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/29 2:19 PM
 * @Version TODO
 */
class AppDetailModel : AppDetailContract.IModel{
    override fun getAppDetailInfo(params: Map<String, String>, observer: Observer<AppDetailBean>) {
        HttpHelper.getInstance().getAppDetailInfo(params,observer)
    }
}
