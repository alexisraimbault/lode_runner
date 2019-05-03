package model.services;

import java.util.List;

public interface IAStarNode<CommandType extends Enum<CommandType>>
{
	ICell getCell();
	int getCost();
	
	/*
	 * set:
	 * 	distance := distance(getCell(), getTarget())
	 * 	meetingCharacter := getCell().getContent().nbCharacters() > 1
	 * 	meetingItem := getCell().getContent().nbItems() > 0
	 * post:
	 * 	meetingCharacter =>
	 * 		meetingItem =>
	 * 			getHeuristic() == distance + 30 - 3
	 * 		!meetingItem =>
	 * 			getHeuristic() == distance + 30
	 * 	!meetingCharacter =>
	 * 		meetingItem =>
	 * 			getHeuristic() == max(distance - 3, 0)
	 * 		!meetingItem =>
	 * 			getHeuristic() == distance
	 */
	int getHeuristic();
	int getWeight();
	boolean hasPred();
	
	ICell getTarget();
	
	/*
	 * pre:
	 * 	hasPred()
	 */
	IAStarNode<CommandType> getPred();
	
	/*
	 * pre:
	 * 	hasPred()
	 */
	CommandType getCommandType();
	
	List<CommandType> getPath();
	
	/*
	 * invariants:
	 * 	getCost() >= 0
	 * 	getHeuristic() >= 0
	 * 	getWeight() = getCost() + getHeuristic()
	 * 	!hasPred() <=> getPath().isEmpty()
	 */
}
