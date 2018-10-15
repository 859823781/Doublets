import java.util.PriorityQueue;

public class PriorityQueueChainManager extends ChainManager {
	PriorityQueue<Entry> myqueue;
	int size = 0;
	String targetWord;
	
	public PriorityQueueChainManager(String end){
		this.targetWord = end;
		this.myqueue = new PriorityQueue<Entry>();
	}
	
	public int diffOfLetters(String current){
		int diff = 0;
		for(int i =0;i<targetWord.length();i++){
			if(targetWord.charAt(i)!=current.charAt(i)){
				diff++;
			}
		}
		return diff;
	}
	public class Entry implements Comparable<Entry>{
		private int length;
		private int estimation;
		private Chain chain;
		
		public Entry(Chain currentChain){
			this.estimation = diffOfLetters(currentChain.getLast());
			this.length = currentChain.length();
			this.chain = currentChain;
		}
		
		public Chain getChain(){
			return this.chain;
		}
		
		@Override
		public int compareTo(Entry other) {
			
			return this.estimation+this.length-other.length-other.estimation;
		}
		
	}
	
	@Override
	public void add(Chain chain) {
		Entry current = new Entry(chain); 
		myqueue.add(current);
		size++;
		this.updateMax(size);
	}

	@Override
	public Chain next() {
		size--;
		this.incrementNumNexts();
		return myqueue.remove().getChain();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
