package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.EntityType;
import model.services.IContent;
import model.services.IEditableEnvironment;
import model.services.IEnvironmentLoader;
import model.services.Nature;

public class EnvironmentLoader implements IEnvironmentLoader
{
	@Override
	public IEditableEnvironment loadFromFile(String file_path) throws IOException
	{
		FileReader fr = new FileReader("maps/" + file_path);
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

	@Override
	public void uploadToFile(String file_path, IEditableEnvironment environment) throws IOException {
		System.out.println("saving to file : " + file_path);
		try (
			Writer writer = new BufferedWriter(new OutputStreamWriter(
		    new FileOutputStream("maps/" + file_path + ".txt"), "utf-8"))) {
			writer.write(environment.getWidth() + " " + environment.getHeight() + '\n');
			for(int j = environment.getHeight() - 1; j >= 0; j--){
				String s = "";
				for(int i = 0; i < environment.getWidth(); i++){
					boolean isEntity = false;
					IContent contentCell = environment.getCellContent(i, j);
					for(EntityType type : EntityType.values())
					{
						if(contentCell.contains(type) && !isEntity){
							switch(type)
							{
							case PLAYER:
								isEntity = true;
								s += 'P';
								break;
							case GUARD:
								isEntity = true;
								s += 'G';
								break;
							case TREASURE:
								isEntity = true;
								s += 'T';
								break;
							case TELEPORTER:
								isEntity = true;
								s += '|';
								break;
							case FANTOM:
								isEntity = true;
								s += 'F';
								break;
							default:
								isEntity = false;
								break;
							
							}
						}
					}
					if(!isEntity){
						switch(environment.getCellNature(i, j))
			  			{
			  			case EMPTY:
			  				s += ' ';
			  				break;
			  			case HOLE:
			  				s += 'x';
			  				break;
			  			case METAL:
			  				s += 'M';
			  				break;
			  			case PLATFORM:
			  				s += 'X';
			  				break;
			  			case LADDER:
			  				s += 'H';
			  				break;
			  			case HANDRAIL:
			  				s += '=';
			  				break;
			  			default:
			  				break;
			  			}
					}
					
				}
				if(j != 0)
					writer.write(s + '\n');
				else
					writer.write(s);
				
			}
		}
	}

}
