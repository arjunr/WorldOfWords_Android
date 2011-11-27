package wow.with.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
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
	BufferedReader br = null;
	BufferedReader br1 = null;
	String inputWords[],word;
	int count=0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        Button reset=(Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
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
			TextView result=(TextView) findViewById(R.id.result);
			int index = Arrays.binarySearch(inputWords, entered);
			if(index<0)
				result.setText("Word Not Found !");
			else
				result.setText("Word found !");
		}
		else if(R.id.reset==v.getId()){
			Reset();
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
		    	 tv.setText(text+"   "+buttonText);
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
        setContentView(myRelativeLayout);
	}
	
	public void populateList()
	{
		int random1;
        random1=1 + (int)(Math.random() * ((4964 - 0)));
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("english.txt"))); //throwing a FileNotFoundException?
           
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
		tv.setText("");
	}
	
}