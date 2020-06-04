package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.utils.GetMusic;
import com.example.myapplication.utils.MusicUtils;
import com.example.myapplication.utils.SpUtils;
import com.example.myapplication.utils.StringUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;


public class MainActivity extends BaseActivity {
  private TextView tv_start, tv_set, tv_about_game, tv_about_me, tv_exit;
  private MusicUtils sqlTools;

  @Override
  int getLayoutID() {
    return R.layout.activity_main;
  }

  @Override
  void initView() {
    sqlTools = new MusicUtils(this);
    tv_start = findViewById(R.id.Main_Start);
    tv_set = findViewById(R.id.Main_Setting);
    tv_about_game = findViewById(R.id.Main_AboutGame);
    tv_about_me = findViewById(R.id.Main_AboutMe);
    tv_exit = findViewById(R.id.Main_Exit);
    requestPermission();
    //开始游戏
    tv_start.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, StartGameActivity.class));
      }
    });

    //游戏设置
    tv_set.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));
      }
    });

    //关于游戏
    tv_about_game.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
      }
    });

    //关于我们点击事件
    tv_about_me.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
      }
    });

    //退出游戏点击事件
    tv_exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MainActivity.this.finish();
      }
    });
  }

  //请求内存卡权限
  void requestPermission() {
    AndPermission.with(this).permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
      .onGranted(new Action() {
        @Override
        public void onAction(List<String> permissions) {
          SpUtils.putBoolean(StringUtils.SD, true);
          Log.e("tag", "是否调用查询音乐：");
          if (!sqlTools.isExists()) {
            GetMusic.Query(MainActivity.this);
          }
        }
      }).onDenied(new Action() {
      @Override
      public void onAction(List<String> permissions) {
        SpUtils.putBoolean(StringUtils.SD, true);
      }
    }).start();
  }


  public static void reStart(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
