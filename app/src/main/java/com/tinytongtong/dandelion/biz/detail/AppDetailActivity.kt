package com.tinytongtong.dandelion.biz.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import com.bumptech.glide.Glide
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.biz.detail.bean.AppDetailBean
import com.tinytongtong.dandelion.biz.qrcode.QRCodeActivity
import com.tinytongtong.dandelion.common.util.LogUtils
import com.tinytongtong.dandelion.common.util.ToastUtils
import kotlinx.android.synthetic.main.activity_app_detail.*


/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/29 3:00 PM
 * @Version TODO
 */
class AppDetailActivity : AppCompatActivity(), AppDetailContract.IView {

    var mPresenter: AppDetailPresenter? = null
    var title: String? = null
    var appKey: String? = null
    var buildKey: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_detail)

        title = intent.getStringExtra("name")
        appKey = intent.getStringExtra("appKey")
        buildKey = intent.getStringExtra("buildKey")

        tv_title.setText(title)

        mPresenter = AppDetailPresenter(this)

        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setOnRefreshListener {
            request()
        }

        request()
    }

    fun request() {
        var params = HashMap<String, String>()
        appKey?.let { params.put("appKey", it) }
        buildKey?.let { params.put("buildKey", it) }
        mPresenter?.getDataFromNet(params)
    }

    override fun sendResultToView(bean: AppDetailBean, params: Map<String, String>) {
        if (bean.data != null) {
            refreshLayout.finishRefresh(true)
            app_detail_name.setText("应用名称：${bean.data.buildName}")
            app_detail_type.setText("应用类型：${getBuildType(bean.data.buildType)}")
            app_detail_version.setText("版本号：${bean.data.buildVersion}")
            app_detail_version_no.setText("上传的版本编号：${bean.data.buildVersionNo}")
            app_detail_build_version.setText("蒲公英生成的用于区分历史版本的build号：${bean.data.buildBuildVersion}")
            app_detail_identifier.setText("包名：${bean.data.buildIdentifier}")
            if (!TextUtils.isEmpty(bean.data.buildIcon)) {
                app_detail_icon_tv.setText("应用图标：")
                app_detail_icon.visibility = View.VISIBLE
                Glide.with(this)
                        .load("https://www.pgyer.com/image/view/app_icons/${bean.data.buildIcon}")
                        .into(app_detail_icon)
            } else {
                app_detail_icon_tv.setText("应用图标：——")
                app_detail_icon.visibility = View.GONE
            }
            app_detail_file_size.setText("文件大小：${bean.data.buildFileSize}")
            app_detail_description.setText(if (!TextUtils.isEmpty(bean.data.buildDescription)) "${bean.data.buildDescription}" else "——")
            app_detail_update_description.setText(if (!TextUtils.isEmpty(bean.data.buildUpdateDescription)) "${bean.data.buildUpdateDescription}" else "——")

            if (!TextUtils.isEmpty(bean.data.buildScreenshots) && bean.data.buildScreenshots.split(",").size > 0) {
                app_detail_screen_shots_tv.setText("应用截图：")
                app_detail_screen_shots_rv.visibility = View.VISIBLE

                val screenShotLists: List<String> = bean.data.buildScreenshots.split(",")
                val adapter: AppDetailScreenShotAdapter = AppDetailScreenShotAdapter(R.layout.adapter_app_detail_screen_shot, screenShotLists)
                app_detail_screen_shots_rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                app_detail_screen_shots_rv.adapter = adapter
            } else {
                app_detail_screen_shots_tv.setText("应用截图：——")
                app_detail_screen_shots_rv.visibility = View.GONE
            }

            if (!TextUtils.isEmpty(bean.data.buildShortcutUrl)) {
                val string = "应用短链接：https://www.pgyer.com/${bean.data.buildShortcutUrl}"
                val startIndex = "应用短链接：".length
                val spannableStringBuilder = SpannableStringBuilder(string)
                //下划线
                val underlineSpan = UnderlineSpan()
                spannableStringBuilder.setSpan(underlineSpan, startIndex, string.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                //设置点击事件
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(view: View) {
                        try {
                            val intent = Intent();
                            intent.action = "android.intent.action.VIEW"
                            val content_url: Uri = Uri.parse("https://www.pgyer.com/${bean.data.buildShortcutUrl}")
                            intent.data = content_url
                            startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                spannableStringBuilder.setSpan(clickableSpan, startIndex, string.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                if (app_detail_shortcut_url != null) {
                    app_detail_shortcut_url.setMovementMethod(LinkMovementMethod.getInstance())
                }
                app_detail_shortcut_url.setText(spannableStringBuilder)
            } else {
                app_detail_shortcut_url.setText("应用短链接：——")
            }
            if (!TextUtils.isEmpty(bean.data.buildQRCodeURL)) {
                val string = "应用二维码地址：${bean.data.buildQRCodeURL}"
                val startIndex = "应用二维码地址：".length
                val spannableStringBuilder = SpannableStringBuilder(string)
                //下划线
                val underlineSpan = UnderlineSpan()
                spannableStringBuilder.setSpan(underlineSpan, startIndex, string.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                //设置点击事件
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(view: View) {
                        try {
                            val intent = Intent();
                            intent.action = "android.intent.action.VIEW"
                            val content_url: Uri = Uri.parse("${bean.data.buildQRCodeURL}")
                            intent.data = content_url
                            startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                spannableStringBuilder.setSpan(clickableSpan, startIndex, string.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                if (app_detail_qr_code_url != null) {
                    app_detail_qr_code_url.setMovementMethod(LinkMovementMethod.getInstance())
                }
                app_detail_qr_code_url.setText(spannableStringBuilder)

                app_detail_qr_code_url_iv.visibility = View.VISIBLE
                Glide.with(this).load(bean.data.buildQRCodeURL).into(app_detail_qr_code_url_iv)
                app_detail_qr_code_url_iv.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        val qrCodeIntent = Intent(this@AppDetailActivity, QRCodeActivity::class.java)
                        qrCodeIntent.putExtra("qrcode_url", bean.data.buildQRCodeURL)
                        startActivity(qrCodeIntent)
                    }

                })
            } else {
                app_detail_qr_code_url.setText("应用二维码地址：——")
                app_detail_qr_code_url_iv.visibility = View.GONE
            }
            app_detail_create_time.setText("应用上传时间：${bean.data.buildCreated}")
            app_detail_update_time.setText("应用更新时间：${bean.data.buildUpdated}")
        } else {
            refreshLayout.finishRefresh(false)
            ToastUtils.showSingleToast(this, "返回数据有误，请重试！")
        }
    }

    private fun getBuildType(type: Int): String {
        return if (1 == type) {
            "IOS"
        } else if (2 == type) {
            "Android"
        } else {
            type.toString()
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