package wow.post.hrs50;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AllMissed extends Activity implements OnClickListener {

	SQLiteDatabase myDB;
	ArrayList<String> words = new ArrayList<String>();
	ArrayAdapter<String> adapter ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmissed);
    	myDB=this.openOrCreateDatabase("missed.db", MODE_PRIVATE, null);
		myDB.execSQL("CREATE TABLE IF NOT EXISTS MISSED" +
                " (WORD VARCHAR);");
		Cursor c = myDB.rawQuery("SELECT * FROM MISSED", null);
		Log.d("arjun", "onCreate");
        
        if (c != null ) {
     
              if  (c.moveToFirst()) {
                    do {
                  String firstName = c.getString(c.getColumnIndex("WORD"));
                  words.add(firstName);
                    }while (c.moveToNext());
                    
              } 
         }
        ListView m_listview = (ListView) findViewById(R.id.list);
        set();
  	      m_listview.setAdapter(adapter);  
  	     c.close(); 
  	      m_listview.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	              // When clicked, show a toast with the TextView text
	            	wordClass.selected=(String)((TextView)view).getText();
	            	Intent intent=new Intent(AllMissed.this,Missed.class);
					startActivity(intent);
	            	//Intent intent=new Intent(CustomListViewActivity.this,Chumma.class);
			         // startActivity(intent);
	            	   
	            }
	          });
  	      Button bt=(Button) findViewById(R.id.clearAll);
  	      bt.setOnClickListener(this);
	
	
	}
	
	 @Override
	    protected void onStart() {
	        super.onStart();
	        Log.d("arjun","onStart");
	        
	        set();
	  	     
	        // The activity is about to become visible.
	    }
	 @Override
	    protected void onResume() {
	        super.onResume();
	        Log.d("arjun","onResume");
	        // The activity has become visible (it is now "resumed").
	    }
	 
	 @Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
		
			Intent intent=new Intent(AllMissed.this,StartScreen.class);	
			startActivity(intent);
			finish();
		
		}
	 
	 public void set(){
		 ListView m_listview = (ListView) findViewById(R.id.list);
		 words.remove(wordClass.missedRemoved); 
		 adapter =
	  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
		 m_listview.setAdapter(adapter);  
	 }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.clearAll){
			new AlertDialog.Builder(this)
		    .setTitle("Clear All Missed Words")
		    .setMessage("Are you sure you want to clear the list of Missed Words?")
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	getApplicationContext().deleteDatabase("missed.db");
		        	 words.clear();
		        	 words.removeAll(words);
		        	 wordClass.missedRemoved=wordClass.selected;
		        	 set();
		        	 Button bt=(Button) findViewById(R.id.clearAll);
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
