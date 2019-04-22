package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import model.services.MoveType;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IShortestPathsCalculator;

public class AStarCalculator implements IShortestPathsCalculator
{
	private ICharacter target;
	
	public AStarCalculator(ICharacter target)
	{
		this.target = target;
	}
	
	class Node
	{
		private int x;
		private int y;
		private int dParcourue;
		private Node pred;
		private ICharacter target;
		
		public Node(int x, int y, Node pred, ICharacter target, int dParcourue)
		{
			this.x = x;
			this.y = y;
			this.pred = pred;
			this.target = target;
			this.dParcourue = dParcourue;
		}
		
		public int getX()
		{
			return this.x;
		}
		
		public int getY()
		{
			return this.y;
		}
		
		public int getDParcourue(){
			return this.dParcourue;
		}
		
		public int heuristique()
		{
			return Math.abs(target.getX() - this.x) + Math.abs(target.getY() - this.y);
		}
		
		public int fCost(){
			return this.heuristique() + this.dParcourue;
		}
		
		public void setPred(Node pred)
		{
			this.pred = pred;
		}
		
		public Node getPred()
		{
			return this.pred;
		}
		
		public boolean isLocated(int x, int y)
		{
			return (this.x == x) && (this.y == y);
		}
		
		public boolean reachedTarget()
		{
			return (this.x == target.getX()) && (this.y == target.getY());
		}
	}
	
	@Override
	public Set<MoveType> getPaths(ICharacter character, ICharacterMoveAccepter accepter)
	{
		List<Node> closed = new ArrayList<Node>();
		List<Node> opened = new ArrayList<Node>();
		Node current = new Node(character.getX(), character.getY(), null, target, 0);
		closed.add(current);
		Set<MoveType> moves;
		
		do
		{
			moves = accepter.accept(character);
			for(MoveType move : moves)
			{
				switch(move)
				{
				case LEFT:
					opened.add(new Node(character.getX() - 1, character.getY(), current, target, current.getDParcourue() + 1));
					break;
				case RIGHT:
					opened.add(new Node(character.getX() + 1, character.getY(), current, target, current.getDParcourue() + 1));
					break;
				case DOWN:
					opened.add(new Node(character.getX() , character.getY() - 1, current, target, current.getDParcourue() + 1));
					break;
				case UP:
					opened.add(new Node(character.getX() , character.getY() + 1, current, target, current.getDParcourue() + 1));
					break;
				case NEUTRAL:
					break;
				default:
					break;
				}
			}
			Collections.sort(opened, new Comparator<Node>(){
				public int compare(Node n1, Node n2){
					if(n1.fCost() > n2.fCost())
						return 1;
					else
						return -1;
				}
			});
			current = opened.remove(0);
			character.setX(current.getX());
			character.setY(current.getY());
		}while(!current.reachedTarget() && !opened.isEmpty());
		
		if(current.reachedTarget()){
			Set<MoveType> movesToDo = EnumSet.noneOf(MoveType.class);
			while(current.getPred() != null){
				if(current.getX() == current.getPred().getX() + 1 && current.getY() == current.getPred().getY())
					movesToDo.add(MoveType.RIGHT);
				if(current.getX() == current.getPred().getX() - 1 && current.getY() == current.getPred().getY())
					movesToDo.add(MoveType.LEFT);
				if(current.getX() == current.getPred().getX() && current.getY() == current.getPred().getY() + 1)
					movesToDo.add(MoveType.UP);
				if(current.getX() == current.getPred().getX() && current.getY() == current.getPred().getY() - 1)
					movesToDo.add(MoveType.DOWN);
				current = current.getPred();
			}
			return movesToDo;
		}else{
			Set<MoveType> movesToDo = EnumSet.noneOf(MoveType.class);
			movesToDo.add(MoveType.NEUTRAL);
			return movesToDo;
		}
	}

}