package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.EntityType;
import model.services.IEditableEnvironment;
import model.services.IEnvironmentLoader;
import model.services.Nature;

public class EnvironmentLoader implements IEnvironmentLoader
{
	@Override
	public IEditableEnvironment loadFromFile(String file_path) throws IOException
	{
		FileReader fr = new FileReader(file_path);
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br);
		
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		scanner.nextLine();
		
		IEditableEnvironment environment = new EditableEnvironment(new DynamicScreen());
		environment.resize(width, height);
		
		String line = null;
		for(int ry = 0; ry < height; ++ry)
		{
			int y = height - 1 - ry;
			line = scanner.nextLine();
			if(line.isEmpty())
				break;
			for(int x = 0; x < line.length(); ++x)
			{
				Nature nature = null;
				switch(line.charAt(x))
				{
				case 'M':
					environment.setCellNature(x, y, Nature.METAL);
					break;
				case 'X':
					environment.setCellNature(x, y, Nature.PLATFORM);
					break;
				case 'x':
					environment.setCellNature(x, y, Nature.HOLE);
					break;
				case 'H':
					environment.setCellNature(x, y, Nature.LADDER);
					break;
				case '=':
					environment.setCellNature(x, y, Nature.HANDRAIL);
					break;
				case ' ':
					environment.setCellNature(x, y, Nature.EMPTY);
					break;
				case 'P':
					environment.getCellContent(x, y).add(EntityType.PLAYER);
					break;
				case 'G':
					environment.getCellContent(x, y).add(EntityType.GUARD);
					break;
				case 'T':
					environment.getCellContent(x, y).add(EntityType.TREASURE);
					break;
				}
				assert nature != null;
			}
		}
		br.close();
		
		return environment;
	}

}
