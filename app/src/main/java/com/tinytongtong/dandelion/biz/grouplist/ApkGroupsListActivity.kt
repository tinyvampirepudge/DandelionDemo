package com.tinytongtong.dandelion.biz.grouplist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion._api_key
import okhttp3.ResponseBody

/**
 * @Description: 查看所有已上传的apk列表
 *
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/9/4 12:05 PM
 */
class ApkGroupsListActivity : AppCompatActivity(), ApkGroupsListContract.IView {
    var mPresenter: ApkGroupsListContract.IPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apk_groups_list)

        mPresenter = ApkGroupsListPresenter(this)

        request()
    }

    fun request() {
        var params = hashMapOf("_api_key" to _api_key)
        mPresenter?.let {
            it.getDataFromNet(params)
        }
    }

    override fun sendResultToView(responseBody: ResponseBody, params: Map<String, String>) {
        Log.e("responseBody.string()：", responseBody.string())
    }

    override fun showLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMsg(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
