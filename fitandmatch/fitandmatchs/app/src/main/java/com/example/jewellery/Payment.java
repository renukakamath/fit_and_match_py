package com.example.jewellery;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends Activity implements JsonResponse {
	EditText e1,e2,e3,e4;
	Spinner s;
	Button b1;
	String ortype,totamount,status,modid,qnty,amount,odms_id;
    String[] quantity={"1","2","3"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		s=(Spinner)findViewById(R.id.spinner1);
		b1=(Button)findViewById(R.id.button1);
		 b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				qnty=s.getSelectedItem().toString();
				int tot=Integer.parseInt(qnty)*Integer.parseInt(Ornaments.price1);
				JsonReq JR=new JsonReq();
		        JR.json_response=(JsonResponse) Payment.this;
		        String q = "/addcart?logid="+Login.logid+"&qua="+qnty+"&pid="+Ornaments.onid1+"&tot="+tot+"";
		        q=q.replace(" ","%20");
		        JR.execute(q);
			}
		});
	    s.setAdapter(new ArrayAdapter<String>(getApplicationContext(),  R.layout.cust_list,quantity));;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment, menu);
		return true;
	}
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try {
			String status=jo.getString("status");
			Log.d("pearl",status);
			if(status.equalsIgnoreCase("success")){
				
				 startActivity(new Intent(getApplicationContext(),Customer_homepage.class));	
				 Toast.makeText(getApplicationContext(), "ADD TO CART", Toast.LENGTH_LONG).show();
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
