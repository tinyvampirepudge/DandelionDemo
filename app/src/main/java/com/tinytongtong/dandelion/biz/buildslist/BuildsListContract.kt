package com.tinytongtong.dandelion.biz.buildslist

import com.tinytongtong.dandelion.base.IBaseModel
import com.tinytongtong.dandelion.base.IBasePresenter
import com.tinytongtong.dandelion.base.IBaseView
import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean
import io.reactivex.Observer
import okhttp3.ResponseBody

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 3:17 PM
 */
interface BuildsListContract {
    interface IModel : IBaseModel {
        fun getBuildList(params: Map<String, String>, observer: Observer<BuildsListBean>)
    }

    interface IView : IBaseView {
        fun sendResultToView(bean: BuildsListBean, params: Map<String, String>)
    }

    interface IPresenter : IBasePresenter {
        fun getDataFromNet(params: Map<String, String>)
    }
}