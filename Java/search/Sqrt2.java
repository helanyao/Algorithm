package search;

/**
 * @Description
 * Compute and return the square root of x and x >= 0.
 * 
 * @Example
 * Given n = 2 return 1.41421356
 * 
 * @Tag Facebook, Math, Binary Search
 */
public class Sqrt2 {
	/**
     * @param x a double
     * @return the square root of x
     */
    public double sqrt(double x) {
        double left = 0.0, right = x, eps = 1e-12;

        if(right < 1.0) 
            right = 1.0;

        while(right - left > eps) {
            // 二分浮点数有精度要求，这里要求小数点后八位，即二分结果达到这个精度就可以。
            // 所以要让right和left小于一个事先设定好的精度值 eps
            double mid = (right + left) / 2;
            if(mid * mid < x) 
                left = mid;
            else 
                right = mid;
        }

        return left;
    }
}
