
/**
* <class StudentEditorTests: test cases for the methods toString, clear and save have been written
*                            test cases for edge cases, boundary cases of the various methods in the Editor class have been written
*                             test case for saving and loading large files>
* Known Bugs: “None”>
*
* @author Firstname Lastname 
* <your Brandeis email> 
* <Month Date, Year> 
* COSI 21A PA1 
*/

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import main.Editor;

class StudentEditorTests {

	@Test
    public void testToString() {
        Editor editor = new Editor();
        assertEquals("", editor.toString());

        editor.insert('a');
        editor.insert('b');
        editor.insert('c');
        assertEquals("abc", editor.toString());
    }

    @Test
    public void testClear() {
        Editor editor = new Editor();
        editor.insert('a');
        editor.insert('b');
        editor.clear();
        assertEquals("", editor.toString());
    }

    @Test
    public void testSaveAndLoad() throws FileNotFoundException {
        // Test saving and loading with different file paths
        Editor editor = new Editor();
        editor.insert('a');
        editor.insert('b');
        editor.save("test_output.txt");
        
        // Load the saved file into a new editor
        Editor loadedEditor = new Editor("test_output.txt");
        assertEquals("ab", loadedEditor.toString());
    }

    @Test
    public void testEdgeCases() {
        // Test with an empty editor
        Editor editor = new Editor();
        assertEquals("", editor.toString());

        // Test with a single character
        editor.insert('a');
        assertEquals("a", editor.toString());

        // Test with multiple characters
        editor.insert('b');
        editor.insert('c');
        assertEquals("abc", editor.toString());

        // Test with special characters
        editor.insert('\n');
        editor.insert('$');
        assertEquals("abc\n$", editor.toString());

        // Test with maximum allowed characters
        for (int i = 0; i < 65536; i++) {
            editor.insert('a');
        }
        // Assuming insertion operation doesn't throw an exception,
        // this will test toString() with maximum allowed characters
        assertNotNull(editor.toString());
    }

    @Test
    public void testBoundaryCases() {
        // Test moving cursor to extreme positions (beginning and end)
        Editor editor = new Editor();
        editor.insert('a');
        editor.moveRight(); // Cursor at end
        editor.moveLeft(); // Cursor back to beginning
        assertEquals(0, editor.getCursorPosition());

        // Test insertion and deletion at extreme positions
        for (int i = 0; i < 65536; i++) {
            editor.insert('a');
        }
        editor.moveRight(); // Cursor at end
        editor.insert('b'); // Insertion at end
        assertEquals(65538, editor.size());
        editor.moveLeft(); // Cursor before last character
        editor.delete(); // Delete last character
        assertEquals(65537, editor.size());
    }

    @Test
    public void testLargeFileSaveAndLoad() throws FileNotFoundException {
        // Test saving and loading large files
        Editor editor = new Editor();
        for (int i = 0; i < 10000; i++) {
            editor.insert('a');
        }
        editor.save("large_file.txt");

        // Load the saved file into a new editor
        Editor loadedEditor = new Editor("large_file.txt");
        assertEquals(10000, loadedEditor.size());
    }

}
