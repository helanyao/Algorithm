package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Description
 * 给出二维平面中的不同的N个点，找出回旋镖三元组的数量。
 * 一个三元组(A,B,C)，如果满足点A到点B的距离等于点A到点C的距离，则被称为回旋镖。同样三个点，不同顺序的三元组算不同的三元组。
 * N<=500，所有点的坐标值为整数且都在[-10000,10000]中。
 * 
 * @Example
 * 输入: [[0,0],[1,0],[2,0]]
 * 输出: 2
 * 说明: 共有两个回旋镖，分别为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 
 * @Reference
 * http://www.jiuzhang.com/solution/number-of-boomerangs/
 *
 * @Tag Facebook
 */
public class BoomerangNumber {
	
	private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
    
	/*
	 * 简单的方法是枚举所有三元组，判断是否满足条件并计数，O(N^3)。
	 * 
	 * 对于点A，如果有k个点到A距离相等，那么有k(k-1)个回旋镖，因为k个点中任取两个不同的点均可和A形成回旋镖，且不同的顺序算不同方案。
	 * 计算所有其他点到A的距离，统计离A有某同距离的点有几个，最后将所有个数代入k*(k-1)并相加，就得到了所有以A点为三元组中第一个点的回旋镖个数。
	 * 枚举所有点用相同的方式计算出以该点为第一个点的回旋镖数量并相加，即可得到答案。
	 * O(N^2)，每次存取HashMap的时间复杂度为O(1)，总的时间复杂度为O(N^2)。
	 */
    public int getBoomerangNum(int[][] points) {
        if (points == null)
            return 0;
        
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> cache = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                int distance = getDistance(points[i], points[j]);
                int count = cache.getOrDefault(distance, 0);
                cache.put(distance, count + 1);
            }
            for (int count : cache.values())
                ans += count * (count - 1);
        }
        
        return ans;
    }

}
