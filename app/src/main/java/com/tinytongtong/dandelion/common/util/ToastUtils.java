package com.tinytongtong.dandelion.common.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Created by tiny on 16/7/30.
 */
public class ToastUtils {

    private static Toast toast;//简单的toast

    /**
     * 单例toast,仅供测试用。提示信息 请用showBlankToast
     *
     * @param content
     */
    public static void showSingleToast(Context context, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showSingleToast(Context context, String content, int duration) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 弹出提示的toast.
     *
     * @param content 提示内容
     * @param showImg 是否隐藏错误提示img.true表示隐藏。
     */
    public static void showErrorToast(Context context, String content, boolean showImg) {
        showSingleToast(context, content);
    }
}
