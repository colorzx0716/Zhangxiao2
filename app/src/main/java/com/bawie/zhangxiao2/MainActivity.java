package com.bawie.zhangxiao2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private InterfaceApi interfaceApi;
    private Retrofit retrofit;
    private Call<Bean> context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl(Api.C_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interfaceApi = retrofit.create(InterfaceApi.class);
        context = interfaceApi.getData();
        context.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if(response != null && response.isSuccessful()&& response.body()!= null){
                    List<Bean.DataBean> data = response.body().getData();
                    for (int i = 0; i < data.size(); i++) {
                        List<Bean.DataBean.ListBean> list = data.get(i).getList();
                        for (int j = 0; j < list.size(); j++) {
                            String title = list.get(j).getTitle();
                            System.out.println("请求的title = " + title);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });



    }

}
