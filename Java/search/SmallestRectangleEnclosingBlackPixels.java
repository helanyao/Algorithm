package search;

/**
 * 
 * @Description
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
 * The black pixels are connected, i.e., there is only one black region. 
 * Pixels are connected horizontally and vertically. 
 * Given the location (x, y) of one of the black pixels, 
 * return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * @Example
 * Given the following image:
 * [
 *	"0010",
 *	"0110",
 *	"0100"
 * ]
 * and x = 0, y = 2,
 * Return 6.
 * 
 * @Tag Google, Binary Search
 * 
 */
public class SmallestRectangleEnclosingBlackPixels {
	 /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) 
            return 0;
        
        int n = image.length, m = image[0].length, 
        		left = findLeft(image, 0, y), right = findRight(image, y, m - 1), 
        		top = findTop(image, 0, x), bottom = findBottom(image, x, n - 1);

        return (right - left + 1) * (bottom - top + 1);
    }
    
    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) 
                start = mid;
            else 
                end = mid;
        }
        
        if (isEmptyColumn(image, start)) 
            return end;
        
        return start;
    }
    
    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) 
                end = mid;
            else 
                start = mid;
        }
        
        if (isEmptyColumn(image, end)) 
            return start;
        
        return end;
    }
    
    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) 
                start = mid;
            else 
                end = mid;
        }
        
        if (isEmptyRow(image, start)) 
            return end;
        
        return start;
    }
    
    private int findBottom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) 
                end = mid;
            else 
                start = mid;
        }
        
        if (isEmptyRow(image, end)) 
            return start;
        
        return end;
    }
    
    private boolean isEmptyColumn(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') 
                return false;
        }
        
        return true;
    }
    
    private boolean isEmptyRow(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') 
                return false;
        }
        
        return true;
    }
}
