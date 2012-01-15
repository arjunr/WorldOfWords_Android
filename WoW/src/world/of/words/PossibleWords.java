package world.of.words;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.TabActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class PossibleWords extends TabActivity implements OnClickListener{
	
	SQLiteDatabase myDB;
	int flag=0,pressed=0;
	String me[]; 
	ArrayList<String> words = new ArrayList<String>();
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.possible);
	        
	        words.addAll(GameSpace.wordsCopy);
	        TabHost mTabHost = getTabHost();
	        String con[]=GameSpace.enter.toArray(new String[GameSpace.enter.size()]);
	        for(int i=0;i<GameSpace.enter.size();i++)
					words.remove(con[i]);
	        ArrayList<String> words3 = new ArrayList<String>();
	        ArrayList<String> words4 = new ArrayList<String>();
	        ArrayList<String> words5 = new ArrayList<String>();
	        ArrayList<String> words6 = new ArrayList<String>();
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
	        String[] array3,array4,array5,array6;
	        array3=words3.toArray(new String[words3.size()]);
	        array4=words4.toArray(new String[words4.size()]);
	        array5=words5.toArray(new String[words5.size()]);
	        array6=words6.toArray(new String[words6.size()]);
	        Arrays.sort(array3);
	        Arrays.sort(array4);
	        Arrays.sort(array5);
	        Arrays.sort(array6);
	      // String me[]={"c","b","a"};
	        ListView m_listview = (ListView) findViewById(R.id.list);
	        ArrayAdapter<String> adapter3 =
	  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array3);
	        ArrayAdapter<String> adapter4 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array4);
	        ArrayAdapter<String> adapter5 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array5);
	        ArrayAdapter<String> adapter6 =
		  	          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array6);
	       
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
	         
	       Log.d("arjun",Integer.toString(me.length));
	          Button bt=(Button) findViewById(R.id.save);
	          bt.setOnClickListener(this);
	          bt.setVisibility(View.VISIBLE);
	      
	 }
		@Override
		public void onBackPressed() {
			 //  android.os.Process.killProcess(android.os.Process.myPid()) ;
		
			finish();
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ArrayList<String> words = new ArrayList<String>();
			if(v.getId()==R.id.save){
				int duration = Toast.LENGTH_SHORT;

		        if(pressed==0){
				pressed=1;
				Log.d("possible","created missed");
				myDB=this.openOrCreateDatabase("missed.db", MODE_PRIVATE, null);
				myDB.execSQL("CREATE TABLE IF NOT EXISTS MISSED" +" (WORD VARCHAR);");
				Cursor c = myDB.rawQuery("SELECT * FROM MISSED", null);
				Toast toast = Toast.makeText(getApplication()
		        		, "The List has been saved", duration);
		        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		        toast.show();
		        Log.d("possible","before cursor");
		        Log.d("possible",wordClass.wordDuplicate);
		        if (c != null ) {
		        	Log.d("possible",Boolean.toString(c.moveToFirst()));
		              if  (c.moveToFirst()) {
		                    do {
		                    	String firstName = c.getString(c.getColumnIndex("WORD"));
		                    	words.add(firstName);
		                    	Log.d("possible",firstName);
		                    }while (c.moveToNext());
		              }
		              else{
		            	  flag=1;
		              }
		         }
		        else{
		        	Log.d("possible","else part");
		        	flag=1;
		        }
		        	
				if(words.contains(wordClass.wordDuplicate)&&(words.size()!=0)){
					Log.d("possible","2ndif part " +wordClass.wordDuplicate + Boolean.toString(words.contains(wordClass.wordDuplicate)));
					Log.d("possible",wordClass.wordDuplicate);
				}
				else{
					Log.d("possible","2ndelse part");
					flag=1;
				}
				Log.d("possible","flag value "+ flag+"words size "+ words.size());
				if(flag==1){
				myDB.execSQL("INSERT INTO MISSED Values ('"+wordClass.wordDuplicate+"');");
				Log.d("possible",wordClass.wordDuplicate);
				myDB.execSQL("CREATE TABLE IF NOT EXISTS " +wordClass.wordDuplicate +" (WORD VARCHAR);");
		        for(int i=0;i<me.length;i++){
		        	myDB.execSQL("INSERT INTO "+wordClass.wordDuplicate+" Values ('"+me[i]+"');"); 	
		        	Log.d("possible",me[i]+" "+wordClass.wordDuplicate);
		        }
		        
		        	
				
			}}}
			
			
		}
		

}
