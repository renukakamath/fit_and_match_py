package com.example.jewellery;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custom_cart extends ArrayAdapter<String> { 
	//needs to extend ArrayAdapter

    private String[] orid;  
    private String[] oname;  
    private String[] quantity;
    private String[] price;
  
    //for custom view name item
    private String[] img;	     //for custom view photo items
    private Activity context;       //for to get current activity context

    public Custom_cart(Activity context, String[] orid,String[] oname,String[] quantity,String[] price, String[] img) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.custom_cart, oname);
        this.context = context;
        this.orid = orid;
        this.oname = oname;
        this.img = img;
        this.quantity = quantity;
        this.price = price;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                 //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_cart, null, true);
		//cust_list_view is xml file of layout created in step no.2

        TextView t1 = (TextView) listViewItem.findViewById(R.id.textView1);
        TextView t2 = (TextView) listViewItem.findViewById(R.id.textView2);
        TextView t3 = (TextView) listViewItem.findViewById(R.id.textView3);
        
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView1);

        t1.setText("Product Name     :"+oname[position]);
        t2.setText("Quantity  :"+quantity[position]);
        t3.setText("Price     :"+price[position]);
        
        String pth = "http://"+IPsettings.ip+"/"+img[position];
        
        Log.d("-------------", pth);
      //  Toast.makeText(context,pth,Toast.LENGTH_LONG).show();	
        Picasso.with(getContext())
                .load(pth)
                .placeholder(R.drawable.fm)
                .error(R.drawable.fm).into(image);
        
        return  listViewItem;
    }

	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
