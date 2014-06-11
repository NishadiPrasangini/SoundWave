package com.synaptik.soundwave;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements AudioMonitor.OnUpdateListener {
	MySurfaceView mView;
	AudioMonitor r;
	
	Thread mThread;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new MySurfaceView(this);
        setContentView(mView);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	
        r = new AudioMonitor(this);
    	mThread = new Thread(r);
    	mThread.start();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	
    	r.done = true;
    	try {
			mThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
	@Override
	public void update(final short[] bytes, final int length, final float sampleLength) {
		runOnUiThread(new Runnable() {
			public void run() {
				mView.setData(bytes, length, sampleLength);
			}
		});
	}
}