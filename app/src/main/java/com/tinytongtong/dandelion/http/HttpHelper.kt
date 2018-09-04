package com.tinytongtong.dandelion.http

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 2:56 PM
 */
class HttpHelper private constructor() {
    init {

    }

    companion object {
        fun getInstance(): HttpHelper {
            return HttpHelperKtHolder.instance
        }
    }

    private object HttpHelperKtHolder {
        val instance = HttpHelper()
    }


    /**
     * 得到retrofit代理接口
     *
     * @return
     */
    fun getRetrofitService(): RetrofitService {
        return getRetrofit().create(RetrofitService::class.java)
    }

    /**
     * 得到retrofit对象
     *
     * @return
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                //配置基础的url
                .baseUrl(BASE_URL)
                //配置提交或者返回的参数的造型方式为gson
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                //返回值可以使用Obserable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用https时需要配置
                .client(OkHttpClientUtils.getInstance())
                .build()
    }

    fun getApkGroupsList(params: Map<String, String>, observer: Observer<ResponseBody>) {
        getRetrofitService()
                .getAppGroupList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}