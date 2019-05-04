package contract.entities;

import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.entities.EntityDecorator;
import model.services.ICell;
import model.services.IEntity;

public class EntityContract extends EntityDecorator{

	public EntityContract(IEntity d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void setPosition(ICell cell){
		delegate.setPosition(cell);
		if(!(getX() == cell.getX() && getY() == cell.getY()))
			throw new PostconditionError("in Entity -> setPosition : the set didnt work");
	}
	

	public void setPosition(int x, int y){
		delegate.setPosition(x,y);
		if(!(getX() == x && getY() == y))
			throw new PostconditionError("in Entity -> setPosition : the set didnt work");
	}
	

	public void setX(int x){
		delegate.setX(x);
		if(!(getX() == x))
			throw new PostconditionError("in Entity -> setX : the set didnt work");
	}
	

	public void setY(int y){
		delegate.setY(y);
		if(!(getY() == y))
			throw new PostconditionError("in Entity -> setY : the set didnt work");
	}

	public void checkInvariant() {
		if(!(getContent().contains(getType())))
			throw new InvariantError("the content of the entity doesn't contain the entity");
		
	}
}
