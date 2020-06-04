package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Package    :com.example.myapplication.utils
 * ClassName  :Utils
 * Description:工具类
 * Data       :2020/3/24 13:42
 */
public class Utils {
  /**
   * drawable转bitmap
   * @param drawable
   *   drawable对象
   * @return bitmap对象
   */
  public static Bitmap drawable2Bitmap(Drawable drawable) {
    // 取 drawable 的长宽
    int w = drawable.getIntrinsicWidth();
    int h = drawable.getIntrinsicHeight();

    // 取 drawable 的颜色格式
    Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
      : Bitmap.Config.RGB_565;
    // 建立对应 bitmap
    Bitmap bitmap = Bitmap.createBitmap(w, h, config);
    // 建立对应 bitmap 的画布
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, w, h);
    // 把 drawable 内容画到画布中
    drawable.draw(canvas);
    return bitmap;
  }

  /**
   * 获取显示指标
   * @param context
   *   上下文
   * @return DsisplayMetrics
   */
  private static DisplayMetrics getDisplayMetrics(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    return displayMetrics;
  }

  /**
   * 获取屏幕的宽度
   * @param context
   *   上下文
   * @return WindowWidth
   */
  public static int getWindowWidth(Context context) {
    DisplayMetrics displayMetrics = getDisplayMetrics(context);
    if (displayMetrics != null)
      return displayMetrics.widthPixels;
    else
      return 0;
  }


  private static Toast toast = null;

  public static Toast getToast() {
    return toast;
  }

  public static void showToast(String msg) {
    if (toast == null)
      toast = Toast.makeText(MyApplication.mAppContext, msg, Toast.LENGTH_SHORT);
    else
      toast.setText(msg);
    toast.show();
  }


  /**
   * 格式化时间，自带格式
   * @param time
   *   时间
   * @return 格式化后的时间
   */
  public static String FormatTime(long time) {
    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    //        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    return format.format(new Date(time));
  }

  public static String getString(int id) {
    return MyApplication.mAppContext.getString(id);
  }

}
