package com.example.jewellery;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Trackorder extends Activity implements JsonResponse, OnItemClickListener{
	ListView l; 
	String[] orid,oname,qnty,amount,img,statuss,orderid; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trackorder);
		
		
		l=(ListView)findViewById(R.id.listView1);
		l.setOnItemClickListener(this);
		
		JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) Trackorder.this;
        String q = "/track_order?logid="+Login.logid;
        q=q.replace(" ","%20");
        JR.execute(q);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	
	public void response(JSONObject jo) {
		
		
		try {
			
			
			String status=jo.getString("status");
		//	Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
			if(status.equalsIgnoreCase("success")){
			
				JSONArray ja1=(JSONArray)jo.getJSONArray("data");
				  orid=new String[ja1.length()];
				  oname=new String[ja1.length()];
				  qnty=new String[ja1.length()];
			      amount=new String[ja1.length()];
			      statuss=new String[ja1.length()];
			      img=new String[ja1.length()];
		     
				for(int i = 0;i<ja1.length();i++)
				{
					orid[i]=ja1.getJSONObject(i).getString("pro_id");
					oname[i]=ja1.getJSONObject(i).getString("pro_name");
				    qnty[i]=ja1.getJSONObject(i).getString("qty");
					amount[i]=ja1.getJSONObject(i).getString("tot_price");
					statuss[i]=ja1.getJSONObject(i).getString("order_status");
					img[i]=ja1.getJSONObject(i).getString("pro_img");
							
				}
				l.setAdapter(new Custom_trackorder(this, orid,oname, qnty, amount,statuss, img));
			
			}
			
			else {
				Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
	
		} 
	

     }catch (Exception e) {
    // TODO: handle exception

      Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
    }
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1,
			int arg2, long arg3) {
		// TODO Auto-generated method stub

	
	}
}

	
	
	
	
	

