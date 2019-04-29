package model.services;

import java.io.IOException;

public interface IEnvironmentLoader
{
	/*
	 * pre:
	 * 	file_path == path(file) such as file is in EnvironmentFormat
	 * 
	 * EnvironmentFormat:
	 * 	<width> <height>
	 * 	<bloc><bloc><bloc> ...
	 * 	<bloc><bloc><bloc> ...
	 * 	<bloc><bloc><bloc> ...
	 * 	......................
	 * 
	 * bloc:
	 * 	M : METAL
	 * 	X : PLATFORM
	 * 	x : HOLE
	 * 	H : LADDER
	 * 	= : HANDRAIL
	 * 	  : EMPTY
	 * 	P : PLAYER
	 * 	G : GUARD
	 * 	T : TREASURE
	 * 
	 * post:
	 * 	false
	 */
	public IEditableEnvironment loadFromFile(String file_path) throws IOException;
	
	public void uploadToFile(String file_path, IEditableEnvironment environment) throws IOException;
}
