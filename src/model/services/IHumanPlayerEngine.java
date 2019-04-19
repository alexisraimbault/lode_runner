package model.services;

public interface IHumanPlayerEngine extends ISmartGuardsEngine
{
	void setCommand(PlayerCommand command);
	void stepPlayer();
}
