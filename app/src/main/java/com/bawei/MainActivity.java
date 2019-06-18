package com.bawei;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.api.ProductApi;
import com.bawei.entity.UpLoadEntity;
import com.bawei.net.RetrofitUtils;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private Unbinder bind;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
    }

    @OnClick(R.id.img)
    public void upload(View view) {
        Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
        //判断是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建文件
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

            //创建文件请求体对象
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            //多表单上传的工具类
            MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("userId","6404");
            headers.put("sessionId","15608661597066404");
            RetrofitUtils.getUtils().createService(ProductApi.class).getUpLoad(headers, imgPart).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpLoadEntity>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(UpLoadEntity upLoadEntity) {
                    Toast.makeText(MainActivity.this, upLoadEntity.message, Toast.LENGTH_SHORT).show();
                }


                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
