package controller;

import model.model;

import java.util.HashMap;

import View.view;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * 
 * The implementation of the controller for the MVC model
 * view - the view of the MVC model in charge of displaying and getting commands to and from the user
 * model - the model of the MVC model in charge of computing
 * Commandsmanager - holds all possible commands to get from view and send to model
 * 
 *  methods expanded in interface
 * @author adam
 *
 */


public class MyController implements Controller {

	private view view;
	private model model;
	private CommandsManager commandsManager;
	
	
	
	
	public MyController(view view, model model) {
		this.view = view;
		this.model = model;
	
		
		/**
		 * A way for us to make sure both CLI and controller share identical commands
		 */
		commandsManager = new CommandsManager(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}


	@Override
	public void notifymaze(String name) {
		view.notifyMazeIsReady(name);
	}


	@Override
	public void notifysolution(String name) {
     view.notifysolutionisready(name);				
	}


	public view getView() {
		return view;
	}


	public void setView(view view) {
		this.view = view;
	}


	public model getModel() {
		return model;
	}


	public void setModel(model model) {
		this.model = model;
	}


	public CommandsManager getCommandsManager() {
		return commandsManager;
	}


	public void setCommandsManager(CommandsManager commandsManager) {
		this.commandsManager = commandsManager;
	}


	@Override
	public void displaymaze(Maze3d todisplay) {
	view.displayMaze(todisplay);
		
	}


	@Override
	public void displaysolution(Solution<Position> todisplay) {
		view.displaysolution(todisplay);		
	}


	@Override
	public void displayerror(String error) {
		System.out.println(error);		
	}


	@Override
	public void notifymazehasbeensaved(String name) {
		view.notifymazehasbeensaved(name);		
	}


	@Override
	public void displaycross(int[][] toreturn,int firstnumber,int secondnumber) {
	view.displaycross(toreturn,firstnumber,secondnumber);
		
	}


	@Override
	public void displayDirPath(String[] list) {
		view.displayDirPath(list);

	}


	@Override
	public void notifymazeloaded() {
		view.notifymazeloaded();
		
	}


	@Override
	public void displayLoadMaze(String string) {
		view.displayloadmaze(string);		
	}


	}




