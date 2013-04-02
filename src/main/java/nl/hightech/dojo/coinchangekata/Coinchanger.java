package nl.hightech.dojo.coinchangekata;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class Coinchanger implements ICoinchanger {

	TreeSet<Integer> coinSet;
	Multiset<Integer> bestmatch;
	Set<Multiset<Integer>> rejects = Sets.newHashSet();

	public Coinchanger(Set<Integer> coinSet) {
		this.coinSet = new TreeSet<Integer>(Collections.reverseOrder());
		this.coinSet.addAll(coinSet);
	}

	public Multiset<Integer> getChangeForAmount(int amountInCents) {

		Set<Multiset<Integer>> candidates = new TreeSet<Multiset<Integer>>(Collections.reverseOrder(new CoinSetComparator()));
		candidates.add(HashMultiset.<Integer> create());
		Set<Multiset<Integer>> mutants;

		rejects = Sets.newHashSet();

		bestmatch = null;

		while (candidates.size() > 0) {
			mutants = mutate(candidates);
			findBetterMatch(mutants, amountInCents);
			rejectTooMuchChange(mutants, amountInCents);
			rejectSetsLargerOrSameSizeAsBestMatch(mutants);
			mutants.removeAll(rejects);
			candidates.addAll(mutants);
		}

		return bestmatch;
	}

	private Set<Multiset<Integer>> mutate(Set<Multiset<Integer>> candidates) {
		Multiset<Integer> mutant = candidates.iterator().next();
		Set<Multiset<Integer>> children = Sets.newHashSet();

		candidates.remove(mutant);
		rejects.add(mutant);

		for (int c : coinSet) {
			Multiset<Integer> child = HashMultiset.create(mutant);
			child.add(c);
			children.add(child);
		}

		return children;
	}

	private void rejectTooMuchChange(Set<Multiset<Integer>> candidates, int amountInCents) {
		for (Multiset<Integer> change : candidates) {
			if (CoinSetComparator.valueOf(change) > amountInCents) {
				rejects.add(change);
			}
		}
	}

	private void findBetterMatch(Set<Multiset<Integer>> candidates, int amountInCents) {
		for (Multiset<Integer> change : candidates) {
			if (CoinSetComparator.valueOf(change) == amountInCents) {
				if (bestmatch == null || bestmatch.size() > change.size()) {
					bestmatch = change;
					rejects.add(change);
				} else {
					rejects.add(change);
				}
			}
		}
	}

	private void rejectSetsLargerOrSameSizeAsBestMatch(Set<Multiset<Integer>> candidates) {
		if (bestmatch == null) return;

		for (Multiset<Integer> change : candidates) {
			if (change.size() >= bestmatch.size()) {
				rejects.add(change);
			}
		}
	}
}
