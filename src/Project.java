
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Enumeration;
	import java.util.Scanner;
	import java.util.Set;

	//Simge Erek,Ekin Silahlioglu,Ezgi Koc,Selin Yesilselve
	public class Project<Key extends Comparable<Key>, Value>{
	     static double array1[]; //Helper array we use this array in our methods
	     static double array2[]; //Helper array we use this array in our methods
		 static HashMP<Integer,String> item = new HashMP<>();//we put item.txt information(movie id,movie name) into this hash map 
		static HashMP<Integer,HashMP<Integer,Integer>> data = new HashMP<Integer,HashMP<Integer,Integer>>();//we put users in data.txt and item hash map into this data hash map.
		 static QuickSort q= new QuickSort();//this quick sort class helps us to sort our datas.
		 
		 //main
		 public static void main(String[] args) throws FileNotFoundException {  
				Scanner scan,scan1;
			//we define two scanner to read given text files.
				try {
					scan = new Scanner(new FileInputStream("uitem.txt"));//we read item text files with scanner.
					while(scan.hasNextLine()){ 
						String lines = scan.nextLine();
						String[] word = lines.split("\\|");//we split | with \\|
						item.put(Integer.parseInt(word[0]), word[1]); //word[0] : movie number, word[1] : movie name
						}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				try {
					scan1 = new Scanner(new FileInputStream("udata.txt"));//we read data text files with scanner.
					while(scan1.hasNextLine()){
						String lines1= scan1.nextLine();
						String[] word1 = lines1.split("\\s+");//we split tabs with \\s+.
						int intdata []=new int[3];//we define new array to take user movie and rating
						
						for(int i=0;i<word1.length-1;i++) {
							intdata[i]=Integer.parseInt(word1[i]);
						}
					   if(data.contains(intdata[0])) {
							data.get(intdata[0]).put(intdata[1], intdata[2]);//intdatacim[0] : user id, intdatacim[1]: movie no. , intdatacim[2]:rating
						}
						else { // if it finds the same user id again it will put next the older one
							HashMP<Integer,Integer> tree=new HashMP<>();
							tree.put(intdata[1], intdata[2]);
							data.put(intdata[0], tree);
						}
					  }
			        }
						catch (Exception e) {
							System.out.println(e.getMessage());
					}
				
				
				
				
				//In[1]##############################################################################
				// dictionary of some movies and their ratings for user 955
				// This dictionary uses a ranking from 1 to 5
				System.out.println("Critics of 1 on the movie : "  +data.get(1).get(242));   
			    System.out.println(item.get(1));
			        

			    //In[6] A Nested Dictionary of movie critics a
			    //Outer Dictionary: Key is person, Value is a set of critics
			    //Inner Dictionary: Key is movie , Value is a rating score
			    System.out.println("user 295's critics for movie Strange Days (1995): " + data.get(295).get(39));
				
			   System.out.println();
			    //In[7]People in our toy data
			    System.out.println("PEOPLE IN OUR TOY DATA");
			    Iterable<Integer> keys = data.keys();
				    for(Integer key: keys){
				    	System.out.println(key);
				}

				//In[9]
				System.out.println("DISTANCE(125,174,80) :" +dist(data,125,174,80)); // we find the distance between user 125 and user 174 for the 80th movie.
	 
				//In[13]
				System.out.println("SIMDISTANCE(5,6) :  " +sim_distance(data,5,6)); //we find the similarity distance between user 5 and 6.
				  
				//In[16] : TOP MATCHES for critics
				HashMP<Integer,Double>b=topMatches(data,1,3);//user 1 top matches
				Iterable<Integer> keysTopMatches=b.keys(); //it takes the b's keys
				System.out.print("Similar (top-3) person like 1: ");
				System.out.print("{");
				for(int i:keysTopMatches) {
					System.out.print("("+b.get(i)+","+i+")");
					
				}
				System.out.println("}");
				
				
				//In[19] : Show Critics
				    for(int value:data.keys()) {
					HashMP<Integer,Integer>dataValue= data.get(value);
					System.out.println();
					System.out.print(" "+value+":");
					System.out.print("{");
					for(Integer key:dataValue.keys()) {
						System.out.print(" "+item.get(key)+" : " +data.get(value).get(key)+"," );
					}
					System.out.println("}");
				}
				
				    
				    
				    System.out.println();
				    System.out.println();
				   
				    HashMP<Integer,HashMP<Integer,Integer>> criticsT = transformPrefs(data);
				    
				//In[20] : TRANSFORMPREFS
				    transformPrefs(data);	
					for(int movies:data.keys()) {
						HashMP<Integer,Integer>bu= data.get(movies);
						System.out.println();
						System.out.print(" "+item.get(movies)+":");
						System.out.print("{");
						for(Integer key:bu.keys()) {
							System.out.print(" "+key+" : " +data.get(movies).get(key)+"," );
						}
						System.out.println("}");
					}
			 
			      	System.out.println();
			      	System.out.println();
				    //In[21]
			      	System.out.println("Set of movies most similar to 1(Toy Story)");
			      	System.out.print("TopMatches:");
			      	HashMP<Integer,Double>c=topMatches(criticsT,1,3);//user 1 top matches
					System.out.print("{");
					for(int i:c.keys()) {
						System.out.print("("+c.get(i)+","+item.get(i)+")");
						
					}
					System.out.println("}");
					System.out.println();
				
				    //In[22] : SIM DISTANCE
				    System.out.println("SIM DISTANCE FOR 1 and 2: " +sim_distance(criticsT, 1, 2));
				    System.out.println();
				    
				    //Recommendations for user 1
					HashMP<Double,String>output=getR(1);
					System.out.print("Gets recommendations for 1: "); 
				    for(Double key:output.keys()) {
				    		System.out.printf("%s",key+","+output.get(key));
				    }
				   
				    System.out.println();
				    System.out.println();
				    //In[29]
				    tenwrite(196);
				    
				    
				    System.out.println();
				    System.out.println("CALCULATE SIMILAR ITEMS");
				    calculateSimilarItems(data);
				    
				  
			 
				    
				    
				    
				    
				    
				    
	    }//end of the main method
			    	 
		 //In[8]
		 // Compute Euclidean Distance between two person's preference on the item based on the given nested dictionary
		 public static double dist( HashMP<Integer,HashMP<Integer,Integer>>data,int person1, int person2, int movie ){ // Distance between two person's preference on the item
				int distance = 0;
				distance  =  data.get(person1).get(movie) - data.get(person2).get(movie);
				
				return distance*distance;	
			}//end of method
		 

		 //In[11]
		 //it finds shared items
		 public static ArrayList<String> intersection(HashMP<Integer,HashMP<Integer,Integer>>data,int person1,int person2){//Get the list of shared_items
				String[] p1= data.get(person1).keys().toString().split(" ");//to get the movie numbers of person1
				String[] p2= data.get(person2).keys().toString().split(" ");//to get the movie numbers of person2
				ArrayList <String> si= new ArrayList<>();
				for(int i=0;i<p1.length;i++){
					for(int j=0;j<p2.length;j++){
					if(p1[i].equals(p2[j])){
						si.add(item.get(Integer.valueOf(p1[i])));
						si.add(p1[i]);
					}
					}
				}	
				return si;
				
			}//end of method
		 

		 //In[12]
		 public static double sim_distance(HashMP<Integer,HashMP<Integer,Integer>>data,int person1,int person2){ //Returns a distance-based similarity score for person1 and person2
				ArrayList<String> common = intersection(data,person1, person2); //we create an arraylist to get the input from intersection method
				double sumOfSquares = 0.0;
				if(common.size()<0){// if its size below zero it returns zero
					return 0;
				}
				for(int i=1;i<common.size();i=i+2){
					sumOfSquares =  (sumOfSquares + dist(data,person1, person2, Integer.valueOf(common.get(i))));
				}
				return  (1/(1+Math.sqrt(sumOfSquares)));
			}//end of method
		 

		 //In[14]
		 // This function will return a value between –1 and 1.
	     //Returns the Pearson correlation coefficient for p1 and p2
		 public static double sim_pearson(HashMP<Integer,HashMP<Integer,Integer>>data,int person1,int person2) { 
				int sum1=0;                                              
				int sum1Sq=0;
				int sum2=0;
				int sum2Sq=0;
				int pSum=0;
				ArrayList<String> si = intersection(data,person1, person2); // we create an arraylist to get the input from intersection
				int [] mis = new int[si.size()];
				
				for(int i=1;i<si.size();i=i+2) {
					mis[i]=Integer.valueOf((si.get(i)));
				}
				int n=si.size();
				if(n==0) {
					return 0;
				}
				for(int i=1;i<mis.length;i=i+2) {
					sum1 = sum1+ data.get(person1).get(mis[i]);
				    sum2 = sum2+ data.get(person2).get(mis[i]);
				}

				for(int e=1;e<mis.length;e=e+2) {
					sum1Sq = sum1Sq +( data.get(person1).get(mis[e])*data.get(person1).get(mis[e]));
					sum2Sq = sum2Sq +( data.get(person2).get(mis[e])*data.get(person2).get(mis[e]));	
				}
				
				for(int x=1;x<mis.length;x=x+2) {
					pSum = pSum +( data.get(person1).get(mis[x])*data.get(person2).get(mis[x]));
				}
				
				int num = pSum-(sum1*sum2/n);
				if(num==0) return 0;
				double den = Math.sqrt((sum1Sq-((sum1*sum1)/n))*(sum2Sq-((sum2*sum2)/n)));
				double r = (double) num/den;
				return r;
				
			}//end of method
		 
		 
		 //In[15]##############################################################################
		 //# Returns the best matches for person from the prefs dictionary.
		 //# Number of results and similarity function are optional params.
		 public static HashMP<Integer,Double> topMatches(HashMP<Integer,HashMP<Integer,Integer>>data,int person, int n) {
			      HashMP<Integer,Double>b= new HashMP<Integer,Double>(); // default hash map
			      HashMP<Integer,Double>topMap = new HashMP<Integer,Double>(); // final hash map
			      Iterable<Integer> keys=data.keys();
			      int k=0;
			      array1=new double[data.size()-1];
			      for(Integer key:keys) {
			    	  if(person!=key) { // if person does not equal to other person 
			    	  double score=sim_distance(data,person,key); 
			    	  b.put(key,score); // we put other person and score in our default hash table
			    	  array1[k]=score; // we put the score into our array
			    	  k++;
			      }}
			     
			     q.quickSort(array1,0,array1.length-1); // we sort our score
			     Iterable<Integer> keysb=b.keys();
			     for(int i=array1.length-1;i>array1.length-n;i--) { // we reverse the array and in the end we put it into topMap
			    	for(Integer key:keysb) {
			    	
			    		if(b.get(key)==array1[i]) { 
			    	    topMap.put(key,array1[i]); 
			    		 
			    	}}}
			     return topMap;
				 }//end of method
		 

		 //In[17]
		 //Gets recommendations for a person by using a weighted average of every other user's rankings
		 public static HashMP<Double,String> getR(int person){
			 HashMP<Integer,Double>totals= new HashMP<Integer,Double>();
			 HashMP<Integer,Double>simSums= new HashMP<Integer,Double>();
			 HashMP<Double,String>rankings= new HashMP<Double,String>();
			 HashMP<Double,String>recommends=new HashMP<Double,String>();
			 double sim=0;
			 double score=0;
			
			 for(int key:data.keys()) {//user numbers
				 if(key==person)continue; 
				 sim=sim_distance(data,person,key);
				 if(sim<=0)continue;
				 HashMP<Integer, Integer> value=data.get(key);
				 for(int keys:value.keys()) {//movies number
					 if(!data.get(person).contains(keys)) { //only score movies user has not seen yet
						 score=data.get(key).get(keys);
						 totals.put(keys,0.0);
						 totals.put(keys,totals.get(keys)+(score*sim));
						 simSums.put(keys,0.0);
						 simSums.put(keys, simSums.get(keys)+sim);
					 }
				 }
			 }
			
			 for(int key:totals.keys()) {
				 rankings.put(totals.get(key)/simSums.get(key),item.get(key));
			 }
			  int k=0; array2 =new double[rankings.size()];
			 Iterable<Double> rank=rankings.keys();
			 for(double key: rank) {
				
				 array2[k]=key;
				 k++;
			 }
			 q.quickSort(array2,0,array2.length-1); // we sort the array
			 for(int i=array2.length-1;i>0;i--) {
				
				 recommends.put(array2[i],rankings.get(array2[i]) ); // we reverse it
			 }
			 
			 return recommends;
		 }//end of method
		 
		 
		 //In[18]
		 //swap the people and the movies.
		 public static HashMP<Integer,HashMP<Integer,Integer>>transformPrefs(HashMP<Integer,HashMP<Integer,Integer>>data){
			 HashMP<Integer, HashMP<Integer, Integer>>transformPref=new HashMP<Integer,HashMP<Integer,Integer>>();
			 HashMP<Integer,Integer>itemm=new HashMP<Integer,Integer>(); // item is the value of transformPref hash map
			 for(Integer key:data.keys()) {
				 HashMP<Integer,Integer>a=data.get(key);  
				 for(Integer movies:a.keys()) {
					
					if(key!=movies) {
						
					 itemm.put(key,data.get(key).get(movies));
					 transformPref.put(movies,itemm);}

				 }}
			 return transformPref;
			 }//end of method
		 
		 //In[24]
		 //Item-based collaborative filtering
		 //Pre-Computation Step: Create a dictionary of items showing which other items they are most similar to.
		 public static HashMP<Integer,HashMP<Integer,Double>> calculateSimilarItems(HashMP<Integer,HashMP<Integer,Integer>>data) {
				HashMP<Integer,Double> scores=new HashMP<>();
				HashMP<Integer,HashMP<Integer,Double>> result=new HashMP<>();
				HashMP<Integer,HashMP<Integer,Integer>> itemPrefs=transformPrefs(data);
				HashMP<Integer,Double> a=new HashMP<>();
				int c=0;
				Iterable<Integer> itemkeys=itemPrefs.keys();
				for(int item:itemkeys) {
					scores=topMatches(itemPrefs,item,50 );
					Iterable<Integer> scorekeys=scores.keys();
					for(Integer key:scorekeys) {
						if(c%100==0) {
							System.out.println(c+" / "+itemPrefs.size());
						}
						a.put(key,scores.get(key));
						result.put(key,a);
						c++;
					}
				}
				return result;	
			}//end of method
		 
		 
		
		//In[29]
			 //prints the first 10 movies with the given person id number by user
			 public static ArrayList<String> tenwrite(int person) {
					String [] a = data.get(person).keys().toString().split(" "); // we get the movie numbers given by the person
					ArrayList <String> d= new ArrayList<>();
					System.out.println("10 critics for " + person +"th person from Movielens Dataset");
					for(int i=0; i<10; i++) {
					System.out.print(i + "   " +item.get(Integer.valueOf(a[i])) + ": " );
					System.out.println(data.get(person).get(Integer.valueOf(a[i])));
					}
					return d;
				}//end of method
		
		 
		 
		 
		 
		 
		 
		 
	} // end of the class



