package model.algorithms;

import java.util.Set;

import model.services.MoveType;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IEnvironment;
import model.services.IShortestPathsCalculator;

public class AStarCalculator implements IShortestPathsCalculator
{
	
	class Node
	{
		private int x;
		private int y;
		
		public Node(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	@Override
	public int[][] getPaths(ICharacter character, ICharacterMoveAccepter accepter)
	{
		IEnvironment environment = character.getEnvironment();
		
		//PriotityQueue<Node> list;
		
		Set<MoveType> moves = accepter.accept(character);
		for(MoveType move : moves)
		{
			switch(move)
			{
			
			}
		}
		// TODO Algorithm A*
		
		return null;
	}

}
