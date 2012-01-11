package wow.post.hrs50;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

public class newGenerateButton {
	String word;
	public newGenerateButton() {
		// TODO Auto-generated constructor stub
	}
	public newGenerateButton(String word){
		this.word=word;
	}
	
	public void ButtonCreator(Button b1[]){
		// TODO code application logic here
		        int r;
		        String wordArray[]=new String[word.length()];
		        int number[]=new int[10];
		        for(int k=0;k<word.length();k++)
		            wordArray[k]=word.substring(k,k+1).toUpperCase();
		        for(int j=0;j<word.length();j++)
		           number[j]=0;
		        int count=0;
		        Log.d("VR", "insideCreator");
		        for(;count<word.length();)
		        {
		        	r=0 + (int)(Math.random() * ((word.length() - 0)));
		            if(number[r]==0){
		            	number[r]=1;
		            	b1[count].setText(wordArray[r]);
		            	b1[count].setBackgroundColor(Color.alpha(Color.GRAY));//(100));
		            	b1[count].setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
		            	count++;
		            }
		            
		        }
	}
}