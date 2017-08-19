package string;

/**
 * @Description
 * 判断字符串是IPv4还是IPv6地址或都不是。
 * IPv4以十进制表示，由四个十进制数组成，每个数字范围从0到255，以点（“.”）分隔，如172.16.254.1;
 * IPv6以十六进制表示，由八个四位的十六进制数组成，以冒号（“:”）分隔，如2001:0db8:85a3:0000:0000:8a2e:0370:7334。
 * 可以省略一些前导零或把字母以大写字母表示，所以2001:db8:85a3:0:0:8A2E:0370:7334也是合法的IPv6地址。
 * 但不能完全省略0，比如2001:0db8:85a3::8A2E:0370:7334不是合法的IPv6地址。
 * 多余的前导零也是不合法的，比如02001:0db8:85a3:0000:0000:8a2e:0370:7334。
 * 
 * @Example
 * 输入: "172.16.254.1"
 * 输出: "IPv4"
 * 输入: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出: "IPv6"
 * 输入: "256.256.256.256"
 * 输出: "Neither"
 * 
 * @Tag String, Google
 *
 */
public class IsIP {
	
	private static final String IPv6_CHAR = "0123456789abcdefABCDEF";
	private static final String IPv4_SPEARATOR = ".";
	private static final String IPv6_SPEARATOR = ":";
	private static final int MAX = 255;
	
	/*
	 * param ip: a string
     * return: 0 for invalid ip address; 1 for IPv4; 2 for IPv6.
	 */
	public int validIP(String ip) {
		if (ip == null || ip.length() < 7)
			return 0;
		
        if (isIPv4(ip))
        	return 1;
        if (isIPv6(ip))
        	return 2;
        
        return 0;
    }
	
	/*
	 * param ip: a string
	 * return: whether the input string is valid IPv4 address
	 */
    public boolean isIPv4(String ip){
    	if (ip == null || ip.length() < 7 || !ip.contains(IPv4_SPEARATOR) || ip.length() > 15)
    		return false;
        
        String[] arr = ip.split("\\.");
        if (arr.length != 4 || (arr[0].length() > 1 && ip.charAt(0) == '0'))
        	return false;
        
        for (String s : arr) {
        	if (s.length() > 3)
        		return false;
        	
        	int num = 0;
        	for (int i = 0; i < s.length(); i++)
        		if (!Character.isDigit(s.charAt(i))) 
                    return false;  
                else 
                	num = num * 10 + s.charAt(i)-'0';
        	if(num > MAX) 
        		return false;
        }
        
        return true;
    }
    
    public boolean isIPv6(String ip) {
        if (ip == null || ip.length() < 15 || ip.length() > 39 || !ip.contains(":"))
        	return false;
        
        String[] arr = ip.split(IPv6_SPEARATOR);
        if (arr.length != 8)
        	return false;
        
        for (String s : arr) {
        	if (s.length() > 4)
        		return false;
        
        	for (int i = 0; i < s.length(); i++)
        		if (!IPv6_CHAR.contains(String.valueOf(s.charAt(i))))
        			return false;
        }
        	
        return true;
    }
    
}
