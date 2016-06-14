package com.kituri.mediapPlayer;

import java.util.HashMap;

import com.data.Constants.Constants;
import com.kituri.wowrich.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class mediaPlayer {

	static public final int SOUND_ATTACK01 = 1;
	static public final int SOUND_ATTACK02 = 2;
	static public final int SOUND_ATTACK03 = 3;
	static public final int SOUND_ATTACK04 = 4;
	static public final int SOUND_ATTACK05 = 5;
	static public final int SOUND_ATTACK06 = 6;
	static public final int SOUND_ATTACK07 = 7;
	static public final int SOUND_ATTACK08 = 8;
	static public final int SOUND_LVUP = 9;

	private float volume;
	private SoundPool soundPool;
	private Context _context;
	private HashMap<Integer, Integer> soundPoolMap;

	public mediaPlayer(Context context) {
		this._context = context;
		initSounds();
	}

	private void initSounds() {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		// soundPool.set
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(SOUND_ATTACK01, soundPool.load(_context,
				R.raw.attack01, 1));
		soundPoolMap.put(SOUND_ATTACK02, soundPool.load(_context,
				R.raw.attack02, 1));
		soundPoolMap.put(SOUND_ATTACK03, soundPool.load(_context,
				R.raw.attack03, 1));
		soundPoolMap.put(SOUND_ATTACK04, soundPool.load(_context,
				R.raw.attack04, 1));
		soundPoolMap.put(SOUND_ATTACK05, soundPool.load(_context,
				R.raw.attack05, 1));
		soundPoolMap.put(SOUND_ATTACK06, soundPool.load(_context,
				R.raw.attack06, 1));
		soundPoolMap.put(SOUND_ATTACK07, soundPool.load(_context,
				R.raw.attack07, 1));
		soundPoolMap.put(SOUND_ATTACK08, soundPool.load(_context,
				R.raw.attack08, 1));
		soundPoolMap.put(SOUND_LVUP, soundPool.load(_context, R.raw.lvup, 1));
		AudioManager mgr = (AudioManager) _context
				.getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = streamVolumeCurrent / streamVolumeMax;
	}

	public void playSound(int sound) {
		/* ����4�д������ڼ���0.0��1.0֮��ĵ�ǰ�� */

		if (Constants.SoundEnabled) {
			/* ʹ����ȷ���������� */
			soundPool.play(soundPoolMap.get(sound), volume, volume, 1, 0, 1f);
		}
	}

	// public void play(int soundID) {
	// playSound(soundID);
	// }

}
