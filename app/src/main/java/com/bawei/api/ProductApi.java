package com.bawei.api;

import com.bawei.entity.UpLoadEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */
public interface ProductApi {

    /**
     * 单图
     * @param headers
     * @param file
     * @return
     */
    @POST(Api.UPLOAD_URL)
    @Multipart
    Observable<UpLoadEntity> getUpLoad(@HeaderMap HashMap<String, String> headers, @Part MultipartBody.Part file);

    /**
     * 多图
     * @param headers
     * @param file
     * @return
     */
    @POST(Api.UPLOAD_URL)
    @Multipart
    Observable<UpLoadEntity> getUpLoads(@HeaderMap HashMap<String, String> headers, @Part List<MultipartBody.Part> file);
}
