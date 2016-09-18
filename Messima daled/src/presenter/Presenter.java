package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.View;

import controller.CommandsManager;
import model.Model;

public class Presenter implements Observer {
	Model model;
	view view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;

	public Presenter(Model model, view view) {
		this.view = view;
		this.model = model;

		commandsManager = new CommandsManager(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
