import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientMarkov extends BaseMarkov {
	
	private Map<String, ArrayList<String>> myMap;


	
	public EfficientMarkov(int order) {
		super(order); // initializes the new state
		 myMap = new HashMap<String,ArrayList<String>>();  //create new hashmap in constructor

	}
	
	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

@Override // Allows the method to be redefined from before, allows function to be called twice in different classes
public void setTraining(String text) {
	myText = text;
	myMap.clear(); // clears
	for(int k= 0; k <= myText.length()- myOrder; k += 1) { // wanna be myOrder away from the end of the text length
		String key = myText.substring(k, k + myOrder); // breaks myText into smaller strings, myOrder creates the appropriate length
		if( k + myOrder == myText.length())	{ //if length of string is greater than the text length, it should run out of bounds
			if(! myMap.containsKey(key)) {
				ArrayList<String> myList = new ArrayList<String>(); //create empty ArrayList
				myList.add(PSEUDO_EOS);
				myMap.put(key, myList);
				break; //Hit the last key, so value following is Pseudo, so end the loop
	
				
			}
			else {
				myMap.get(key).add(PSEUDO_EOS); // if duplicated, add to the map again
				break; // end the loop
			}
		}
		if(! myMap.containsKey(key)) { // making sure that the key is not repeated (String in this case)
			String nextChar = myText.substring(k + myOrder, k + myOrder + 1); // Starts at the end of the string created and then finds the letter following it
			ArrayList<String> myList = new ArrayList<String>(); //create empty ArrayList
			myList.add(nextChar); //put letter found into array list
			myMap.put(key,myList);  //put string key (such as abc) and map it with letter added into myList
		}
		else {
			String nextChar = myText.substring(k + myOrder, k + myOrder + 1); // use else bc if key is in there, still want to add next char in the list
			myMap.get(key).add(nextChar); //gets the array list out of myMap, adds the char to the list, gets list associated with key
			
		}
		
	}
	return; // doesn't run, just lets me know that I am done
}
@Override   
public ArrayList<String> getFollows(String key){
	if(myMap.containsKey(key)) { // makes sure my map has the key 
		return myMap.get(key); // return Array List of letters found after the string 
		
	}
	else {
		throw new NoSuchElementException(key+" not in map");
		}
	}
}
	