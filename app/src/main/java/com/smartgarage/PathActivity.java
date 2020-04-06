package com.smartgarage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.smartgarage.adapter.PathAdapter;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.PathInfo;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.view.TitleView;

import java.util.List;


public class PathActivity extends AppCompatActivity {

    private ListView mPathListView;
    private TitleView mTitleView;
    private PathAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        init();
    }

    public void init() {

        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("路线列表");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PathActivity.this.finish();
            }
        });

        List<PathInfo> mPaths = DBManger.getInstance(this).mPaths;

        mAdapter = new PathAdapter(this,mPaths);
        mPathListView = findViewById(R.id.path_listview);
        mPathListView.setAdapter(mAdapter);
    }
}
