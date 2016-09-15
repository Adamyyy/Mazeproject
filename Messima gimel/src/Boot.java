import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import View.Myview;
import View.view;
import controller.Controller;
import controller.MyController;
import model.Mymodel;
import model.model;

public class Boot {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		view mainview= new Myview(out, in);
		
		model mainmodel = new Mymodel();
		Controller maincontroller = new MyController (mainview,mainmodel);
		mainview.setcontroller(maincontroller);
		mainmodel.setController(maincontroller);
		
		mainview.start();

	}
}