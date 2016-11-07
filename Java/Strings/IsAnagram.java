package Strings;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {

	public static void main(String[] args) {
		String s1 = "我爱巴黎";
		String s2 = "巴黎爱我";
		System.out.println(isAnagram(s1, s2));
	}
	
	public static boolean isAnagram(String s1, String s2) {
		if(s1 == null && s2 == null) {
			return true;
		} else if(s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}
		
		HashMap<Byte, Integer> hm = new HashMap<Byte, Integer>();
		
		byte[] b1 = s1.getBytes();
		for(int i = 0; i < b1.length; i++) {
			if(hm.containsKey(b1[i])) {
				int count = hm.get(b1[i]);
				hm.put(b1[i], count + 1);
			} else {
				hm.put(b1[i], 1);
			}
		}
		
		byte[] b2 = s2.getBytes();
		for(int i = 0; i < b2.length; i++) {
			if(hm.containsKey(b2[i])) {
				int count = hm.get(b2[i]);
				if(count == 0) {
					return false;
				} else {
					hm.put(b2[i], count - 1);
				}
			} else {
				return false;
			}
		}
		
		for(Map.Entry<Byte, Integer> e : hm.entrySet()){
			if(e.getValue() != 0) {
				return false;
			}
		}
		
		return true;
	}
}
