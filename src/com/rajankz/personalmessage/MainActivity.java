package com.rajankz.personalmessage;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	SeekBar seekRed, seekGreen, seekBlue, seekAlpha;
	EditText valRed, valGreen, valBlue, valAlpha;
	DrawingView drawingCanvas;
	Button clearCanvas, shareCanvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initContentView();
	}
	
	public void initContentView(){
		seekRed = (SeekBar) findViewById(R.id.seekRed);
		seekGreen = (SeekBar) findViewById(R.id.seekGreen);
		seekBlue = (SeekBar) findViewById(R.id.seekBlue);
		seekAlpha = (SeekBar) findViewById(R.id.seekAlpha);
		
		valRed = (EditText) findViewById(R.id.valRed);
		valGreen = (EditText) findViewById(R.id.valGreen);
		valBlue = (EditText) findViewById(R.id.valBlue);
		valAlpha = (EditText) findViewById(R.id.valAlpha);
		
		seekRed.setOnSeekBarChangeListener(seekChangeListener);
		seekGreen.setOnSeekBarChangeListener(seekChangeListener);
		seekBlue.setOnSeekBarChangeListener(seekChangeListener);
		seekAlpha.setOnSeekBarChangeListener(seekChangeListener);
		
		drawingCanvas = (DrawingView)findViewById(R.id.drawingCanvas);
		drawingCanvas.setOnTouchListener(drawingCanvas);
		
		clearCanvas = (Button)findViewById(R.id.clearCanvas);
		clearCanvas.setOnClickListener(clearCanvasClick);
		
		shareCanvas = (Button)findViewById(R.id.shareCanvas);
		shareCanvas.setOnClickListener(sendMessageClick);
	}
	
	private SeekBar.OnSeekBarChangeListener seekChangeListener = new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			switch(seekBar.getId()){
				case R.id.seekRed:
					valRed.setText(""+progress);
					drawingCanvas.setRed(progress);
					break;
				case R.id.seekGreen:
					valGreen.setText(""+progress);
					drawingCanvas.setGreen(progress);
					break;
				case R.id.seekBlue:
					valBlue.setText(""+progress);
					drawingCanvas.setBlue(progress);
					break;
				case R.id.seekAlpha:
					valAlpha.setText(""+progress);
					drawingCanvas.setAlpha(progress);
					break;
			}
		}
	};

	private OnClickListener clearCanvasClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(drawingCanvas != null){
				drawingCanvas.clearCanvas();
			}
		}
	};
	
	private OnClickListener sendMessageClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			Bitmap canvas = drawingCanvas.getDrawingCache(); //need to enable drawing cache in DrawingView
			if(canvas != null){
				//TODO: generate sharing image data here
				//TODO: set the image data in intent extra
				canvas.recycle();
				sharingIntent.setType("image/png");
				startActivity(Intent.createChooser(sharingIntent, "Share via"));
			}else{
				Toast.makeText(getBaseContext(), R.string.err_no_canvas_image, Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
