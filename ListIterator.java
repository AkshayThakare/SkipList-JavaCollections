import java.util.Iterator;
/**
 * The ListIterator class is used to create an iterator to iterate on the ListIterator 
 * @author  Akshay Thakare
 * @version 1.0, February 2015 
 */
public class ListIterator<T extends Comparable<? super T>> implements Iterator<T> {
    private SkipNode<T> iterNode; // current node
    
	public ListIterator(SkipNode<T> head) {
		this.iterNode=head;
	}
	
	/**
	 * Returns true if skiplist has next element
	 * @param
	 * @return 
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iterNode.getNext()[0]!=null;
	}
	
	/**
	 * Returns the next node data
	 * @param
	 * @return 
	 */
	@Override
	public T next() {
		// TODO Auto-generated method stub
		iterNode=iterNode.getNext()[0];
		return iterNode.getData();
	}
}
