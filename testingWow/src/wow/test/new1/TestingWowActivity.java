package wow.test.new1;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestingWowActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	static int buttonId=1,textId=1,clickCount=0;
	Button b[];
	TextView tv[];
	String word;
	@Override
    
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        RelativeLayout myRelativeLayout=(RelativeLayout) findViewById(R.id.name);
        word="incompletee";
        b=ButtonCreator(word);
        textId=buttonId;
        tv=TextCreator(word.length());
        for(int k=0;k<word.length();k++)
        {	
        	b[k].setOnClickListener(this);
        	myRelativeLayout.addView(b[k]);
        }
        for(int k=0;k<word.length();k++)
        	myRelativeLayout.addView(tv[k]);
        setContentView(myRelativeLayout);		//y???
        
        
	}
	
	public Button[] ButtonCreator(String word){
	// TODO code application logic here
	        int r;
	        
	        Button b1[]=new Button[word.length()];
	        String wordArray[]=new String[word.length()];
	        int number[]=new int[10];
	        for(int k=0;k<word.length();k++)
	            wordArray[k]=word.substring(k,k+1).toUpperCase();
	        for(int j=0;j<10;j++)
	           number[j]=0;
	        int count=0;
	        
	        for(int i=0;count<word.length();i++)
	        {
	            
	            r=0 + (int)(Math.random() * ((word.length() - 0)));
	            if(count==0)
	            {
	            	number[r]=1;
	            	b1[count++]=createButton(wordArray[r]);
	            }
	            
	            else
	            {
	            if(number[r]==0)
	            {
	            	b1[count]=createButton(wordArray[r],b1[count-1]);
	            	number[r]=1;
	                count++;
	            }
	            }
	                         
	            
	        }
	        return b1;
    }
	
	public TextView[] TextCreator(int len)
	{
		TextView tv[]=new TextView[len];
		tv[0]=createText("0");
		for(int i=1;i<len;i++)
			tv[i]=createText(Integer.toString(i),tv[i-1]);
		return tv;
	}
	

    public Button createButton(String buttonText){
		
		Button newButton= new Button(getApplicationContext());
		newButton.setId(buttonId++);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.setMargins(0, 30,0,0 );
		newButton.setLayoutParams(params);
		newButton.setText(buttonText);
		return newButton;
	}
    
    public TextView createText(String Text)
    {
		
		TextView newText= new TextView(getApplicationContext());
		newText.setId(textId++);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.setMargins(0,300,0,0 );
		newText.setLayoutParams(params);
		newText.setText(Text);
		newText.setVisibility(View.INVISIBLE);
		return newText;
	}
    
    public TextView createText(String Text,TextView parent)
    {
    	TextView newText= new TextView(getApplicationContext());
		newText.setId(textId++);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF ,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BASELINE,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BOTTOM,parent.getId());
    	newText.setLayoutParams(params);
		newText.setText(Text);
		newText.setVisibility(View.INVISIBLE);
		return newText;
    	
    }
    
    public Button createButton(String buttonText,Button parent)
    {
    	Button newButton=new Button(getApplicationContext());
    	newButton.setId(buttonId++);
    	RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	params.addRule(RelativeLayout.RIGHT_OF,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BASELINE,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BOTTOM,parent.getId());
    	newButton.setLayoutParams(params);
    	newButton.setText(buttonText);
    	return newButton;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 
		for (int i = 0; i < b.length; i++)
		   {
		      if (b[i].getId() == v.getId())
		      {
		         b[i].setVisibility(View.INVISIBLE);
		         tv[clickCount].setText(b[i].getText().toString());
		         tv[clickCount++].setVisibility(View.VISIBLE);
		         break;
		      }
		   }
		
		
	}
    
};