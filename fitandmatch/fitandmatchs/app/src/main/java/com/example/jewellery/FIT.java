package com.example.jewellery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.net.URL;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class FIT extends Activity {
    //====================================================
    private Bitmap myBitmap1, myBitmap2;
    private int width, height;
    private FaceDetector.Face[] detectedFaces;
    private int NUMBER_OF_FACES = 1;
    private FaceDetector faceDetector;
    private int NUMBER_OF_FACE_DETECTED;
    private float eyeDistance;
    //ImageView iv;
    //====================================================

    String method = "fitface";
   
    String url1 = "";
    SharedPreferences sh1;

    //========================================

    ImageView img1;
    TextView tv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit);

        if (android.os.Build.VERSION.SDK_INT > 9) 
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        img1 = (ImageView) findViewById(R.id.imageViewforfit);
        tv = (TextView) findViewById(R.id.textView1);
        b = (Button) findViewById(R.id.buttonfitbook);
        b.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View arg0)
            {
                Intent i = new Intent(getApplicationContext(), Payment.class);
                startActivity(i);
            }
        });

        sh1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url1 = sh1.getString("url1", "");
       /// Toast.makeText(getApplicationContext(), "ur1= " + url1, Toast.LENGTH_SHORT).show();
        System.out.print("sha ur1=" + url1.toString());
        BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
        bitmapFatoryOptions.inPreferredConfig = Bitmap.Config.RGB_565;
