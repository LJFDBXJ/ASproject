package com.manger.manger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class  PhoneManager {
	// 定义一个手机管理父类
	static Context context;
	private CPUManager cpuManager = null; // CPU管理
	private MemoryManager memoryManager = null;// 内存管理
	private ScreenManager screenManager = null;// 屏幕管理
	private CameraManager cameraManager = null;// 相机管理
	private DeviceManager deviceManager = null;// 设备管理
	private SystemManager systemManager = null;// 系统管理

	/**
	 * 设置成单例模式 三点： 1.单例类只能有一个实例， 2.单例类必须自己创建自己的唯一实例 3.单例类必须给所有其他对象提供这一实例
	 * 构造方法必须设成私有化，在同一个模拟器中，只能通过get方法访问 特点：单例模式的类的构造方法必须是私有化
	 */
	private static PhoneManager phManager = null;// 先申明一个类的对象为静态空；

	/**
	 * 单例模式的类只提供私有的构造方法 类定义中含有一个该类的静态私有对象 提供一个静态共有的函数去实现静态私有对象
	 */

	private PhoneManager() {
		super();
	}

	public static PhoneManager getPhoneManager(Context ct) {
		if (phManager == null) {
			phManager = new PhoneManager();
		}
		context = ct;
		return phManager;
	}

	/**
	 * 实例化包装好的类
	 */
	public CPUManager getCPUManager() {
		if (cpuManager == null) {
			cpuManager = new CPUManager();
		}
		return cpuManager;
	}

	public MemoryManager getMemoryManager() {
		if (memoryManager == null) {
			memoryManager = new MemoryManager();
		}
		return memoryManager;
	}

	public ScreenManager getScreenManager() {
		if (screenManager == null) {
			screenManager = new ScreenManager();
		}
		return screenManager;
	}

	public CameraManager getCameraManager() {
		if (cameraManager == null) {
			cameraManager = new CameraManager();
		}
		return cameraManager;
	}

	public DeviceManager getDeviceManager() {
		if (deviceManager == null) {
			deviceManager = new DeviceManager();
		}
		return deviceManager;
	}

	public SystemManager getSystemManager() {
		if (systemManager == null) {
			systemManager = new SystemManager();
		}
		return systemManager;
	}

	/**
	 * 获取CPU相关信息
	 *
	 * @author Administrator
	 *
	 */
	public class CPUManager {
		/**
		 * 获取CPU名字
		 *
		 * @return
		 */
		public String getCPUName() {
			BufferedReader br = null;
			String[] array = null;
			try {

				FileReader fr = new FileReader("/proc/cpuinfo");// 固定的地址----
				// /proc/cpuinfo
				br = new BufferedReader(fr);
				String text = br.readLine();

				array = text.split(":\\s+");// 存入数组，以指定的分割符号分割，返回下标一的内容

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return array[1];
		}

		/**
		 * 获取CPU数量
		 *
		 * @return
		 */
		public int getCPUNumber() {
			/*
			 * 局部内部类
			 */
			try {
				File dir = new File("/sys/devices/system/cpu/");// 指定的地址----
				// /sys/devices/system/cpu/
				/**
				 * 获取由该文件表示的目录中的文件列表， 然后后通过文件筛选器筛选该列表 并将匹配文件作为文件数组返回，
				 * 如果该文件不是目录则返回空， 如果过滤器为空， 那么所有文件的条目匹配和表示当前父目录不作为返回部分。
				 */
				File[] files = dir.listFiles(new CpuFilter());// CpuFilter()实现了FileFilter
				return files.length;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		}
	}

	// FileFilter 一种根据名字或其他信息过滤文件对象的接口。
	class CpuFilter implements FileFilter {
		public boolean accept(File pathname) {
			/**
			 * Tests whether the given regularExpression matches the given
			 * input. Equivalent to
			 * Pattern.compile(regularExpression).matcher(input).matches(). If
			 * the same regular expression is to be used for multiple
			 * operations, it may be more efficient to reuse a compiled Pattern.
			 *
			 * 测试是否给定的正则表达式匹配给定的输入。 相当于模式，编译（正则表达式）匹配（输入）的比赛
			 * 如果相同的正则表达式是用于多个操作，它可能更有效的利用编译模式。
			 */
			if (Pattern.matches("cpu[0-9]", pathname.getName())) {// Pattern模板
				return true;
			}
			return false;
		}
	}

	/**
	 * 获取内存情况
	 *
	 * @author Administrator
	 *
	 */
	public static class MemoryManager {
		/**
		 * 总的运行内存
		 */
		public long getTotalMemory() {
			String str1 = "/proc/meminfo";// 系统内存信息文件
			String totalMemory = "";// 总内存
			long tm = 0;
			BufferedReader bufferedReader = null;

			try {
				FileReader fileReader = new FileReader(str1);
				bufferedReader = new BufferedReader(fileReader);
				String content = bufferedReader.readLine();
				totalMemory = content.split("\\s+")[1];
				tm = Long.valueOf(totalMemory) * 1024;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return tm;
		}

		/**
		 * 空闲内存
		 */
		public long getFreeMemory() {
			long freeMemory = 0;// 申明一个长整型的变量。

			MemoryInfo memoryInfo = new MemoryInfo();

			ActivityManager activityManager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);

			activityManager.getMemoryInfo(memoryInfo);

			freeMemory = memoryInfo.availMem;// 获得系统空余内存。
			return freeMemory;
		}
	}

	/**
	 * 获取屏幕分辨率
	 */
	public class ScreenManager {
		/**
		 * 分辨率
		 */

		public String getScreen() {
			WindowManager windowManager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);// 获取系统服务
			DisplayMetrics dm = new DisplayMetrics();// DisplayMetrics显示度量。

			windowManager.getDefaultDisplay().getMetrics(dm);
			return (dm.widthPixels + " * " + dm.heightPixels);// 显示宽高
		}
	}

	/**
	 * 获取相机信息
	 */
	public class CameraManager {
		/**
		 * 获取相机分辨率
		 */
		public String getCameraPix() {
			/**
			 * To access the device camera, you must declare申明 the CAMERA
			 * permission允许 in your Android Manifest. Also be sure to include
			 * the <uses-feature> manifest element to declare camera features
			 * used by your application. For example,
			 *
			 * if you use the camera and auto-focus feature, 如果你使用相机的对焦功能
			 *
			 * your Manifest should include the following: 你的清单应该包含以下内容
			 *
			 */
			Camera camera = Camera.open();
			/**
			 * Camera.open(); Creates a new Camera object to access the first
			 * back-facing camera on the device. //If the device does not have a
			 * back-facing camera, this returns null.
			 *
			 * 创建一个新的相机对象访问第一背面摄像头，如果设备没有后置摄像头，则返回空null.
			 */

			/**
			 * 返回此相机服务当前的设置， 如果对返回的参数进行修改， 必须将他们传递给设置参数的摄像机参数以生效。
			 */
			Camera.Parameters para = camera.getParameters();// Camera.Parameters相机参数

			/**
			 * 通过对象para获得支持照片大小的数据，返回数组。相机所有的参数。 *
			 * getSupportedPictureSizes，支持图片的大小列表，此方法始终返回至少一个元素的列表。
			 */
			List<Size> sizes = para.getSupportedPictureSizes();

			Size maxSize = sizes.get(0); // 定位元素返回的索引，返回指定位置的元素

			for (Size size : sizes) {// Image size (width and height
				// dimensions).图片大小宽和高

				if (size.width > maxSize.width && size.height > maxSize.height) {

					maxSize = size;
				}
			}

			camera.release();// 切读结束时要释放照相机，否则再次打开会报错

			return maxSize.width + "*" + maxSize.height;// 返回最大的宽，和最大的高
		}
	}

	/**
	 * 获取设备
	 */
	public class DeviceManager {
		/**
		 * 获取设备名称
		 */
		public String getDeviceName() {

			String deviceName = "";
			deviceName = Build.MODEL;
			return deviceName;

		}

		/**
		 * 获取设备版本号
		 */
		public String getDeviceNumber() {
			String deviceNumber = "";
			deviceNumber = Build.VERSION.RELEASE;
			return deviceNumber;
		}

		@SuppressWarnings("deprecation")
		public String getBaseBrand() {
			return Build.BOOTLOADER;
		}
	}

	/**
	 * 一键root
	 */
	public class SystemManager {
		/**
		 * 是否root
		 */
		public String isRoot() {
			String isRoot = null;

			try {
				Process process = Runtime.getRuntime().exec("su");// su
				// 获取最高的管理员权限
				DataOutputStream os = new DataOutputStream(
						process.getOutputStream());
				os.writeBytes("ls /data/\n");
				os.writeBytes("exit\n");
				os.flush();
				isRoot = "是";

			} catch (Exception e) {
				isRoot = "否";
			}
			return isRoot;
		}
	}
}
