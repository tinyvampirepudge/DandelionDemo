package com.tinytongtong.dandelion.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @Description: $desc$
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 2:36 PM
 */
public interface RetrofitService {
    //查看自己上传的应用
    @FormUrlEncoded
    @POST(UrlConfigKt.URL_APP_GROUPS_LIST)
    Observable<ResponseBody> getAppGroupList(@FieldMap Map<String, String> params);
}
