package model.services;

public interface IMover<CommandAccepter extends ICommandAccepter<?, ?>>
{
	public CommandAccepter getAccepter();
	public void move(MoveType type, ICharacter character);
}
