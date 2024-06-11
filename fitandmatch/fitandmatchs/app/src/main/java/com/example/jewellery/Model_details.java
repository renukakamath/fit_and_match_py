package com.example.jewellery;



import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Model_details extends Activity {
	
	TextView t1,t2,t3;
	Button b1,b2;
	String weight,price;
	ImageView im1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model_details);
		
		t1=(TextView)findViewById(R.id.textViewpname);
		t2=(TextView)findViewById(R.id.textViewdesc);
		t3=(TextView)findViewById(R.id.textViewprice);
		im1=(ImageView)findViewById(R.id.imageView1);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		
		t1.setText(Ornaments.oname1);
		t2.setText(Ornaments.desc1);
		t3.setText(Ornaments.price1);
		
		String pth = "http://"+IPsettings.ip+"/"+Ornaments.image1;
     
        Picasso.with((getApplicationContext()))
                .load(pth)
                .placeholder(R.drawable.fm)
                .error(R.drawable.fm).into(im1);
        
		 b1.setOnClickListener(new View.OnClickListener()
		 {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
//					startActivity(new Intent(getApplicationContext(),PHOTO.class));

					String pth = "http://"+IPsettings.ip+"/"+Ornaments.image1;


					Picasso.with(getApplicationContext()).load(pth).transform(new CircleTransform()). into(im1);
				}
			});
		 b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					startActivity(new Intent(getApplicationContext(),Payment.class));
				}
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.model_details, menu);
		return true;
	}

}
