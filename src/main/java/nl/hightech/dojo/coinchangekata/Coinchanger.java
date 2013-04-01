package nl.hightech.dojo.coinchangekata;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

public class Coinchanger implements ICoinchanger {

	TreeSet<Integer> coinSet;

	public Coinchanger(Set<Integer> coinSet) {
		this.coinSet = new TreeSet<Integer>(Collections.reverseOrder());
		this.coinSet.addAll(coinSet);
	}

	public Multiset<Integer> getChangeForAmount(int amountInCents) {

		Multiset<Integer> change = HashMultiset.create();

		while (valueOf(change) < amountInCents) {
			change.add(largestFittingCoin(amountInCents - valueOf(change)));
		}

		return change;
	}

	private int largestFittingCoin(int amount) {
		for (int c : coinSet) {
			if (c <= amount) return c;
		}

		throw new IllegalArgumentException();
	}

	private static int valueOf(Multiset<Integer> coinSet) {
		int t = 0;
		for (Entry<Integer> coin : coinSet.entrySet()) {
			t += coin.getCount() * coin.getElement();
		}
		return t;
	}

}
