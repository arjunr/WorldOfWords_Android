package wow.post.hrs50;

import java.util.ArrayList;
import java.util.Arrays;

import android.util.Log;

public class GeneratePossibilities {
		
			
			
			public void generate(){
				
				String s = wordClass.word;
				wordClass.possibilities.clear(); 
				wordClass.possibilities.removeAll(wordClass.possibilities);
				 
				  String temp="";
				  int index;
				
				  int count[]={0,0,0,0};
				
				  //All 3 letter anagrams  
				  
				 
				  for(int j = 0; j<6; j++)
			            for(int k=0; k<6; k++)
			                for(int i=0; i<6; i++)
			                     
			                          {
			                                if((i!=j) && (j!=k) && (k!=i)){
			                				temp = "" + s.charAt(j) + s.charAt(k)+ s.charAt(i);
			                				index=Arrays.binarySearch(wordClass.wordRead, temp);
			                				if(index>0){
			                					if(wordClass.possibilities.contains(temp)){}
			                					else
			                					wordClass.possibilities.add(temp);
			                					
			                				}
			                                    //System.out.println(temp  );
			                                    count[0]++;
			                                }
			                          }
			        
			        //All 4 letter anagrams  
			        for(int j = 0; j<6; j++)
			            for(int k = 0; k<6; k++)
			                for(int i=0; i<6; i++)
			                    for(int l=0; l<6; l++)
			                          
			                          {
			                    	if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=i) && (l!=j)){    
			                    		temp = "" + s.charAt(j) + s.charAt(k)+ s.charAt(i)+ s.charAt(l);
			                    		index=Arrays.binarySearch(wordClass.wordRead, temp);
		            				if(index>0){
		            					if(wordClass.possibilities.contains(temp)){}
	                					else
		            					wordClass.possibilities.add(temp);
		            					
		            				}    
			                    		count[1]++;
			                                    //System.out.println(temp );
			                          }}
			         //All 5 letter anagrams
			         for(int j = 0; j<6; j++)
			            for(int k = 0; k<6; k++)
			                for(int i=0; i<6; i++)
			                    for(int l=0; l<6; l++)
			                        for(int f=0; f<6; f++)
			                          
			                          {
			                        	if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=i) && (l!=j) && (f!=j) &&(f!=k)&&(f!=i)&&(f!=l)){
			                        	temp = "" + s.charAt(j) + s.charAt(k)+ s.charAt(i)+ s.charAt(l)+s.charAt(f);
			                        	index=Arrays.binarySearch(wordClass.wordRead, temp);
		            				if(index>0){
		            					if(wordClass.possibilities.contains(temp)){}
	                					else
		            					wordClass.possibilities.add(temp);
		            					
		            				} 
			                        	count[2]++;
			                                    //System.out.println(temp  );
			                          }}
			          //All 6 letter anagrams
			          for(int j = 0; j<6; j++)
			            for(int k = 0; k<6; k++)
			                for(int i=0; i<6; i++)
			                    for(int l=0; l<6; l++)
			                        for(int f=0; f<6; f++)
			                            for(int g=0; g<6; g++)
			                          {
			      if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=i) && (l!=j) && (f!=j) &&(f!=k)&&(f!=i)&&(f!=l)&&(g!=i)&&(g!=j)&&(g!=k)&&(g!=f)&&(g!=l)){
			                            	temp = "" + s.charAt(j) + s.charAt(k)+ s.charAt(i)+ s.charAt(l)+ s.charAt(f)+ s.charAt(g);
			                            	index=Arrays.binarySearch(wordClass.wordRead, temp);
			                				if(index>0){
			                					if(wordClass.possibilities.contains(temp)){}
			                					else
			                					wordClass.possibilities.add(temp);
			                					
			                				}
			                            	count[3]++;
			                                    //System.out.println(temp  );
			                          }}
			
						}
			
		
}
