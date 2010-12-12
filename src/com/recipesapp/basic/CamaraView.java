package com.recipesapp.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path.Direction;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint;
import android.hardware.Camera;

	public class CamaraView extends Activity implements SurfaceHolder.Callback {
		
		private SurfaceView mySurfaceView;
		private SurfaceHolder mySurfaceHolder;
		private Camera myCamera;
		private boolean isPreviewRunning;
		
		 @Override
		 public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//setContentView(R.layout.camera_surface);
			//mySurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
			mySurfaceHolder = mySurfaceView.getHolder();
			mySurfaceHolder.addCallback(this);
			mySurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
		}
		 
		public void surfaceCreated(SurfaceHolder holder) {
			myCamera = Camera.open();
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

			if (isPreviewRunning) {
				myCamera.stopPreview();
			}

			Camera.Parameters p = myCamera.getParameters();
			p.setPreviewSize(w, h);
			myCamera.setParameters(p);

			try {
				myCamera.setPreviewDisplay(holder);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			myCamera.startPreview();
			isPreviewRunning = true;
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			myCamera.stopPreview();
			isPreviewRunning = false;
			myCamera.release();
		}
		
	private void takePicture() {
		myCamera.takePicture(null, null, myPhotoCallback);
	}
	
	Camera.PictureCallback myPhotoCallback = new Camera.PictureCallback() {
		public void onPictureTaken( byte[] data, Camera camera ) {
			new storeNewImage().execute( data );
			camera.startPreview();
		}
	};

	class storeNewImage extends AsyncTask<byte[], String, String> {
		protected String doInBackground(byte[]... png) {
			File photo = new File( Environment.DIRECTORY_PICTURES,"photo.png" );

			if (photo.exists()) {
				photo.delete();
			}

			try {
				FileOutputStream fos = new FileOutputStream( photo.getPath() );

				fos.write( png[0] );
				fos.close();
			}
			catch ( java.io.IOException e ) {
				e.printStackTrace();
			}

			return(null);
		}
	}

/*		private MediaRecorder mediaRecorder;
		private final int maxDurationInMs = 20000;
		private final long maxFileSizeInBytes = 500000;
		private final int videoFramesPerSecond = 20;

		public void startRecording(){
			try {
				myCamera.unlock();

				mediaRecorder = new MediaRecorder();

				mediaRecorder.setCamera(myCamera);
				mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

				mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

				mediaRecorder.setMaxDuration(maxDurationInMs);

				String cacheFileName = "video";
				File tempFile = new File( getCacheDir(),cacheFileName );
				mediaRecorder.setOutputFile(tempFile.getPath());

				mediaRecorder.setVideoFrameRate( videoFramesPerSecond );
				mediaRecorder.setVideoSize( mySurfaceView.getWidth(), mySurfaceView.getHeight() );

				mediaRecorder.setAudioEncoder( MediaRecorder.AudioEncoder.DEFAULT );
				mediaRecorder.setVideoEncoder( MediaRecorder.VideoEncoder.DEFAULT );

				mediaRecorder.setPreviewDisplay( mySurfaceHolder.getSurface() );
				mediaRecorder.setMaxFileSize( maxFileSizeInBytes );

				mediaRecorder.prepare();
				mediaRecorder.start();

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void stopRecording(){
			mediaRecorder.stop();
			myCamera.lock();
		}
	
	MediaRecorder myAudioRecorder = new MediaRecorder();
	ContentValues myContentValues = new ContentValues(3);
	String notes = "Notes for My App";
	myContentValues.put( MediaStore.MediaColumns.TITLE, notes );
	myContentValues.put( MediaStore.MediaColumns.TIMESTAMP, System.currentTimeMillis() );
	myContentValues.put( MediaStore.MediaColumns.MIME_TYPE, myAdioRecorder.getMimeContentType() );
	
	ContentResolver myContentResolver = new ContentResolver();
	
	Uri base = MediaStore.Audio.INTERNAL_CONTENT_URI;
	Uri newUri = myContentResolver.insert( base, values );
	
	String path = myContentResolver.getDataFilePath( newUri );
	
	myAudioRecorder.setAudioSource( MediaRecorder.AudioSource.MIC );
	myAudioRecorder.setOutputFormat( MediaRecorder.OutputFormat.MP3);
	myAudioRecorder.setAudioEncoder( MediaRecorder.AudioEncoder.AMR_NB );
	myAudioRecorder.setOutputFile( path );
	
	myAudioRecorder.prepare();
	myAudioRecorder.start();
		*/
	}