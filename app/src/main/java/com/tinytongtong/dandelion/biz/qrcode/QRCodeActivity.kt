package com.tinytongtong.dandelion.biz.qrcode

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tinytongtong.dandelion.R
import com.tinytongtong.dandelion.R.id.never
import com.tinytongtong.dandelion.common.util.ToastUtils
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_qrcode.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Description: 识别图片二维码
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/10/1 6:38 PM
 * @Version TODO
 */
class QRCodeActivity : AppCompatActivity() {
    private var loadSuccess: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        var qrcodeUrl: String? = intent.getStringExtra("qrcode_url")
        val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_default_pic)
                .error(R.drawable.ic_error)

        val rxPermissions: RxPermissions = RxPermissions(this)
        rxPermissions
                .requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(object : Consumer<Permission> {
                    override fun accept(permission: Permission) {
                        if (permission.granted) {
                            // `permission.name` is granted !
                            println("`permission.name` is granted !")
                            Glide.with(this@QRCodeActivity)
                                    .load(qrcodeUrl)
                                    .apply(options)
                                    .listener(object : RequestListener<Drawable> {
                                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                            Log.e(QRCodeActivity::javaClass.name, "onLoadFailed")
                                            return false
                                        }

                                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                            Log.e(QRCodeActivity::javaClass.name, "onResourceReady")
                                            loadSuccess = true
                                            return false
                                        }
                                    })
                                    .into(qr_code_iv)
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            println("Denied permission without ask never again")
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            println("Denied permission with ask never again, Need to go to the settings")
                        }
                    }

                })

        qr_code_iv.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                println("加载成功之后的长按事件")
                if (loadSuccess) {
                    val bitmapDrawable: BitmapDrawable = qr_code_iv.drawable as BitmapDrawable
                    val obmp: Bitmap = bitmapDrawable.bitmap
                    analysisBitmap(obmp)
                } else {
                    ToastUtils.showSingleToast(this@QRCodeActivity, "图片加载失败，请重试")
                }
                return true
            }
        })
    }

    /**
     * 分析bitmap，识别其内容是否有二维码，如果有的话，获取二维码信息
     */
    fun analysisBitmap(bitmap: Bitmap) {
        val width = bitmap.width
        val height = bitmap.height
        val data: IntArray = IntArray(width * height)
        bitmap.getPixels(data, 0, width, 0, 0, width, height)
        val source: RGBLuminanceSource = RGBLuminanceSource(width, height, data)
        val bitmap1: BinaryBitmap = BinaryBitmap(HybridBinarizer(source))
        val reader = QRCodeReader()
        var result: Result? = null
        try {
            result = reader.decode(bitmap1)
        } catch (e: NotFoundException) {
            e.printStackTrace()
        } catch (e: ChecksumException) {
            e.printStackTrace()
        } catch (e: FormatException) {
            e.printStackTrace()
        }
        if (result == null) {
            showAlert(bitmap)
        } else {
            showSelectAlert(bitmap, result.text)
        }
    }

    /**
     * 保存图片到本地
     */
    fun showAlert(bitmap: Bitmap) {
        val builder: AlertDialog = AlertDialog.Builder(this)
                .setMessage("保存到本地")
                .setCancelable(true)
                .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        saveImageToGallery(bitmap)
                    }
                })
                .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                .show()
    }

    /**
     * 保存图片，识别bitmap中二维码
     */
    fun showSelectAlert(bitmap: Bitmap, url: String) {
        val strs: Array<String> = arrayOf("保存到本地", "识别图中二维码");
        val builder: AlertDialog = AlertDialog.Builder(this)
                .setTitle("请选择")
                .setCancelable(true)
                .setItems(strs) { dialog, which ->
                    when (which) {
                        0 -> {
                            saveImageToGallery(bitmap)
                        }
                        1 -> {
                            println("识别图中二维码:${url}")
                            if (!TextUtils.isEmpty(url)) {
                                val intent = Intent();
                                intent.action = "android.intent.action.VIEW"
                                val content_url: Uri = Uri.parse(url)
                                intent.data = content_url
                                startActivity(intent)
                            }
                        }
                    }
                }
                .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        println("取消")
                    }
                })
                .show()
    }

    /**
     * 保存图片到手机相册
     */
    fun saveImageToGallery(bitmap: Bitmap) {
        // 首先保存图片
        val storePath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "dandelion"
        val appDir = File(storePath)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss",
                Locale.US)
        val fileName = sdf.format(Date()) + ".jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            //通过io流的方式来压缩保存图片
            val isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            val uri = Uri.fromFile(file)
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            return if (isSuccess) {
                println("保存成功")
            } else {
                println("保存失败")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}