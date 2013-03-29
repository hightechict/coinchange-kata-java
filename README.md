Coinchange Kata Java
===============

The goal of this kata is to create a class which can provide change for a given amount of money.

An interface is provided to start from, as is an empty test class to run with jUnit. The interface implies that the set of valid coins will be known before executing, so we suggest you allow specifying the set of valid coins in the constructor.

Test priority
1. The change uses the coins specified
2. The change matches the amount required
3. The change is given in the optimal amount of coins
4. Calculating the change doesn't take forever