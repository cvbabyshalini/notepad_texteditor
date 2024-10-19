
/**
* <class Node: each character in the editor is stored in node, each character pointing
* to previous and next node>
* Known Bugs: “None”>
*
* @author Firstname Lastname 
* <your Brandeis email> 
* <Month Date, Year> 
* COSI 21A PA1 
*/

package main;

public class Node {
	
	public Node next;
	public Node prev;
	public char data;
	
	public Node(char c) {
		this.next = null;
		this.prev = null;
		this.data = c;
	}
}
