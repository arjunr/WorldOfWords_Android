package wow.post.hrs50;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

public class Missed extends TabActivity implements OnClickListener {

	SQLiteDatabase myDB;
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> words3 = new ArrayList<String>();
	ArrayList<String> words4 = new ArrayList<String>();
	ArrayList<String> words5 = new ArrayList<String>();
	ArrayList<String> words6 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter3,adapter4,adapter5,adapter6; 
	ListView m_listview;
	String[] me;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.possible);
        myDB=this.openOrCreateDatabase("missed.db", MODE_PRIVATE, null);
        Cursor c = myDB.rawQuery("SELECT * FROM "+wordClass.selected, null);
        
        if (c != null ) {
     
              if  (c.moveToFirst()) {
                    do {
                  String firstName = c.getString(c.getColumnIndex("WORD"));
                  words.add(firstName);
                  
                    }while (c.moveToNext());
              } 
         }
        c.close();
                me=words.toArray(new String[words.size()]);
        
        for(int i=0;i<me.length;i++){
        	if(me[i].length()==3)
        		words3.add(me[i]);
        	else if(me[i].length()==4)
        		words4.add(me[i]);
        	else if(me[i].length()==5)
        		words5.add(me[i]);
        	else if(me[i].length()==6)
        		words6.add(me[i]);
        }
        TabHost mTabHost = getTabHost();
       
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words3);
	        adapter4 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words4);
	        adapter5 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words5);
	        adapter6 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words6);
        
	        m_listview = (ListView) findViewById(R.id.list);
	        m_listview.setAdapter(adapter3);
	        m_listview = (ListView) findViewById(R.id.list4);
	        m_listview.setAdapter(adapter4);
	        m_listview = (ListView) findViewById(R.id.list5);
	        m_listview.setAdapter(adapter5);
	        m_listview = (ListView) findViewById(R.id.list6);
	        m_listview.setAdapter(adapter6);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Three Letter").setContent(R.id.list));
	        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Four Letter").setContent(R.id.list4));
	        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Five Letter").setContent(R.id.list5));
	        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Six Letter").setContent(R.id.list6));
	        mTabHost.setCurrentTab(0);
  	        Button bt=(Button) findViewById(R.id.clearWords);
  	        bt.setOnClickListener(this);
  	        bt.setVisibility(View.VISIBLE);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.clearWords){
			new AlertDialog.Builder(this)
		    .setTitle("Clear ")
		    .setMessage("Are you sure you want to clear the Missed Words of "+wordClass.selected.toUpperCase())
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	myDB.execSQL("DROP TABLE IF EXISTS "+wordClass.selected);
		        	myDB.execSQL("delete from MISSED where word='"+wordClass.selected+"'");
		        	 words3.clear();
		        	 words3.removeAll(words3);
		        	 words4.clear();
		        	 words4.removeAll(words4);
		        	 words5.clear();
		        	 words5.removeAll(words5);
		        	 words6.clear();
		        	 words6.removeAll(words6);
		        	 wordClass.missedRemoved=wordClass.selected;
		        	 set();
		        	 Button bt=(Button) findViewById(R.id.clearWords);
		   	         bt.setClickable(false);

		        }
		     })
		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		     .show();
			//myDB.execSQL("DROP TABLE IF EXISTS "+wordClass.selected);
			
		}
		
	}
	public void set(){
		  m_listview = (ListView) findViewById(R.id.list);
		adapter3 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words3);
		 m_listview.setAdapter(adapter);  
		 adapter4 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words4);
		 m_listview.setAdapter(adapter);
		 adapter5 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words5);
		 m_listview.setAdapter(adapter);
		 adapter6 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words6);
		 m_listview.setAdapter(adapter);
	}
	
	
	
}
