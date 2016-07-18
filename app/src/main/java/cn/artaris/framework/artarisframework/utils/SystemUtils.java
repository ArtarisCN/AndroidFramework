/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package cn.artaris.framework.artarisframework.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;

/**
 * @author kymjs (https://github.com/kymjs)
 */
@SuppressLint("SimpleDateFormat")
public final class SystemUtils {

    /**
     * 调用系统发送短信
     * @param context
     * @param smsBody
     */
    public static void sendSMS(Context context, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        context.startActivity(intent);
    }

    /**
     * 跳转到拨号
     * @param activity
     * @param phoneNumber
     */
    public static void forwardToDial(Activity activity, String phoneNumber) {
        if (activity != null && !TextUtils.isEmpty(phoneNumber)) {
            activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 直接呼叫号码
     * @param activity
     * @param phoneNumber
     */
    public static void callPhone(Activity activity, String phoneNumber) {
        if (activity != null && !TextUtils.isEmpty(phoneNumber)) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 发邮件
     * @param mContext
     * @param mailID
     */
    public static void sendMail(Context mContext, String mailID) {
        Uri uri = Uri.parse("mailto:" + mailID);
        mContext.startActivity(new Intent(Intent.ACTION_SENDTO, uri));
    }

    /**
     * 隐藏系统键盘
     * @param activity
     */
    public static void hideKeyBoard(Activity activity) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断当前应用程序是否后台运行
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    /**
     * 判断手机是否处理睡眠
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return kgMgr.inKeyguardRestrictedInputMode();
    }

    /**
     * 安装apk
     * @param context
     * @param apkfile
     */
    public static void installApk(Context context, File apkfile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(apkfile));
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    static final String suSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};

    /**
     * 是否root
     */
    public static boolean isRooted() {
        File file;
        boolean flag1 = false;
        for (String suSearchPath : suSearchPaths) {
            file = new File(suSearchPath + "su");
            if (file.isFile() && file.exists()) {
                flag1 = true;
                break;
            }
        }
        return flag1;
    }

    /**
     * 当前设备是否是模拟器
     * @return
     */
    public static boolean isRunningOnEmulator() {
        return Build.BRAND.contains("generic")
                || Build.DEVICE.contains("generic")
                || Build.PRODUCT.contains("sdk")
                || Build.HARDWARE.contains("goldfish")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
    }

    /**
     * 获取当前应用程序的版本名称
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取当前应用程序的版本号
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int version = 0;
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 返回Home
     * @param context
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * 获取应用签名
     * @param context
     * @param pkgName
     * @return
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo pis = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
            return hexdigest(pis.signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            throw new RuntimeException(SystemUtils.class.getName() + "the " + pkgName + "'s application not found");
        }
    }

    /**
     * 32位签名
     * @param paramArrayOfByte
     * @return
     */
    public static String hexdigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0; ; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取设备可用空间
     * @param context
     * @return
     */
    public static int getDeviceUsableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return (int) (mi.availMem / (1024 * 1024));
    }

    /**
     * 清理后台进程和服务
     * @param context
     * @return
     */
    public static int gc(Context context) {
        //long i = getDeviceUsableMemory(context);
        int count = 0;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> serviceList = am.getRunningServices(100);
        if (serviceList != null)
            for (RunningServiceInfo service : serviceList) {
                if (service.pid == android.os.Process.myPid())
                    continue;
                try {
                    android.os.Process.killProcess(service.pid);
                    count++;
                } catch (Exception e) {
                    e.getStackTrace();
                    continue;
                }
            }

        List<RunningAppProcessInfo> processList = am.getRunningAppProcesses();
        if (processList != null)
            for (RunningAppProcessInfo process : processList) {
                if (process.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkgList = process.pkgList;
                    for (String pkgName : pkgList) {
                        try {
                            am.killBackgroundProcesses(pkgName);
                            count++;
                        } catch (Exception e) {
                            e.getStackTrace();
                            continue;
                        }
                    }
                }
            }
        return count;
    }

    /**
     * 创建桌面快捷方式
     * @param context
     * @param shortCutName
     * @param icon
     * @param cls
     */
    public static void createDeskShortCut(Context context, String shortCutName, int icon, Class<?> cls) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        Parcelable ico = Intent.ShortcutIconResource.fromContext(context.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(context, cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        context.sendBroadcast(shortcutIntent);
    }

    /**
     * 创建快捷方式
     * @param context
     * @param shortCutName
     * @param iconId
     * @param presentIntent
     */
    public static void createShortcut(Context context, String shortCutName, int iconId, Intent presentIntent) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, iconId));
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, presentIntent);
        context.sendBroadcast(shortcutIntent);
    }

    /**
     * 分享文本
     * @param context
     * @param title
     * @param text
     */
    public static void shareText(Context context, String title, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
       /* List<ResolveInfo> ris = getShareTargets(context);
        if (ris != null && ris.size() > 0) {
            context.startActivity(Intent.createChooser(intent, title));
        }*/
    }

    /**
     * 分享文件(此方法是调用FileUtils.shareFile中的方式)
     * @param context
     * @param title
     * @param filePath
     */
    public static void shareFile(Context context, String title, String filePath) {
        FileUtils.shareFile(context, title, filePath);
    }

    /**
     * 获取可接受分享的应用
     * @param context
     * @return
     */
    public static List<ResolveInfo> getShareTargets(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        PackageManager pm = context.getPackageManager();
        return pm.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    }

    /**
     * 获取当前系统的语言
     * @return
     */
    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统的语言
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        if (context != null) {
            return context.getResources().getConfiguration().locale.getLanguage();
        }
        return null;
    }

    /**
     *  GPS是否打开
     * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    /**
     * 显示软键盘
     * @param context
     * @param editText
     */
    public static void showSoftInputMethod(Context context, EditText editText) {
        if (context != null && editText != null) {
            editText.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }

    /**
     * 关闭软键盘
     * @param context
     * @param editText
     */
    public static void closeSoftInputMethod(Context context, EditText editText) {
        if (context != null && editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     * @param context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 关闭软键盘
     * @param context
     */
    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}