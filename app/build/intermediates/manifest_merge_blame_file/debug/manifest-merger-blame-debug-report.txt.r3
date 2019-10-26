1<?xml version="1.0" encoding="utf-8"?>
2<manifest xmlns:android="http://schemas.android.com/apk/res/android"
3    package="com.appveg.farmfamily"
4    android:versionCode="1"
5    android:versionName="1.0" >
6
7    <uses-sdk
8        android:minSdkVersion="15"
8-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml
9        android:targetSdkVersion="28" />
9-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml
10
11    <application
11-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:5:5-42:19
12        android:allowBackup="true"
12-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:6:9-35
13        android:appComponentFactory="androidx.core.app.CoreComponentFactory"
13-->[androidx.core:core:1.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\35f11e87cb9373d0196416cee400ce7f\core-1.1.0\AndroidManifest.xml:24:18-86
14        android:debuggable="true"
15        android:icon="@mipmap/ic_launcher"
15-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:7:9-43
16        android:label="@string/app_name"
16-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:8:9-41
17        android:roundIcon="@mipmap/ic_launcher_round"
17-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:9:9-54
18        android:supportsRtl="true"
18-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:10:9-35
19        android:testOnly="true"
20        android:theme="@style/AppTheme" >
20-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:11:9-40
21        <activity
21-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:12:9-17:20
22            android:name="com.appveg.farmfamily.MainActivity"
22-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:13:13-41
23            android:label="@string/app_name"
23-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:14:13-45
24            android:theme="@style/AppTheme.NoActionBar" >
24-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:15:13-56
25        </activity>
26        <activity
26-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:19:9-27:20
27            android:name="com.appveg.farmfamily.ui.login.LoginActivity"
27-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:20:13-72
28            android:label="@string/app_name"
28-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:21:13-45
29            android:theme="@style/AppTheme.NoActionBar" >
29-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:22:13-56
30            <intent-filter>
30-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:23:13-26:29
31                <action android:name="android.intent.action.MAIN" />
31-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:24:17-69
31-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:24:25-66
32
33                <category android:name="android.intent.category.LAUNCHER" />
33-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:25:17-77
33-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:25:27-74
34            </intent-filter>
35        </activity>
36        <activity
36-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:29:9-34:20
37            android:name="com.appveg.farmfamily.ui.login.SignUpActivity"
37-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:30:13-73
38            android:label="@string/app_name"
38-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:31:13-45
39            android:theme="@style/AppTheme.NoActionBar" >
39-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:32:13-56
40        </activity>
41        <activity
41-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:36:9-41:20
42            android:name="com.appveg.farmfamily.ui.login.ForgotPPass"
42-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:37:13-70
43            android:label="@string/app_name"
43-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:38:13-45
44            android:theme="@style/AppTheme.NoActionBar" >
44-->C:\Users\16130\AndroidStudioProjects\FarmFaMaster\app\src\main\AndroidManifest.xml:39:13-56
45        </activity>
46
47        <provider
47-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\ca2ce8a01ce231ab9c2242651f772e4b\lifecycle-process-2.1.0\AndroidManifest.xml:23:9-27:43
48            android:name="androidx.lifecycle.ProcessLifecycleOwnerInitializer"
48-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\ca2ce8a01ce231ab9c2242651f772e4b\lifecycle-process-2.1.0\AndroidManifest.xml:24:13-79
49            android:authorities="com.appveg.farmfamily.lifecycle-process"
49-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\ca2ce8a01ce231ab9c2242651f772e4b\lifecycle-process-2.1.0\AndroidManifest.xml:25:13-69
50            android:exported="false"
50-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\ca2ce8a01ce231ab9c2242651f772e4b\lifecycle-process-2.1.0\AndroidManifest.xml:26:13-37
51            android:multiprocess="true" />
51-->[androidx.lifecycle:lifecycle-process:2.1.0] C:\Users\16130\.gradle\caches\transforms-2\files-2.1\ca2ce8a01ce231ab9c2242651f772e4b\lifecycle-process-2.1.0\AndroidManifest.xml:27:13-40
52    </application>
53
54</manifest>