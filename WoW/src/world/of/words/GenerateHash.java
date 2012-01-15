package world.of.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class GenerateHash {
	
	BufferedReader br = null;
	String contain="",word,toRead="";
	ArrayList<String> wordList = new ArrayList<String>();
	Context myContext;
	public GenerateHash() {
		// TODO Auto-generated constructor stub
		word="";
	}
	public GenerateHash(String word){
		this.word=word;
		
	}
	public String[] createHash(Context myContext){
		word=word.toUpperCase();
		char myWord[]=word.toCharArray();
        Arrays.sort(myWord);
		final Map<String, String> h = new HashMap<String, String>(  );
        {
        // add some key/value pairs to the HashMap
        	for(char ch=65;ch<=90;ch++){
        		h.put(String.valueOf(ch) , ch+"Words.txt" );
        	}
        }
       
        try {
            for(int i=0;i<6;i++){
            	if(contain.contains(String.valueOf(myWord[i]))){}
            	else{
            	
            		contain=contain+myWord[i];
            		br = new BufferedReader(new InputStreamReader(myContext.getAssets().open( myWord[i]+"Words.txt"))); //throwing a FileNotFoundException?
           
            		while((toRead=br.readLine())!=null)
            		{	
            			wordList.add(toRead);
            		}
            	}
            }
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
        return wordList.toArray(new String[wordList.size()]);	
	}
}
