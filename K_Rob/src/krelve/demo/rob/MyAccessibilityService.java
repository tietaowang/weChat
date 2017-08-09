package krelve.demo.rob;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyAccessibilityService extends AccessibilityService
{
  private static final String TAG = "MyAccessibilityService";
  public static CommandType commandType;
  String description = "";
  public int linkIndex = 0;
  public String perlikes = "";
  public AccessibilityNodeInfo prelines;
  public boolean secondRegister = false;
  public HashMap<String, String> serverData = new HashMap();
  public ArrayList<StepModel> taskList = new ArrayList();
  private MyThread thread = null;
  public UserModel userModel;

  public void addMyFriends(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
    Log.i("MyAccessibilityService", " tabbar 数量:" + localList1.size());
    AccessibilityNodeInfo localAccessibilityNodeInfo = NodeUtils.getNodeBydiscrip(paramAccessibilityNodeInfo, "搜索");
    Log.i("MyAccessibilityService", " 搜索 数量:" + localAccessibilityNodeInfo);
    List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("搜索");
    Log.i("MyAccessibilityService", " 搜索输入框 数量:" + localList2.size());
    if ((localList1.size() > 0) && (localAccessibilityNodeInfo != null))
    {
      Log.i("MyAccessibilityService", " 点击 搜索" + localAccessibilityNodeInfo);
      localAccessibilityNodeInfo.performAction(16);
    }
    while (localList2.size() <= 0)
      return;
    Bundle localBundle = new Bundle();
    Log.i("MyAccessibilityService", "输入微信号");
    localBundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("openid"));
    ((AccessibilityNodeInfo)localList2.get(0)).performAction(2097152, localBundle);
  }

  public void goLogin(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cjk");
    Log.i("MyAccessibilityService", "第一次进登陆界面 登陆 数量:" + localList1.size());
    List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/at4");
    Log.i("MyAccessibilityService", "更多 数量:" + localList2.size());
    List localList3 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("切换帐号");
    Log.i("MyAccessibilityService", "切换账号按钮数量:" + localList3.size());
    List localList4 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("用微信号/QQ号/邮箱登录");
    Log.i("MyAccessibilityService", "用微信号/QQ号/邮箱登录  数量:" + localList4.size());
    List localList5 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("微信号/QQ/邮箱登录");
    Log.i("MyAccessibilityService", " 请填写微信号/QQ号/邮箱 数量" + localList5.size());
    List localList6 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bhj");
    Log.i("MyAccessibilityService", " 登陆 数量:" + localList6.size());
    List localList7 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b3l");
    Log.i("MyAccessibilityService", " 需要身份验证弹出 数量:" + localList7.size());
    List localList8 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("手机不在身边或无法扫码？");
    Log.i("MyAccessibilityService", "手机不在身边或无法扫码？ 数量:" + localList8.size());
    List localList9 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("你在新手机登录微信，我们需要验证你的身份。");
    Log.i("MyAccessibilityService", " 验证手机号 数量:" + localList9.size());
    List localList10 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("看看手机通讯录里谁在使用微信？（不保存通讯录的任何资料，仅使用特征码作匹配识别）");
    Log.i("MyAccessibilityService", "看看手机通讯录里谁在使用微信？  数量:" + localList10.size());
    List localList11 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
    Log.i("MyAccessibilityService", "tabbar 数量:" + localList11.size());
    List localList12 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("用短信验证码登录");
    Log.i("MyAccessibilityService", "用短信验证码登录  数量:" + localList12.size());
    if (localList5.size() != 0)
    {
      List localList16 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/h2");
      if (localList16.size() != 0)
      {
        if ((localList16.get(0) != null) && (((AccessibilityNodeInfo)localList16.get(0)).getClassName().equals("android.widget.EditText")) && (this.serverData.get("mobile") != null))
        {
          Bundle localBundle2 = new Bundle();
          Log.i("MyAccessibilityService", "输入账号");
          localBundle2.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("mobile"));
          ((AccessibilityNodeInfo)localList16.get(0)).performAction(2097152, localBundle2);
        }
        if ((localList16.get(1) != null) && (((AccessibilityNodeInfo)localList16.get(1)).getClassName().equals("android.widget.EditText")) && (this.serverData.get("pwd") != null))
        {
          Bundle localBundle1 = new Bundle();
          Log.i("MyAccessibilityService", "输入密码");
          localBundle1.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("pwd"));
          ((AccessibilityNodeInfo)localList16.get(1)).performAction(2097152, localBundle1);
          ((AccessibilityNodeInfo)localList6.get(0)).performAction(16);
        }
      }
    }
    do
    {
      AccessibilityNodeInfo localAccessibilityNodeInfo;
      do
      {
        List localList13;
        do
        {
          while (true)
          {
            return;
            if (localList4.size() != 0)
            {
              Log.i("MyAccessibilityService", "点击使用其他方式登录按钮");
              ((AccessibilityNodeInfo)localList4.get(0)).performAction(16);
              return;
            }
            if (localList3.size() != 0)
            {
              Log.i("MyAccessibilityService", "点击切换账号按钮");
              ((AccessibilityNodeInfo)localList3.get(0)).getParent().performAction(16);
              return;
            }
            if (localList1.size() != 0)
            {
              ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
              return;
            }
            if (localList2.size() != 0)
            {
              Log.i("MyAccessibilityService", "点击 更多 按钮");
              ((AccessibilityNodeInfo)localList2.get(0)).performAction(16);
              return;
            }
            if (localList7.size() != 0)
            {
              Log.i("MyAccessibilityService", "点击  身份验证");
              ((AccessibilityNodeInfo)localList7.get(0)).performAction(16);
              return;
            }
            if (localList9.size() != 0)
            {
              List localList14 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("手机不在身边？");
              Log.i("MyAccessibilityService", " 点击 手机不在身边?" + localList14.size());
              if (localList14.size() > 0)
                ((AccessibilityNodeInfo)localList14.get(0)).performAction(16);
              try
              {
                Thread.sleep(2000L);
                List localList15 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("确定");
                if (localList15.size() > 0)
                {
                  Log.i("MyAccessibilityService", " 点击 确定 " + localList15.size());
                  ((AccessibilityNodeInfo)localList15.get(0)).performAction(16);
                  return;
                }
              }
              catch (InterruptedException localInterruptedException)
              {
                while (true)
                  localInterruptedException.printStackTrace();
              }
            }
          }
          if (localList10.size() <= 0)
            break;
          localList13 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("是");
        }
        while ((localList13 == null) || (localList13.size() <= 0));
        Log.i("MyAccessibilityService", " 点击 是 " + localList13.size());
        ((AccessibilityNodeInfo)localList13.get(0)).performAction(16);
        return;
        if (localList12.size() <= 0)
          break;
        localAccessibilityNodeInfo = NodeUtils.getNodeBydiscrip(paramAccessibilityNodeInfo, "更多");
      }
      while (localAccessibilityNodeInfo == null);
      Log.i("MyAccessibilityService", " 点击 更多 " + localAccessibilityNodeInfo);
      localAccessibilityNodeInfo.performAction(16);
      return;
    }
    while (localList11.size() != 0);
    webViewPage(paramAccessibilityEvent);
  }

  public void goQuit(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bch");
    Log.i("MyAccessibilityService", "退出当前账号按钮数量:" + localList1.size());
    List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
    Log.i("MyAccessibilityService", "tabbar 数量:" + localList2.size());
    AccessibilityNodeInfo localAccessibilityNodeInfo1 = NodeUtils.getNode(paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/i7"), "设置");
    Log.i("MyAccessibilityService", "设置 数量:" + localAccessibilityNodeInfo1);
    AccessibilityNodeInfo localAccessibilityNodeInfo2 = NodeUtils.getNode(paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/i7"), "退出");
    Log.i("MyAccessibilityService", "退出 数量:" + localAccessibilityNodeInfo2);
    List localList3 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aes");
    Log.i("MyAccessibilityService", "确定退出 数量:" + localList3.size());
    if (localList1.size() != 0)
    {
      Log.i("MyAccessibilityService", "点击小退按钮");
      ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
    }
    do
    {
      return;
      if ((localList2.size() != 0) && (localAccessibilityNodeInfo1 == null))
      {
        Log.i("MyAccessibilityService", "点击 我");
        ((AccessibilityNodeInfo)localList2.get(-1 + localList2.size())).getParent().performAction(16);
        return;
      }
      if (localAccessibilityNodeInfo1 != null)
      {
        Log.i("MyAccessibilityService", "点击 设置");
        localAccessibilityNodeInfo1.getParent().performAction(16);
        return;
      }
      if (localAccessibilityNodeInfo2 != null)
      {
        Log.i("MyAccessibilityService", "点击 退出");
        localAccessibilityNodeInfo2.getParent().performAction(16);
        return;
      }
    }
    while (localList3.size() == 0);
    Log.i("MyAccessibilityService", "点击 确定退出");
    ((AccessibilityNodeInfo)localList3.get(0)).performAction(16);
  }

  public void h5Vote(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo1 = NodeUtils.getNodeByRootContanisClass(paramAccessibilityNodeInfo, "android.webkit.WebView");
    if (localAccessibilityNodeInfo1 != null)
      Log.i("MyAccessibilityService", " webview step1  " + localAccessibilityNodeInfo1.getClassName());
    int i;
    if (("android.webkit.WebView".equals(paramAccessibilityEvent.getClassName())) || (localAccessibilityNodeInfo1 != null))
    {
      i = 0;
      if (i < this.taskList.size());
    }
    else
    {
      return;
    }
    AccessibilityNodeInfo localAccessibilityNodeInfo2 = NodeUtils.getNodeBydiscrip(paramAccessibilityNodeInfo, ((StepModel)this.taskList.get(i)).describe);
    if ((localAccessibilityNodeInfo2 != null) && (!((StepModel)this.taskList.get(i)).Status))
    {
      ((StepModel)this.taskList.get(i)).Status = true;
      Log.i("MyAccessibilityService", " 点击 step" + localAccessibilityNodeInfo2.getClassName() + "   " + ((StepModel)this.taskList.get(i)).describe + "  ");
      if (((StepModel)this.taskList.get(i)).action != 32)
        break label298;
      Rect localRect = new Rect();
      localAccessibilityNodeInfo2.getBoundsInScreen(localRect);
      localRect.left = (100 + localRect.left);
      localRect.top = (100 + localRect.top);
      LongPressModel localLongPressModel = new LongPressModel();
      localLongPressModel.rect = localRect;
      localLongPressModel.pressTimer = ((StepModel)this.taskList.get(i)).pressTiem;
      longPress(localLongPressModel);
    }
    while (true)
    {
      i++;
      break;
      label298: localAccessibilityNodeInfo2.performAction(((StepModel)this.taskList.get(i)).action);
    }
  }

  public void longPress(final LongPressModel paramLongPressModel)
  {
    new Thread()
    {
      public void run()
      {
        Instrumentation localInstrumentation = new Instrumentation();
        localInstrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, paramLongPressModel.rect.left, paramLongPressModel.rect.top, 0));
        try
        {
          Thread.sleep(3000L);
          localInstrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, paramLongPressModel.rect.left, paramLongPressModel.rect.top, 0));
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    .start();
  }

  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = getRootInActiveWindow();
    if (localAccessibilityNodeInfo == null);
    List localList;
    do
    {
      return;
      if ((localAccessibilityNodeInfo != null) && (localAccessibilityNodeInfo.getContentDescription() != null))
      {
        this.description = localAccessibilityNodeInfo.getContentDescription().toString();
        Log.i("MyAccessibilityService", "描述====" + this.description);
      }
      if (commandType == CommandType.login)
        goLogin(localAccessibilityNodeInfo, paramAccessibilityEvent);
      if (commandType == CommandType.quit)
        goQuit(localAccessibilityNodeInfo, paramAccessibilityEvent);
      if (commandType == CommandType.register)
        registerWachatid(localAccessibilityNodeInfo, paramAccessibilityEvent);
      if (commandType == CommandType.vote)
        strtVote(localAccessibilityNodeInfo, paramAccessibilityEvent);
      if (commandType == CommandType.setOpenid)
        setTingOpenid(localAccessibilityNodeInfo, paramAccessibilityEvent);
      if (commandType == CommandType.addFriends)
        addMyFriends(localAccessibilityNodeInfo, paramAccessibilityEvent);
      localList = localAccessibilityNodeInfo.findAccessibilityNodeInfosByText("忽略");
      Log.i("MyAccessibilityService", "忽略 数量:" + localList.size());
    }
    while ((localList.size() <= 0) || (localList.get(0) == null) || (!"android.widget.Button".equals(((AccessibilityNodeInfo)localList.get(0)).getClassName())));
    ((AccessibilityNodeInfo)localList.get(0)).performAction(16);
  }

  public void onCreate()
  {
    super.onCreate();
    this.serverData.put("mobile", "17182248402");
    this.serverData.put("pwd", "wangtietao");
    this.serverData.put("nike", "漂亮女孩");
    this.serverData.put("openid", "grilwtt88");
    this.serverData.put("openid_set", "false");
    this.serverData.put("friends", "189115");
    this.userModel = new UserModel();
    this.thread = new MyThread();
    this.thread.myAccessibilityService = this;
    this.thread.start();
  }

  public void onInterrupt()
  {
    Log.i("MyAccessibilityService", "onInterrupt");
  }

  protected void onServiceConnected()
  {
    super.onServiceConnected();
    Log.i("MyAccessibilityService", "onServiceConnected");
    StepModel localStepModel1 = new StepModel();
    localStepModel1.action = 16;
    localStepModel1.describe = "确认登录";
    StepModel localStepModel2 = new StepModel();
    localStepModel2.action = 16;
    localStepModel2.describe = "我要投票";
    StepModel localStepModel3 = new StepModel();
    localStepModel3.action = 32;
    localStepModel3.describe = "thumb_5957110b49f46";
    localStepModel3.pressTiem = 2000L;
    StepModel localStepModel4 = new StepModel();
    localStepModel4.action = 16;
    localStepModel4.describe = "识别图中二维码";
    this.taskList.add(localStepModel1);
    this.taskList.add(localStepModel2);
    this.taskList.add(localStepModel4);
    commandType = CommandType.register;
  }

  public void registerWachatid(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("注册");
    Log.i("MyAccessibilityService", " 注册 数量:" + localList1.size());
    List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("更多");
    Log.i("MyAccessibilityService", " 更多 数量:" + localList2.size());
    List localList3 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/hb");
    List localList4 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/hv");
    Log.i("MyAccessibilityService", " 国家列表 数量:" + localList4.size());
    List localList5 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("确认手机号码");
    Log.i("MyAccessibilityService", "确认手机号码 数量:" + localList5.size());
    List localList6 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("点击上面的“注册”按钮，即表示你同意  《腾讯微信软件许可及服务协议》 和 《腾讯隐私政策》");
    Log.i("MyAccessibilityService", " 注册协议 数量:" + localList6.size());
    List localList7 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
    Log.i("MyAccessibilityService", "tabbar 数量:" + localList7.size());
    if (localList7.size() == 0)
      if (localList6.size() > 0)
      {
        localList11 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("昵称");
        localList12 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("中国（+86）");
        localList13 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("国家/地区");
        localList14 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("5555218135");
        localList15 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("密码");
        localList16 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("注册");
        Log.i("MyAccessibilityService", " 昵称 数量:" + localList11.size());
        Log.i("MyAccessibilityService", " 国家/地区 数量:" + localList13.size());
        Log.i("MyAccessibilityService", " 中国（+86） 数量:" + localList12.size());
        Log.i("MyAccessibilityService", " phones 数量:" + localList14.size());
        Log.i("MyAccessibilityService", " pwds 数量:" + localList15.size());
        Log.i("MyAccessibilityService", " 注册 数量:" + localList16.size());
        if (localList14.size() > 0)
        {
          if (localList11.size() != 0)
          {
            localAccessibilityNodeInfo3 = ((AccessibilityNodeInfo)localList11.get(0)).getParent().getChild(1);
            if ((localAccessibilityNodeInfo3 != null) && (localAccessibilityNodeInfo3.getClassName().equals("android.widget.EditText")) && (localAccessibilityNodeInfo3.getText() != null) && (localAccessibilityNodeInfo3.getText().toString().contains("例如")))
            {
              localBundle3 = new Bundle();
              Log.i("MyAccessibilityService", "输入昵称");
              localBundle3.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("nike"));
              localAccessibilityNodeInfo3.performAction(2097152, localBundle3);
            }
          }
          if ((localList14.get(0) != null) && (((AccessibilityNodeInfo)localList14.get(0)).getClassName().equals("android.widget.EditText")) && (((AccessibilityNodeInfo)localList14.get(0)).getText().toString().contains("5555218135")) && (this.serverData.get("mobile") != null))
          {
            localBundle2 = new Bundle();
            Log.i("MyAccessibilityService", "输入账号");
            localBundle2.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("mobile"));
            ((AccessibilityNodeInfo)localList14.get(0)).performAction(2097152, localBundle2);
          }
          if (localList15.size() > 0)
          {
            localAccessibilityNodeInfo1 = (AccessibilityNodeInfo)localList15.get(0);
            if (localAccessibilityNodeInfo1 != null)
            {
              localAccessibilityNodeInfo2 = localAccessibilityNodeInfo1.getParent().getChild(1);
              if ((localAccessibilityNodeInfo2.getClassName().equals("android.widget.EditText")) && (localAccessibilityNodeInfo2.getText() == null) && (this.serverData.get("pwd") != null))
              {
                localBundle1 = new Bundle();
                Log.i("MyAccessibilityService", "输入密码");
                localBundle1.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("pwd"));
                localAccessibilityNodeInfo2.performAction(2097152, localBundle1);
              }
            }
          }
          if ((localList13.size() == 0) || (localList12.size() != 0));
        }
      }
    label899: label1068: 
    while (localList7.size() <= 0)
    {
      do
      {
        List localList9;
        List localList10;
        do
        {
          do
          {
            List localList11;
            List localList12;
            List localList13;
            List localList14;
            List localList15;
            List localList16;
            AccessibilityNodeInfo localAccessibilityNodeInfo3;
            Bundle localBundle3;
            Bundle localBundle2;
            AccessibilityNodeInfo localAccessibilityNodeInfo1;
            AccessibilityNodeInfo localAccessibilityNodeInfo2;
            Bundle localBundle1;
            ((AccessibilityNodeInfo)localList13.get(0)).getParent().performAction(16);
            break label899;
            break label899;
            do
              return;
            while (localList12.size() <= 0);
            Log.i("MyAccessibilityService", " 点击 注册 " + localList1.size());
            ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
            return;
            if ((localList1.size() <= 0) || (localList6.size() != 0) || (localList3.size() != 0))
              break label1068;
            Log.i("MyAccessibilityService", " 点击 注册 " + localList1.size());
            if (paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("不是我的，继续注册").size() <= 0)
              break;
          }
          while (!this.secondRegister);
          ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
          return;
          ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
          return;
          if ((localList4.size() <= 0) || (localList7.size() != 0))
            break;
          localList9 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/a40");
          localList10 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("中非共和国");
          paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("中国");
          Log.i("MyAccessibilityService", " 中非共和国 数量:" + localList10.size());
          ((AccessibilityNodeInfo)localList4.get(0)).performAction(4096);
          ((AccessibilityNodeInfo)localList4.get(0)).performAction(4096);
          ((AccessibilityNodeInfo)localList4.get(0)).performAction(4096);
          Log.i("MyAccessibilityService", " 滚动 国家列表 " + localList4.size());
        }
        while (localList10.size() <= 0);
        Log.i("MyAccessibilityService", " 点击 中国 " + localList9.size());
        ((AccessibilityNodeInfo)localList9.get(-1 + localList9.size())).getParent().performAction(16);
        return;
        if (localList5.size() > 0)
        {
          List localList8 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("确定");
          Log.i("MyAccessibilityService", "点击 确定:" + localList8.size());
          ((AccessibilityNodeInfo)localList8.get(0)).performAction(16);
          return;
        }
        if (localList2.size() > 0)
        {
          Log.i("MyAccessibilityService", " 点击 更多 " + localList2.size());
          ((AccessibilityNodeInfo)localList2.get(0)).performAction(16);
          return;
        }
      }
      while ((localList1.size() <= 0) || (localList3.size() <= 0));
      Log.i("MyAccessibilityService", " 点击 注册2 " + localList3.size());
      ((AccessibilityNodeInfo)localList3.get(3)).performAction(16);
      return;
    }
    commandType = CommandType.setOpenid;
  }

  public void setTingOpenid(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
    Log.i("MyAccessibilityService", "tabbar 数量:" + localList1.size());
    List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/i7");
    AccessibilityNodeInfo localAccessibilityNodeInfo1 = NodeUtils.getNode(localList2, "设置");
    Log.i("MyAccessibilityService", "设置 数量:" + localAccessibilityNodeInfo1);
    AccessibilityNodeInfo localAccessibilityNodeInfo2 = NodeUtils.getNode(localList2, "帐号与安全");
    Log.i("MyAccessibilityService", "帐号与安全 数量:" + localAccessibilityNodeInfo2);
    AccessibilityNodeInfo localAccessibilityNodeInfo3 = NodeUtils.getNode(localList2, "微信号");
    Log.i("MyAccessibilityService", "微信号 数量:" + localAccessibilityNodeInfo3);
    List localList3 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("确定微信号");
    Log.i("MyAccessibilityService", "确定微信号 数量:" + localList3.size());
    List localList4 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("微信号是帐号的唯一凭证，只能设置一次");
    Log.i("MyAccessibilityService", "微信号是帐号的唯一凭证 数量:" + localList4.size());
    List localList5 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("你的微信号成功设置为");
    Log.i("MyAccessibilityService", "你的微信号成功设置为 数量:" + localList5.size());
    paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("未设置");
    if (((String)this.serverData.get("openid_set")).equals("false"))
      if ((localList1.size() != 0) && (localAccessibilityNodeInfo1 == null))
      {
        Log.i("MyAccessibilityService", "点击 我");
        ((AccessibilityNodeInfo)localList1.get(-1 + localList1.size())).getParent().performAction(16);
      }
    while ((localList1.size() != 0) || (this.userModel.setOpendid_back_count >= 3))
    {
      do
        while (true)
        {
          return;
          if (localAccessibilityNodeInfo1 != null)
          {
            Log.i("MyAccessibilityService", "点击 设置");
            localAccessibilityNodeInfo1.getParent().performAction(16);
            return;
          }
          if (localAccessibilityNodeInfo2 != null)
          {
            Log.i("MyAccessibilityService", "点击 帐号与安全");
            localAccessibilityNodeInfo2.getParent().performAction(16);
            return;
          }
          if (localAccessibilityNodeInfo3 != null)
          {
            Log.i("MyAccessibilityService", "点击 微信号");
            localAccessibilityNodeInfo3.getParent().performAction(16);
            return;
          }
          if (localList4.size() > 0)
          {
            List localList7 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ckz");
            List localList8 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("保存");
            if ((localList7.size() > 0) && (localList7.get(0) != null) && (((AccessibilityNodeInfo)localList7.get(0)).getText() == null))
            {
              Bundle localBundle = new Bundle();
              Log.i("MyAccessibilityService", "输入微信号");
              localBundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("openid"));
              ((AccessibilityNodeInfo)localList7.get(0)).performAction(2097152, localBundle);
              return;
            }
            if ((localList8.size() > 0) && (localList7.size() > 0) && (localList7.get(0) != null) && (((AccessibilityNodeInfo)localList7.get(0)).getText() != null))
            {
              Log.i("MyAccessibilityService", " 点击 保存 ");
              ((AccessibilityNodeInfo)localList8.get(0)).performAction(16);
            }
          }
          else
          {
            if (localList3.size() <= 0)
              break;
            Log.i("MyAccessibilityService", " 点击 确定 ");
            List localList6 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("确定");
            for (int i = 0; i < localList6.size(); i++)
              if ((localList6.get(i) != null) && (((AccessibilityNodeInfo)localList6.get(i)).getClassName().equals("android.widget.Button")))
                ((AccessibilityNodeInfo)localList6.get(i)).performAction(16);
          }
        }
      while (localList5.size() <= 0);
      this.serverData.put("openid_set", "true");
      performGlobalAction(1);
      UserModel localUserModel2 = this.userModel;
      localUserModel2.setOpendid_back_count = (1 + localUserModel2.setOpendid_back_count);
      return;
    }
    UserModel localUserModel1 = this.userModel;
    localUserModel1.setOpendid_back_count = (1 + localUserModel1.setOpendid_back_count);
    performGlobalAction(1);
  }

  public void strtVote(AccessibilityNodeInfo paramAccessibilityNodeInfo, AccessibilityEvent paramAccessibilityEvent)
  {
    try
    {
      MyThread.sleep(200L);
      label6: Log.i("MyAccessibilityService", " =======================================");
      List localList1 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aie");
      Log.i("MyAccessibilityService", " 聊天列表 数量:" + localList1.size());
      List localList2 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/a5j");
      Log.i("MyAccessibilityService", " 笑脸  数量:" + localList2.size());
      List localList3 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("撸起袖子加油干");
      Log.i("MyAccessibilityService", " 撸起袖子加油干  数量:" + localList3.size());
      List localList4 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/bw3");
      Log.i("MyAccessibilityService", "tabbar 数量:" + localList4.size());
      List localList5 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("http");
      Log.i("MyAccessibilityService", " http  数量:" + localList5.size());
      List localList6 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("识别图中二维码");
      List localList7 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("关注");
      List localList8 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText("帐号主体");
      if ((localList1.size() > 0) && (localList2.size() == 0) && (localList5.size() == 0) && (localList4.size() > 0))
      {
        Log.i("MyAccessibilityService", "点击 进入聊天室 " + localList1.size());
        ((AccessibilityNodeInfo)localList1.get(0)).performAction(16);
      }
      do
      {
        do
        {
          return;
          if ((localList5.size() <= 0) || (localList2.size() <= 0))
            break;
        }
        while ((((AccessibilityNodeInfo)localList5.get(-1 + localList5.size())).getText() == null) || (this.perlikes.equals(((AccessibilityNodeInfo)localList5.get(-1 + localList5.size())).getText().toString())));
        this.perlikes = ((AccessibilityNodeInfo)localList5.get(-1 + localList5.size())).getText().toString();
        List localList10 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/a7i");
        Log.i("MyAccessibilityService", "点击 链接 " + localList5.size());
        ((AccessibilityNodeInfo)localList10.get(-1 + localList10.size())).performAction(16);
        try
        {
          MyThread.sleep(1000L);
          return;
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          return;
        }
        if (localList6.size() > 0)
        {
          List localList9 = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aog");
          StringBuilder localStringBuilder = new StringBuilder(" 识别图中二维码  数量:");
          Log.i("MyAccessibilityService", ((AccessibilityNodeInfo)localList9.get(0)).getChildCount());
          ((AccessibilityNodeInfo)localList9.get(0)).getChild(-1 + ((AccessibilityNodeInfo)localList9.get(0)).getChildCount()).performAction(16);
          return;
        }
        if ((localList7.size() <= 0) || (localList8.size() <= 0))
          break;
      }
      while (!((AccessibilityNodeInfo)localList7.get(0)).getParent().isClickable());
      Log.i("MyAccessibilityService", " 关注  数量:" + localList7.size());
      ((AccessibilityNodeInfo)localList7.get(0)).getParent().performAction(16);
      int i = 0;
      while (i < 2)
        try
        {
          Thread.sleep(2000L);
          performGlobalAction(1);
          i++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      h5Vote(paramAccessibilityNodeInfo, paramAccessibilityEvent);
      return;
    }
    catch (Exception localException1)
    {
      break label6;
    }
  }

  public void webViewPage(AccessibilityEvent paramAccessibilityEvent)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo1;
    if ("android.webkit.WebView".equals(paramAccessibilityEvent.getClassName()))
    {
      localAccessibilityNodeInfo1 = paramAccessibilityEvent.getSource();
      Log.i("MyAccessibilityService", "  webviwe 里面数量 " + localAccessibilityNodeInfo1.getChildCount());
      localAccessibilityNodeInfo1.findAccessibilityNodeInfosByText("确认登录");
      Log.i("MyAccessibilityService", " 当成窗口名称  " + localAccessibilityNodeInfo1.getContentDescription());
      if ((localAccessibilityNodeInfo1.getChildCount() <= 3) || (localAccessibilityNodeInfo1.getContentDescription() == null) || (!localAccessibilityNodeInfo1.getContentDescription().equals("确认登录")))
        break label158;
      AccessibilityNodeInfo localAccessibilityNodeInfo6 = localAccessibilityNodeInfo1.getChild(4);
      if (localAccessibilityNodeInfo6 != null)
      {
        Log.i("MyAccessibilityService", "  第四个 数量 " + localAccessibilityNodeInfo6.getChildCount());
        localAccessibilityNodeInfo6.getChild(0).performAction(16);
      }
    }
    label158: AccessibilityNodeInfo localAccessibilityNodeInfo2;
    AccessibilityNodeInfo localAccessibilityNodeInfo3;
    AccessibilityNodeInfo localAccessibilityNodeInfo4;
    do
    {
      do
      {
        do
        {
          do
          {
            AccessibilityNodeInfo localAccessibilityNodeInfo5;
            do
            {
              do
              {
                return;
                if ((localAccessibilityNodeInfo1.getChildCount() <= 3) || (localAccessibilityNodeInfo1.getContentDescription() == null) || (!localAccessibilityNodeInfo1.getContentDescription().equals("好友辅助安全登录验证")))
                  break;
              }
              while (localAccessibilityNodeInfo1.getChildCount() <= 4);
              localAccessibilityNodeInfo5 = localAccessibilityNodeInfo1.getChild(4);
            }
            while (localAccessibilityNodeInfo5 == null);
            Log.i("MyAccessibilityService", "  好友辅助安全登录验证 验证码 " + localAccessibilityNodeInfo5.getContentDescription());
            return;
          }
          while ((localAccessibilityNodeInfo1.getChildCount() >= 3) || (localAccessibilityNodeInfo1.getContentDescription() == null) || (!localAccessibilityNodeInfo1.getContentDescription().equals("好友辅助安全登录验证")));
          localAccessibilityNodeInfo2 = localAccessibilityNodeInfo1.getChild(0);
        }
        while ((localAccessibilityNodeInfo2 == null) || (localAccessibilityNodeInfo2.getChildCount() <= 3));
        localAccessibilityNodeInfo3 = localAccessibilityNodeInfo2.getChild(3);
      }
      while (localAccessibilityNodeInfo3 == null);
      localAccessibilityNodeInfo4 = localAccessibilityNodeInfo3.getChild(0);
    }
    while ((localAccessibilityNodeInfo4 == null) || (localAccessibilityNodeInfo2 == null) || (localAccessibilityNodeInfo3 == null) || (localAccessibilityNodeInfo4 == null) || (!localAccessibilityNodeInfo4.getClassName().equals("android.widget.EditText")) || (localAccessibilityNodeInfo4.getContentDescription() == null) || (!localAccessibilityNodeInfo4.getContentDescription().equals("手机号")) || (localAccessibilityNodeInfo4.getText() != null));
    Bundle localBundle = new Bundle();
    Log.i("MyAccessibilityService", " 输入验证手机号  ");
    localBundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)this.serverData.get("mobile"));
    localAccessibilityNodeInfo4.performAction(2097152, localBundle);
  }
}

/* Location:           D:\360安全浏览器下载\jd-gui\classes_dex2jar.jar
 * Qualified Name:     krelve.demo.rob.MyAccessibilityService
 * JD-Core Version:    0.6.2
 */