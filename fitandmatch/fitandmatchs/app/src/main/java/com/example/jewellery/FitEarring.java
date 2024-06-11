package com.example.jewellery;

import java.net.URL;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class FitEarring extends Activity 
{
	
	ImageView img1;
    RelativeLayout rl;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fit_earring);
		
		
		if (Build.VERSION.SDK_INT > 9) 
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        rl = (RelativeLayout) findViewById(R.id.fitban_rl);
        img1 = (ImageView) findViewById(R.id.fitban_imgv);
        BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
        bitmapFatoryOptions.inPreferredConfig = Config.RGB_565;
        Bitmap bm1 = PHOTO.image;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bm1);
        rl.setBackgroundDrawable(bitmapDrawable);
        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ornamnce = Ornaments.image1;
            String path = "http://" + sh.getString("ip","") + "/";
            URL ur1 = new URL(path.concat(ornamnce));
            Bitmap bm2 = BitmapFactory.decodeStream(ur1.openStream(), null, bitmapFatoryOptions);
            img1.setImageBitmap(bm2);

        } catch (Exception e) {
            System.out.println("error in try catch " + e.toString());
        }
        img1.setOnTouchListener(new Touch());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.fit_earring, menu);
		return true;
	}

}
