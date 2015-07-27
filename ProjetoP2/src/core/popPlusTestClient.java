package core;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class popPlusTestClient {

	public static void main(String[] args) {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("use_case_1.txt");
		// Instantiate the Monopoly Game fa�ade
		Facade popFacade = new Facade();
		// Instantiate EasyAccept fa�ade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(popFacade, files);
		// Execute the tests
		eaFacade.executeTests();
		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
	}

}