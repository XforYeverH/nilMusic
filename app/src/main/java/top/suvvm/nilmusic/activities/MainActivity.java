package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(false, "nilMusic", true);

    }
}
