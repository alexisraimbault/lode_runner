package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import model.gamestate.entities.Cell;
import model.services.IAStarCalculator;
import model.services.IAStarNode;
import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;

public class AStarCalculator<
	Character extends ICharacter, 
	CommandType extends Enum<CommandType>> 
		implements IAStarCalculator<Character, CommandType>
{
	class Node implements IAStarNode<CommandType>
	{
		private ICell cell;
		private ICell target;
		private int cost;
		private IAStarNode<CommandType> pred;
		private CommandType type;
		
		public Node(ICell cell, ICell target, int cost, IAStarNode<CommandType> pred, CommandType type)
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
			int dist = Math.abs(target.getX() - cell.getX()) + Math.abs(target.getY() - cell.getY());
			if(cell.getContent().nbCharacters() > 1)
				return dist + 30;
			else if(cell.getContent().nbItems() > 0)
			{
				if(dist - 3 < 0)
					return 0;
				else
					return dist - 3;
			}
			else
				return dist;
		}

		@Override
		public int getWeight(){
			return this.getHeuristic() + this.cost;
		}

		@Override
		public IAStarNode<CommandType> getPred()
		{
			return pred;
		}

		@Override
		public List<CommandType> getPath()
		{
			List<CommandType> path = new ArrayList<CommandType>();
			
			IAStarNode<CommandType> current = this;
			while(current.getPred() != null)
			{
				path.add(current.getCommandType());
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
		public CommandType getCommandType()
		{
			return type;
		}

		@Override
		public ICell getTarget()
		{
			return target;
		}
	}
	
	@Override
	public List<CommandType> getPath(Character source, ICell target, ICommandApplier<Character, CommandType> applier)
	{
		Comparator<IAStarNode<CommandType>> compare_better_node = new Comparator<IAStarNode<CommandType>>()
		{
			public int compare(IAStarNode<CommandType> n1, IAStarNode<CommandType> n2)
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
		Comparator<IAStarNode<CommandType>> compare_heuristic = new Comparator<IAStarNode<CommandType>>()
		{
			public int compare(IAStarNode<CommandType> n1, IAStarNode<CommandType> n2)
			{
				if(n1.getWeight() < n2.getWeight())
					return -1;
				else if(n1.getWeight() == n2.getWeight())
					return 0;
				else
					return 1;
			}
		};
		

		ICell start = new Cell(source.getEnvironment(), source.getX(), source.getY());
		List<IAStarNode<CommandType>> closed = new ArrayList<IAStarNode<CommandType>>();
		Queue<IAStarNode<CommandType>> opened = new PriorityQueue<IAStarNode<CommandType>>(compare_heuristic);
		IAStarNode<CommandType> current = new Node(start, target, 0);
		opened.add(current);
		ICommandAccepter<Character, CommandType> accepter = applier.getAccepter();
		while(!opened.isEmpty())
		{
			current = opened.peek();
			opened.remove();
			
			if(current.getCell().equals(target))
			{
				source.setPosition(start);
				return current.getPath();
			}
			
			source.setPosition(current.getCell());
			Set<CommandType> accepted = accepter.accept(source);
			for(CommandType type : accepted)
			{
				ICell before = new Cell(source.getEnvironment(), source.getX(), source.getY());
				applier.apply(type, source);
				ICell after = new Cell(source.getEnvironment(), source.getX(), source.getY());
				source.setPosition(before);
				
				IAStarNode<CommandType> next_node = new Node(after, target, current.getCost() + 1, current, type);
				boolean found = false;
				
				for(IAStarNode<CommandType> node : closed)
				{
					if(compare_better_node.compare(node, next_node) == -1)
					{
						found = true;
						break;
					}
				}
				if(found)
					continue;
				
				for(IAStarNode<CommandType> node : opened)
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
		source.setPosition(start);
		return null;
	}
}