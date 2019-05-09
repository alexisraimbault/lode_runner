package contract.environment;

import contract.contracterr.InitError;
import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.environment.ContentDecorator;
import model.services.EntityType;
import model.services.IContent;

public class ContentContract extends ContentDecorator{
	
	public ContentContract(IContent d) {
		super(d);
		checkInvariant();
		checkInit();
	}

	/*
	 * add(type) := add(type, 1)
	 */
	public void add(EntityType type) {
		int prenb = counts(type);
		checkInvariant();
		super.add(type);
		checkInvariant();
		if(!(counts(type) == prenb + 1))
			throw new PostconditionError("in Content -> add : the nb of type didnt increment as expected");
			
	}
	
	/*
	 * post:
	 * 	counts(type) == counts(type)@before + occ
	 */
	public void add(EntityType type, int occ){
		int prenb = counts(type);
		checkInvariant();
		super.add(type, occ);
		checkInvariant();
		if(!(counts(type) == prenb + occ))
			throw new PostconditionError("in Content -> add : the nb of type didnt increment as expected");
	}
	
	
	/*
	 * remove(type) := remove(type, 1)
	 */
	public void remove(EntityType type){
		int prenb = counts(type);
		checkInvariant();
		super.remove(type);
		checkInvariant();
		if(!(counts(type) == prenb - 1))
			throw new PostconditionError("in Content -> remove : the nb of type didnt decrease as expected");
	}
	
	/*
	 * pre:
	 * 	counts(type) >= occ
	 * 
	 * post:
	 * 	count(type) == count(type)@before - occ
	 */
	public void remove(EntityType type, int occ){
		if(!(counts(type) >= occ))
			throw new PreconditionError("in Content -> remove : cant remove more than there already is");
		int prenb = counts(type);
		checkInvariant();
		super.remove(type, occ);
		checkInvariant();
		if(!(counts(type) == prenb - occ))
			throw new PostconditionError("in Content -> remove : the nb of type didnt decrease as expected");
	}
	
	/*
	 * post:
	 * 	size() == 0
	 */
	public void clear(){
		checkInvariant();
		super.clear();
		checkInvariant();
		if(!(super.size() == 0))
			throw new PostconditionError("in Content -> clear : the size should be equal to zero");
	}
	
	/*
	 * invariants:
	 * 	forall type : EntityType.values()
	 * 		counts(type) >= 0
	 * 	forall type : EntityType.values()
	 * 		contains(type) <=> counts(type) > 0
	 * 	size() == nbCharacters() + nbItems()
	 * 	size() == 0 <=> isEmpty()
	 * 	nbCharacters() >= 0
	 * 	nbItems() >= 0
	 * 	size() == sum(forall type : EntityType.values(), counts(type))
	 * 	
	 */
	public void checkInit(){
		if(!(super.size() == 0))
			throw new InitError("in Content : the size should be equal to zero at init");
	}
	
	public void checkInvariant(){
		int sum = 0;
		for(EntityType type : EntityType.values()){
			if(!(counts(type) >= 0))
				throw new InvariantError("the counts() of a EntityType should not be negative");
			if(!(contains(type) && counts(type) <= 0))
				throw new InvariantError("the counts() of a EntityType that is positive to the contains test should not be null.");
			sum += counts(type);
		}
		if(!(size() == nbCharacters() + nbItems()))
			throw new InvariantError("size() should be equat to nbCharacters() + nbItems().");
		if(size() == 0 && !isEmpty())
			throw new InvariantError("if size() == 0, the isEmptyTest should be positive.");
		if(!(nbCharacters() >= 0))
			throw new InvariantError("nbCharacters() cant be negative");
		if(!(nbItems() >= 0))
			throw new InvariantError("nbItems() cant be negative");
		if( !(size() == sum))
			throw new InvariantError("size() should be equal to the sum of the counts() of all possible types");
	}

}
