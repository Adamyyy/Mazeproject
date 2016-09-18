package View;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;



public class Myview extends Observable implements view,Observer {
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;	
	
	
	public Myview(BufferedReader insertin, PrintWriter insertout) {
		in = insertin;
		out = insertout;
		cli = new CLI(insertin,insertout);
		cli.addObserver(this);
	}
	
	
	@Override
	public void start() {
		cli.start();

	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifysolutionisready(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setcontroller(Controller insert) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayerror(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaysolution(Solution<Position> insert) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifymazehasbeensaved(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaycross(int[][] toreturn, int firstnumber, int secondnumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayDirPath(String[] list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifymazeloaded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayloadmaze(String string) {
		// TODO Auto-generated method stub

	}


	@Override
	public void update(Observable arg0, Object arg1) {
	if (arg0==cli) {
		setChanged();
		notifyObservers(arg1);
	}
		
	}

}
