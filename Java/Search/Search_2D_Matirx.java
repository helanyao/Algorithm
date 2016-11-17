package Search;

/**
 * This matrix has the following properties:
 * 1. Integers in each row are sorted from left to right.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example:
 * [
 *  [1, 3, 5, 7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 *  ]
 *  
 *  Given target = 3, return true.
 *  
 *  http://www.lintcode.com/en/problem/search-a-2d-matrix/
 */

public class Search_2D_Matirx {

	public static void main(String[] args) {
		int[][] nums = new int[][]{{1,2,8,10,16,21,23,30,31,37,39,43,44,46,53,59,66,68,71},{90,113,125,138,157,182,207,229,242,267,289,308,331,346,370,392,415,429,440},{460,478,494,506,527,549,561,586,609,629,649,665,683,704,729,747,763,786,796},{808,830,844,864,889,906,928,947,962,976,998,1016,1037,1058,1080,1093,1111,1136,1157},{1180,1204,1220,1235,1251,1272,1286,1298,1320,1338,1362,1378,1402,1416,1441,1456,1475,1488,1513},{1533,1548,1563,1586,1609,1634,1656,1667,1681,1706,1731,1746,1760,1778,1794,1815,1830,1846,1861}};
		System.out.println(nums.length);
		System.out.println(nums[0].length);
		System.out.println(searchMatrix2(nums, 1861));
	}
	
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
        	return false;
        }
        int row = findRow(matrix, target);
        if (matrix[row][0] == target) { // just for performance improvement
        	return true;
        } else {
        	return binarySearch(matrix, row, target);
        }
    }
    
    private static int findRow(int[][] matrix, int target) {
    	int low = 0, high = matrix.length - 1;
    	
    	while (low + 1 < high) {
    		int mid = low + (high - low) / 2;
    		if (matrix[mid][0] == target) {
    			return mid;
    		} else if (matrix[mid][0] < target) {
    			low = mid;
    		} else {
    			high = mid;
    		}
    	}
    	
    	if (matrix[high][0] <= target) { // a tricky bug here, need firstly check the high position
    		return high;
    	} else {
    		return low;
    	}
    }
    
    private static boolean binarySearch(int[][] matrix, int row, int target) {
    	int low = 0, high = matrix[row].length - 1;
    	
    	while (low + 1 < high) {
    		int mid = low + (high - low) / 2;
    		if (matrix[row][mid] == target) {
    			return true;
    		} else if (matrix[row][mid] < target) {
    			low = mid;
    		} else {
    			high = mid;
    		}
    	}
    	
    	if (matrix[row][low] == target || matrix[row][high] == target) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // only binary search once
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length, column = matrix[0].length;
        int start = 0, end = row * column - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / column][mid % column];
            if (number == target) {
                return true;
            } else if (number < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (matrix[start / column][start % column] == target) {
            return true;
        } else if (matrix[end / column][end % column] == target) {
            return true;
        }
        
        return false;
    }

}
