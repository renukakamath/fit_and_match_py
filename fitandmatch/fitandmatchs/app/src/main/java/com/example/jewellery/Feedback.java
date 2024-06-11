package com.example.jewellery;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Feedback extends Activity implements JsonResponse {
	
	
	EditText e1;
	Button b1;
	String feedback;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		
		
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				feedback=e1.getText().toString();
				if(feedback.equalsIgnoreCase(""))
				{
					
					e1.setError("please fill this field");
					e1.setFocusable(true);
				}
				else
				{
					JsonReq JR=new JsonReq();
			        JR.json_response=(JsonResponse) Feedback.this;
			        String q = "/feedback?feedbacks="+feedback+"&log_id="+Login.logid;
			        q=q.replace(" ","%20");
			        JR.execute(q);
				}
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
		return true;
	}
	
	
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try {
			String status=jo.getString("status");
			
			
			if(status.equalsIgnoreCase("success")){
				
			
			
			Toast.makeText(getApplicationContext(),"successfully added",Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(),Customer_homepage.class));
				
			} else {
				Toast.makeText(getApplicationContext(),"feedback not send..!",Toast.LENGTH_LONG).show();
			}
				
			
		}catch (Exception e) {
			// TODO: handle exception
			
			  Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	

}
