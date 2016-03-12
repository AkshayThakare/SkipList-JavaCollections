import java.lang.reflect.Array;


/**
 * The SkipNode class is used to create skip list nodes for skip list implementation
 * @author  Akshay Thakare
 * @version 1.0, February 2015 
 */
public class SkipNode<T extends Comparable<? super T>> {
	private T data; // defines the data of skip node
	private SkipNode<T>[] next; // defines the array of next pointers
	@SuppressWarnings("unchecked")
	SkipNode(T x, int level){
		data=x; // initializing data = x
		next=(SkipNode<T>[])new SkipNode[level+1]; //initializing the next pointer array to size of MAXLEVEL+1 
	}
	
	/**
	 * resizes the next[] size of specified node by 1
	 * @param   level, node
	 */
	public void resizeIndex(int level, SkipNode<T> node){
		if(this.next.length-1 < level){
			@SuppressWarnings("unchecked")
			SkipNode<T> [] rebuildArray = (SkipNode<T>[]) Array.newInstance(SkipNode.class, level+1);
			System.arraycopy(this.next, 0, rebuildArray, 0, this.next.length); // copies the next array contents into the rebuildArray 
			rebuildArray[level]=node;
			this.next = rebuildArray;
		}else{
			this.next[level]=node;
		}
	}
	
	/**
	 * returns the next[]
	 * @param
	 */
	public SkipNode<T>[] getNext() {
		return next;
	}
	
	/**
	 * sets the next[]
	 * @param   next[]
	 */
	public void setNext(SkipNode<T>[] next) {
		this.next = next;
	}
	
	/**
	 * sets the data of node
	 * @param   data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * returns the size of next[]
	 * @param
	 */
	public int level(){
		return next.length-1;
	}
	/**
	 * returns the data
	 * @param
	 */
	public T getData(){
		return data;
	}
}
