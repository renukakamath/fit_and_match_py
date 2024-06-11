package com.example.jewellery;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Ornaments extends Activity implements JsonResponse , OnItemClickListener {
	ListView l;
	String[] onid,cat,oname,image,desc,price,RES; 
	public static String onid1,cat1,oname1,image1,desc1,price1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ornaments);
		
		l=(ListView)findViewById(R.id.listView1);
		l.setOnItemClickListener(this);
		
		JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) Ornaments.this;
        String q = "/view_ornament?catid="+Categories.catid1;
        q=q.replace(" ","%20");
        JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ornaments, menu);
		return true;
	}

	public void response(JSONObject jo) {
		
		try {
			String status=jo.getString("status");
		//	Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
			if(status.equalsIgnoreCase("success")){
			
				JSONArray ja1=(JSONArray)jo.getJSONArray("data");
				onid=new String[ja1.length()];
				cat=new String[ja1.length()];
				oname=new String[ja1.length()];
				desc=new String[ja1.length()];
				price=new String[ja1.length()];
				image=new String[ja1.length()];
				RES=new String[ja1.length()];
				for(int i = 0;i<ja1.length();i++)
				{
					cat[i]=ja1.getJSONObject(i).getString("category");
				    oname[i]=ja1.getJSONObject(i).getString("pro_name");
				    onid[i]=ja1.getJSONObject(i).getString("pro_id");
				    desc[i]=ja1.getJSONObject(i).getString("pro_desc");
					price[i]=ja1.getJSONObject(i).getString("pro_price");
					image[i]=ja1.getJSONObject(i).getString("pro_img");
		            RES[i]=oname[i]+" \n";
				}
			 l.setAdapter(new cust_list(this, oname, image));
			}
			else 
			{
				Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
			} 
	
		}catch (Exception e) {
		// TODO: handle exception
		
		  Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
		}
	}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) 
		{
			// TODO Auto-generated method stub
			onid1=onid[arg2];
			oname1=oname[arg2];
			image1=image[arg2];
			desc1=desc[arg2];
			price1=price[arg2];
			
			startActivity( new Intent (getApplicationContext(),Model_details.class));
		}
}

