package com.tinytongtong.dandelion.biz.grouplist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.R.id.recyclerView
import com.tinytongtong.dandelion._api_key_value
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean
import com.tinytongtong.dandelion.common.util.LogUtils
import com.tinytongtong.dandelion.common.util.ToastUtils
import kotlinx.android.synthetic.main.activity_apk_groups_list.*
import okhttp3.ResponseBody
import java.util.HashMap

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

        refreshLayout.setEnableLoadMore(false)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mPresenter = ApkGroupsListPresenter(this)

        refreshLayout.setOnRefreshListener {
            request()
        }

        request()// 第一次进入自动刷新
    }

    fun request() {
        var params = HashMap<String, String>()
        mPresenter?.let {
            it.getDataFromNet(params)
        }
    }

    override fun sendResultToView(bean: ApkGroupsListBean, params: Map<String, String>) {
        Log.e("responseBody.string()：", bean.toString())
        if (0 == bean.code && bean.data != null && bean.data.list != null && bean.data.list.size > 0) {
            refreshLayout.finishRefresh(true)
            bean.data.list.forEach {
                LogUtils.e(it.buildName)
            }
            var adapter = ApkGroupsListAdapter(R.layout.adapter_apk_groups_list,bean.data.list)
//            var adapter = ApkGroupsListAdapterKt(this, bean.data.list)
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
