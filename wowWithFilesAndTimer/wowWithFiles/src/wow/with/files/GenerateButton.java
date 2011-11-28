package wow.with.files;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;


public class GenerateButton {
	
	static int buttonId=1;
	String word;
	Context applicationContext;
	
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
		            if(count==0)
		            {
		            	number[r]=1;
		            	b1[count++]=createButton(wordArray[r],applicationContext);
		            }
		            
		            else
		            {
		            if(number[r]==0)
		            {
		            	b1[count]=createButton(wordArray[r],b1[count-1],applicationContext);
		            	number[r]=1;
		                count++;
		            }
		            }
		                         
		            
		        }
		        return b1;
	    }
	
	
    public Button createButton(String buttonText,Context con){
		
  		Button newButton= new Button(con);
  		newButton.setId(buttonId++);
  		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
  		params.addRule(RelativeLayout.ALIGN_TOP);
  		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
  		params.setMargins(0, 200,0,0 );
  		newButton.setLayoutParams(params);
  		newButton.setText(buttonText);
  		return newButton;
  	}
    
    public Button createButton(String buttonText,Button parent,Context con)
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
}
