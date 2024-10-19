
/**
* <class Editor: loading the editor in 2 ways: i-> loading an empty editor
*                                              ii-> loading the editor with the given file>
* Known Bugs: “None”>
*
* @author Firstname Lastname 
* <your Brandeis email> 
* <Month Date, Year> 
* COSI 21A PA1 
*/

package main;

public class EditorMain {

	public static void main(String[] args) {

		try {
			// Uncomment the line below to open the editor with no input file
			new EditorDisplay();

			// Uncomment the line below to open the editor with an input file that has a
			// single line of text
//			new EditorDisplay("single_line_file.txt");

			// Uncomment the line below to open the editor with an input file that has
			// multiple lines of text
//			new EditorDisplay("multi_line_file.txt");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
