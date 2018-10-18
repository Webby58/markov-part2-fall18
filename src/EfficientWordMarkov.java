import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientWordMarkov extends BaseWordMarkov{
	
	private Map<WordGram, ArrayList<String>> myMap; // initialize a map
	
	public EfficientWordMarkov() {
		this(2);
	}
	
	public EfficientWordMarkov(int order){
		super(order); //initializes the new state
		myMap = new HashMap<WordGram, ArrayList<String>>(); // set myMap equal to a new hashmap
	}
	
	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		myMap.clear();
		 
		for(int k=0; k <= myWords.length - myOrder; k += 1) { // goes through loop and makes sure that it goes through everything, being myOrder away from the end
			WordGram currently = new WordGram(myWords, k, myOrder); // initialize WordGram, indexed with k
			ArrayList<String> myList = new ArrayList<>(); // create empty array list
			if(k + myOrder == myWords.length) {   // if length equals k + myOrder, at the end of the list
				if(! myMap.containsKey(currently)) {
					myList.add(PSEUDO_EOS);  // add pseudo because you are at the end of the list
					myMap.put(currently, myList);
					break; // code should stop because at end and should add pseudo
				}
				else {
					myMap.get(currently).add(PSEUDO_EOS); // if duplicated, add to map again, code should break bc at end of list
					break;
				}
			}
			if(! myMap.containsKey(currently)) {
				String nextWord = myWords[k+myOrder]; 
				myList.add(nextWord);
				myMap.put(currently, myList);
			}
			else { 
				String nextWord = myWords[k + myOrder];
				myMap.get(currently).add(nextWord);
			}
		}
	}

	@Override
	public ArrayList<String> getFollows(WordGram kGram) {
		if(myMap.containsKey(kGram)) {  // makes sure kGram is in map
			return myMap.get(kGram); // return the array list of the words that follow structure 
		}
		else {
			throw new NoSuchElementException(kGram +" not in map");
		}
	}
	
}
