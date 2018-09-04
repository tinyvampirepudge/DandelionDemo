package com.tinytongtong.dandelion.biz.grouplist

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
class ApkGroupsListModel : ApkGroupsListContract.IModel {
    override fun getApkGroupsList(params: Map<String, String>, observer: Observer<ResponseBody>) {
        HttpHelper.getInstance().getApkGroupsList(params, observer)
    }
}