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

public class Categories extends Activity implements JsonResponse , OnItemClickListener {
	ListView l;
	String[] catid,cat,RES; 
	public static String catid1,cat1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		
		l=(ListView)findViewById(R.id.listView1);
		l.setOnItemClickListener(this);
		
		JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) Categories.this;
        String q = "/view_categories";
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
				catid=new String[ja1.length()];
				cat=new String[ja1.length()];
				RES=new String[ja1.length()];
				for(int i = 0;i<ja1.length();i++)
				{
					cat[i]=ja1.getJSONObject(i).getString("category");
					catid[i]=ja1.getJSONObject(i).getString("cat_id");
		            RES[i]="\n"+cat[i]+" \n";
				}
				 l.setAdapter(new ArrayAdapter<String>(getApplicationContext(),  R.layout.cust_list,RES));
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
			catid1=catid[arg2];
			cat1=cat[arg2];
			startActivity( new Intent (getApplicationContext(),Ornaments.class));
		}
}