//		Bitmap bm1 =BitmapFactory.decodeFile(Imgcap.path,bitmapFatoryOptions);
        Bitmap bm1 = PHOTO.image;
        //Toast.makeText(getApplicationContext(),"bm1..."+bm1.toString(), Toast.LENGTH_LONG).show();

        //        Bitmap bm2 =BitmapFactory.decodeResource(getResources(), R.drawable.abb,bitmapFatoryOptions);

        //     Bitmap bm=rtbtmp();

        try {
           // SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ornamnce = Ornaments.image1;
//			String path=sh.getString("iurl", "");
           // Toast.makeText(getApplicationContext(), "type..." + CustViewOrnaments.cscatname, Toast.LENGTH_LONG).show();
            SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	//	String path="http://"+sh.getString("ip","")+"/ornaments/"+CustProductLists.ciimage;
            String path = "http://" + sh.getString("ip","")+ "/";
//			Toast.makeText(getApplicationContext(),"ornament is"+ornamnce,Toast.LENGTH_LONG).show();
            URL ur1 = new URL(path.concat(ornamnce));
            Bitmap bm2 = BitmapFactory.decodeStream(ur1.openStream(), null, bitmapFatoryOptions);
            //	Toast.makeText(getApplicationContext(),"bm2..."+bm2.toString(), Toast.LENGTH_LONG).show();
            if (Categories.cat1.equalsIgnoreCase("Earring")) {
               // getEars(bm1,bm2,PHOTO.encodedImage);
                Toast.makeText(getApplicationContext(), "type... " + Ornaments.oname1, Toast.LENGTH_LONG).show();
                getFace4ear(bm1, bm2);
            } else if (Categories.cat1.equalsIgnoreCase("hr")) {
                //      Toast.makeText(getApplicationContext(), "type... " + Home.selectedtyp, Toast.LENGTH_LONG).show();
                gethead(bm1, bm2);
            } else {
                //  Toast.makeText(getApplicationContext(), "type... " + Home.selectedtyp, Toast.LENGTH_LONG).show();
                getFace(bm1, bm2);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error   " + e.toString(), Toast.LENGTH_LONG).show();
        }

        img1.setOnTouchListener(new Touch());

    }

    private void gethead(Bitmap bm1, Bitmap bm2) {
        // TODO Auto-generated method stub
        try {
            myBitmap1 = bm1;
            myBitmap2 = bm2;

            width = myBitmap1.getWidth();
            height = myBitmap1.getHeight();
            detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
            faceDetector = new FaceDetector(width, height, NUMBER_OF_FACES);
            NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(myBitmap1, detectedFaces);

            //		Toast.makeText(getApplicationContext(),"faces..."+NUMBER_OF_FACE_DETECTED, Toast.LENGTH_SHORT).show();

            if (NUMBER_OF_FACE_DETECTED > 0) {
                for (int count = 0; count < NUMBER_OF_FACE_DETECTED; count++) {
                    Face face = detectedFaces[count];
                    PointF midPoint = new PointF();
                    face.getMidPoint(midPoint);

                    eyeDistance = face.eyesDistance();
                   /// Toast.makeText(getApplicationContext(), midPoint.x + "--" + midPoint.y + "--" + eyeDistance, Toast.LENGTH_LONG).show();
                   /// Toast.makeText(getApplicationContext(), myBitmap1.getWidth() + "====" + myBitmap1.getHeight(), Toast.LENGTH_LONG).show();

                    //  	Bitmap bm=rtbtmp(myBitmap1, myBitmap2,(int)eyeDistance,(int)midPoint.x,(int)midPoint.y);
                    Bitmap bm = rtbtmphead(myBitmap1, myBitmap2, (int) eyeDistance, (int) midPoint.x, (int) midPoint.y);
                    //	x-eyedst,h1-(y+eyedst),x+eyedst,h1-y+eyedst
                    img1.setImageBitmap(bm);
                    //canvas.drawRect(midPoint.x-eyeDistance, midPoint.y-eyeDistance, midPoint.x+eyeDistance, midPoint.y+eyeDistance, myPaint);


                    //////////////////////////////////////////////////////
                    Paint myPaint = new Paint();
                    myPaint.setColor(Color.GREEN);
                    myPaint.setStyle(Paint.Style.STROKE);
                    myPaint.setStrokeWidth(3);
                    Canvas c = new Canvas(bm);
                    c.drawRect(midPoint.x - eyeDistance, midPoint.y - eyeDistance, midPoint.x + eyeDistance, midPoint.y + eyeDistance, myPaint);


                }
            } else 
            {
                img1.setImageBitmap(bm1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }


    }

    private void getEars(Bitmap b, Bitmap b2, int h, int x, int y) {
        String res = "10*10*10";
        if (res.equalsIgnoreCase("nf")) 
        {

        } else {

            String r[] = res.split("\\*");

            myBitmap1 = b;
            myBitmap2 = b2;

            width = myBitmap1.getWidth();
            height = myBitmap1.getHeight();

//				int x=Integer.parseInt(r[0]);
//				int y=Integer.parseInt(r[1]);
//				int h=Integer.parseInt(r[2]);

//            Bitmap bm = rtbtmpEr(myBitmap1, myBitmap2, h, x - h - (h / 2), y - (h / 2) - 70);
//            27-09-2018
            Bitmap bm = rtbtmpEr(myBitmap1, myBitmap2, h, x - h + 50, y - (h / 2) - 10);
            
//            Bitmap bm = rtbtmp(myBitmap1, myBitmap2, h, x - h - (h / 2), y - (h / 2) - 70);
            
            img1.setImageBitmap(bm);

            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);
            Canvas c = new Canvas(bm);
            c.drawRect(x - h, y, x + h, y + h, myPaint);


        }
    }




    ///////////////////////EAR/////////////////////////////////
    private void getFace4ear(Bitmap b, Bitmap b2) {
//    	BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
//		bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
//		myBitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.fmpass,bitmapFatoryOptions);
//		myBitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.abbb,bitmapFatoryOptions);
        myBitmap1 = b;
        myBitmap2 = b2;

        width = myBitmap1.getWidth();
        height = myBitmap1.getHeight();
        detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
        faceDetector = new FaceDetector(width, height, NUMBER_OF_FACES);
        NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(myBitmap1, detectedFaces);

        Toast.makeText(getApplicationContext(), "faces..." + NUMBER_OF_FACE_DETECTED, Toast.LENGTH_SHORT).show();

        if (NUMBER_OF_FACE_DETECTED > 0) {
            for (int count = 0; count < NUMBER_OF_FACE_DETECTED; count++) {
                Face face = detectedFaces[count];
                PointF midPoint = new PointF();
                face.getMidPoint(midPoint);

                eyeDistance = face.eyesDistance();
                ///Toast.makeText(getApplicationContext(), midPoint.x + "--" + midPoint.y + "--" + eyeDistance, Toast.LENGTH_LONG).show();
               /// Toast.makeText(getApplicationContext(), myBitmap1.getWidth() + "====" + myBitmap1.getHeight(), Toast.LENGTH_LONG).show();

                //  	Bitmap bm=rtbtmp(myBitmap1, myBitmap2,(int)eyeDistance,(int)midPoint.x,(int)midPoint.y);
                getEars(myBitmap1, myBitmap2, (int) eyeDistance, (int) midPoint.x, (int) midPoint.y);

//        	Bitmap bm=rtbtmp(myBitmap1, myBitmap2,(int)eyeDistance,(int)midPoint.x,(int)midPoint.y);
//    	//	x-eyedst,h1-(y+eyedst),x+eyedst,h1-y+eyedst
//    		img1.setImageBitmap(bm);
//        	//canvas.drawRect(midPoint.x-eyeDistance, midPoint.y-eyeDistance, midPoint.x+eyeDistance, midPoint.y+eyeDistance, myPaint);
//
//
//    		//////////////////////////////////////////////////////
//    		Paint myPaint = new Paint();
//            myPaint.setColor(Color.GREEN);
//            myPaint.setStyle(Paint.Style.STROKE);
//            myPaint.setStrokeWidth(3);
//            Canvas c = new Canvas(bm);
//    		c.drawRect(midPoint.x-eyeDistance, midPoint.y-eyeDistance, midPoint.x+eyeDistance, midPoint.y+eyeDistance, myPaint);


            }
        } else 
        {
            img1.setImageBitmap(b);
        }
    }
    //////////////////////////////////////////////////////////


    private void getFace(Bitmap b, Bitmap b2) {
//	    	BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
//			bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
//			myBitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.fmpass,bitmapFatoryOptions);
//			myBitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.abbb,bitmapFatoryOptions);
        myBitmap1 = b;
        myBitmap2 = b2;

        width = myBitmap1.getWidth();
        height = myBitmap1.getHeight();
        detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
        faceDetector = new FaceDetector(width, height, NUMBER_OF_FACES);
        NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(myBitmap1, detectedFaces);

        Toast.makeText(getApplicationContext(), "faces..." + NUMBER_OF_FACE_DETECTED, Toast.LENGTH_SHORT).show();

        if (NUMBER_OF_FACE_DETECTED > 0) {
            for (int count = 0; count < NUMBER_OF_FACE_DETECTED; count++) {
                Face face = detectedFaces[count];
                PointF midPoint = new PointF();
                face.getMidPoint(midPoint);

                eyeDistance = face.eyesDistance();
               /// Toast.makeText(getApplicationContext(), midPoint.x + "--" + midPoint.y + "--" + eyeDistance, Toast.LENGTH_LONG).show();
               /// Toast.makeText(getApplicationContext(), myBitmap1.getWidth() + "====" + myBitmap1.getHeight(), Toast.LENGTH_LONG).show();

                //  	Bitmap bm=rtbtmp(myBitmap1, myBitmap2,(int)eyeDistance,(int)midPoint.x,(int)midPoint.y);
                Bitmap bm = rtbtmp(myBitmap1, myBitmap2, (int) eyeDistance, (int) midPoint.x, (int) midPoint.y);
                //	x-eyedst,h1-(y+eyedst),x+eyedst,h1-y+eyedst
                img1.setImageBitmap(bm);
                //canvas.drawRect(midPoint.x-eyeDistance, midPoint.y-eyeDistance, midPoint.x+eyeDistance, midPoint.y+eyeDistance, myPaint);


                //////////////////////////////////////////////////////
                Paint myPaint = new Paint();
                myPaint.setColor(Color.GREEN);
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(3);
                Canvas c = new Canvas(bm);
                c.drawRect(midPoint.x - eyeDistance, midPoint.y - eyeDistance, midPoint.x + eyeDistance, midPoint.y + eyeDistance, myPaint);

            }
        } else {
            img1.setImageBitmap(b);
        }
    }

    public Bitmap rtbtmpEr(Bitmap bitmap1, Bitmap bitmap2, int eyedst, int x, int y) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = getResources();
            Drawable drawable1 = new BitmapDrawable(bitmap1);
            Drawable drawable2 = new BitmapDrawable(bitmap2);
            drawable1.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
            //    drawable2.setBounds(bitmap1.getWidth()-x-eyedst,bitmap1.getHeight()-y-eyedst,bitmap1.getWidth()-x+eyedst,bitmap1.getHeight()-eyedst);
            int w1 = bitmap1.getWidth();
            int h1 = bitmap1.getHeight();
            int w2 = bitmap2.getWidth();
            int h2 = bitmap2.getHeight();

//            drawable2.setBounds(x - eyedst - 45, y + eyedst + 40, x + eyedst + 20, y + (3 * eyedst) + 50);
            drawable2.setBounds(x - (eyedst/2) - 80, y + (eyedst) + 60, x + (eyedst/3), y + (2 * eyedst) + 20);
//            drawable2.setBounds(x - eyedst - 10, y + (2 * eyedst) - 10, x + eyedst + 10, y + (4 * eyedst) + 20);
//           drawable2.setBounds(x-eyedst,w2,x+eyedst,h1-eyedst);
//          drawable2.setBounds(x-eyedst,w2,x+eyedst,h2+eyedst);
//           drawable2.setBounds(x-((int)eyedst/2),y+((int)eyedst/2)+60,x+((int)eyedst/2),y+2*((int)eyedst/2)+60);

            drawable1.draw(c);
            drawable2.draw(c);

        } catch (Exception e) {
        }
        return bitmap;
    }

    public Bitmap rtbtmp(Bitmap bitmap1, Bitmap bitmap2, int eyedst, int x, int y) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = getResources();
            Drawable drawable1 = new BitmapDrawable(bitmap1);
            Drawable drawable2 = new BitmapDrawable(bitmap2);
            drawable1.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
            //    drawable2.setBounds(bitmap1.getWidth()-x-eyedst,bitmap1.getHeight()-y-eyedst,bitmap1.getWidth()-x+eyedst,bitmap1.getHeight()-eyedst);
            int w1 = bitmap1.getWidth();
            int h1 = bitmap1.getHeight();
            int w2 = bitmap2.getWidth();
            int h2 = bitmap2.getHeight();

            //           drawable2.setBounds(w1-x-eyedst,h1-y-eyedst,w1-x+eyedst,h1);
            //drawable2.setBounds(x-eyedst,w2,x+eyedst,h1-eyedst);
            //       drawable2.setBounds(x-eyedst,w2,x+eyedst,h2+eyedst);
            drawable2.setBounds(x - eyedst - 10, y + (2 * eyedst) - 10, x + eyedst + 10, y + (4 * eyedst) + 20);
            //  drawable2.setBounds(y+(2*eyedst)-10,x-eyedst-10,y+(4*eyedst)+20,x+eyedst+10);
            // drawable2.setBounds(x-50,y,x-50,y);

            drawable1.draw(c);
            drawable2.draw(c);

        } catch (Exception e) {
        }
        return bitmap;
    }

    public Bitmap rtbtmphead(Bitmap bitmap1, Bitmap bitmap2, int eyedst, int x, int y) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = getResources();
            Drawable drawable1 = new BitmapDrawable(bitmap1);
            Drawable drawable2 = new BitmapDrawable(bitmap2);
            drawable1.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
            drawable2.setBounds(bitmap1.getWidth() - x - eyedst - 90, bitmap1.getHeight() - y - (2 * eyedst) - 20, bitmap1.getWidth() - x + eyedst + (eyedst / 2), bitmap1.getHeight() - eyedst - 2 * eyedst);
            // drawable2.setBounds(bitmap1.getWidth()-x-eyedst,bitmap1.getHeight()-y-(2*eyedst),bitmap1.getWidth()-x+eyedst+(eyedst/2),bitmap1.getHeight()-eyedst);
            int w1 = bitmap1.getWidth();
            int h1 = bitmap1.getHeight();
            int w2 = bitmap2.getWidth();
            int h2 = bitmap2.getHeight();

