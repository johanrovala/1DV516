package a1;

import a1.A1Queue;
import a1.A1Tree;

import java.io.FileNotFoundException;

public interface A1Main {
	public A1Queue<String> constructQueue(String jsonFilename) throws FileNotFoundException;
	public A1Tree<String> constructTree(A1Queue<String> jsonQueue);
	public void printTree(A1Tree<String> jsonTree);
}
