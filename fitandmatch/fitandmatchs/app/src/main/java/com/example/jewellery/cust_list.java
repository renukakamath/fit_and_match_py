package com.example.jewellery;

import com.squareup.picasso.Picasso;

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

public class cust_list extends ArrayAdapter<String> 
{ 
	//needs to extend ArrayAdapter

    private String[] oname;         //for custom view name item
    private String[] img;	     //for custom view photo items
    private Activity context;       //for to get current activity context

    public cust_list(Activity context, String[] oname, String[] img) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.customlist, oname);
        this.context = context;
        this.oname = oname;
        this.img = img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                 //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.customlist, null, true);
		//cust_list_view is xml file of layout created in step no.2

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textView1);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView1);

        textViewName.setText(oname[position]);
        String pth = "http://"+IPsettings.ip+"/"+img[position];
        
        Log.d("-------------", pth);
       // Toast.makeText(context,pth,Toast.LENGTH_LONG).show();	
        Picasso.with(getContext())
                .load(pth)
                .placeholder(R.drawable.fm)
                .error(R.drawable.fm).into(image);
        
        return  listViewItem;
    }
	private Context getApplicationContext()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
