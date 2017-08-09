package krelve.demo.rob;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import krelve.demo.rob.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MyAccessibilityService";

	public static String  starupapp = "starupapp";
	public static String  killBroadcastflag = "killBroadcastflag";
	public static String  longPressMessageReceiverFlag = "longPressMessageReceiverFlag";
	
	
	 PowerManager.WakeLock mWakeLock = null;
	private BroadcastReceiver ackMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			mWakeLock.acquire();  
			doStartApplicationWithPackageName("com.tencent.mm");
			
			
		}
	};
	
	/**
	 * 处理长按时间
	 */
	private BroadcastReceiver longPressMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
//			inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//					SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 200, 200, 0));
			
			
		}
	};
	
	private BroadcastReceiver killBroadcast = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			mWakeLock.acquire();  
			getRunningServiceInfo(context, "com.tencent.mm");
			try {
//				ActivityManager am =  (ActivityManager) context  
//		                .getSystemService(Context.ACTIVITY_SERVICE);
//				 Method forceStopPackage = am.getClass().getDeclaredMethod("forceStopPackage", String.class);  
//				 forceStopPackage.setAccessible(true);  
//				 forceStopPackage.invoke(am, "com.tencent.mm");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String phoneName = android.os.Build.MODEL;
		String brand = Build.BRAND;
		String bm = Build.MODEL;//手机型号(MI XXX)
		String bm2 = Build.SERIAL;//硬件序列号serial
		String bm3 = Build.PRODUCT;//手机制造商
		String bm4 = Build.MANUFACTURER;//硬件制造商
		String bm5 =Build.RADIO;//无线电固件版本 radio
		String bm6 =Build.FINGERPRINT;//硬件识别码
		String bm7 = Build.HARDWARE;//硬件名称hardware
		String bm10 = Build.CPU_ABI;//cpu指令集
		String bm8 = Build.CPU_ABI2;//cpu指令集2
		String bm9 = Build.BOARD;  //主板 
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		String sim = tm.getSimSerialNumber();
		String imsi =( (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		Log.i(TAG,phoneName+" | "+brand +" | "+bm +" | "+bm2 +" | "+bm3 +"|  "+bm4 +" | "+bm5 +" | "+bm6 +" | "+bm7 
				+" | "+bm8 +" | "+bm9 +" | "+bm10
				+" | "+deviceId +" | "+sim +" | "+imsi +" | ");
		IntentFilter dynamic_filter = new IntentFilter();
        dynamic_filter.addAction(starupapp);            //添加动态广播的Action
        registerReceiver(ackMessageReceiver, dynamic_filter);  // 注册自定义动态广播消息
        
        IntentFilter dynamic_filter2 = new IntentFilter();
        dynamic_filter2.addAction(killBroadcastflag);            //添加动态广播的Action
        registerReceiver(killBroadcast, dynamic_filter2);  // 注册自定义动态广播消息
        
        IntentFilter dynamic_filter3 = new IntentFilter();
        dynamic_filter3.addAction(longPressMessageReceiverFlag);            //添加动态广播的Action
        registerReceiver(longPressMessageReceiver, dynamic_filter3);  // 注册自定义动态广播消息
        
        
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        
       mWakeLock.acquire();
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_start).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						openServiceSetting();
					}
				});

		String apkRoot="chmod 777 "+getPackageCodePath();
        RootCommand(apkRoot);
        runRootCommand(apkRoot);
		upgradeRootPermission(getPackageCodePath());
	}

	private void openServiceSetting() {
		try {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到抢红包服务，开启即可", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean upgradeRootPermission(String pkgCodePath) {
		Process process = null;
		DataOutputStream os = null;
		try {
			String cmd = "chmod 777 " + pkgCodePath;
			process = Runtime.getRuntime().exec("su"); // 切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	public static boolean runRootCommand(String command) {
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(command + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			Log.d(TAG,
					"the device is not rooted,  error message： "
							+ e.getMessage());
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (process != null) {
					process.destroy();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean RootCommand(String command) {
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(command + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}
	
	 /***
	  * 根据包名启动其它应用
	  */
	 public  void doStartApplicationWithPackageName(String packagename) {  
		  
		    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
		    PackageInfo packageinfo = null;  
		    try {  
		        packageinfo = getPackageManager().getPackageInfo(packagename, 0);  
		    } catch (NameNotFoundException e) {  
		        e.printStackTrace();  
		    }  
		    if (packageinfo == null) {  
		        return;  
		    }  
		  
		    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
		    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
		    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
		    resolveIntent.setPackage(packageinfo.packageName);  
		  
		    // 通过getPackageManager()的queryIntentActivities方法遍历  
		    List<ResolveInfo> resolveinfoList = getPackageManager()  
		            .queryIntentActivities(resolveIntent, 0);  
		  
		    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
		    if (resolveinfo != null) {  
		        // packagename = 参数packname  
		        String packageName = resolveinfo.activityInfo.packageName;  
		        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]  
		        String className = resolveinfo.activityInfo.name;  
		        // LAUNCHER Intent  
		        Intent intent = new Intent(Intent.ACTION_MAIN);  
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
		  
		        // 设置ComponentName参数1:packagename参数2:MainActivity路径  
		        ComponentName cn = new ComponentName(packageName, className);  
		  
		        intent.setComponent(cn);  
		        startActivity(intent);  
		    }  
	}  
	 

	    public static void getRunningServiceInfo(Context context ,String packageName) {  
	        ActivityManager mActivityManager = (ActivityManager) context  
	                .getSystemService(Context.ACTIVITY_SERVICE);  
	        // 通过调用ActivityManager的getRunningAppServicees()方法获得系统里所有正在运行的进程  
	        List<ActivityManager.RunningServiceInfo> runServiceList = mActivityManager  
	                .getRunningServices(50);  
	        System.out.println(runServiceList.size());  
	        // ServiceInfo Model类 用来保存所有进程信息  
	        for (ActivityManager.RunningServiceInfo runServiceInfo : runServiceList) {  
	            ComponentName serviceCMP = runServiceInfo.service;  
	            String serviceName = serviceCMP.getShortClassName(); // service 的类名  
	            String pkgName = serviceCMP.getPackageName(); // 包名  
	              
	            if (pkgName.equals(packageName)) {  
	            	mActivityManager.killBackgroundProcesses(packageName);
//	                mActivityManager.forceStopPackage(packageName);  
	                mActivityManager.killBackgroundProcesses(packageName);  
	                  
	            }  
	  
	        }  
	    }  
	    
	    @Override
	    protected void onDestroy() {
	    	// TODO Auto-generated method stub
	    	unregisterReceiver(ackMessageReceiver);
	    	unregisterReceiver(longPressMessageReceiver);
	    	unregisterReceiver(killBroadcast);
	    	
	    	super.onDestroy();
	    }

}
