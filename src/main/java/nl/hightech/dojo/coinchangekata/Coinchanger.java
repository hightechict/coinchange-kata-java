package nl.hightech.dojo.coinchangekata;

import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Coinchanger implements ICoinchanger {

	public Coinchanger(Set<Integer> coinSet) {}

	public Multiset<Integer> getChangeForAmount(int amountInCents) {
		Multiset<Integer> change = HashMultiset.create();
		change.add(1);

		return change;
	}

}
