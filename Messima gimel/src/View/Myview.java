package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Command;
import controller.CommandsManager;
import controller.Controller;
/**
 * The standard implementation for the view in the project
 * controller - the controller in the MVC mechanism
 * CLI - the datamember that will keep running in a special thread until user is done with the program
 * in - used to get data from user
 * out - used to write data, display data
 * Methods explained in the interface page
 * @author adam
 *
 */
public class Myview implements view {

	Controller controller;
	CLI cli;
	BufferedReader in;
	PrintWriter out;
	
	
	
	public Myview(PrintWriter insertout,BufferedReader insertin) {
		this.in=insertin;
		this.out=insertout;
		cli= new CLI(insertin, insertout);
		
	}
	
	
	@Override
	public void notifyMazeIsReady(String name) {
		System.out.println(" ");
		System.out.println("The maze" + name +" is ready");
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
	}
	

	public void start() {
		cli.start();		
	}
	
	
	@Override
	public void displayMaze(Maze3d maze) {
		System.out.println(" ");
		maze.print();
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
		
	}

	@Override
	public void displaysolution(Solution<Position> insert) { //SIMON OLAY BAYATI HAKETA SHEL HA SOLUTION
		System.out.println(" ");
		System.out.println(insert.getres().toString()); 
		System.out.println("Please enter next command or enter exit to exit");

	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		this.cli.setcommand(commands);

	}

	@Override
	public void notifysolutionisready(String name) {
		System.out.println(" ");
		System.out.println("The solution" + name +" is ready");
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
	}

	@Override
	public void displayerror(String error) {
		System.out.println(error);		
	}

	@Override
	public void notifymazehasbeensaved(String name) {
		System.out.println(" ");
		System.out.println(name + " has been saved!");	
		System.out.println("Please enter next command or enter exit to exit");

	}

	@Override
	public void setcontroller(Controller insert) {
		this.controller=insert;	
		
	
	}


	@Override
	public void displaycross(int[][] toreturn, int firstnumber,int secondnumber) {
		System.out.println(" ");
		for(int i = 0; i <firstnumber; i++)
		{
			for(int j = 0; j < secondnumber; j++)
			{
				System.out.print(toreturn[i][j] + " ");
			}
			System.out.println();
		}	
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
	}


	@Override
	public void displayDirPath(String[] list) {
		System.out.println(" ");
		System.out.println("Files and directories in this path: ");
		for(String s:list) {
			System.out.println(s);
		}
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
	}


	@Override
	public void notifymazeloaded() {
		System.out.println(" ");
	System.out.println("Maze loaded successfully");
	System.out.println(" ");
	System.out.println("Please enter next command or enter exit to exit");
	}


	@Override
	public void displayloadmaze(String string) {
		System.out.println(string);
		System.out.println(" ");
		System.out.println("Please enter next command or enter exit to exit");
	}
		
	}
			
		




