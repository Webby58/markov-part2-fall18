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
