package wow.post.hrs50;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class onEnd extends Activity implements OnClickListener{

	SharedPreferences settings;
	SQLiteDatabase myDB;
	public static int flag=0;
	int mutexForHighScore=0;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.onend);
		Button bt=(Button) findViewById(R.id.button1);
	
		bt.setOnClickListener(this);
		TextView tv1=(TextView) findViewById(R.id.textView1);
		tv1.setTextColor(Color.BLACK);
		TextView tv2=(TextView) findViewById(R.id.word);
		tv2.setTextColor(Color.BLACK);
		//	res.setText(getIntent().getExtras().getString("score"));
		
			
			Button bt3=(Button) findViewById(R.id.highScore);
			bt3.setOnClickListener(this);
			TextView ans=(TextView) findViewById(R.id.result);
			ans.setText(Score.result);
			if(WowPost50Activity.letter==true)
			{
				Button bt2=(Button) findViewById(R.id.button1);
				bt2.setText("Next !");
			}
			else{
		
				
				tv2.setText("The word was : "+wordClass.wordDuplicate);
				TextView re=(TextView) findViewById(R.id.result);
				re.setText(Score.result);
				bt3.setVisibility(View.VISIBLE);
				settings=PreferenceManager.getDefaultSharedPreferences(this);
			//	bt3.setVisibility(View.VISIBLE);
			//	bt3.setClickable(true);
			}
			
			if(WowPost50Activity.letter==true)
			{
				tv1.setText("Your score is: "+Integer.toString(Score.myScore));
			}
			else
			{
				if(Score.myScore!=0){
					Score.highScoreDB=Score.myScore;
					Score.myScore=0;
				}
				tv1.setText("Your score is: "+Integer.toString(Score.highScoreDB));
			}
			
			Button bt1=(Button) findViewById(R.id.button2);
			bt1.setOnClickListener(this);
			Button bt4=(Button) findViewById(R.id.missed);
			bt4.setOnClickListener(this);
			
			TextView message=(TextView) findViewById(R.id.Message);
			message.setTextColor(Color.BLACK);
			TextView result=(TextView) findViewById(R.id.result);
			result.setTextColor(Color.BLACK);
		
	
			RelativeLayout myRelativeLayout=(RelativeLayout) findViewById(R.id.end);
			myRelativeLayout.setBackgroundColor(Color.WHITE);
		
	}
	
	@Override
	public void onBackPressed() {

		android.os.Process.killProcess(android.os.Process.myPid()) ;
		finish();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		myDB=this.openOrCreateDatabase("arjun.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                "MYTABLE" +
                " (NAME VARCHAR, SCORE INT(5));");
	}
	public void callPopUp(){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("High Score :");
        alert.setMessage("Enter your Name :");

        // Set an EditText view to get user input 
        final EditText input = new EditText(this);
        alert.setView(input);
        input.setText(settings.getString("Name", "New Player"));
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          Score.name= input.getText().toString();
          myDB.execSQL("INSERT INTO MYTABLE Values ('"+Score.name+"',"+Score.highScoreDB+");");
          //Score.highScoreDB=0;
          flag=1;
          Intent intent=new Intent(onEnd.this,Repo.class);
          startActivity(intent);
          SharedPreferences.Editor ep = settings.edit();
          ep.putString("Name", Score.name);
          ep.commit();
        }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
          }
        });

        alert.show();

		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Score my=new Score();
		if(v.getId()==R.id.button1){
			
			Intent intent=new Intent(onEnd.this,WowPost50Activity.class);
			
			startActivity(intent);
			finish();
		}
		else if(v.getId()==R.id.button2){
			Intent intent1=new Intent(onEnd.this,PossibleWords.class);
			startActivity(intent1);
		}
		else if(v.getId()==R.id.highScore){
				if(flag==0)
					callPopUp();
				else{
					Intent newIntent=new Intent(onEnd.this,Repo.class);
					startActivity(newIntent);
					
				}
		}
		
	}
}
