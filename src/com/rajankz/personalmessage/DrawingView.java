package com.rajankz.personalmessage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawingView extends SurfaceView implements OnTouchListener{

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.i("hello", "on touch");
		return false;
	}

}
