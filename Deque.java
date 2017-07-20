import java.lang.IllegalArgumentException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int count;
	
	private class Node{
		Item item;
		Node next;
		Node prev;
	}
	
	private boolean validItem(Item item){
		if(item == null){
			throw new IllegalArgumentException("Ilegal argument!");
		}
		return true;
	}
	
	public Deque(){
		this.first = null;
		this.last = null;
		this.count = 0;
	}

	public boolean isEmpty(){
		boolean ret_val = false;
		if(this.size() == 0){
			ret_val = true;
		}
		return ret_val;
	}
	
	public int size(){
	    return this.count;	
	}
	
	public void addFirst(Item item){
		if(validItem(item)){
			//Case only one or zeros nodes in queue
			if(this.size() == 0){
				this.first = new Node();
				this.first.item = item;
				this.last = this.first ;
			}
			
			else{
				Node temp = this.first;
			    this.first = new Node();
			    this.first.item = item;
			    this.first.next = temp;
			    temp.prev = this.first;
			    while(temp.next != null){
				    temp = temp.next;
			    }
			    this.last = temp;
			}
			
		    this.count += 1;
		}
	}
	
	public void addLast(Item item){
		if(validItem(item)){
			if(this.size() == 0){
				this.last = new Node();
				this.last.item = item;
				this.first = this.last;
			}
			
			else{
			    Node temp = this.last;
			    this.last = new Node();
			    this.last.item = item;
			    temp.next = this.last;
			    this.last.prev = temp;
			}
			
			this.count += 1;
		}
	}
	
	public Item removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Item ret;
		ret = this.first.item;

		if(this.first.next != null){
			this.first = this.first.next;
		}
		this.first.prev = null;
		
		this.count -= 1;
		
		//if(this.size() == 0){
		//	this.first = null;
		//	this.last = null;
		//}

		return ret;
	}
	
	public Item removeLast(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Item ret;
		ret = this.last.item;
		if(this.last.prev != null){
		    this.last = this.last.prev;
		}
		this.last.next = null;
		this.count -= 1;
		return ret;
	}
	
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		
		private Node current = first;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.current != null;
		}

		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deque<Integer> test = new Deque<Integer>();
		
		test.addLast(1);
		test.addLast(2);
		test.addLast(3);
		test.addLast(4);
		
	    for(Integer i : test)
	    	System.out.println(i);
		
		System.out.println("Size: " + test.size());
		System.out.println("Remove: " + test.removeLast());
		System.out.println("Size: " + test.size());
		System.out.println("Remove: " + test.removeFirst());
		System.out.println("Size: " + test.size());
		System.out.println("Remove: " + test.removeLast());
		System.out.println("Size: " + test.size());
		System.out.println("Remove: " + test.removeFirst());
		System.out.println("Size: " + test.size());
	}
}
