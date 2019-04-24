package model.services;

public interface ICell
{
	public IEnvironment getEnvironment();
	public int getX();
	public int getY();
	
	/*
	 * post:
	 * 	getNature() = getEnvironment().getCellNature(x, y)
	 */
	public Nature getNature();
	
	/*
	 * post:
	 * 	getContent() = getEnvironment().getCellContent(x, y)
	 */
	public IContent getContent();
	
	/*
	 * post:
	 * 	equals(other) = getX() = other.getX() && getY() = other.getY() && getEnvironment() == other.getEnvironment()
	 */
	public boolean equals(ICell other);
	
	/*
	 * invariants:
	 * 	0 <= getX() <= getEnvironment().getWidth() - 1
	 * 	0 <= getY() <= getEnvironment().getHeight() - 1
	 */
}
