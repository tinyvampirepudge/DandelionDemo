package com.tinytongtong.dandelion.biz.grouplist

import android.util.Log
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 3:21 PM
 */
class ApkGroupsListPresenter(iView: ApkGroupsListContract.IView) : ApkGroupsListContract.IPresenter {
    var view = iView

    val iModel: ApkGroupsListContract.IModel = ApkGroupsListModel()

    override fun getDataFromNet(params: Map<String, String>) {
        iModel.getApkGroupsList(params, object : Observer<ApkGroupsListBean> {
            override fun onComplete() {
                Log.e("", "")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ApkGroupsListBean) {
                view.sendResultToView(t, params)
            }

            override fun onError(e: Throwable) {

            }

        })
    }
}