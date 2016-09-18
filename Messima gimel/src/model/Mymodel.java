package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.demo.Mazeadapter;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastCellChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Controller;

//check forgithub
/**
 * The class that implements the model interface for the MVC mechanism
 * 
 * functions expanded in the interface
 * mazes - holds every created maze
 * solutions - holds every created solution
 * controller - holds the controller for the MVC mechanism
 * threadarray - holds every created thread in an array
 * 
 * 
 * @author Adam
 *
 */
public class Mymodel implements model {

	private Map<String,Maze3d> mazes= new HashMap<String,Maze3d>();
	private Map<String, Solution<Position>> solutions = new HashMap<String,Solution<Position>>();
	private Controller controller;
	private ArrayList<Thread> threadarray= new ArrayList<Thread>();
	
	
	/**
	 * created with the idea that a model will get the controller in the main with the set command
	 * 
	 */
	
	
public Mymodel() {
	mazes= new HashMap<String,Maze3d>();
	solutions = new HashMap<String,Solution<Position>>();
	controller=null;
	threadarray= new ArrayList<Thread>();
}
	
	public Mymodel(Controller insertc) {
		this.controller=insertc;
	}
	
	@Override
	public void generatemaze(String name, int z, int y, int x) {
		Thread threadgm = new Thread (new Runnable() {

			@Override
			public void run() {
				LastCellChooser LCC=new LastCellChooser();
				GrowingTreeGenerator generator=new GrowingTreeGenerator(LCC);	
				Maze3d maze= generator.generate(z, y, x);
				mazes.put(name,maze);
				controller.notifymaze(name);
			}
		});
		threadgm.start();
		threadarray.add(threadgm);
	}

	@Override
	public void display(String name) {
		Maze3d maze = mazes.get(name);
		if (!mazes.containsKey(name)) {
			controller.displayerror("There is no maze with the name: " + maze);
			return;
		}
		controller.displaymaze(maze); 
		
	}

	@Override
	public Map<String, Maze3d> getmazes() {
		
		return mazes;
	}

	@Override
	public void solvemaze(String name, String algoname) {
		Thread threadsm= new Thread(new Runnable() {
			public void run() {
				
	
		Maze3d tosolve= mazes.get(name);
		Solution<Position> sol;
		Searchable<Position> SearchableMaze= new Mazeadapter(tosolve);
		Searcher<Position> searcher= null;
		if (algoname.equals("DFS")) {searcher=new DFS<State>();;}
		if (algoname.equals("BFS")) {searcher=new BFS<Position>();}
		 sol=searcher.search(SearchableMaze);
		 solutions.put(name,sol);
		 controller.notifysolution(name);
			}
			});threadsm.start();
			threadarray.add(threadsm);
	}



	@Override
	public void displaysol(String name) {
		Solution<Position> todisplay = solutions.get(name);
		if (!solutions.containsKey(name)) {
			controller.displayerror("There is no maze solution with the name: " + name);
			return;
		}
		controller.displaysolution(todisplay);
		
		
	}

	@Override
	public void savemaze(String name, String filename) { //LO BATOACH LEGABEY HA SHMIRA SHEL HAKOVETZ
		Maze3d toworkon = mazes.get(name);
		if (!mazes.containsKey(name)) {
			controller.displayerror("There is no maze solution with the name: " + name);
			return;
		}
		byte []bytearray=toworkon.toByteArray();
		MyCompressorOutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(filename));
			out.write(bytearray);
			out.close();
			controller.notifymazehasbeensaved(name);
			

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void getcrossbyindex(int index, String z_y_x,String name) {
		int [][]toreturn = null;
		int firstnumber=0;
		int secondnumber=0;
		
		if (!(z_y_x.equals("y")) && !( z_y_x.equals( "z")) && !(z_y_x.equals("x"))) {controller.displayerror("invalid index"); return;}
		if (!mazes.containsKey(name)) {
			controller.displayerror("There is no maze solution with the name: " + name);
			return;
		}
		Maze3d tocut = mazes.get(name);

try{
if (z_y_x.equals("y")) {firstnumber=tocut.getNumofz();secondnumber =tocut.getNumofx(); toreturn=tocut.getCrossSectionByY(index);}
if (z_y_x.equals("x")) {firstnumber=tocut.getNumofz(); secondnumber=tocut.getNumofy();toreturn=tocut.getCrossSectionByX(index);}
if (z_y_x.equals("z")) {firstnumber=tocut.getNumofy();secondnumber=tocut.getNumofy();toreturn=tocut.getCrossSectionByZ(index);}
}
catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
controller.displaycross(toreturn,firstnumber,secondnumber);
	}

	@Override
	public void dirpath(String[] args) {
		if(args.length != 1) {
			controller.displayerror("Invalid path");
			return;
		}
		File file = new File(args[0].toString());
		if(!file.exists()) {
			controller.displayerror("Directory not found");
			return;
		}

		if(!file.isDirectory()) {
			controller.displayerror("Path is incorrect");
			return;
		}

		controller.displayDirPath(file.list());
	}

	@Override
	public void exit() {
	for (Thread t: threadarray) {
	t.interrupt();
	}
		
	}

	@Override
	public void load(String filename, String mazename) {
		
		File file = new File(filename);
		
		if (!(file.exists())) {
			controller.displayerror("File doesnt exist");
			return;
		}
		try {

			
			 Path path = Paths.get(file.getAbsolutePath());
			 byte[] data = Files.readAllBytes(path);
				InputStream in=new MyDecompressorInputStream(new FileInputStream(filename));
				in.read(data);
			 Maze3d newmaze= new Maze3d(data);
			 mazes.put(mazename, newmaze);
			 controller.displayLoadMaze(filename + "Has been saved" + " under the name " + mazename);			 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	}


		
	
		
	
	
	



