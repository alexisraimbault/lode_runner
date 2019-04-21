package model.services;

public interface IHumanPlayerEngine extends ISmartGuardsEngine
{
	void setCommand(PlayerCommandType command);
	void stepPlayer();
}
