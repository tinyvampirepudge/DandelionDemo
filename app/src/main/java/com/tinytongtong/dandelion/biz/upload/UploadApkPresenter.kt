package com.tinytongtong.dandelion.biz.upload

import com.tinytongtong.dandelion.biz.upload.bean.UploadResultBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.RequestBody

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/6 2:03 PM
 * @Version TODO
 */
class UploadApkPresenter(iView: UploadApkContract.IView) : UploadApkContract.IPresenter {
    private val TAG = this::class.java.simpleName
    var view = iView
    val model = UploadApkModel()

    override fun upload(params: RequestBody) {
        model.uploadApk(params, object : Observer<UploadResultBean> {
            override fun onComplete() {
                println("${TAG}, onComplete")
                view.dismissLoadingDialog()
                view.showErrorMsg("上传成功")
            }

            override fun onSubscribe(d: Disposable) {
                println("${TAG}, onSubscribe")
                view.showLoadingDialog()
            }

            override fun onNext(t: UploadResultBean) {
                println("${TAG}, onNext")
                view.sendResultToView(t, params)
            }

            override fun onError(e: Throwable) {
                println("${TAG}, onError")
                view.showErrorMsg("上传失败")
            }
        })
    }

}