package com.example.jewellery;


import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pay extends Activity implements JsonResponse
{
	EditText e,ecard,ecvv,edate;
	Button b;
	String amt,card,cvv,dt;
	SharedPreferences sh;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		
		e=(EditText)findViewById(R.id.editText1);
		ecard=(EditText)findViewById(R.id.editText2);
		ecvv=(EditText)findViewById(R.id.editText3);
		edate=(EditText)findViewById(R.id.editText4);
		e.setText(Cart_view.total+"");
		e.setEnabled(false);
		b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				amt=e.getText().toString();
				card=ecard.getText().toString();
				cvv=ecvv.getText().toString();
				dt=edate.getText().toString();
				
				if(card.equalsIgnoreCase(""))
				{
					
				 ecard.setError("enter card number");
				 ecard.setFocusable(true);
					
				}
				else if(card.length()!=16)
				{
					
					 ecard.setError("enter valid card number(16 digit)");
					 ecard.setFocusable(true);
						
					}
				else if(cvv.equalsIgnoreCase(""))
				{
					
					 ecvv.setError("enter your cvv");
					 ecvv.setFocusable(true);
						
					}
				else if(cvv.length()!=3)
				{
					
					 ecvv.setError("enter valid cvv (3 digit)");
					 ecvv.setFocusable(true);
						
					}
				else if(dt.equalsIgnoreCase(""))
				{
					
					 edate.setError("enter valid date");
					 edate.setFocusable(true);
						
					}
				
				else 
				{
					JsonReq JR=new JsonReq();
			        JR.json_response=(JsonResponse)Pay.this;
			        String q = "/buy?logid="+Login.logid;
			        q=q.replace(" ","%20");
			        JR.execute(q);
		
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pay, menu);
		return true;
	}
	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try{
			String status=jo.getString("status");
		
			if(status.equalsIgnoreCase("success"))
			{
				Toast.makeText(getApplicationContext(),"order placed..", Toast.LENGTH_LONG).show();
				
				startActivity(new Intent(getApplicationContext(),Customer_homepage.class));
				
           	 
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Failed..", Toast.LENGTH_LONG).show();
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "exp : "+e, Toast.LENGTH_LONG).show();
		}
	}
}
