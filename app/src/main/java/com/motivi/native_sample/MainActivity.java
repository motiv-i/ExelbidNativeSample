package com.motivi.native_sample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.motivi.native_sample.act.Sample300x250;
import com.motivi.native_sample.act.Sample320x100;
import com.motivi.native_sample.act.Sample320x50;
import com.motivi.native_sample.act.Sample320x50_2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<ActivityInfo> dataList = new ArrayList<>();
        dataList.add(new ActivityInfo(Sample320x50.class, "320x50"));
        dataList.add(new ActivityInfo(Sample320x50_2.class, "320x50 2"));
        dataList.add(new ActivityInfo(Sample320x100.class, "320x100"));
        dataList.add(new ActivityInfo(Sample300x250.class, "300x250"));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(dataList);
        adapter.setOnItemClickListener(position -> {
            ActivityInfo activityInfo = dataList.get(position);
            Intent intent = new Intent(MainActivity.this, activityInfo.activityClass);
            intent.putExtra("data", activityInfo.data); // 데이터 전달 (선택 사항)
            MainActivity.this.startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

}