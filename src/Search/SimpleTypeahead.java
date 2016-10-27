package Search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class SimpleTypeahead {
	public static void main(String[] args) {
		Typeahead t = new Typeahead();
		List<String> words = new ArrayList<String>();
		words.add("Jason Zhang");
		words.add("James Yu");
		words.add("Bob Zhang");
		words.add("Larry Shi");
		t.addAll(words);
		
		System.out.println(t.search("Zhang"));
		System.out.println(t.search("James"));
	}

}

class Typeahead {
	public TreeMap<String, List<String>> data = null;
	
	public Typeahead() {
		data = new TreeMap<String, List<String>>();
	}
	
    // @param str: a string
    // @return a list of words
	public List<String> search(String head) {
		if(head == null || head.length() == 0 || !data.containsKey(head)) {
			return new ArrayList<String>();
		}
		
		return data.get(head);
	}
	
    // @param dict A dictionary of words dict
	public void addAll(List<String> dict) {
		if(dict == null) {
			return;
		}
		
		Iterator<String> it = dict.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
	}
	
	public void add(String word) {
		if(word == null) {
			return;
		}
		
		for(int i = 0; i < word.length(); i++) {
			StringBuilder s = new StringBuilder();
			for(int j = i; j < word.length(); j++) {
				s.append(word.charAt(j));
				addToData(s.toString(), word);
			}
		}
	}
	
	private void addToData(String s, String word) {
		if(s == null || word == null || s.length() == 0 || word.length() == 0) {
			return;
		}
		List<String> words = null;
		if(data.containsKey(s)) {
			words = data.get(s);
		} else {
			words = new ArrayList<String>();
		}
		words.add(word);
		data.put(s, words);
	}
}