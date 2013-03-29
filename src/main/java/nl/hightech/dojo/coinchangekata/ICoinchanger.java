package nl.hightech.dojo.coinchangekata;

import com.google.common.collect.Multiset;

public interface ICoinchanger {

	public Multiset<Integer> getChangeForAmount(int amountInCents);
}
