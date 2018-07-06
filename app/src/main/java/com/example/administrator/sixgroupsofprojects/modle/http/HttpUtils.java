package com.example.administrator.sixgroupsofprojects.modle.http;

import android.util.Log;

import com.example.administrator.sixgroupsofprojects.application.Constant;
import com.example.administrator.sixgroupsofprojects.modle.callback.HttpUtilsCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils implements Callback{
    private static HttpUtils INSTANCE;
    private final OkHttpClient okHttpClient;
    private HttpUtilsCallback mHttpUtilsCallback;

    /**
     * 单例模式
     */
    private HttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static HttpUtils getInstance(){
        if(INSTANCE == null) {
           INSTANCE = new HttpUtils();
        }
        return INSTANCE;
    }

    /**
     * 设置编码格式
     */
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * okhttp get请求
     * @param path
     * @return
     */
    public String doGet(String path) {

        Request request = new Request.Builder().url(Constant.BASE_URL + path).build();


        Call call = okHttpClient.newCall(request);

        call.enqueue(this);

        return "data ok";
    }

    /**
     * okhttp post请求
     * @param path url 相对路径
     * @param map 参数
     */

    public void doPost(String path,HashMap<String,String> map,HttpUtilsCallback httpUtilsCallback) {
        //String url = "https://www.zhaoapi.cn/product/searchProducts";
        this.mHttpUtilsCallback = httpUtilsCallback;
        FormBody.Builder builder =  new FormBody.Builder();

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            builder.add(key,value);
        }


        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(Constant.BASE_URL + path)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(this);

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String string = response.body().string();
        mHttpUtilsCallback.onSuccess(string);
        //Log.e("myMessage",""+string);
    }


/**
 * okhttp+Rxjava+retrofit请求
 */
//    public void  Signin(String regType,String name,String passwrod,LoginPre miLoginView){
//        this.miLoginView = miLoginView;
//        //添加token拦截器
//        Observable<Signin> observable = (Observable<Signin>) RetrofitUtils
//                .retorfitApi.login(regType,name,passwrod);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Signin>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//                    @Override
//                    public void onNext(Signin signin) {
//                        miLoginView.onLoginData(signin);
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

}