//	            drawable2.setBounds(x+30,y-20,x-10,y+20);

            drawable1.draw(c);
            drawable2.draw(c);

        } catch (Exception e) {
        }
        return bitmap;
    }

    public Bitmap rtbtmp() {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(1000, 1000, Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = getResources();
            Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.fm); //blue
            Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.fm); //green
            Drawable drawable1 = new BitmapDrawable(bitmap1);
            Drawable drawable2 = new BitmapDrawable(bitmap2);
            drawable1.setBounds(10, 10, 1000, 1000);
            drawable2.setBounds(290, 550, 740, 950);
            drawable1.draw(c);
            drawable2.draw(c);
        } catch (Exception e) {
        }
        return bitmap;
    }


    private Bitmap combineImages(Bitmap c, Bitmap s) {
        // TODO Auto-generated method stub
        Bitmap cs = null;

        int width, height = 0;

        //  if(c.getWidth() > s.getWidth()) {
        width = c.getWidth();
        height = c.getHeight();// + s.getHeight();
//       } else {
//         width = s.getWidth();
//         height = c.getHeight() + s.getHeight();
//       }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, width - 300, height - 300, null);

        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location
       /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";

       OutputStream os = null;
       try {
         os = new FileOutputStream(loc + tmpImg);
         cs.compress(CompressFormat.PNG, 100, os);
       } catch(IOException e) {
         Log.e("combineImages", "problem combining images", e);
       }*/

        return cs;
    }

    private class MyView extends View 
    {
        private Bitmap myBitmap;
        private int width, height;
        private FaceDetector.Face[] detectedFaces;
        private int NUMBER_OF_FACES = 1;
        private FaceDetector faceDetector;
        private int NUMBER_OF_FACE_DETECTED;
        private float eyeDistance;

        public MyView(Context context) {
            super(context);
            BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
            bitmapFatoryOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fm, bitmapFatoryOptions);
            width = myBitmap.getWidth();
            height = myBitmap.getHeight();
            detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
            faceDetector = new FaceDetector(width, height, NUMBER_OF_FACES);
            NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(myBitmap, detectedFaces);
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(myBitmap, 0, 0, null);
            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.fm);

            for (int count = 0; count < NUMBER_OF_FACE_DETECTED; count++) 
            {
                Face face = detectedFaces[count];
                PointF midPoint = new PointF();
                face.getMidPoint(midPoint);
                Log.d("xxxxx", midPoint.x + "----" + midPoint.y);

              ///  Toast.makeText(getApplicationContext(), midPoint.x + "", Toast.LENGTH_SHORT).show();
                Log.d("yyyyyyyy", face.EULER_Y + "");
                Log.d("zzzzzz", face.EULER_Z + "");
                eyeDistance = face.eyesDistance();
                canvas.drawRect(midPoint.x - eyeDistance, midPoint.y - eyeDistance, midPoint.x + eyeDistance, midPoint.y + eyeDistance, myPaint);
//	            	canvas.draw

//	            	Matrix matrix = iv.getImageMatrix();//new Matrix();
//	            	matrix.postScale((float)0.5,(float)0.5, midPoint.x, midPoint.y);
//	            	iv.setImageMatrix(matrix);     	

            }
        }

    }
}
