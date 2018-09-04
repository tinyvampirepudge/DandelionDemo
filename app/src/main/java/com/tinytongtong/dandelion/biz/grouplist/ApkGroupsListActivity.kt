package com.tinytongtong.dandelion.biz.grouplist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.appKey
import com.tinytongtong.dandelion.biz.buildslist.BuildsListActivity
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean
import com.tinytongtong.dandelion.buildKey
import com.tinytongtong.dandelion.common.util.LogUtils
import com.tinytongtong.dandelion.common.util.ToastUtils
import kotlinx.android.synthetic.main.activity_apk_groups_list.*
import java.util.*

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
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

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
            val adapter = ApkGroupsListAdapter(R.layout.adapter_apk_groups_list, bean.data.list)
//            var adapter = ApkGroupsListAdapterKt(this, bean.data.list)

            adapter.setOnItemClickListener { adapter, view, position ->
                val bean = bean.data.list[position]
                val intent = Intent(this, BuildsListActivity::class.java)
                intent.putExtra("name", bean.buildName)
                intent.putExtra(appKey, bean.appKey)
                intent.putExtra(buildKey, bean.buildKey)
                startActivity(intent)
            }
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
