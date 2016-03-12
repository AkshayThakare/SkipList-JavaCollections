import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
/**
 * The SkipListImpl class implements the methods from SkipList interface.
 * The methods implemented are add ,ceiling ,contains ,findIndex ,first ,floor ,isEmpty ,last ,rebuild ,remove and size
 * @author  Akshay Thakare
 * @version 1.0, February 2015 
 */

public class SkipListImpl<T extends Comparable<? super T>> implements SkipList<T> {
	private SkipNode<T> head; // defines the head of the SkipList
	private static int size=0; // defines the current size of SkipList
	private static int MAXLEVEL = 10; // defines the max size of the next[]


	public SkipListImpl(){
		head=new SkipNode<T>(null, MAXLEVEL); // creating the head of skiplist with data = null and next[] size as "MAXLEVEL"
		size=0;                               // setting size of new skiplist as 0
	}

	public static void main(String[] args) {

		Scanner sc = null;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		SkipListImpl<Long> skipList = new SkipListImpl<Long>();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Add": {
				operand = sc.nextLong();
				skipList.add(operand);
				result = (result + 1) % modValue;
				break;
			}
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "FindIndex": {
				operand = sc.nextLong();
				returnValue = skipList.findIndex((int)operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "First": {
				returnValue = skipList.first();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = skipList.last();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = skipList.floor(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Remove": {
				operand = sc.nextLong();
				if (skipList.remove(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			case "Contains": {
				operand = sc.nextLong();
				if (skipList.contains(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			case "Rebuild": {
				skipList.rebuild();
				break;
			}

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(result + " " + elapsedTime);

	}
	/**
	 * returns the FindResult object with node and all previously visited nodes[]
	 * @param   x
	 * @return FindResult object
	 */
	public FindResult<T> find(T x){
		SkipNode<T> p = head;
		@SuppressWarnings("unchecked")
		SkipNode<T>[] prev=(SkipNode<T>[])new SkipNode[MAXLEVEL+1];
		for (int level = head.level(); level >= 0; level--) {
			while (p.getNext()[level] != null && x.compareTo(p.getNext()[level].getData()) > 0) { //checks if x is greater than current nodes data 
				p = p.getNext()[level];
			}
			prev[level]=p;
		}
		if (p.getNext()[0]!=null && x.equals(p.getNext()[0].getData())) {
			return new FindResult<T>(p.getNext()[0],prev); // if x is found in the skiplist
		}else{
			return new FindResult<T>(null,prev); // if x is not found in the skiplist
		}
	}
	/**
	 * returns the level for new skiplist node
	 * @param   maxLevel
	 * @return level
	 */
	private int choice(int maxLevel) {
		// TODO Auto-generated method stub
		int level=0;
		Random rand=new Random();
		while(level<maxLevel){
			int b=rand.nextInt(2);
			if(b==0){
				break;
			}else{
				level++;
			}
		}
		return level;
	}
	/**
	 * returns the string representation of Skiplist by iterating over it
	 * @param
	 * @return String
	 */
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		SkipNode<T> current = head.getNext()[0];
		while (current != null) {
			result.append(current.getData() + " ");
			current = current.getNext()[0];
		}
		return result.toString();
	}
	// --------------------Override methods----------------------------------
	
	/**
	 * Add an element x to the list
	 * @param x
	 * @return 
	 */
	@Override
	public void add(T x) {
		FindResult<T> result=find(x);
		if(result.getNode()!=null){
			try {
				throw new AlreadyExistsException("Element Already Exists");
			} catch (AlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}else{
			int maxLevel = head.level();
			int level = choice(maxLevel);
			SkipNode<T> n = new SkipNode<T>(x, level);
			for(int i=0; i<=level;i++){
				n.getNext()[i]=result.getPrev()[i].getNext()[i];
				result.getPrev()[i].getNext()[i]=n;
			}
			size++;
		}
	}
	
	/**
	 * returns Least element that is >= x, or null if no such element
	 * @param x
	 * @return element >= x 
	 */
	@Override
	public T ceiling(T x) {
		if(isEmpty()){
			return null;
		}else{
			FindResult<T> result=find(x);
			if(result.getNode()==null ){
				if(result.getPrev()[0].getNext()[0]!= null ){
					return result.getPrev()[0].getNext()[0].getData();
				}
			}else{
				return result.getNode().getData();
			}
			return null;
		}
	}
	
	/**
	 * returns true if x is in the list
	 * @param x
	 * @return true/false
	 */
	@Override
	public boolean contains(T x) {
		FindResult<T> result=find(x);
		if(result.getNode()==null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Return the element at index n in the list
	 * @param n
	 * @return element
	 */
	@Override
	public T findIndex(int n) {
		ListIterator<T> itr = (ListIterator<T>) iterator();
		T data=null;
		if(isEmpty()){
			return null;
		}else{
			if(size()>=n){
				for(int i=0; i<=n; i++){
					if(itr.hasNext()){
						data = itr.next();
					}else{
						break;
					}
				}
			}
		}
		return data;
	}
	
	/**
	 * Return the first element of the list
	 * @param
	 * @return element
	 */
	@Override
	public T first() {
		if(isEmpty()){
			return null;
		}else{
			SkipNode<T> p = head;
			if(p.getNext()!=null){
				return p.getNext()[0].getData();
			}
			return null;
		}
	}
	
	/**
	 * Greatest element that is <= x, or null if no such element
	 * @param x
	 * @return element
	 */
	@Override
	public T floor(T x) {
		if(isEmpty()){
			return null;
		}else{
			FindResult<T> result=find(x); // calls the find() method with x as parameter
			if(result.getNode()==null){
				return result.getPrev()[0].getData();
			}else{
				if(result.getPrev()!=null){
					return result.getNode().getData();
				}
				return null;
			}
		}
	}
	
	/**
	 * returns true if list empty?
	 * @param
	 * @return true/false 
	 */
	@Override
	public boolean isEmpty() {
		if(size==0){
			return true; // if no elements are present in the skip list
		}else{
			return false; // if atleast 1 element is present in skip list
		}
	}
	
	/**
	 * returns an iterator to iterate on skip list
	 * @param
	 * @return iterator 
	 */
	@Override
	public Iterator<T> iterator() {
		ListIterator<T> listIterator = new ListIterator<T>(head);
		return listIterator;
	}
	
	/**
	 * Return the last element of the list
	 * @param
	 * @return element
	 */
	@Override
	public T last() {
		if(isEmpty()){
			return null;
		}else{
			SkipNode<T> p = head; // p points to the head of skip list
			for (int level = head.level(); level >= 0; level--) {
				while (p.getNext()[level] != null && p.getNext()[level].getNext()[0]!= null) {
					p = p.getNext()[level];
				}
			}
			return p.getNext()[0].getData();
		}
	}
	
	/**
	 * Rebuilds this list into a perfect skip list
	 * @param
	 * @return
	 */
	@Override
	public void rebuild() {
		int size = size();
		int height = (int) (Math.log(size) / Math.log(2));
		SkipNode<T> p = head;
		SkipNode<T> [] node;
		for(int i=1;i<=height;i++){
			p=head;
			while(p.getNext()!=null && p.getNext()[0]!=null & p.getNext()[i-1]!=null){
				node=p.getNext()[i-1].getNext();
				if(node[0]==null){
					p.resizeIndex(i, p.getNext()[i-1]);
				}else{
					p.resizeIndex(i, node[i-1]);
				}
				p=p.getNext()[i];
			}
		}

	}
	
	/**
	 * Removes x from this list and returns false if x is not in list
	 * @param x
	 * @return true/false
	 */
	@Override
	public boolean remove(T x) {

		if(isEmpty()){
			System.out.println("List is Empty"); // Remove this before submission
		}else{
			FindResult<T> result=find(x); // calls the find() method with x as parameter
			if(result.getNode()==null){
				return false;
			}else{
				size--;
				for(int i=0; i<=MAXLEVEL;i++){
					if(result.getPrev()[i].getNext()[i]!= null){
						if(result.getNode().getData().equals(result.getPrev()[i].getNext()[i].getData())){
							result.getPrev()[i].getNext()[i]=result.getNode().getNext()[i];
						}else{
							break;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns number of elements in the list
	 * @param 
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}
	// --------------------Override methods----------------------------------
}