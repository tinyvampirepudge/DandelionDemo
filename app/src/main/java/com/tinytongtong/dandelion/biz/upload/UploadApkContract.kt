package com.tinytongtong.dandelion.biz.upload

import com.tinytongtong.dandelion.base.IBaseModel
import com.tinytongtong.dandelion.base.IBasePresenter
import com.tinytongtong.dandelion.base.IBaseView
import com.tinytongtong.dandelion.biz.upload.bean.UploadResultBean
import io.reactivex.Observer
import okhttp3.RequestBody

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/6 1:51 PM
 * @Version TODO
 */
interface UploadApkContract {
    interface IModel : IBaseModel {
        fun uploadApk(params: RequestBody, observer: Observer<UploadResultBean>)
    }

    interface IView : IBaseView {
        fun sendResultToView(bean: UploadResultBean, params: RequestBody)
    }

    interface IPresenter : IBasePresenter {
        fun upload(params: RequestBody)
    }
}