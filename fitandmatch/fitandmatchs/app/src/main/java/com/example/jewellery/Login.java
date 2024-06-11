package com.example.jewellery;

import org.json.JSONArray;
import org.json.JSONObject;


import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements JsonResponse {
	EditText e1,e2;
	Button b;
	TextView t;
	String uname,pass;
	
	public static String logid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
	    t=(TextView)findViewById(R.id.textView2);
		b=(Button)findViewById(R.id.button1);
		
		
		
        t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				
				
				
				startActivity(new Intent(getApplicationContext(),Registration.class));
				
				
				
			}
        });
		
		
		
		
		
		
		
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				uname=e1.getText().toString();
				pass=e2.getText().toString();
			
				
				
				JsonReq JR=new JsonReq();
		        JR.json_response=(JsonResponse) Login.this;
		        String q = "/login?username="+uname+"&password="+pass;
		        q=q.replace(" ","%20");
		        JR.execute(q);
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try {
			String status=jo.getString("status");
			if(status.equalsIgnoreCase("success")){
				JSONArray ja1=(JSONArray)jo.getJSONArray("data");
				logid=ja1.getJSONObject(0).getString("log_id");
			startActivity(new Intent(getApplicationContext(),Customer_homepage.class));
				
			} else {
				Toast.makeText(getApplicationContext(),"Login failed..!",Toast.LENGTH_LONG).show();
			}
				
		}catch (Exception e) {
			// TODO: handle exception
			
			  Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public void onBackPressed() 
	 {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent b=new Intent(getApplicationContext(),IPsettings.class);			
			startActivity(b);
	}

}
	
	
	


