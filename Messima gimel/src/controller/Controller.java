package controller;

import View.view;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.model;


/**
 * 
 * @author adam
 * notifymaze - sends View order to tell user maze was created
 * notifysolution - sends View order to tell user solution was created
 * setview - "connects" a specific view to the controller (makes it the controller's datamember)
 * setmodel - "connects" a specific model to the controller (makes it the controller's datamember)
 * displayerror - gets a string error and tells view to display it
 * displaysulotion - gets a created solution and tells view to display it
 * notifymazehasbeensaved - sends View order to tell user the maze was saved, to file etc'
 * displaycross - shows the user the cross section of the maze - toreturn = the crosssection, firstnumber - the first parameter for the cross section for example the first number in the x axis is z second number 
 *secondnumber- the second part for the cross section for example secondnumber for x axis is y
 *notifymazeloaded - sends View order to tell user the maze was loaded from the file
 *displayLoadMaze - displays the maze that was loaded from the last function
 */

public interface Controller {
	

public void notifymaze(String name);
public void notifysolution (String name);
public void displaymaze(Maze3d todisplay);
public void setView(view view);
public void setModel(model model);
public void displayerror (String error);
public void displaysolution(Solution<Position> todisplay);
public void notifymazehasbeensaved(String name);
public void displaycross(int[][] toreturn,int firstnumber,int secondnumber);
public void displayDirPath(String[] list);
public void notifymazeloaded();
public void displayLoadMaze(String string);

}
