package com.tinytongtong.dandelion.biz.grouplist

import com.tinytongtong.dandelion.base.IBaseModel
import com.tinytongtong.dandelion.base.IBasePresenter
import com.tinytongtong.dandelion.base.IBaseView
import io.reactivex.Observer
import okhttp3.ResponseBody

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 3:17 PM
 */
interface ApkGroupsListContract {
    interface IModel : IBaseModel {
        fun getApkGroupsList(params: Map<String, String>, observer: Observer<ResponseBody>)
    }

    interface IView : IBaseView {
        fun sendResultToView(responseBody: ResponseBody, params: Map<String, String>)
    }

    interface IPresenter : IBasePresenter {
        fun getDataFromNet(params: Map<String, String>)
    }
}