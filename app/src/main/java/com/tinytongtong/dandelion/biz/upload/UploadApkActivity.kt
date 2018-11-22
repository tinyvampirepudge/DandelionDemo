package com.tinytongtong.dandelion.biz.upload

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.biz.upload.bean.UploadResultBean
import com.tinytongtong.dandelion.common.util.ToastUtils
import com.tinytongtong.dandelion.common.util.UriUtils
import kotlinx.android.synthetic.main.activity_upload_apk.*
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

/**
 * @Description: 上传apk页面
 *
 * @Author wangjianzhou@qding.me
 * @Version
 * @Date 2018/9/4 12:03 PM
 */
class UploadApkActivity : AppCompatActivity(), UploadApkContract.IView {
    private val TAG = this::class.java.simpleName

    private val request_code: Int = 1000
    private var selectSuccess = false
    private var selectedFileUri: Uri? = null

    private var mPresenter: UploadApkPresenter? = null

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_apk)

        mPresenter = UploadApkPresenter(this)

        btn_select_file.setOnClickListener {
            selectFile()
        }

        btn_start_upload.setOnClickListener({
            if (selectSuccess) {
                val path = UriUtils.getPath(this,selectedFileUri)
                var apkFile: File = File(path)
                var params = RequestBody.create(MediaType.parse("multipart/form-data"), apkFile)
                mPresenter?.upload(params)
            }
        })
    }

    fun selectFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, request_code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (request_code == requestCode) {
                val uri: Uri? = data?.data
                if (!TextUtils.isEmpty(uri?.path)) {
                    selectSuccess = true
                    selectedFileUri = uri
                    tv_file_info.setText("文件信息：\n${uri?.path}")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun sendResultToView(bean: UploadResultBean, params: RequestBody) {
        println("${TAG}, sendResultToView:${bean.toString()}")
    }

    override fun showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setTitle("上传apk")
            progressDialog?.setMessage("uploading...")
            progressDialog?.setCancelable(true)
        }
        progressDialog?.show()
    }

    override fun dismissLoadingDialog() {
        progressDialog?.dismiss()
    }

    override fun showErrorMsg(errorMsg: String) {
        ToastUtils.showSingleToast(this, errorMsg)
        tv_file_info.setText("错误信息${errorMsg}")
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog?.dismiss()
        progressDialog = null
    }
}
