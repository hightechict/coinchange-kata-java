package nl.hightech.dojo.coinchangekata;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class CoinchangerTest {

	Coinchanger coinchanger;
	Multiset<Integer> change;

	@Test
	public void testConstructor() {
		new Coinchanger(new HashSet<Integer>());
	}

	@Test
	public void testSingleCoin() {
		coinchanger = new Coinchanger(Sets.newHashSet(1, 5, 10));
		change = coinchanger.getChangeForAmount(1);
		assertEquals(1, change.size());
	}
}
