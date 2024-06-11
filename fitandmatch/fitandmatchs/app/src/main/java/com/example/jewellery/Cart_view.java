package com.example.jewellery;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Cart_view extends Activity  implements JsonResponse{
	ListView l;
	Button b;
	TextView t;
	String[] orid,oname,qnty,amount,img,orderid; 
	int tot = 0; 
	public static int total = 0; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_view);
		
		l=(ListView)findViewById(R.id.listView1);
		
		t=(TextView)findViewById(R.id.textView2);
		b=(Button)findViewById(R.id.button1);
		
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 startActivity(new Intent(getApplicationContext(),Pay.class));
				 
				
			}
		});
		JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Cart_view.this;
        String q = "/view_cart?logid="+Login.logid;
        q=q.replace(" ","%20");
        JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart_view, menu);
		return true;
	}
	
	public void response(JSONObject jo) {
		try {
			String method=jo.getString("method");
			if(method.equalsIgnoreCase("view_cart")){
			String status=jo.getString("status");
		//	Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
			if(status.equalsIgnoreCase("success")){
			JSONArray ja1=(JSONArray)jo.getJSONArray("data");
				 
			  orid=new String[ja1.length()];
			  oname=new String[ja1.length()];
			  qnty=new String[ja1.length()];
		      amount=new String[ja1.length()];
		      img=new String[ja1.length()];
		      
				for(int i = 0;i<ja1.length();i++)
				{
				orid[i]=ja1.getJSONObject(i).getString("pro_id");
				oname[i]=ja1.getJSONObject(i).getString("pro_name");
			    qnty[i]=ja1.getJSONObject(i).getString("qty");
				amount[i]=ja1.getJSONObject(i).getString("tot_price");
				img[i]=ja1.getJSONObject(i).getString("pro_img");
				tot=tot+Integer.parseInt(amount[i]);
               l.setAdapter(new Custom_cart(this, orid,oname, qnty, amount, img));
				
				}
		       t.setText("Total amount : " + tot);
		       total=tot;
		    //   Toast.makeText(getApplicationContext(),tot, Toast.LENGTH_LONG).show();
		       
			}
			
			else {
				Toast.makeText(getApplicationContext(), "no items in cart", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(),Customer_homepage.class));
				 
	
		} 
			}
		

}catch (Exception e) {
// TODO: handle exception

  Toast.makeText(getApplicationContext(),"exp.."+e.toString(), Toast.LENGTH_LONG).show();
}

	}
}
