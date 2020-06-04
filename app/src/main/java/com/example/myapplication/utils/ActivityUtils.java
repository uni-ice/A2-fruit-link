package com.example.myapplication.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Package    : com.example.baseutils
 * Date       : 2018/12/20 9:11
 */
public class ActivityUtils {
    private static ActivityUtils   mBaseUtils;
    public static  Stack<Activity> mActivityList = new Stack<>();


    public static ActivityUtils getInstance() {
        if (null == mBaseUtils) {
            synchronized (ActivityUtils.class) {
                if (null == mBaseUtils) {
                    mBaseUtils = new ActivityUtils();
                }
            }
        }
        return mBaseUtils;
    }


    public void addActivity(Activity pActivity) {
        mActivityList.add(pActivity);
    }

    public void removeActivity(Activity pActivity) {
        if (pActivity != null) {
            mActivityList.remove(pActivity);
            pActivity.finish();
            pActivity = null;
        }
    }

    //获取栈顶Activity
    public static Activity getCurrentActivity() {
        if (mActivityList.size() == 0)
            return null;
        else
            return mActivityList.lastElement();
    }

    //获取Act数组长度
    public int getActivityListSize() {
        if (mActivityList != null)
            return mActivityList.size();
        else
            return 0;
    }

    /**
     * 通过类名关闭Act
     * @param className
     *         类名
     */
    public void UseNamefinishAct(String className) {
        Activity activity = null;
        for (Activity _ActivityRef : mActivityList) {
            try {
                if (_ActivityRef.getClass().getName().equals(className)) {
                    activity = _ActivityRef;
                    break;
                }
            } catch (NullPointerException ignored) {
            }
        }
        if (activity != null) {
            mActivityList.remove(activity);
            if (!activity.isDestroyed())
                activity.finish();
        }
    }

    /**
     * 通过类名获取Act
     * @param className
     *         类名
     */
    public static Activity UseNameGetAct(String className) {
        Activity activityWeakReference = null;
        for (Activity _ActivityRef : mActivityList) {
            try {
                if (_ActivityRef.getClass().getName().equals(className)) {
                    activityWeakReference = _ActivityRef;
                    break;
                }
            } catch (NullPointerException ignored) {
            }
        }
        assert activityWeakReference != null;
        return activityWeakReference;
    }

}
