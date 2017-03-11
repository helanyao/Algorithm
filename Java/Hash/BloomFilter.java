package Hash;

import java.util.BitSet;

public class BloomFilter {
	/*  BitSet is initialized to 2^24 bit  */ 
	private static final int DEFAULT_SIZE = 1 << 25; 
	/* different hash seed which should be primer */
	private static final int[] seeds = new int[] { 5, 7, 11, 13, 31, 37, 61 };
	private BitSet bits = new BitSet(DEFAULT_SIZE);
	/* Hash featured objects */ 
	private SimpleHash[] func = new SimpleHash[seeds.length];

	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++)
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
	}

	// mark string into bits
	public void add(String value) {
		for (SimpleHash f : func) 
			bits.set(f.hash(value), true);
	}

	public boolean isContain(String value) {
		if (value == null)
			return false;
		boolean ret = true;
		for (SimpleHash f : func) 
			ret = ret && bits.get(f.hash(value));
		
		return ret;
	}

	/* Class of Hash function */
	public static class SimpleHash {
		private int cap;
		private int seed;

		public SimpleHash(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		// to hash
		public int hash(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) 
				result = seed * result + value.charAt(i);
			
			return (cap - 1) & result;
		}
	}
}
