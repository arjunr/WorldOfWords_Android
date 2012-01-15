package world.of.words;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.StaticLayout;
import android.util.Log;

public class RunningThreads implements Runnable {
    Context myContext;
    ProgressDialog dialog; 
    void setContextDialog(Context thisContext,ProgressDialog newDialog){
    	myContext=thisContext;
    	dialog=newDialog;
    }
    void setContext(Context thisContext){
    	myContext=thisContext;
    	dialog=null;
    }
    
	public void run() {
		GenerateHash gh=new GenerateHash(wordClass.word);
		wordClass.wordRead=gh.createHash(myContext);
		wordClass.enteredWords.clear();
		wordClass.enteredWords.removeAll(wordClass.enteredWords);
		GeneratePossibilities gp=new GeneratePossibilities();
		gp.generate();	
		wordClass.flag=true;
		if(wordClass.flag){
			dialog.dismiss();
			GameSpace.poss=wordClass.possibilities.toArray(new String[wordClass.possibilities.size()]);
			
			Arrays.sort(GameSpace.poss);

	        for(int i=0;i<GameSpace.poss.length;i++)
	        	Log.d("poss1",GameSpace.poss[i]);
	        for(int i=0;i<wordClass.possibilities.size();i++)
	        	Log.d("poss2",wordClass.possibilities.get(i));
			GameSpace.words=new ArrayList<String>(Arrays.asList(GameSpace.poss));
			GameSpace.checkFlag=false;
        }
	}
}
