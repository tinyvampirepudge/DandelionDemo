package com.tinytongtong.dandelion.biz.buildslist

import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean
import com.tinytongtong.dandelion.http.HttpHelper
import io.reactivex.Observer
import okhttp3.ResponseBody

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 3:20 PM
 */
class BuildsListModel : BuildsListContract.IModel {
    override fun getBuildList(params: Map<String, String>, observer: Observer<BuildsListBean>) {
        HttpHelper.getInstance().getBuildsList(params, observer)
    }
}