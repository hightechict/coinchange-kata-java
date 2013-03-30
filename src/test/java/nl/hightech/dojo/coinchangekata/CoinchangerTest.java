package nl.hightech.dojo.coinchangekata;

import java.util.HashSet;

import org.junit.Test;

public class CoinchangerTest {

	@Test
	public void testConstructor() {
		new Coinchanger(new HashSet<Integer>());
	}
}
