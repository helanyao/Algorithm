package string.test;

import static org.junit.Assert.*;
import org.junit.Test;
import string.IsIP;

public class IsIPTest {

	private static IsIP tester = new IsIP();

	@Test
	public void testIsIPv4() {
		String ip = "";
		assertFalse(tester.isIPv4(ip));
		ip = "172.16.254.1";
		assertTrue(tester.isIPv4(ip));
		ip = "256.256.256.256";
		assertFalse(tester.isIPv4(ip));
		ip = "0172.16.254.1";
		assertFalse(tester.isIPv4(ip));
		ip = "00.16.254.1";
		assertFalse(tester.isIPv4(ip));
		ip = "0.16.254.1";
		assertTrue(tester.isIPv4(ip));
		ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
		assertFalse(tester.isIPv4(ip));
	}

	@Test
	public void testIsIPv6() {
		String ip = "";
		assertFalse(tester.isIPv6(ip));
		ip = "172.16.254.1";
		assertFalse(tester.isIPv6(ip));
		ip = "256.256.256.256";
		assertFalse(tester.isIPv6(ip));
		ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
		assertTrue(tester.isIPv6(ip));
		ip = "2001:db8:85a3:0:0:8A2E:0370:7334";
		assertTrue(tester.isIPv6(ip));
		ip = "2001:0db8:85a3::8A2E:0370:7334";
		assertFalse(tester.isIPv6(ip));
		ip = "02001:0db8:85a3:0000:0000:8a2e:0370:7334";
		assertFalse(tester.isIPv6(ip));
		ip = "0:0db8:85a3:0000:0000:8a2e:0370:7334";
		assertTrue(tester.isIPv6(ip));
	}
	
}
