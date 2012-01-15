package world.of.words;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Repo extends Activity implements OnClickListener {

	SQLiteDatabase myDB;
	TextView tv[]=new TextView[10];
	RelativeLayout myRelativeLayout;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repo);
		myRelativeLayout=(RelativeLayout) findViewById(R.id.repo);
		myDB=this.openOrCreateDatabase("arjun.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                "MYTABLE" +
                " (NAME VARCHAR, SCORE INT(5));");
    	tv[0]=(TextView) findViewById(R.id.textView3);
		tv[1]=(TextView) findViewById(R.id.textView4);
		tv[2]=(TextView) findViewById(R.id.textView5);
		tv[3]=(TextView) findViewById(R.id.textView6);
		tv[4]=(TextView) findViewById(R.id.textView7);
		tv[5]=(TextView) findViewById(R.id.textView8);
		tv[6]=(TextView) findViewById(R.id.textView9);
		tv[7]=(TextView) findViewById(R.id.textView10);
		tv[8]=(TextView) findViewById(R.id.textView11);
		tv[9]=(TextView) findViewById(R.id.textView12);
		TextView t=(TextView) findViewById(R.id.textView1);
		TextView t1=(TextView) findViewById(R.id.textView2);
		t.setTextColor(Color.BLACK);
		t1.setTextColor(Color.BLACK);
		for(int j=0;j<10;j++){
			tv[j].setText("");
			tv[j].setTextColor(Color.BLACK);
		}
		Button bt=(Button) findViewById(R.id.clearScore);
		bt.setOnClickListener(this);
		
		
		
        Cursor c = myDB.rawQuery("SELECT NAME, SCORE FROM MYTABLE ORDER BY(SCORE) DESC", null);
        
        if (c != null ) {
        	int a=0;
              if  (c.moveToFirst()) {
                    do {
                  String firstName = c.getString(c.getColumnIndex("NAME"));
                    int age = c.getInt(c.getColumnIndex("SCORE"));
                    Log.d("arjun", firstName+" "+Integer.toString(age));
                    tv[a++].setText(firstName);
                    tv[a++].setText(Integer.toString(age));
                    if(a==10)
                    	break;
                    }while (c.moveToNext());
              } 
         }
     
        myRelativeLayout.setBackgroundColor(Color.WHITE);
        c.close();
        //   myDB.execSQL("DELETE FROM MYTABLE");
        
    }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.clearScore){
			
			new AlertDialog.Builder(this)
		    .setTitle("Delete entry")
		    .setMessage("Are you sure you want to clear High Scores?")
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	myDB.execSQL("DELETE FROM MYTABLE");
		        	for(int j=0;j<10;j++)
		    			tv[j].setText("");
		        	Button bt=(Button) findViewById(R.id.clearScore);
		    		bt.setClickable(false);
		    	}
		     })
		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		     .show();
		}
	
		
	}
	
}

