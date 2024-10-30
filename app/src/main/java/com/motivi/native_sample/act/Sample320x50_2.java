package com.motivi.native_sample.act;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.motivi.native_sample.R;
import com.onnuridmc.exelbid.ExelBidNative;
import com.onnuridmc.exelbid.common.ExelBidError;
import com.onnuridmc.exelbid.common.NativeAsset;
import com.onnuridmc.exelbid.common.NativeViewBinder;
import com.onnuridmc.exelbid.common.OnAdNativeListener;

public class Sample320x50_2 extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private ExelBidNative mNativeAd;
    private View mNativeRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sample320x50_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView title = findViewById(R.id.title);
        title.setText(this.getClass().getSimpleName());

        // 네이티브 요청 객체를 생성한다.
        mNativeAd = new ExelBidNative(this, "b014ec22dccb43bfc35ea1e5051c83862d7357cf", new OnAdNativeListener() {

            @Override
            public void onFailed(ExelBidError error, int statusCode) {
                Log.d(TAG, "onFailed" + error.toString());
            }

            @Override
            public void onShow() {
                Log.d(TAG, "onShow");
            }

            @Override
            public void onClick() {
                Log.d(TAG, "onClick");
            }

            @Override
            public void onLoaded() {
                Log.d(TAG, "onLoaded");
                mNativeRootLayout.setVisibility(View.VISIBLE);
                mNativeAd.show();
            }
        });

        //네이티브 광고 데이터가 바인딩 될 뷰의 정보를 셋팅합니다.
        // Builder의 생성자에 바인딩 될 뷰와 각각의 항목을 넘기면 bindNativeAdData가 호출 될때
        // 광고가 바인딩 됩니다.
        // 바인딩 되지 않아도 되는 항목이 있을시 builder에 id를 셋팅하지 않으면 됩니다.
        mNativeRootLayout = findViewById(R.id.native_layout);
        mNativeAd.setNativeViewBinder(new NativeViewBinder.Builder(mNativeRootLayout)
                .mainImageId(R.id.native_main_image)
                .titleTextViewId(R.id.native_title)
                .textTextViewId(R.id.native_text)
                .iconImageId(R.id.native_icon_image)
                .adInfoImageId(R.id.native_privacy_information_icon_image)
                .build());
        // 네이티브 요청시 필수로 존재해야 하는 값을 셋팅한다. 해당 조건 셋팅으로 인해서 광고가 존재하지 않을 확률이 높아집니다.
        mNativeAd.setRequiredAsset(new NativeAsset[] {NativeAsset.TITLE, NativeAsset.ICON, NativeAsset.MAINIMAGE, NativeAsset.DESC});
        mNativeRootLayout.setVisibility(View.GONE);
        //광고를 요청한다.
        mNativeAd.loadAd();
    }
}