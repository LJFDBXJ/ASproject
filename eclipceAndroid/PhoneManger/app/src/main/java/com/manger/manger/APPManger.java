package com.manger.manger;

import java.util.ArrayList;
import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;

import com.manger.base.ShoftMa;
import com.manger.base.Speed;

public class APPManger {

	private Context context;
	private PackageManager pm;
	private ActivityManager am;

	// 私有的构造方法
	private APPManger(Context context) {
		this.context = context;
		pm = context.getPackageManager();
		am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);// 获得系统服务

	}

	// 私有的对象
	private static APPManger appManager;

	// 静态共有的方法实现静态私有对象
	public static APPManger getAPPManger(Context context) {
		if (appManager == null) {
			appManager = new APPManger(context);
		}
		return appManager;
	}

	public void killMyRunApp(String packageName) {
		am.killBackgroundProcesses(packageName);
	}

	/**
	 * 内部接口
	 */
	public interface MyDataLinister {
		public void updataAll(ArrayList<ShoftMa> list);

		public void updataOne(ShoftMa app);
	}

	/**
	 * 根据 String 返回不同的集合 100
	 */
	public ArrayList<ShoftMa> getAppListAll(String str, MyDataLinister linister) {

		// 返回的数据
		ArrayList<ShoftMa> listAll = new ArrayList<ShoftMa>();
		ArrayList<ShoftMa> listUser = new ArrayList<ShoftMa>();
		ArrayList<ShoftMa> listSys = new ArrayList<ShoftMa>();
		// 全部qpp
		List<PackageInfo> plist = pm
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (int i = 0; i < plist.size(); i++) {
			ShoftMa a = new ShoftMa();
			// try {
			// Thread.sleep(40);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// 每个应用
			PackageInfo packinfo = plist.get(i);

			a.setAppName(packinfo.applicationInfo.loadLabel(pm) + "");
			a.setAppIcon(packinfo.applicationInfo.loadIcon(pm));
			a.setPackageName(packinfo.packageName);
			a.setVersionName(packinfo.versionName);
			// 得到1 intent
			Intent intent = pm.getLaunchIntentForPackage(packinfo.packageName);
			a.setIntent(intent);

			// 全部数据 分别存放在3个不同的集合
			int currentFlag = packinfo.applicationInfo.flags; // 每个标签
			// 系统 100
			int sysFlag = ApplicationInfo.FLAG_SYSTEM
					| ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;

			if ((sysFlag & currentFlag) != 0) { // 系统

				listSys.add(a);

			} else {// 用户
				listUser.add(a);
			}

			listAll.add(a);
			// 通知UI 更新一条
			linister.updataOne(a);

		}

		if ("所有应用".equals(str)) {

			// 更新全部
			linister.updataAll(listAll);
			return listAll;

		} else if ("系统应用".equals(str)) {
			// 更新全部
			linister.updataAll(listSys);
			return listSys;

		} else if ("用户应用".equals(str)) {
			linister.updataAll(listUser);
			return listUser;
		}
		return listAll;

	}

	// 方法 获取 全部正在运行的进程集合
	public ArrayList<Speed> getRunAllApp() {

		ArrayList<Speed> list = new ArrayList<Speed>();
		// 手机中所有的安装过app信息 包名 图标 内存
		List<PackageInfo> alllist = pm
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		// 正在运行的app
		List<RunningAppProcessInfo> alist = am.getRunningAppProcesses();

		for (int i = 0; i < alllist.size(); i++) {

			PackageInfo pinfo = alllist.get(i);

			for (int j = 0; j < alist.size(); j++) {

				RunningAppProcessInfo runinfo = alist.get(j);

				if (pinfo.packageName.equals(runinfo.processName)
						&& runinfo.importance >= 100
						&& !pinfo.packageName.equals("com.an")) {
					// 名字
					String appname = (String) pinfo.applicationInfo
							.loadLabel(pm);
					// 图标
					Drawable appimage = pinfo.applicationInfo.loadIcon(pm);
					// 内存
					MemoryInfo[] memoryInfos = am
							.getProcessMemoryInfo(new int[] { runinfo.pid });

					// 1个元素
					MemoryInfo mInfos = memoryInfos[0];

					int memory = mInfos.getTotalPrivateDirty() * 1024;

					// SoftmsgTwoMessage message = new
					// SoftmsgTwoMessage(appimage,
					// appname, memory + "", pinfo.packageName, false);
					Speed message = new Speed(pinfo.packageName, appimage,
							appname, false);

					list.add(message);
				}

			}

		}

		return list;

	}
}
