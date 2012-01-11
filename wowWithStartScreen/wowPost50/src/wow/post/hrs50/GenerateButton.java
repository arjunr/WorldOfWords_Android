package wow.post.hrs50;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;

public class GenerateButton {

	static int buttonId=1;
	int width;
	String word;
	Context applicationContext;
	Button bRight,bLeft;
	int topOffest=20;
	
	public GenerateButton() {
		// TODO Auto-generated constructor stub
	}
	public GenerateButton(String word,Context applicationContext,int width){
		this.word=word;
		this.applicationContext=applicationContext;
		this.width=240;
	}
	
	public Button[] ButtonCreator(){
		// TODO code application logic here
		        int r;
		        
		        Button b1[]=new Button[word.length()];
		        String wordArray[]=new String[word.length()];
		        int number[]=new int[10];
		        for(int k=0;k<word.length();k++)
		            wordArray[k]=word.substring(k,k+1).toUpperCase();
		        for(int j=0;j<word.length();j++)
		           number[j]=0;
		        int count=0;
		        Log.d("VR", "insideCreator");
		        int c=width/2;
		    	//c=c+(width*5/100);
		        int x=c;
		    	float f=35f;
		    	float x1=(x+f/2+width-f/2)/2;
		    	double y1=(c*c);
		    	y1=x1-c;
		    	
		        for(;count<word.length();)
		        {
		        	r=0 + (int)(Math.random() * ((word.length() - 0)));
		            if(number[r]==0){
		            if(count==0)
		            {
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,topOffest,(c-c/2));
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count<2){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,topOffest,c+((c-c/2)/3));
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==2){
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,(((int)(y1))+topOffest),(int)(x1-x)/3);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==3){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,(((int)(y1))+topOffest),(int)x1);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==4)
		            {
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,(((int)y1+((int)y1))+topOffest),(c-c/2));
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==5){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,(((int)y1+((int)y1))+topOffest),c+((c-c/2)/3));
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            count++;
		        }
		        }
		        Log.d("arjun", "Inside Generate button");
		        return b1;
	    }
	
	
    public Button createButton(String buttonText,Context con,int marginTop,int marginLeft){
		
    	Log.d("VR", "FirstButton");
    	Button newButton= new Button(con);
  		newButton.setId(buttonId++);
  		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
  		params.addRule(RelativeLayout.ALIGN_TOP);
  		params.setMargins(marginLeft,marginTop,0,0 );
  		newButton.setBackgroundColor(Color.alpha(0));
  		newButton.setGravity(Gravity.CENTER|Gravity.BOTTOM);
  		newButton.setLayoutParams(params);
  		newButton.setText(buttonText);
  		return newButton;
  	}
    
    public Button createButtonNew(String buttonText,Button parent,Context con,int marginTop,int marginLeft)
    {
    	Button newButton=new Button(con);
    	newButton.setId(buttonId++);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
    	params.setMargins(marginLeft,marginTop,0,0 );
  		newButton.setGravity(Gravity.CENTER|Gravity.BOTTOM);
  		newButton.setLayoutParams(params);
  		newButton.setBackgroundColor(R.drawable.button_shape);
  		newButton.setBackgroundColor(Color.alpha(0));
  		newButton.setText(buttonText);
    	return newButton;
    }
	
}
