1<?xml version="1.0" encoding="utf-8"?>
2<manifest xmlns:android="http://schemas.android.com/apk/res/android"
3    package="com.appveg.farmfamily"
4    android:versionCode="1"
5    android:versionName="1.0" >
6
7    <uses-sdk
8        android:minSdkVersion="15"
8-->G:\FarmFa\app\src\main\AndroidManifest.xml
9        android:targetSdkVersion="28" />
9-->G:\FarmFa\app\src\main\AndroidManifest.xml
10
11    <application
11-->G:\FarmFa\app\src\main\AndroidManifest.xml:5:5-22:19
12        android:allowBackup="true"
12-->G:\FarmFa\app\src\main\AndroidManifest.xml:6:9-35
13        android:appComponentFactory="androidx.core.app.CoreComponentFactory"
13-->[androidx.core:core:1.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\d6d54f3d63d7335e24b4d0ffa0e9e9e4\core-1.1.0\AndroidManifest.xml:24:18-86
14        android:debuggable="true"
15        android:icon="@mipmap/ic_launcher"
15-->G:\FarmFa\app\src\main\AndroidManifest.xml:7:9-43
16        android:label="@string/app_name"
16-->G:\FarmFa\app\src\main\AndroidManifest.xml:8:9-41
17        android:roundIcon="@mipmap/ic_launcher_round"
17-->G:\FarmFa\app\src\main\AndroidManifest.xml:9:9-54
18        android:supportsRtl="true"
18-->G:\FarmFa\app\src\main\AndroidManifest.xml:10:9-35
19        android:theme="@style/AppTheme" >
19-->G:\FarmFa\app\src\main\AndroidManifest.xml:11:9-40
20        <activity
20-->G:\FarmFa\app\src\main\AndroidManifest.xml:12:9-21:20
21            android:name="com.appveg.farmfamily.MainActivity"
21-->G:\FarmFa\app\src\main\AndroidManifest.xml:13:13-41
22            android:label="@string/app_name"
22-->G:\FarmFa\app\src\main\AndroidManifest.xml:14:13-45
23            android:theme="@style/AppTheme.NoActionBar" >
23-->G:\FarmFa\app\src\main\AndroidManifest.xml:15:13-56
24            <intent-filter>
24-->G:\FarmFa\app\src\main\AndroidManifest.xml:16:13-20:29
25                <action android:name="android.intent.action.MAIN" />
25-->G:\FarmFa\app\src\main\AndroidManifest.xml:17:17-69
25-->G:\FarmFa\app\src\main\AndroidManifest.xml:17:25-66
26
27                <category android:name="android.intent.category.LAUNCHER" />
27-->G:\FarmFa\app\src\main\AndroidManifest.xml:19:17-77
27-->G:\FarmFa\app\src\main\AndroidManifest.xml:19:27-74
28            </intent-filter>
29        </activity>
30
31        <provider
31-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\0e654394adb5140559059dead572df03\lifecycle-process-2.1.0\AndroidManifest.xml:23:9-27:43
32            android:name="androidx.lifecycle.ProcessLifecycleOwnerInitializer"
32-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\0e654394adb5140559059dead572df03\lifecycle-process-2.1.0\AndroidManifest.xml:24:13-79
33            android:authorities="com.appveg.farmfamily.lifecycle-process"
33-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\0e654394adb5140559059dead572df03\lifecycle-process-2.1.0\AndroidManifest.xml:25:13-69
34            android:exported="false"
34-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\0e654394adb5140559059dead572df03\lifecycle-process-2.1.0\AndroidManifest.xml:26:13-37
35            android:multiprocess="true" />
35-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\NGUYEN HOANG VU\.gradle\caches\transforms-2\files-2.1\0e654394adb5140559059dead572df03\lifecycle-process-2.1.0\AndroidManifest.xml:27:13-40
36    </application>
37
38</manifest>
