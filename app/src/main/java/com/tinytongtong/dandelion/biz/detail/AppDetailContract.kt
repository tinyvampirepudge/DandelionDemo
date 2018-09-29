package com.tinytongtong.dandelion.biz.detail

import com.tinytongtong.dandelion.base.IBaseModel
import com.tinytongtong.dandelion.base.IBasePresenter
import com.tinytongtong.dandelion.base.IBaseView
import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.detail.bean.AppDetailBean
import io.reactivex.Observer

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/29 2:14 PM
 * @Version TODO
 */
interface AppDetailContract {
    interface IModel : IBaseModel {
        fun getAppDetailInfo(params: Map<String, String>, observer: Observer<AppDetailBean>)
    }

    interface IView : IBaseView {
        fun sendResultToView(bean: AppDetailBean, params: Map<String, String>)
    }

    interface IPresenter : IBasePresenter {
        fun getDataFromNet(params: Map<String, String>)
    }
}