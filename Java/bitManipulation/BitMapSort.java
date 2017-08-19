package bitManipulation;

import java.util.Random;

// Remember although BitMap requires less memory, but it is not able to
// deal with data with duplicate records, like [3, 5, 3, 1].
public class BitMapSort {
    private static final int CAPACITY = 1_000_000; // the max capacity of input data

    public static void main(String[] args) {
        MyBitMap ms = new MyBitMap();
        byte[] bytes = null;
        Random random = new Random();
        // for (int i = 0; i < CAPACITY; i++) {
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt();
            bytes = ms.setBit(num);
            System.out.println(num);
        }
        System.out.println("--------");
        ms.output(bytes);
    }
}

class MyBitMap {
    private byte[] dataBytes = new byte[1 << 29]; // to store data whose size is 512MB
    private static final int SHIFT = 3; // for performance improvement if there is mass data
    private static final int MASK = 0x07; // the same as above

    /**
     * store data
     *
     * @param num read data
     * @return byte array dataBytes
     */
    public byte[] setBit(int num) {
        long bitIndex = num + (1l << 31); // 1. data may be out of array index(int)
        								  // 2. 1l << 31 mapping to 0
        int index = (int) (bitIndex >> SHIFT); // index of byte array, equal to / 8 (32 for int)
        int innerIndex = (int) (bitIndex & MASK); // index of "bit array" in byte array, equal to % 8
        dataBytes[index] = (byte) (dataBytes[index] | (1 << innerIndex)); // store data
        
        return dataBytes;
    }

    /**
     * output data
     * 
     * @param bytes bytes array
     */
    public void output(byte[] bytes) {
        int count = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (((bytes[i]) & (1 << j)) != 0) {
                    count++;
                    int number = (int) ((((long) i * 8 + j) - (1l << 31)));
                    System.out.println("count = " + count + "\t data = " + number);
                }
            }
        }
    }
}
