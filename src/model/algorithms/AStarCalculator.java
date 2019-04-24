package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import model.services.MoveType;
import model.services.IAStarNode;
import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.IMover;
import model.services.IShortestPathCalculator;

public class AStarCalculator<Character extends ICharacter> implements IShortestPathCalculator<Character>
{
	class Node implements IAStarNode
	{
		private ICell cell;
		private ICell target;
		private int cost;
		private IAStarNode pred;
		private MoveType type;
		
		public Node(ICell cell, ICell target, int cost, IAStarNode pred, MoveType type)
		{
			this.cell = cell;
			this.target = target;
			this.cost = cost;
			this.pred = pred;
			this.type = type;
		}
		
		public Node(ICell cell, ICell target, int cost)
		{
			this(cell, target, cost, null, null);
		}
		
		@Override
		public ICell getCell()
		{
			return cell;
		}
		
		@Override
		public int getCost(){
			return this.cost;
		}

		@Override
		public int getHeuristic()
		{
			return Math.abs(target.getX() - cell.getX()) + Math.abs(target.getY() - cell.getY());
		}

		@Override
		public int getWeight(){
			return this.getHeuristic() + this.cost;
		}

		@Override
		public IAStarNode getPred()
		{
			return pred;
		}

		@Override
		public List<MoveType> getPath()
		{
			List<MoveType> path = new ArrayList<MoveType>();
			
			IAStarNode current = this;
			while(current.getPred() != null)
			{
				path.add(current.getMoveType());
				current = current.getPred();
			}
				
			Collections.reverse(path);
			return path;
		}

		@Override
		public boolean hasPred()
		{
			return pred != null;
		}

		@Override
		public MoveType getMoveType()
		{
			return type;
		}
	}
	
	@Override
	public List<MoveType> getPath(Character source, ICell target, IMover<Character> mover)
	{
		Comparator<IAStarNode> compare_better_node = new Comparator<IAStarNode>()
		{
			public int compare(IAStarNode n1, IAStarNode n2)
			{
				if(!n1.getCell().equals(n2.getCell()))
					return 1;

				if(n1.getWeight() < n2.getWeight())
					return -1;
				else if(n1.getWeight() == n2.getWeight())
					return 0;
				else
					return 1;
			}
		};
		Comparator<IAStarNode> compare_heuristic = new Comparator<IAStarNode>()
		{
			public int compare(IAStarNode n1, IAStarNode n2)
			{
				if(n1.getWeight() < n2.getWeight())
					return -1;
				else if(n1.getWeight() == n2.getWeight())
					return 0;
				else
					return 1;
			}
		};
		

		ICell start = source.getCell();
		List<IAStarNode> closed = new ArrayList<IAStarNode>();
		Queue<IAStarNode> opened = new PriorityQueue<IAStarNode>(compare_heuristic);
		IAStarNode current = new Node(source.getCell(), target, 0);
		opened.add(current);
		ICommandAccepter<Character, MoveType> accepter = mover.getAccepter();
		while(!opened.isEmpty())
		{
			current = opened.peek();
			opened.remove();
			
			if(current.getHeuristic() <= 1)
			{
				source.setCell(start);
				return current.getPath();
			}
			
			source.setCell(current.getCell());
			Set<MoveType> accepted = accepter.accept(source);
			for(MoveType type : accepted)
			{
				ICell next = mover.next(type, source);
				IAStarNode next_node = new Node(next, target, current.getCost() + 1, current, type);
				boolean found = false;
				
				for(IAStarNode node : closed)
				{
					if(compare_better_node.compare(node, next_node) == -1)
					{
						found = true;
						break;
					}
				}
				if(found)
					continue;
				
				for(IAStarNode node : opened)
				{
					if(compare_better_node.compare(node, next_node) == -1)
					{
						found = true;
						break;
					}
				}
				
				if(found)
					continue;
				
				opened.add(next_node);
			}
			closed.add(current);
		}
		source.setCell(start);
		return null;
	}
}