package com.tinytongtong.dandelion.biz.buildslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.appKey
import com.tinytongtong.dandelion.biz.buildslist.bean.BuildsListBean
import com.tinytongtong.dandelion.biz.grouplist.ApkGroupsListContract
import com.tinytongtong.dandelion.buildKey
import com.tinytongtong.dandelion.common.util.LogUtils
import com.tinytongtong.dandelion.common.util.ToastUtils
import kotlinx.android.synthetic.main.activity_builds_list.*

/**
 * @Description: 获取App所有版本
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/4 9:17 PM
 * @Version TODO
 */
class BuildsListActivity : AppCompatActivity(), BuildsListContract.IView {
    var mPresenter: BuildsListContract.IPresenter? = null
    var title: String? = null
    var appKey: String? = null
    var buildKey: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_builds_list)

        title = intent.getStringExtra("name")
        appKey = intent.getStringExtra("appKey")
        buildKey = intent.getStringExtra("buildKey")

        tv_title.setText(title)
        LogUtils.e("appKey:$appKey")
        LogUtils.e("buildKey:$buildKey")

        mPresenter = BuildsListPresenter(this)

        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setOnRefreshListener {
            request()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        request()
    }

    fun request() {
        var params = HashMap<String, String>()
        appKey?.let { params.put("appKey", it) }
        buildKey?.let { params.put("buildKey", it) }
        mPresenter?.getDataFromNet(params)
    }

    override fun sendResultToView(bean: BuildsListBean, params: Map<String, String>) {
        if (bean.data != null && bean.data.list != null && bean.data.list.size > 0) {
            refreshLayout.finishRefresh(true)
            bean.data.list.forEach {
                LogUtils.e(it.buildName)
            }

            val adapter = BuildsListAdapter(R.layout.adapter_builds_list, bean.data.list)
            recyclerView.adapter = adapter
        } else {
            refreshLayout.finishRefresh(false)
            ToastUtils.showSingleToast(this, "返回数据有误，请重试！")
        }
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
