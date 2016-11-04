package Strings;

import java.util.ArrayList;
import java.util.List;

public class SplitStringByFixedLength {
	
	 // for input string, split it into arrays by size
	 private static List<String> splitString(int size, String s) {
	        List<String> strings = new ArrayList<String>();
	        if (s == null) {
	            return strings;
	        }
	        for(int i = 0; i < s.length(); i++) {
	            StringBuilder sb = new StringBuilder();
	            int j = i;
	            for(; j < i + size && j < s.length(); j++) {
	                sb.append(s.charAt(j));
	            }
	            strings.add(sb.toString());
	            i = j - 1;
	        }
	        
	        return strings;
	 }
}
