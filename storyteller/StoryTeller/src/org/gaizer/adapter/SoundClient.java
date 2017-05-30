package org.gaizer.adapter;

import org.stt.R;
import org.stt.StoryTellerMAC;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundClient implements SoundPool.OnLoadCompleteListener {
	
	public void test() {
		MediaPlayer mp = MediaPlayer.create(StoryTellerMAC._this, R.raw.audiofile);
		mp.start();
//		
	}

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//		soundPool.load(fd, offset, length, 1);
//		int soundId = soundPool.load(path, 1);
//		
//		int streamId = soundPool.play(soundId, leftVolume, rightVolume, 1, loop, rate);
//		soundPool.setOnLoadCompleteListener(this);
//		soundPool.release();
//		
	}

}
