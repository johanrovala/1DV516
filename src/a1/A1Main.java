package a1;

import a1.A1Queue;
import a1.A1Tree;

public interface A1Main {
	public A1Queue<String> constructQueue(String jsonFilename);
	public A1Tree<String> constructTree(A1Queue<String> jsonQueue);
	public void printTree(A1Tree<String> jsonTree);
}
