package com.droidupdate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.droidupdate.jni.PatchUtil;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final String tag = "MainActivity";


    /**
     * git 测试2
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//		init();
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new String[]{"111",
                "222", "333", "444", "555", "666", "777","888","999"});
        listView.setAdapter(adapter);
    }

    private void init() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "apk.patch");
            File newFile = new File(Environment.getExternalStorageDirectory(), "apkTmp.apk");
            if (file.exists()) {
                int ret = PatchUtil.applyPatchToOwn(getApplicationContext(), newFile.getAbsolutePath(), file.getAbsolutePath());
                if (ret == 0) {
                    // 删除patch文件 开始安装新的APK文件
                    file.delete();
                    update(newFile);
                }
            } else {

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void update(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (file.exists()) {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "安装失败,安装文件未找到", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 升级
     */
    public void onUpdate(View view) {
        switch (view.getId()) {
            case R.id.update:

                init();
//                testDownLoad();
                break;
//            case R.id.updated:
//                Toast.makeText(MainActivity.this, "已经升级了", Toast.LENGTH_LONG).show();
//                break;
        }


    }



}
