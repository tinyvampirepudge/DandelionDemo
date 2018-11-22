package com.tinytongtong.dandelion.biz.upload

import com.tinytongtong.dandelion.biz.upload.bean.UploadResultBean
import com.tinytongtong.dandelion.http.HttpHelper
import io.reactivex.Observer
import okhttp3.RequestBody

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/6 2:02 PM
 * @Version TODO
 */
class UploadApkModel : UploadApkContract.IModel {
    override fun uploadApk(params: RequestBody, observer: Observer<UploadResultBean>) {
        HttpHelper.getInstance().uploadApk(params, observer)
    }

}