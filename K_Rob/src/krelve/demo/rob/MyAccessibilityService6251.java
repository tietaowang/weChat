//package krelve.demo.rob;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.net.SocketTimeoutException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import android.R.integer;
//import android.accessibilityservice.AccessibilityService;
//import android.accessibilityservice.AccessibilityServiceInfo;
//import android.app.Instrumentation;
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.ResolveInfo;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.os.SystemClock;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//import android.widget.Toast;
//
//enum CommandType {
//	norun, // 没有任何操作
//	login, // 登陆
//	quit, // 退出
//}
//
//public class MyAccessibilityService6251 extends AccessibilityService {
//
//	private static final String TAG = "MyAccessibilityService";
//	private MyThread thread = null;
//	static public CommandType commandType;
//	
//    public HashMap<String, String> serverData = new HashMap<String, String>();
//	
//	@Override
//	public void onCreate() {
//		// TODO Auto-generated method stub
//		super.onCreate();
//		thread = new MyThread();
//		thread.myAccessibilityService = this;
//		thread.start();
//	}
//
//	@Override
//	protected void onServiceConnected() {
//		 super.onServiceConnected();
//		Log.i(TAG, "onServiceConnected");
//       
//	}
//
//	String description = "";
//
//	@Override
//	public void onAccessibilityEvent(AccessibilityEvent event) {
//	   //  commandType =  CommandType.login;
//		
//		if (commandType == CommandType.norun) {
//			return;
//		}
//		// 微信UI界面的根节点，开始遍历节点
//		AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
//		if (rootNodeInfo == null) {
//			return;
//		}
//		if (rootNodeInfo.getContentDescription() != null) {
//			description = rootNodeInfo.getContentDescription().toString();
//			Log.i(TAG, description);
//
//		}
//
//		if (commandType == CommandType.login) {
//			goLogin(rootNodeInfo, event);
//		}
//		if (commandType == CommandType.quit) {
//			goQuit(rootNodeInfo, event);
//		}
//
//	}
//
//	@Override
//	public void onInterrupt() {
//		Log.i(TAG, "onInterrupt");
//
//	}
//
//	/***
//	 * 登陆方法
//	 */
//	public void goLogin(AccessibilityNodeInfo rootNodeInfo,
//			AccessibilityEvent event) {
// 
//		// 1. 启动微信 找到 登陆 按钮
//		List<AccessibilityNodeInfo> loginButtons = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bn4");
//		Log.i(TAG, "第一次进登陆界面 登陆 数量:" + loginButtons.size());
//		// 5.找到更多按钮
//		List<AccessibilityNodeInfo> moreInfo = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/at4");
//		Log.i(TAG, "更多 数量:" + moreInfo.size());
//		// 6.切换账号按钮
//		List<AccessibilityNodeInfo> changeBtnInfo = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/dv");
//		Log.i(TAG, "切换账号按钮数量:" + changeBtnInfo.size());
//		// 7.使用其他方式登录按钮
//		List<AccessibilityNodeInfo> changeWayInfo = rootNodeInfo
//				.findAccessibilityNodeInfosByText("使用其他方式登录");
//		Log.i(TAG, "使用其他方式登录按钮数量:" + changeWayInfo.size());
//		// 8.到微信登陆界面开始登陆
//		List<AccessibilityNodeInfo> logintitleInfo = rootNodeInfo
//				.findAccessibilityNodeInfosByText("登录微信");
//		Log.i(TAG, "登陆界面标题框数量:" + logintitleInfo.size());
//		
//		List<AccessibilityNodeInfo> loginbtns = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/asr");
//		Log.i(TAG, " 登陆 数量:" + loginbtns.size());
//
//		// 9. 新手机号 第一次需要 验证
//		List<AccessibilityNodeInfo>  verifyFrists = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b3l");
//		Log.i(TAG, "需要身份验证弹出 数量:" + verifyFrists.size());
//		// 10.到微信登陆界面开始登陆
//		List<AccessibilityNodeInfo> goverifybuttons = rootNodeInfo
//						.findAccessibilityNodeInfosByText("手机不在身边或无法扫码？");
//		Log.i(TAG, "手机不在身边或无法扫码？ 数量:" + goverifybuttons.size());
//		
//		// 11. 手机不在身边吗》
//		 List<AccessibilityNodeInfo> phoneNotSides = rootNodeInfo
//						.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bmo");
//		 Log.i(TAG, " 手机不在身边? 数量:" + phoneNotSides.size());
//		        
//			
//		if (logintitleInfo.size() != 0 && loginbtns.size() != 0) {
//			List<AccessibilityNodeInfo> loginInputInfo = rootNodeInfo
//					.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/f0");
//			if (loginInputInfo.size() != 0) {
//				if (loginInputInfo.get(0) != null
//						&& loginInputInfo.get(0).getClassName()
//								.equals("android.widget.EditText")) {
//					if(serverData.get("mobile") != null){
//						Bundle arguments = new Bundle();
//						Log.i(TAG, "输入账号");
//						arguments.putCharSequence(
//										AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//										serverData.get("mobile") );
//						loginInputInfo.get(0).performAction(
//								AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//					}
//					
//				}
//				if (loginInputInfo.get(1) != null
//						&& loginInputInfo.get(1).getClassName()
//								.equals("android.widget.EditText")) {
//					if(serverData.get("pwd") != null){
//						Bundle arguments = new Bundle();
//						Log.i(TAG, "输入密码");
//						arguments.putCharSequence(
//										AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//										serverData.get("pwd"));
//						loginInputInfo.get(1).performAction(
//								AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//						loginbtns.get(0).performAction(
//								AccessibilityNodeInfo.ACTION_CLICK);
//					}
//				}
//			}
//		} else if (changeWayInfo.size() != 0) {
//			Log.i(TAG, "点击使用其他方式登录按钮");
//			changeWayInfo.get(0).performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (changeBtnInfo.size() != 0) {
//			Log.i(TAG, "点击切换账号按钮");
//			changeBtnInfo.get(0).getParent()
//					.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (loginButtons.size() != 0) {
//			loginButtons.get(0).performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (moreInfo.size() != 0) {
//			Log.i(TAG, "点击 更多 按钮");
//			moreInfo.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (verifyFrists.size() != 0) {
//			Log.i(TAG, "点击  身份验证");
//			verifyFrists.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//		}else if (phoneNotSides.size() != 0) {
//			Log.i(TAG, " 点击 手机不在身边?");
//			phoneNotSides.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}else {
//			webViewPage(event);
//		}
//	}
//
//	public void webViewPage(AccessibilityEvent event){
//		if("android.webkit.WebView".equals(event.getClassName())){
//			AccessibilityNodeInfo info =event.getSource();
//			Log.i(TAG,"  webviwe 里面数量 "+info.getChildCount() );
//			List<AccessibilityNodeInfo>  comfirmLogins =  info.findAccessibilityNodeInfosByText("确认登录");
//			Log.i(TAG," 当成窗口名称  "+ info.getContentDescription());
//			if(info.getChildCount() > 3 &&info.getContentDescription() != null && info.getContentDescription().equals("确认登录")){
//				AccessibilityNodeInfo  fourNode =	info.getChild(4);
//				if(fourNode != null){
//					Log.i(TAG,"  第四个 数量 "+fourNode.getChildCount() );
//					fourNode.getChild(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//				}
//			}else if(info.getChildCount() > 3 &&info.getContentDescription() != null && info.getContentDescription().equals("好友辅助安全登录验证")){
//				AccessibilityNodeInfo  fourNode =	info.getChild(4);
//				Log.i(TAG,"  好友辅助安全登录验证 验证码 "+fourNode.getContentDescription());
//			}else if(info.getChildCount() < 3 && info.getContentDescription() != null && info.getContentDescription().equals("好友辅助安全登录验证")){
//				AccessibilityNodeInfo  node =	info.getChild(0);
//				if(node != null && node.getChildCount() > 3){
//					AccessibilityNodeInfo  subnode  = node.getChild(3);
//					if(subnode !=null){
//						AccessibilityNodeInfo  subsubnode  = subnode.getChild(0);
//						if(subsubnode != null){
//							if(node != null && subnode != null && subsubnode != null && subsubnode.getClassName()
//									.equals("android.widget.EditText") && subsubnode.getContentDescription() != null &&
//									subsubnode.getContentDescription().equals("手机号")&& subsubnode.getText() == null){
//								Bundle arguments = new Bundle();
//								Log.i(TAG, " 输入验证手机号  ");
//								arguments.putCharSequence(
//												AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//												serverData.get("mobile") );
//								subsubnode.performAction(
//										AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//							}
//						}
//					}
//				}else{
//					if(node != null){
//					//	node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//					}
//				}
//				
//			}
//		}
//	}
//	/***
//	 * 退出 方法
//	 */
//	public void goQuit(AccessibilityNodeInfo rootNodeInfo,
//			AccessibilityEvent event) {
//		// 3.退出当前账号按钮
//		List<AccessibilityNodeInfo> quiteNowCounts = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bch");
//		Log.i(TAG, "退出当前账号按钮数量:" + quiteNowCounts.size());
//		// 9 我
//		List<AccessibilityNodeInfo> tabbarButtons = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b3n");
//		Log.i(TAG, "tabbar 数量:" + tabbarButtons.size());
//		// 10 设置
//		List<AccessibilityNodeInfo> myPages = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/g7");
//		AccessibilityNodeInfo settingButton = NodeUtils.getNode(myPages, "设置");
//		// 11 退出
//		List<AccessibilityNodeInfo> quitButtons = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/g7");
//		AccessibilityNodeInfo quitButton = NodeUtils.getNode(myPages, "退出");
//		Log.i(TAG, "退出 数量:" + settingButton);
//		// 12 确定退出
//		List<AccessibilityNodeInfo> confirmQuitButtons = rootNodeInfo
//				.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b3l");
//		Log.i(TAG, "确定退出 数量:" + confirmQuitButtons.size());
//		if (quiteNowCounts.size() != 0) {
//			Log.i(TAG, "点击小退按钮");
//			// 点击退出当前账号
//			quiteNowCounts.get(0).performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (tabbarButtons.size() != 0 && settingButton == null) {
//			Log.i(TAG, "点击 我");
//			tabbarButtons.get(tabbarButtons.size() - 1).getParent()
//					.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (settingButton != null) {
//			Log.i(TAG, "点击 设置");
//			settingButton.getParent().performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (quitButton != null) {
//			Log.i(TAG, "点击 退出");
//			quitButton.getParent().performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		} else if (confirmQuitButtons.size() != 0) {
//			Log.i(TAG, "点击 确定退出");
//			confirmQuitButtons.get(0).performAction(
//					AccessibilityNodeInfo.ACTION_CLICK);
//		}
//	}
//	
//	
//	 
//	
//
//}
//
//class MyThread extends Thread {
//	private static final String TAG = "FxService";
//	private ObjectOutputStream out;
//	private BufferedReader in;
//	private Socket socket;
//	public Handler hander;
//	public MyAccessibilityService6251 myAccessibilityService;
//	@Override
//	public void run() {
//		// 定义消息
//		Message msg = new Message();
//		msg.what = 0x11;
//		Bundle bundle = new Bundle();
//		bundle.clear();
//		try {
//			// 连接服务器 并设置连接超时为5秒
//			socket = new Socket("192.168.0.102", 8995);
//			// socket.connect(new InetSocketAddress("192.168.0.102", 8995));
//			OutputStream ou = socket.getOutputStream();
//			in = new BufferedReader(new InputStreamReader(
//					socket.getInputStream()));
//			ReadThread readTh = new ReadThread();
//			readTh.start();
//			// 发送数据 暂时不做
//			ou.write("wtt:88|5559|王铁涛2|8888".getBytes("utf-8"));
//			ou.flush();
//		} catch (SocketTimeoutException aa) {
//			// 连接超时 在UI界面显示消息
//			bundle.putString("msg", "服务器连接失败！请检查网络是否打开");
//			msg.setData(bundle);
//			// 发送消息 修改UI线程中的组件
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//class ReadThread extends Thread {
//
//		boolean runFlag = true;
//
//		public void run() {
//			try {
//				// in= new BufferedInputStream(
//				// socket.getInputStream());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			if (socket.isClosed()) {
//				return;
//			}
//			try {
//				String line = null;
//
//				while ((line = in.readLine()) != null) {
//					Log.i(TAG, "=======" + line);
//					if (line.contains("wttbk")) {
//						if (line.contains("login")) {
//							//手机号 login |123242|wpd|
//							ArrayList<String> inof = NodeUtils.regexText("\\[(.*?)\\]", line);
//							if(inof.size() > 1){
//								myAccessibilityService.serverData.put("mobile", inof.get(0));
//								myAccessibilityService.serverData.put("pwd", inof.get(1));
//							}
//							myAccessibilityService.commandType = CommandType.login;
//						} else if (line.contains("quit")) {
//							myAccessibilityService.commandType = CommandType.quit;
//						}else if(line.contains("startup")){
//							Intent intent = new Intent();
//							intent.setAction("starupapp");
//							myAccessibilityService.sendBroadcast(intent);
//						}else if(line.contains("home")){
//							myAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
//						}else if(line.contains("kill")){
//							Intent intent = new Intent();
//							intent.setAction("killBroadcastflag");
//							myAccessibilityService.sendBroadcast(intent);
//						}else if(line.contains("back")){
//							myAccessibilityService.commandType = CommandType.norun;
//							ArrayList<String> execut = NodeUtils.regexText("\\[(.*?)\\]", line);
//							if(execut.size() > 0){
//								 int  backs = Integer.parseInt(execut.get(0));
//								for(int i = 0;i < backs;i++){
//									myAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
//								}
//							}
//						}
//						else {
//							myAccessibilityService.commandType = CommandType.norun;
//						}
//						// Log.d("!!!!", "click");
//						// Instrumentation inst = new Instrumentation();
//						// Log.d("!!!!", "click");
//						// inst.sendPointerSync(MotionEvent.obtain(
//						// SystemClock.uptimeMillis(),
//						// SystemClock.uptimeMillis(),
//						// MotionEvent.ACTION_DOWN, 200, 200, 0));
//						// Log.d("!!!!", "click");
//						// inst.sendPointerSync(MotionEvent.obtain(
//						// SystemClock.uptimeMillis(),
//						// SystemClock.uptimeMillis(),
//						// MotionEvent.ACTION_UP, 200, 200, 0));
//						// Log.d("!!!!", "click");
//						// inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
//					}
//
//				}
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		public void exit() {
//			runFlag = false;
//		}
//}
//
//}