/**
 * The FindResult class creates a Result object to store the node and Previous nodes visited in an array
 * @author  Akshay Thakare
 * @version 1.0, February 2015 
 */
public class FindResult<T extends Comparable<? super T>> {
private SkipNode<T> node; // node of Skiplist
private SkipNode<T>[] prev; // array of prev[]
public FindResult(SkipNode<T> node, SkipNode<T>[] prev) {
	super();
	this.node = node;
	this.prev = prev;
}
/**
 * Returns the node of Skiplist
 * @param
 * @return 
 */
public SkipNode<T> getNode() {
	return node;
}

/**
 * sets the skip node
 * @param 
 * @return 
 */
public void setNode(SkipNode<T> node) {
	this.node = node;
}

/**
 * returns the previously visited array
 * @param 
 * @return prev[]
 */
public SkipNode<T>[] getPrev() {
	return prev;
}

/**
 * sets the prev[]
 * @param
 * @return 
 */
public void setPrev(SkipNode<T>[] prev) {
	this.prev = prev;
}


}
