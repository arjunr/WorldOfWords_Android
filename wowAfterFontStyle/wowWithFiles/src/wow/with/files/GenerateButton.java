package wow.with.files;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

public class GenerateButton {
	
	static int buttonId=1;
	String word;
	Context applicationContext;
	Button bRight,bLeft;
	
	public GenerateButton() {
		// TODO Auto-generated constructor stub
	}
	public GenerateButton(String word,Context applicationContext){
		this.word=word;
		this.applicationContext=applicationContext;
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
		        
		        for(;count<word.length();)
   {
		            
		            r=0 + (int)(Math.random() * ((word.length() - 0)));
		            if(number[r]==0){
		            if(count==0)
		            {
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,100,130);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count<2){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,100,270);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==2){
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,200,45);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==3){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,200,355);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==4)
		            {
		            	number[r]=1;
		            	b1[count]=createButton(wordArray[r],applicationContext,300,130);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            else if(count==5){
		            	number[r]=1;
		            	b1[count]=createButtonNew(wordArray[r],bLeft,applicationContext,300,270);
		            	bLeft=b1[0];
		            	bRight=b1[0];
		            }
		            count++;
		            }
		        }
		        return b1;
	
	    }
	
	
    public Button createButton(String buttonText,Context con,int marginTop,int marginLeft){
		
    	Log.d("VR", "FirstButton");
    	Button newButton= new Button(con);
  		newButton.setId(buttonId++);
  		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
  		params.addRule(RelativeLayout.ALIGN_TOP);
  		
  		//params.addRule(RelativeLayout.CENTER_HORIZONTAL);
  		params.setMargins(marginLeft,marginTop,0,0 );
  		newButton.setBackgroundColor(R.drawable.button_shape);
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
//    	params.addRule(RelativeLayout.ALIGN_BASELINE,parent.getId());
  //  	params.addRule(RelativeLayout.ALIGN_BOTTOM,parent.getId());
  		newButton.setLayoutParams(params);
  		newButton.setBackgroundColor(R.drawable.button_shape);
  	  	newButton.setText(buttonText);
    	return newButton;
    }

    public Button createButtonRight(String buttonText,Button parent,Context con)
    {
    	Button newButton=new Button(con);
    	newButton.setId(buttonId++);
    	RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	params.addRule(RelativeLayout.RIGHT_OF,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BASELINE,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BOTTOM,parent.getId());
    	newButton.setLayoutParams(params);
    	newButton.setText(buttonText);
    	return newButton;
    }
    
    public Button createButtonLeft(String buttonText,Button parent,Context con)
    {
    	Button newButton=new Button(con);
    	newButton.setId(buttonId++);
    	RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	params.addRule(RelativeLayout.LEFT_OF,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BASELINE,parent.getId());
    	params.addRule(RelativeLayout.ALIGN_BOTTOM,parent.getId());
    	newButton.setLayoutParams(params);
    	newButton.setText(buttonText);
    	return newButton;
    }
}
