package com.tinytongtong.dandelion.biz.buildslist

import android.util.Log
import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.grouplist.ApkGroupsListContract
import com.tinytongtong.dandelion.biz.grouplist.ApkGroupsListModel
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
class BuildsListPresenter(iView: BuildsListContract.IView) : BuildsListContract.IPresenter {
    var view = iView

    val iModel: BuildsListContract.IModel = BuildsListModel()

    override fun getDataFromNet(params: Map<String, String>) {
        iModel.getBuildList(params, object : Observer<BuildsListBean> {
            override fun onComplete() {
                Log.e("", "")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BuildsListBean) {
                view.sendResultToView(t, params)
            }

            override fun onError(e: Throwable) {

            }

        })
    }
}