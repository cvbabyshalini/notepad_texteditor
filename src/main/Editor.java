
/**
* <class Editor: the methods to insert, delete, backspace text in the editor have been 
* written in this class. The save method is for saving the text into a file. Two constructors 
* have been written default one is to load empty editor and parameterized is to load a file
* into empty editor>
* Known Bugs: “None”>
*
* @author Firstname Lastname 
* <your Brandeis email> 
* <Month Date, Year> 
* COSI 21A PA1 
*/

package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Editor {
	
	public int numChars; /** KEEP THIS PUBLIC : use this to store the number of characters in your Editor */
	public int curPos; /** KEEP THIS PUBLIC : use this to store the current cursor index in [0, numChars] */
	
	public Node cur; /** KEEP THIS PUBLIC : use this to reference the node that is after the visual cursor or null if curPos = numChars */
	public Node head; /** KEEP THIS PUBLIC : use this to reference the first node in the Editor's doubly linked list */
	public Node tail; /** KEEP THIS PUBLIC : use this to reference the last node in the Editor's doubly linked list */
	
	public Editor() {
		numChars = 0;
		curPos = 0;
		cur = null;
		head = null;
		tail = null;
	}
	
	public Editor(String filepath) throws FileNotFoundException {
		this();
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (char c : line.toCharArray()) {
                insert(c);
            }
            if (scanner.hasNextLine()) {
                insert('\n');
            }
        }
        moveToTail();
        scanner.close();
	}
	
	public int getCursorPosition() {
		return curPos;
	}
	
	public int size() {
		return numChars;
	}
	
	public void moveRight() {
		if (curPos != numChars) {
            cur = cur.next;
            curPos++;
        }
	}
	
	public void moveLeft() {
		if (curPos == numChars) {
            cur = tail;
            curPos--;
        }
		else if(curPos<numChars && curPos>0) {
			cur = cur.prev;
			curPos--;
		}
	}
	
	public void moveToHead() {
		cur = head;
        curPos = 0;
	}
	
	public void moveToTail() {
		cur = null;
        curPos = numChars;
	}
	
	public void insert(char c) { 
		Node newNode = new Node(c);
        if (numChars == 0) {
            head = newNode;
            tail = newNode;
        } else {
            if (cur != null) {
                if (cur.prev != null) {
                    cur.prev.next = newNode;
                    newNode.prev = cur.prev;
                    newNode.next = cur;
                    cur.prev = newNode;
                } else {
                	head = newNode;
                	cur.prev = newNode;
                	newNode.next = cur;
                }
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
        numChars++;
        curPos++;
	}
	
	public void delete() {
		System.out.println("delete");
		if (cur != null && cur.next != null) {
            Node nextNode = cur.next;
            if (cur.prev != null) {
                cur.prev.next = nextNode;
            } else {
                head = nextNode;
            }
            nextNode.prev = cur.prev;
            cur = nextNode;
            numChars--;
        }
		else if(cur != null && cur.next == null) {
			if(cur.prev==null) {
				clear();
			}
			else {
				tail = cur.prev;
				tail.next = null;
				cur = null;
				numChars--;
			}
		}
	}
	
	public void backspace() {
		System.out.println("backspace");
		System.out.println(cur!=null?cur.data:"null");
		if(cur == null) {
			if(numChars == 1 || numChars == 0) {
				clear();
			}
			else if(curPos == numChars) {
				tail = tail.prev;
				tail.next = null;
				curPos--;
				numChars--;
			}
		}
		else {
			if(cur.prev == null) {
				curPos = 0;
			}
			else if(cur.prev != null) {
				Node prevNode = cur.prev;
				if(prevNode.prev == null) {
					head = head.next;
					prevNode.next = null;
					cur.prev = null;
					curPos--;
					numChars--;
				}
				else {
					prevNode.prev.next = cur;
					cur.prev = prevNode.prev;
					prevNode = null;
					curPos--;
					numChars--;
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
	}
	
	public void clear() {
		 numChars = 0;
	     curPos = 0;
	     head = null;
	     tail = null;
	     cur = null;
	}
	
	public void save(String savepath) throws FileNotFoundException {
		File file = new File(savepath);
        PrintStream ps = new PrintStream(file);
        ps.print(toString());
        ps.close();
	}
	
	
}
