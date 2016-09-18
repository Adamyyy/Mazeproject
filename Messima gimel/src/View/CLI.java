package View;
import controller.Command;
import controller.CommandsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
/**
 * 
 * @author adam
 * CLI - the CLI of the view, 
 * in - used to get commands from user
 * out - to display info to user/to write to file's etc'
 *command - intialized ouside the CLI by the Controller that has an identical datamember
 *userCommand - the command user enters
 *commandarray - the user's command split into sections (the command itself and parameters)
 */
public class CLI extends Thread {
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> Command;

	public CLI(BufferedReader insertbfr, PrintWriter insertpfr) {
		this.in=insertbfr;																//this will be the Hashmap commands inside Controller
		this.out=insertpfr;
		Command= null;
	}

/**
 * the typical start algorithm - overide the run function in a new thread	
 */

	
	
	public void start(){
		new Thread(new Runnable(){
			@Override
			public void run()
			{
		
				String userCommand;
				printinstructions();

				try{
					while(true){
						
					userCommand= in.readLine();
					String [] commandarray=userCommand.split(" "); //Splits the user command into command and parameters
					if (Command.get(commandarray[0]) !=null) {Command.get(commandarray[0]).doCommand(commandarray);} 
					//System.out.println("Command executed printing instructions)"); printinstructions();} //user inserted command exists
					if(userCommand.equals("exit")) { break;}// user wants to exit
					if (Command.get(commandarray[0])==null) {System.out.println("Invalid command");
					System.out.println("Command not executed printing instructions)"); printinstructions();} //user inserted command doesnt exist
					

					}
					System.out.println("You decdied to leave, good day :) ");
				}
				catch (Exception e) {
					System.out.println("Command error");
					System.out.println(" ");
					start();
				}

			}
		}).start();
	}
	
	
	/**
	 * A way for the commandmanger to change via the controller+view
	 * @param CMG
	 */
	
	public void setcommand(HashMap<String, Command> CMG)
	{
		
		this.Command=CMG;
	}


	/**
	 * The instructions of the cli for the user
	 */
	
	
	public void printinstructions ()
	{
		System.out.println("Please enter the commnad with no capital letters,seperate words with 1 space (commands are on the left parameters in <>)");
		System.out.println("Command dirpath <path>");
		System.out.println("Command generatemaze <name> <floors> <rows> <cols>");
		System.out.println("Command display <name>");
		System.out.println("Command displaycross <index> <x/y/z> <name>");
		System.out.println("Command savemaze <name> <file name>");
		System.out.println("command load <file name> <name> ");
		System.out.println("command solvemaze <name> <BFS/DFS>");
		System.out.println("Command displaysolution: <name>");
		System.out.println("Command Exit: Bye!");

	}
}