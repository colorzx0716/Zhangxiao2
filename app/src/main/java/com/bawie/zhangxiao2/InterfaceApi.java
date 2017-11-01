package com.bawie.zhangxiao2;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 张肖肖 on 2017/10/30.
 */

public interface InterfaceApi {

    @GET("getCarts?uid=146")
    Call<Bean> getData();

}
