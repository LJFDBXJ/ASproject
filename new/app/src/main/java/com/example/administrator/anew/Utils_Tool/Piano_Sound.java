package com.example.administrator.anew.Utils_Tool;


import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 音效 工具类
 *
 * @author 喵喵
 */
public class Piano_Sound {
	private static boolean isSoundOn;
	private static SoundPool soundPool;
	private static int[] soundIds;

	private Piano_Sound() {
	}

	public static boolean isSoundOn() {
		return isSoundOn;
	}

	public static void setSoundOnOff(boolean isOn) {
		isSoundOn = isOn;
	}

	/**
	 * 初始化音效
	 *
	 * @param maxNumber
	 *            最大数量
	 * @param resIds
	 *            音效文件的资源Id数组
	 *
	 *
	 *            public SoundPool(int maxStream, int streamType, int
	 *            srcQuality) maxStream —— 同时播放的流的最大数量 streamType ——
	 *            流的类型，一般为STREAM_MUSIC(具体在AudioManager类中列出) srcQuality ——
	 *            采样率转化质量，当前无效果，使用0作为默认值
	 */
	@SuppressWarnings("deprecation")
	public static void initSound(int maxNumber, int[] resIds) {

		soundPool = new SoundPool(maxNumber, AudioManager.STREAM_MUSIC, 0);
		soundIds = new int[resIds.length];
		for (int i = 0; i < resIds.length; i++) {
			/**
			 * 通过一个AssetFileDescriptor对象 int load(Context context, int resId,
			 * int priority)
			 */
			soundIds[i] = soundPool.load(MyContext.getContext(),
					resIds[i], 1);
		}
	}

	/**
	 * 播放音效
	 *
	 * @param index
	 *            要播放的音效在数组中的位置
	 */
	public static void playSound(int index) {
		if (soundPool == null) {
			return;
		}
		if (isSoundOn) {
			/*
			 * final int play(int soundID, float leftVolume, float rightVolume,
			 * int priority, int loop, float rate) 播放指定音频的音效，并返回一个streamID 。
			 * priority —— 流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理； loop
			 * —— 循环播放的次数，0为值播放一次，-1为无限循环，其他值为播放loop+1次（例如，3为一共播放4次）. rate ——
			 * 播放的速率，范围0.5-2.0(0.5为一半速率，1.0为正常速率，2.0为两倍速率)
			 */
			soundPool.play(soundIds[index], 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}
}

