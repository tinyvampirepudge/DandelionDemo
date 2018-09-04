package com.tinytongtong.dandelion.http

import com.tinytongtong.dandelion._api_key
import com.tinytongtong.dandelion._api_key_value
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 2:58 PM
 */
class OkHttpClientUtils private constructor() {
    init {

    }

    companion object {
        fun getInstance(): OkHttpClient {
            return OkHttpClientUtilsKtHolder.instance
        }
    }

    private object OkHttpClientUtilsKtHolder {
        val instance = init()

        private fun init(): OkHttpClient {
            var builder = OkHttpClient().newBuilder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                                .newBuilder()
//                            .addHeader("User-Agent", UserAgentUtils.getAgentParams())
                                .build()
                        chain.proceed(request)
                    }.connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS)

            builder = addParamsToClient(builder)
            return builder.build()
        }


        /**
         * 给okhttp添加一些数据。方法可以改成拦截器
         *
         * @param builder
         * @return
         */
        fun addParamsToClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
            //添加公共参数apiversion
            var basicParamsInterceptor = BasicParamsInterceptor.Builder()
                    .addQueryParam(_api_key, _api_key_value)
                    .addParam(_api_key, _api_key_value)
                    .build()
            return builder.addInterceptor(basicParamsInterceptor)
        }
    }

}