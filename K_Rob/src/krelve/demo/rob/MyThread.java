package krelve.demo.rob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

class MyThread extends Thread
{
  private static final String TAG = "FxService";
  public Handler hander;
  private BufferedReader in;
  public MyAccessibilityService myAccessibilityService;
  private ObjectOutputStream out;
  private Socket socket;

  public void run()
  {
    Message localMessage = new Message();
    localMessage.what = 17;
    Bundle localBundle = new Bundle();
    localBundle.clear();
    try
    {
      this.socket = new Socket("192.168.0.102", 8995);
      OutputStream localOutputStream = this.socket.getOutputStream();
      this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      new ReadThread().start();
      localOutputStream.write("wtt:88|5559|王铁涛2|8888".getBytes("utf-8"));
      localOutputStream.flush();
      return;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      localBundle.putString("msg", "服务器连接失败！请检查网络是否打开");
      localMessage.setData(localBundle);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  class ReadThread extends Thread
  {
    boolean runFlag = true;

    ReadThread()
    {
    }

    public void exit()
    {
      this.runFlag = false;
    }

    public void run()
    {
      if (MyThread.this.socket.isClosed())
        return;
      while (true)
      {
        String str;
        try
        {
          str = MyThread.this.in.readLine();
          if (str == null)
            break;
          Log.i("FxService", "=======" + str);
          if (!str.contains("wttbk"))
            continue;
          if (str.contains("login"))
          {
            ArrayList localArrayList2 = NodeUtils.regexText("\\[(.*?)\\]", str);
            if (localArrayList2.size() > 1)
            {
              MyThread.this.myAccessibilityService.serverData.put("mobile", (String)localArrayList2.get(0));
              MyThread.this.myAccessibilityService.serverData.put("pwd", (String)localArrayList2.get(1));
            }
            MyAccessibilityService.commandType = CommandType.login;
            continue;
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return;
          if (str.contains("quit"))
          {
            MyAccessibilityService.commandType = CommandType.quit;
            continue;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        if (str.contains("startup"))
        {
          Intent localIntent1 = new Intent();
          localIntent1.setAction("starupapp");
          MyThread.this.myAccessibilityService.sendBroadcast(localIntent1);
        }
        else if (str.contains("home"))
        {
          MyThread.this.myAccessibilityService.performGlobalAction(2);
        }
        else if (str.contains("kill"))
        {
          Intent localIntent2 = new Intent();
          localIntent2.setAction("killBroadcastflag");
          MyThread.this.myAccessibilityService.sendBroadcast(localIntent2);
        }
        else if (str.contains("back"))
        {
          MyAccessibilityService.commandType = CommandType.norun;
          ArrayList localArrayList1 = NodeUtils.regexText("\\[(.*?)\\]", str);
          if (localArrayList1.size() > 0)
          {
            int i = Integer.parseInt((String)localArrayList1.get(0));
            for (int j = 0; j < i; j++)
              MyThread.this.myAccessibilityService.performGlobalAction(1);
          }
        }
        else
        {
          MyAccessibilityService.commandType = CommandType.norun;
        }
      }
    }
  }
}

/* Location:           D:\360安全浏览器下载\jd-gui\classes_dex2jar.jar
 * Qualified Name:     krelve.demo.rob.MyThread
 * JD-Core Version:    0.6.2
 */