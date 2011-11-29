package wow.with.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WowWithFilesActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	Button buttonOriginal[];
	ArrayList<String> wordList = new ArrayList<String>();
	ArrayList<String> enteredWords = new ArrayList<String>();
	BufferedReader br = null;
	BufferedReader br1 = null;
	String inputWords[],word;
	int count=0,score=0;
	boolean letter=false;
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        Button reset=(Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        Button shuffle=(Button) findViewById(R.id.shuffle);
        shuffle.setOnClickListener(this);
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		if(R.id.submit==v.getId())
		{
			TextView tv=(TextView) findViewById(R.id.textView1);
			String entered=tv.getText().toString();
			entered=entered.replaceAll(" ", "");
			TextView result=(TextView) findViewById(R.id.textView2);
			int index = Arrays.binarySearch(inputWords, entered);
			if(index<0){
				result.setText("Word Not Found !");
				
			}
			else{
				boolean b=enteredWords.contains(entered);
				if(b){
					result.setText("Word already entered !");
				}
				else{
				switch(entered.length()){
				case 3:
					score+=5;
					break;
				case 4:
					score+=10;
					break;
				case 5:
					score+=20;
					break;
				case 6:
					score+=100;
					letter=true;
					
				}
				enteredWords.add(entered);
				result.setText("    Word found !");
				TextView scoreText=(TextView) findViewById(R.id.score);
				scoreText.setText("Score: " +Integer.toString(score));
				}
			}
			Handler myHandler = new Handler();
			myHandler.postDelayed(mMyRunnable, 1200);
		}
		else if(R.id.reset==v.getId()){
			Reset();
		}
		else if(R.id.shuffle==v.getId()){
			Reset();
			Shuffle();
		}
		else
		{
		String text,buttonText;
		TextView tv=(TextView) findViewById(R.id.textView1);
		for (int i = 0; i < buttonOriginal.length; i++)
		   {
		      if (buttonOriginal[i].getId() == v.getId())
		      {
		    	 buttonOriginal[i].setVisibility(View.INVISIBLE);
		         buttonText=buttonOriginal[i].getText().toString();
		         text=tv.getText().toString();
		    	 tv.setText(text+" "+buttonText);
		         break;
		      }
		   }
		}
	}
	
	@Override
	public void onStart() 
	{
		// TODO Auto-generated method stub
		super.onStart();
		populateList();
		RelativeLayout myRelativeLayout=(RelativeLayout) findViewById(R.id.name);
		GenerateButton generatebutton=new GenerateButton(word,getApplicationContext());
        buttonOriginal=generatebutton.ButtonCreator();
        for(int k=0;k<word.length();k++)
        {	
        	buttonOriginal[k].setOnClickListener(this);
        	myRelativeLayout.addView(buttonOriginal[k]);
        }
        TextView scoreText=(TextView) findViewById(R.id.score);
        scoreText.setText("Score: " +Integer.toString(score));
        new CountDownTimer(121000, 1000) {
        	TextView mTextField=(TextView) findViewById(R.id.timerText);
            public void onTick(long millisUntilFinished) {
            	long millis = millisUntilFinished;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds     = seconds % 60;

                if (seconds < 10) 
                {
                   mTextField.setText("" + minutes + ":0" + seconds);
                } 
                else 
                {
                	mTextField.setText("" + minutes + ":" + seconds);            
                }
                
            }

            public void onFinish() {
                mTextField.setText("Done!");
                Intent intent=new Intent(WowWithFilesActivity.this,onEnd.class);
                intent.putExtra("score", Integer.toString(score));
                if(letter)
                	intent.putExtra("result", "Congrats ! You won !");
                else
                	intent.putExtra("result", "Keep trying !");
                startActivity(intent);
            }
         }.start();
        setContentView(myRelativeLayout);
	}
	
	public void populateList()
	{
		int random1;
        random1=1 + (int)(Math.random() * ((15788 - 0)));
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("6letter.txt"))); //throwing a FileNotFoundException?
           
            while(count<=random1)
            {
            	word=br.readLine();
            	count++;
            }
            Log.d("arjun",Integer.toString(count));
            Log.d("arjun",word);
            //break txt file into different words, add to wordList
            count=0;
            String word1="";
            br1 = new BufferedReader(new InputStreamReader(getAssets().open("words.txt")));
            while((word1=br1.readLine()) != null)
            {
            	count++;
            	wordList.add(word1.toUpperCase());
            
            }
            inputWords=wordList.toArray(new String[wordList.size()]);
            Log.d("arjun",Integer.toString(count));
        }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally 
            {
                try 
                {
                    br.close(); //stop reading
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
		
	}
	
	public void Reset(){
		
		for(int i=0;i<word.length();i++)
			buttonOriginal[i].setVisibility(View.VISIBLE);
		TextView tv=(TextView) findViewById(R.id.textView1);
		TextView tv1=(TextView) findViewById(R.id.textView2);
		tv1.setText("");
		tv.setText("");
		
	}
	public void Shuffle(){
		Log.d("arjun", "inside shuffle");
		String wordArray[]=new String[word.length()];
        int number[]=new int[10];
        for(int k=0;k<word.length();k++)
            wordArray[k]=word.substring(k,k+1).toUpperCase();
        for(int j=0;j<word.length();j++)
           number[j]=0;
        int count=0;
        int r=0;
        for(;count<word.length();)
        {
        	r=0 + (int)(Math.random() * ((word.length() - 0)));
            if(number[r]==0)
            {
            	number[r]=1;
            	buttonOriginal[count++].setText(wordArray[r]);
            	Log.d("arjun", Integer.toString(count));
            	Log.d("arjun", wordArray[r]);
            }   
        }
        TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setText("");
	}
	private Runnable mMyRunnable = new Runnable()
	{
	    @Override
	    public void run()
	    {
	    	//Change state here
	    	Reset();
	    }
	 };
	
}