package contract.entities;

import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import decorator.entities.EntityDecorator;
import model.services.ICell;
import model.services.IEntity;

public class EntityContract extends EntityDecorator{

	public EntityContract(IEntity d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void setPosition(ICell cell){
		checkInvariant();
		super.setPosition(cell);
		checkInvariant();
		if(!(getX() == cell.getX() && getY() == cell.getY()))
			throw new PostconditionError("in Entity -> setPosition : the set didnt work");
	}
	

	public void setPosition(int x, int y){
		checkInvariant();
		super.setPosition(x,y);
		checkInvariant();
		if(!(getX() == x && getY() == y))
			throw new PostconditionError("in Entity -> setPosition : the set didnt work");
	}
	

	public void setX(int x){
		checkInvariant();
		super.setX(x);
		checkInvariant();
		if(!(getX() == x))
			throw new PostconditionError("in Entity -> setX : the set didnt work");
	}
	

	public void setY(int y){
		checkInvariant();
		super.setY(y);
		checkInvariant();
		if(!(getY() == y))
			throw new PostconditionError("in Entity -> setY : the set didnt work");
	}

	public void checkInvariant() {
		if(!(getContent().contains(getType())))
			throw new InvariantError("the content object of the entity doesn't contain the entity");
	}
}
