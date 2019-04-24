package model.services;

public interface IAStarDecision<Character extends ICharacter> extends IShortestPathDecision<Character>
{
	/*
	 * invariants:
	 * 	getCalculator() is IAStarCalculator
	 */
}
