package com.tinytongtong.dandelion.biz.detail

import com.tinytongtong.dandelion.biz.detail.bean.AppDetailBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/29 2:33 PM
 * @Version TODO
 */
class AppDetailPresenter(iView: AppDetailContract.IView) : AppDetailContract.IPresenter {
    var view = iView
    val iModel: AppDetailContract.IModel = AppDetailModel()
    override fun getDataFromNet(params: Map<String, String>) {
        iModel.getAppDetailInfo(params, object : AppDetailContract, Observer<AppDetailBean> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: AppDetailBean) {
                view.sendResultToView(t, params)
            }

            override fun onError(e: Throwable) {

            }

        })
    }
}