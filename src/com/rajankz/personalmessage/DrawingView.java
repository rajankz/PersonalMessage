package com.rajankz.personalmessage;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawingView extends SurfaceView implements OnTouchListener{

	int red, green, blue, alpha;
	
	float prevX, prevY, x, y;
	Paint mPaint;
	Path mPath;
	Color mColor;
	List<Path> pathList;
	List<Paint> paintList;
	
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		red = green = blue = alpha = 0;
		mColor = new Color();
		pathList = new ArrayList<Path>();
		paintList = new ArrayList<Paint>();
	}
	
	@Override
	public void draw(Canvas canvas){
		super.draw(canvas);
		int size = pathList.size();
		//repaint all previous drawings
		for(int i=0;i<size;i++){
			canvas.drawPath(pathList.get(i), paintList.get(i));
		}
		if(mPath != null && mPaint != null){
			canvas.drawPath(mPath, mPaint);
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getActionMasked();
		x = event.getX();
		y = event.getY();
		switch(action){
		case MotionEvent.ACTION_DOWN:
			mPath = new Path();
			mPaint = new Paint();
			mPaint.setStyle(Style.STROKE);
			mPaint.setAntiAlias(true);
			mPaint.setStrokeWidth(5);
			mPaint.setColor(mColor.argb(alpha, red, green, blue));

			prevX = x;
			prevY = y;
			mPath.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			mPath.quadTo(prevX, prevY, x, y);
			mPath.lineTo(x, y);
			prevX = x;
			prevY = y;
			break;
		case MotionEvent.ACTION_UP:
			mPath.setLastPoint(x, y);
			mPath.lineTo(x, y);
			pathList.add(mPath);
			paintList.add(mPaint);
			mPath = null;
			mPaint = null;
			break;
		}
		invalidate();
		return true;
	}
	
	public void clearCanvas(){
		pathList.clear();
		paintList.clear();
		invalidate();
	}
	
	public void setRed(int value){
		red = value;
	}
	
	public void setGreen(int value){
		green = value;
	}
	
	public void setBlue(int value){
		blue = value;
	}
	
	public void setAlpha(int value){
		alpha = value;
	}

}
