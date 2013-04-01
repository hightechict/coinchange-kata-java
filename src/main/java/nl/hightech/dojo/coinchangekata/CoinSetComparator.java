package nl.hightech.dojo.coinchangekata;

import java.io.Serializable;
import java.util.Comparator;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

public class CoinSetComparator implements Comparator<Multiset<Integer>>, Serializable {

	private static final long serialVersionUID = -8824730849435579714L;

	public int compare(Multiset<Integer> o1, Multiset<Integer> o2) {

		return valueOf(o1) - valueOf(o2);
	}

	public static int valueOf(Multiset<Integer> coinSet) {
		int t = 0;
		for (Entry<Integer> coin : coinSet.entrySet()) {
			t += coin.getCount() * coin.getElement();
		}
		return t;
	}
}