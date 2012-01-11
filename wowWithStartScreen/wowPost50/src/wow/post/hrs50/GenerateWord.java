package wow.post.hrs50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class GenerateWord {

	public String wordGenerator(Context myContext){
		
		BufferedReader br = null;
		int random1,count=0;
		String word="";
        random1=1 + (int)(Math.random() * ((5347 - 0)));
        try {
            br = new BufferedReader(new InputStreamReader(myContext.getAssets().open("word6.txt"))); //throwing a FileNotFoundException?
           
            while(count<=random1)
            {
            	word=br.readLine();
            	count++;
            }
            Log.d("arjun",Integer.toString(count));
            Log.d("arjun",word);
            
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

		
		return word;
		
		
	}
	
}
