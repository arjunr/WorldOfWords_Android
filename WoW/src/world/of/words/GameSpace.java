package world.of.words;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameSpace extends Activity  implements OnClickListener{
    
	String word="",pressedButton="";
//	BufferedReader br = null;
	Button buttonOriginal[],lastPressed,img;
	Context myContext;
	TextView timerText,possibleWordsView,myText,result,wordsEntered[],suggestions[];
	int last[],top=0,score=0,buttonCount=0,buttonPressed[];
	public static String poss[];
	public static ArrayList<String> words,enter=new ArrayList<String>(),wordsCopy = new ArrayList<String>();;
	int possibleLength;
	public static boolean checkFlag=true;
	CountDownTimer countTimer;
	/** Called when the activity is first created. */
	public static Boolean letter=false;
	
	//Adding shake req variables
	
		private float xPreviousAccel;
		private float yPreviousAccel;
		private float zPreviousAccel;
		private float xAccel;
		private float yAccel;
		private float zAccel;
		private boolean firstUpdate = true;

		/*What acceleration difference would we assume as a rapid movement? */
		private final float shakeThreshold = 3f;
		
		/* Has a shaking motion been started (one direction) */
		private boolean shakeInitiated = false;
		  private SensorManager mySensorManager;
		//End of shake req var
   
		  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        myContext=getApplicationContext();    
       
        /*if(!wordClass.flag){
        	ProgressDialog newdialog = ProgressDialog.show(GameSpace.this, "","Loading. Please wait...", true);
        	RunningThreads p = new RunningThreads();
        	p.setContextDialog(myContext,newdialog);
        	new Thread(p).start();
        }*/
        //while(checkFlag);
		
        if(!wordClass.possibilities.contains(wordClass.word))
        	wordClass.possibilities.add(wordClass.word);
        wordClass.flag=false;
        
       // GenerateWord wordGen=new GenerateWord();
        //word=wordGen.wordGenerator(myContext);
        wordClass.wordDuplicate=wordClass.word;
        word=wordClass.word;
        
       /* RunningThreads p1 = new RunningThreads();
	    p1.setContext(myContext);
        new Thread(p1).start();
         */       
        RelativeLayout myRelativeLayout=(RelativeLayout) findViewById(R.id.name);
        Display display=getWindowManager().getDefaultDisplay();
        int screenWidth=display.getWidth();
        newGenerateButton generatebutton=new newGenerateButton(wordClass.wordDuplicate);
        
        Button buttonOriginal1[]=new Button[6];
        
        buttonOriginal1[0]=(Button) findViewById(R.id.button1);
        buttonOriginal1[1]=(Button) findViewById(R.id.Button01);
        buttonOriginal1[2]=(Button) findViewById(R.id.button2);
        buttonOriginal1[3]=(Button) findViewById(R.id.Button21);
        buttonOriginal1[4]=(Button) findViewById(R.id.button3);
        buttonOriginal1[5]=(Button) findViewById(R.id.Button31);
        
        buttonOriginal=buttonOriginal1;
        
        

        generatebutton.ButtonCreator(buttonOriginal);
        
        last=new int[word.length()+1];
        buttonPressed=new int[word.length()];
        for(int k=0;k<word.length();k++)
        {	
        	buttonOriginal[k].setOnClickListener(this);
        	Typeface myTypeface = Typeface.createFromAsset(this.getAssets(),"fonts/batmfa.ttf");
        	buttonOriginal[k].setTextColor(Color.BLACK);
        	buttonPressed[k]=0;
        	buttonOriginal[k].setTypeface(myTypeface);
        }
        
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        timerText=(TextView) findViewById(R.id.timer);
        timerText.setText("");
  		Typeface myTypeface = Typeface.createFromAsset(this.getAssets(),"fonts/Futured.TTF");
  		timerText.setTextColor(Color.BLACK);
  		timerText.setTypeface(myTypeface);
    	
        countTimer=new CountDownTimer(121100, 1000) {
        	
        	TextView mTextField=(TextView) findViewById(timerText.getId());
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
                mTextField.setText("Time's UP!");
                GameSpace.wordsCopy.clear();
            	GameSpace.wordsCopy.addAll(wordClass.possibilities);
                Intent intent=new Intent(GameSpace.this,onEnd.class);
                intent.putExtra("score", Integer.toString(score));
                Score mySc=new Score();
                wordClass.flag=false;
                mySc.myScore=score;
                if(letter)
                	Score.result="Congrats ! You won !";
                else
                	Score.result="Keep trying !";
                startActivity(intent);
                finish();
            }
         }.start();

       
         
        int c=screenWidth/2;
     	int x=c;
     	int f=30;
     	int x1=(x+f/2+screenWidth-f/2)/2;
     	double y1=(c*c);
     	y1=x1-c;
     	
     	img=(Button) findViewById(R.id.button4);
     	img.setBackgroundColor(R.drawable.button_shape);
  		//img.setTextSize(25.0f);
  		//img.setBackgroundColor(Color.alpha(0));
  		img.setTextColor(Color.BLACK);
  		img.setOnClickListener(this);
  		  		
     	myText=new TextView(getApplicationContext());
  		myText=(TextView) findViewById(R.id.textView3);
  		myText.setText("");
  		myText.setTextColor(Color.BLACK);
  	//	myText.setOnClickListener(this);
  		 
  		result=new TextView(getApplicationContext());
	  	result=(TextView) findViewById(R.id.textView2);
  		result.setTextColor(Color.BLACK);
  		//result.setText("hai");
        Score sc=new Score();
        
        score=sc.myScore;
        TextView parent;
    	suggestions = new TextView[4];
    	int j=0;
    	int suggestionId=700;

    	params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//params1.setMargins(0, (int)((3*y1)+j*18+30),0,0);
    	params1.addRule(RelativeLayout.BELOW,myText.getId());
		params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	suggestions[j]= new TextView(getApplicationContext());
    	suggestions[j]=(TextView) findViewById(R.id.Suggestion4);
		suggestions[j].setTextColor(Color.BLACK);
		suggestions[j].setText("");
		
		
		suggestions[1]= new TextView(getApplicationContext());
    	suggestions[1]=(TextView) findViewById(R.id.Suggestion2);
		suggestions[1].setTextColor(Color.BLACK);
		suggestions[1].setText("");
		
		suggestions[2]= new TextView(getApplicationContext());
    	suggestions[2]=(TextView) findViewById(R.id.center);
		suggestions[2].setTextColor(Color.BLACK);
		suggestions[2].setText("");
		
		suggestions[3]= new TextView(getApplicationContext());
    	suggestions[3]=(TextView) findViewById(R.id.Suggestion3);
		suggestions[3].setTextColor(Color.BLACK);
		suggestions[3].setText("");
				
		possibleWordsView=new TextView(getApplicationContext());
		possibleWordsView.setId(12345);
		
		DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
		float dp = myText.getTextSize();
		float fpixels = metrics.density * dp;
		int pixels = (int) (metrics.density * dp + 0.5f);
		
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW,R.id.button3);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
  		possibleWordsView.setLayoutParams(params);
  		
  		while(checkFlag);
  		possibleLength=poss.length;
  		possibleWordsView.setText("Number of possible Words: "+Integer.toString(poss.length));
  		//possibleWordsView.setTextSize(dp);
  		possibleWordsView.setTextColor(Color.BLACK);
  		myRelativeLayout.addView(possibleWordsView);
  		
    	TextView scoreText=(TextView) findViewById(R.id.textView1);
        scoreText.setText("Score: " +Integer.toString(score));
        scoreText.setTextColor(Color.BLACK);
    	result.setText("");
    	myRelativeLayout.setBackgroundColor(Color.WHITE);
    	  mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // (1)
  		mySensorManager.registerListener(mySensorEventListener, mySensorManager
  				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
  				SensorManager.SENSOR_DELAY_NORMAL); // (2)
  	    letter=false;
  		setContentView(myRelativeLayout);
		
    	}
    
	//Gesture Functions
	
		 private final SensorEventListener mySensorEventListener = new SensorEventListener() {

			  public void onSensorChanged(SensorEvent se) {
				     /* we will fill this one later */
			        	 updateAccelParameters(se.values[0], se.values[1], se.values[2]);   // (1)
			             if ((!shakeInitiated) && isAccelerationChanged()) {                                      // (2) 
			     	    shakeInitiated = true; 
			     	} else if ((shakeInitiated) && isAccelerationChanged()) {                              // (3)
			     	    executeShakeAction();
			     	} else if ((shakeInitiated) && (!isAccelerationChanged())) {                           // (4)
			     	    shakeInitiated = false;
			     	}
			        	
			        }
			    	private void updateAccelParameters(float xNewAccel, float yNewAccel,
			    			float zNewAccel) {
			                    /* we have to suppress the first change of acceleration, it results from first values being initialized with 0 */
			    		if (firstUpdate) {  
			    			xPreviousAccel = xNewAccel;
			    			yPreviousAccel = yNewAccel;
			    			zPreviousAccel = zNewAccel;
			    			firstUpdate = false;
			    		} else {
			    			xPreviousAccel = xAccel;
			    			yPreviousAccel = yAccel;
			    			zPreviousAccel = zAccel;
			    		}
			    		xAccel = xNewAccel;
			    		yAccel = yNewAccel;
			    		zAccel = zNewAccel;
			    	}
			    	private boolean isAccelerationChanged() {
			    		float deltaX = Math.abs(xPreviousAccel - xAccel);
			    		float deltaY = Math.abs(yPreviousAccel - yAccel);
			    		float deltaZ = Math.abs(zPreviousAccel - zAccel);
			    		return (deltaX > shakeThreshold && deltaY > shakeThreshold)
			    				|| (deltaX > shakeThreshold && deltaZ > shakeThreshold)
			    				|| (deltaY > shakeThreshold && deltaZ > shakeThreshold);
			    	}
			    	   private void executeShakeAction() {
			    			/* Save the cheerleader, save the world 
			    			   or do something more sensible... */
			    		  Shuffle();
			    		  pressedButton="";
			    		  for (int i1 = 0; i1 < suggestions.length; i1++){
				    		  suggestions[i1].setText("");
			    		  }
			    		}
				public void onAccuracyChanged(Sensor sensor, int accuracy) {
				    /* can be ignored in this example */
				       }
			    };

		//End of Gesture Functions

			    public void Shuffle(){
					String word=new String(wordClass.wordDuplicate);
			    	Reset();
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
		public void Reset(){
			top=0;
			for(int i=0;i<word.length();i++){
				
				buttonOriginal[i].setTextColor(Color.BLACK);
				buttonOriginal[i].setClickable(true);
				buttonPressed[i]=0;
			}
			//TextView tv=(TextView) findViewById(R.id.textView1);
			TextView tv1=(TextView) findViewById(myText.getId());
			tv1.setText("");
			result.setText("");
			//tv.setText("");
		//	firstW=true;

			
		}
		
		@Override
		public void onBackPressed() {
			wordClass.flag=false;
			Intent intent=new Intent(GameSpace.this,StartScreen.class);
			startActivity(intent);
			countTimer.cancel();
			finish();
		}
		
		public static String removeChar(String s, char c) {

			   String r = "";
			   int flag=0;
			   for (int i = 0; i < s.length(); i ++) {
			      if ((s.charAt(i) != c) || (flag!=0)){ 
			    	  r += s.charAt(i);
			    	  
			      }
			      else
			    	  flag++;
			   }

			   return r;
			}
		
		@Override
		    protected void onStart() {
		        super.onStart();
		        for(int i=0;i<poss.length;i++)
		        	Log.d("poss",poss[i]);
		        enter.clear();
		        
		    }
	@Override
	public void onClick(View v) {
	
		
	       if(v.getId()==img.getId()){
			
			int index;
			TextView tv=(TextView) findViewById(myText.getId());
			String entered=tv.getText().toString();
			tv.setText("");
			entered=entered.replaceAll(" ", "");
			entered=entered.toLowerCase();
			Log.d("arjun", entered);
			index = Arrays.binarySearch(poss, entered);
			pressedButton="";
  		  	for (int i1 = 0; i1 < suggestions.length; i1++){
  		  		suggestions[i1].setText("");
  		  	}
  		  	if(index<0){
				
				result.setText("Word Not Found!");
				
			}
			else{
				boolean b=wordClass.enteredWords.contains(entered);
				if(b){
					result.setText("Word already entered !");
				}
				else{
					enter.add(entered);
					possibleWordsView.setText("Number of possible Words: "+Integer.toString(--possibleLength));
					switch(entered.length()){
					case 3:
						score+=20;
						break;
					case 4:
						score+=30;
						break;
					case 5:
						score+=50;
						break;
					case 6:
						score+=100;
						letter=true;
					}
					
					
					wordClass.enteredWords.add(entered);
					result.setText("Word found!");
					TextView scoreText=(TextView) findViewById(R.id.textView1);
			        scoreText.setText("Score: " +Integer.toString(score));
					
				}
			}
			Handler myHandler = new Handler();
			myHandler.postDelayed(mMyRunnable, 650);
			
	       }
		else{
		String buttonText,text;
		int countViews=0;
		for (int i = 0; i < buttonOriginal.length; i++)
		   {
		      if (buttonOriginal[i].getId() == v.getId())
		      {
		    	  
		    	  if(buttonPressed[i]==1){
		    		  Log.d("arjun",buttonOriginal[i].getText().toString());
		    		  TextView tv=(TextView) findViewById(myText.getId());
		    		  buttonOriginal[i].setTextColor(Color.BLACK);
		    		  buttonPressed[i]=0;
		    		  char myString=buttonOriginal[i].getText().charAt(0);
		    		  String textString=tv.getText().toString();
		    		  textString=removeChar(textString, myString);
		    		  textString=textString.replaceAll("  "," ");
		    		  tv.setText(textString);
		    		//  top--;
		    		  for (int i1 = 0; i1 < suggestions.length; i1++){
			    		  suggestions[i1].setText("");  
			    	  }
					
					pressedButton=textString.replaceAll(" ", "").toLowerCase();
			    	if(top!=0){
				    	for(int j=0;j<wordClass.enteredWords.size();j++){
				    		Log.d("top",pressedButton+" "+ wordClass.enteredWords.get(j)+" "+Integer.toString(top));
				    		if(wordClass.enteredWords.get(j).startsWith(pressedButton)){
				    			suggestions[wordClass.enteredWords.get(j).length()-3].setText(suggestions[wordClass.enteredWords.get(j).length()-3].getText().toString()+"  " + wordClass.enteredWords.get(j).toUpperCase());
				    		}
				    	}

		    		  break;
		    		  
		    	  }}
		    	  else{
		    	  for (int i1 = 0; i1 < suggestions.length; i1++){
		    		  suggestions[i1].setText("");  
		    	  }
		    			
		    	 pressedButton=pressedButton+buttonOriginal[i].getText().toString().toLowerCase();
		    	 for(int j=0;j<wordClass.enteredWords.size();j++){
		    		 
			         if(wordClass.enteredWords.get(j).startsWith(pressedButton)){
			        	 suggestions[wordClass.enteredWords.get(j).length()-3].setText(suggestions[wordClass.enteredWords.get(j).length()-3].getText().toString()+"  " + wordClass.enteredWords.get(j).toUpperCase());
		    		 }
		    	 }
		    	 Log.d("arjun","Button");
		    	 buttonPressed[i]=1;	 
		    	 buttonOriginal[i].setTextColor(Color.rgb(233, 150, 122));
		    	 buttonText=buttonOriginal[i].getText().toString();
		         text=myText.getText().toString();
		    	 myText.setText(text+" "+buttonText);
		        
		    	 break;
		      }
		
		
		      }
		   }
		}
	
	}
}





