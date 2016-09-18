package View;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Command;
import controller.Controller;


/**
 * 
 * @author adam
 * start - runs the CLI and initiates the progrma
 *notifyMazeisReady - notifies user maze was created, name = maze name
 *notifysolutionisready - notifies user solution was created, name = solution name
 *setcontroller - sets the controller for the MVC model
 *displayeror - gets a string of error to display for the user - error = String containing the error
 *displaymaze - shows the user the generated maze
 *displaysolution - shows the user the generated solution
 *setCommands - sets the CLI Command to the Commandset in the controller
 *notifymazehasbeensaved - notifies user the Maze has been saved file
 *displaycross - shows the user the cross section of the maze - toreturn = the crosssection, firstnumber - the first parameter for the cross section for example the first number in the x axis is z second number 
 *secondnumber- the second part for the cross section for example secondnumber for x axis is y
 *displayDirPath - display the directory
 *notifymazeloaded - notifies user the maze has loaded from file
 *displayloadmaze -  
 *
 *
 */


public interface view {
	public void start();
	public void notifyMazeIsReady(String name);
	public void notifysolutionisready(String name);
	public void setcontroller (Controller insert);
	public void displayMaze(Maze3d maze);
	public void displayerror(String error);
	public void displaysolution (Solution <Position> insert); //SIMON OLAY BAYATI HAKETA SHEL HA SOLUTION
	public void setCommands(HashMap<String, Command> commands);
	public void notifymazehasbeensaved(String name);
	public void displaycross(int[][] toreturn,int firstnumber,int secondnumber);
	public void displayDirPath(String[] list);
	public void notifymazeloaded();
	public void displayloadmaze(String string);
}


