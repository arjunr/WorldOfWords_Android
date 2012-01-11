package wow.post.hrs50;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreen extends Activity implements OnClickListener{

	Context myContext;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		
		myContext=getApplicationContext();
		GenerateWord wordGen=new GenerateWord(); 
	    wordClass.word=wordGen.wordGenerator(myContext);
	    temp p = new temp();
        new Thread(p).start();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Button bt=(Button) findViewById(R.id.startGame);
		bt.setOnClickListener(this);
		Button bt1=(Button) findViewById(R.id.options);
		bt1.setOnClickListener(this);
		Button bt2=(Button) findViewById(R.id.highScores);
		bt2.setOnClickListener(this);
		Button bt3=(Button) findViewById(R.id.instructions);
		bt3.setOnClickListener(this);
		Button bt4=(Button) findViewById(R.id.missedWords);
		bt4.setOnClickListener(this);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	
		android.os.Process.killProcess(android.os.Process.myPid()) ;
		System.exit(0);
		finish();
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.startGame){
			Intent intent=new Intent(StartScreen.this,WowPost50Activity.class);	
			startActivity(intent);
			finish();
		}
		else if(v.getId()==R.id.highScores){
			Intent intent=new Intent(StartScreen.this,Repo.class);	
			startActivity(intent);
		}
		else if(v.getId()==R.id.missedWords){
			Intent intent=new Intent(StartScreen.this,AllMissed.class);	
			startActivity(intent);
			finish();
		}
		else if(v.getId()==R.id.options){
			Intent intent=new Intent(StartScreen.this,Options.class);	
			startActivity(intent);
			finish();
		}
		
	}
	class temp implements Runnable {
	    
		public void run() {
	        // compute primes larger than minPrime
	    	    Log.d("arjun", "Before Hash");
	            GenerateHash gh=new GenerateHash(wordClass.word);
	    		wordClass.wordRead=gh.createHash(myContext);
	    		
	    		wordClass.enteredWords.clear();
	    		wordClass.enteredWords.removeAll(wordClass.enteredWords);
	    		Log.d("arjun", Integer.toString(wordClass.wordRead.length));
	    		temp1 p1 = new temp1();
		        new Thread(p1).start(); 
	    	}
	}
	
	class temp1 implements Runnable {
	    
		public void run() {
	        // compute primes larger than minPrime
	    	   GeneratePossibilities gp=new GeneratePossibilities();
	    	   gp.generate();

	    	}
	}
	
	
}
