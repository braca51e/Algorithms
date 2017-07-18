import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int count;
	
	private class Node{
		Item item;
		Node next;
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
	}

	public boolean isEmpty(){
		boolean ret_val = false;
		if(this.first == null && this.last == null){
			ret_val = true;
		}
		return ret_val;
		
	}
	
	public int size(){
		int ret = 0;
		if(!isEmpty()){
			ret = this.count;
		}
	    return ret;	
	}
	
	public void addFirst(Item item){
		if(validItem(item)){
		    Node temp = this.first;
		    this.first = new Node();
		    this.first.item = item;
		    this.first.next = temp;
		}
	}
	
	public void addLast(Item item){
		
	}
	
	public Item removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Item ret;
		ret = this.first.item;
		this.first = this.first.next;
		return ret;
	}
	
	public Item removeLast(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		
		Item ret;
		ret = this.last.item;
		this.last = this.last.next;
		return ret;
		return ret;
		
	}
	
	public Iterator<Item> iterator(){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
