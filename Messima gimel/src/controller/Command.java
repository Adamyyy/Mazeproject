package controller;
/**
 * 
 * @author adam
 * an interface that represents every Command from the CommandsManager
 * doCommand - every command class has a different implementation to the function
 *
 */
public interface Command {
	public void doCommand(String[] args);
}