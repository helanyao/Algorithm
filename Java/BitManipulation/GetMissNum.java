package BitManipulation;

/*
 * 很多成对出现数字保存在磁盘文件中，成对的数字不一定是相邻的，
 * 如2, 3, 4, 3, 4, 2 ……，由于意外有一个数字消失了，
 * 如何尽找到是哪个数字消失了？ 
 */
public class GetMissNum {
	
	public int findLost(int a[]) { 
        int result = 0; 
        
        for(int i = 0 ; i < a.length ; i++) { 
            result ^= a[i]; // 异域是相同为0不同为1.
        } 
        
        return result; 
	} 

}
