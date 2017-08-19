package com.echedey.imagefling;

import java.io.IOException;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher;

public class ImageFlingActivity extends Activity implements
		ViewSwitcher.ViewFactory, OnItemSelectedListener {

	private MediaPlayer mPlayer;
	private static final String TAG = "AUDIO";
	private String erroraudiostring = "Unable to play audio queue do to exception: ";
	int musicId = R.raw.prueba_y_error_01_96;
	int resId = 0;
	private int currentPage;

	public class PageListener extends SimpleOnPageChangeListener {
		public void onPageSelected(int position) {
			Log.i(TAG, "page selected " + position);
			currentPage = position;

			switch (position) {
			case 0:
				musicId = R.raw.prueba_y_error_01_96;
				Toast.makeText(getApplicationContext(),
						"Tocando: " + "Prueba y error", Toast.LENGTH_SHORT)
						.show();
				break;
			case 1:
				musicId = R.raw.magia_y_efectos_especiales_02_96;
				 Toast.makeText(getApplicationContext(),
						 "Tocando: " + "Magia y efectos especiales",
						 Toast.LENGTH_SHORT).show();
				break;
			case 2:
				musicId = R.raw.extranho_regalo_03_96;
				 Toast.makeText(getApplicationContext(),
						 "Tocando: " + "Extraño regalo",
						 Toast.LENGTH_SHORT).show();
				break;
			case 3:
				musicId = R.raw.tu_continente_04_96;
				 Toast.makeText(getApplicationContext(),
						 "Tocando: " + "Tu continente",
						 Toast.LENGTH_SHORT).show();
				break;
			}

			playSample(musicId);

		}
	}

	// private ImageAdapter ia;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTitle("Izal App Album");

		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		myPager.setAdapter(adapter);

		myPager.setCurrentItem(0);

		PageListener pageListener = new PageListener();
		myPager.setOnPageChangeListener(pageListener);

		mPlayer = MediaPlayer.create(ImageFlingActivity.this,
				R.raw.prueba_y_error_01_96);

		playSample(R.raw.prueba_y_error_01_96);

		Toast.makeText(getApplicationContext(),
				"Tocando ahora: " + "Prueba y error", Toast.LENGTH_SHORT)
				.show();

	}

	public void onDestroy() {

		mPlayer.stop();
		super.onDestroy();

	}

	private void playSample(int resid) {
		AssetFileDescriptor afd = getResources().openRawResourceFd(resid);
		if (mPlayer != null) {
			if (mPlayer.isPlaying())
				mPlayer.stop();
			mPlayer.reset();

			try {
				// mPlayer.reset();
				mPlayer.setDataSource(afd.getFileDescriptor(),
						afd.getStartOffset(), afd.getDeclaredLength());
				mPlayer.prepare();
				mPlayer.start();
				afd.close();
			} catch (IllegalArgumentException e) {
				Log.e(TAG, erroraudiostring + e.getMessage(), e);
			} catch (IllegalStateException e) {
				Log.e(TAG, erroraudiostring + e.getMessage(), e);
			} catch (IOException e) {
				Log.e(TAG, erroraudiostring + e.getMessage(), e);
			}

		} else {

//			mPlayer = MediaPlayer.create(ImageFlingActivity.this,
//					R.raw.prueba_y_error_01);
//			// mPlayer.start();
		}

	}

	private class MyPagerAdapter extends PagerAdapter {

		public int getCount() {
			return 4;
		}

		public Object instantiateItem(View collection, int position) {

			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			int musicId = 0;

			switch (position) {
			case 0:
				resId = R.layout.magia_y_efectos_especiales;
				break;
			case 1:
				resId = R.layout.prueba_y_error;
				break;
			case 2:
				resId = R.layout.extranho;
				break;
			case 3:
				resId = R.layout.tu_continente;
				break;
			}

			View view = inflater.inflate(resId, null);

			((ViewPager) collection).addView(view, 0);

			return view;

		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);

		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

		Toast.makeText(getApplicationContext(),
				"Playing now nuevo int: " + arg2, Toast.LENGTH_SHORT).show();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public View makeView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);

		switch (item.getItemId()) {

		case R.id.contacto:
			// TODO:Get remote XML file
			alert.setTitle("Izal: ¡Búscanos!");
			alert.setMessage("\nfacebook.com/izalmusica\n\ntwitter.com/izalmusic\n\nyoutube.com/user/izalmusic\n\nizalmusic@gmail.com\n\n\n by IZAL & echedey@gmail.com");
			alert.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	

}