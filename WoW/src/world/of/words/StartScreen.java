package world.of.words;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreen extends Activity  implements OnClickListener{
	Context myContext;
	private static ProgressDialog newdialog;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		newdialog = ProgressDialog.show(StartScreen.this, "","Loading. Please wait...", true);
		newdialog.hide();
        
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
		if(!wordClass.flag){
        	//newdialog = ProgressDialog.show(StartScreen.this, "","Loading. Please wait...", true);
			myContext=getApplicationContext();
			GenerateWord wordGen=new GenerateWord(); 
		    wordClass.word=wordGen.wordGenerator(myContext);
			GameSpace.checkFlag=true;
		    RunningThreads p = new RunningThreads();
        	p.setContextDialog(myContext,newdialog);
        	//newdialog.hide();
        	new Thread(p).start();
        }
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		android.os.Process.killProcess(android.os.Process.myPid()) ;
		System.exit(0);
		finish();
	
	}
	@Override
	protected void onPause() {
		
		super.onPause();
		newdialog.dismiss();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.startGame){
			newdialog.show();
			
			Intent intent=new Intent(StartScreen.this,GameSpace.class);	
			startActivity(intent);
			finish();
		}
		else if(v.getId()==R.id.highScores){
			if(newdialog!=null){
			newdialog.dismiss();
			}
				wordClass.flag=false;
			Intent intent=new Intent(StartScreen.this,Repo.class);	
			
			startActivity(intent);
			
		}
		else if(v.getId()==R.id.missedWords){
			if(newdialog!=null){
				newdialog.dismiss();
				}
			wordClass.flag=false;
			Intent intent=new Intent(StartScreen.this,AllMissed.class);	
			startActivity(intent);
		}
		else if(v.getId()==R.id.options){
			if(newdialog!=null){
				newdialog.dismiss();
				}
			wordClass.flag=false;
			Intent intent=new Intent(StartScreen.this,Options.class);	
			startActivity(intent);
		}
		
	}
	/*class temp implements Runnable {
	    
		public void run() {
			GenerateHash gh=new GenerateHash(wordClass.word);
			wordClass.wordRead=gh.createHash(myContext);
			wordClass.enteredWords.clear();
			wordClass.enteredWords.removeAll(wordClass.enteredWords);
			Log.d("arjun", Integer.toString(wordClass.wordRead.length));
			GeneratePossibilities gp=new GeneratePossibilities();
			gp.generate();	
			dialog.dismiss();
		}
	}*/
	
}
