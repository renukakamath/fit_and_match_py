package com.example.jewellery;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registration extends Activity implements JsonResponse {
	
	EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
	Button b1;
	RadioButton r0,r1,r2;
	String fname,lname,hname,place,phone,gen,email,pin,uname,pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		r0=(RadioButton)findViewById(R.id.radio0);
		r1=(RadioButton)findViewById(R.id.radio1);
		r2=(RadioButton)findViewById(R.id.radio2);
		e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
		e8=(EditText)findViewById(R.id.editText8);
		e9=(EditText)findViewById(R.id.editText9);
		e10=(EditText)findViewById(R.id.editText10);
		
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				fname=e1.getText().toString();
				lname=e2.getText().toString();
				hname=e3.getText().toString();
				phone=e4.getText().toString();
				email=e6.getText().toString();
				place=e7.getText().toString();
				pin=e8.getText().toString();
				uname=e9.getText().toString();
				pass=e10.getText().toString();
				if(r0.isChecked())
				{
					gen="Male";
				}
				else if(r1.isChecked())
				{
					gen="Female";
				}
				else
				{
					gen="Other";
				}
				if(fname.equalsIgnoreCase(""))
				{
					
					e1.setError("please fill this field");
					e1.setFocusable(true);
				}
				else if(lname.equalsIgnoreCase(""))
				{
					
					e2.setError("please fill this field");
					e2.setFocusable(true);
				}
				
				else if(hname.equalsIgnoreCase(""))
				{
					
					e3.setError("please fill this field");
					e3.setFocusable(true);
				}
				else if(phone.equalsIgnoreCase(""))
				{
					
					e4.setError("please fill this field");
					e4.setFocusable(true);
				}
				else if(phone.length()!=10)
				{
					
					e4.setError("enter valid phone number");
					e4.setFocusable(true);
				}
				else if(email.equalsIgnoreCase(""))
				{
					
					e6.setError("please fill this field");
					e6.setFocusable(true);
				}
				else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
				{
					
					e6.setError("enter valid email address");
					e6.setFocusable(true);
				}
				else if(place.equalsIgnoreCase(""))
				{
					
					e7.setError("please fill this field");
					e7.setFocusable(true);
				}
				else if(pin.equalsIgnoreCase(""))
				{
					
					e8.setError("please fill this field");
					e8.setFocusable(true);
				}
				else if(uname.equalsIgnoreCase(""))
				{
					
					e9.setError("please fill this field");
					e9.setFocusable(true);
				}
				else if(pass.equalsIgnoreCase(""))
				{
					
					e10.setError("please fill this field");
					e10.setFocusable(true);
				}
				else
				{
					JsonReq JR=new JsonReq();
			        JR.json_response=(JsonResponse) Registration.this;
			        String q = "/registration?firstname="+fname+"&lastname="+lname+"&housename="+hname+"&phonenumber="+phone+"&gen="+gen+"&email="+email+"&place="+place+"&pin="+pin+"&uname="+uname+"&pass="+pass;
			        q=q.replace(" ","%20");
			        JR.execute(q);
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
	
	
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try {
			String status=jo.getString("status");
			Log.d("pearl",status);
			
			
			if(status.equalsIgnoreCase("success")){
				
				
			 
				 startActivity(new Intent(getApplicationContext(),Login.class));	
			    
				 Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
				 
			}
			else if(status.equalsIgnoreCase("duplicate")){
				
				
				 
				 Toast.makeText(getApplicationContext(), "Username already Exist...", Toast.LENGTH_LONG).show();
				 
			}
			else
			{
				Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			  Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
	}

}

	
	
	


