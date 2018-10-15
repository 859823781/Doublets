import java.util.Iterator;
import java.util.LinkedHashSet;

public class Chain implements Iterable<String> {
	//HashSet<String> chainSet;
	//LinkedList<String> chain;
	LinkedHashSet<String> chain;
	String last;
	
	public Chain() {
		//this.chainSet = new HashSet<String>();
		//this.chain = new LinkedList<String>();
		this.chain = new LinkedHashSet<String>();
	}
	
	@SuppressWarnings("unchecked")
	public Chain(LinkedHashSet<String> oldSet, String addOn) {
		this.chain = new LinkedHashSet<String>();
		this.chain.addAll(oldSet);
		this.chain.add(addOn);
		this.last = addOn;
		
	}
	
	public Chain addLast(String string) {
		return new Chain(this.chain, string);
	}

	public String getLast() {
		return this.last;
	}

	public boolean contains(String string) {
		return chain.contains(string);
	}

	public int length() {
		return chain.size();
	}

	@Override
	public Iterator<String> iterator() {
		return this.chain.iterator();
	}

}
