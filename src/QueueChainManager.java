import java.util.LinkedList;

public class QueueChainManager extends ChainManager {

	private int size = 0;
	private LinkedList<Chain> listOfChains = new LinkedList<Chain>();

	@Override
	public void add(Chain chain) {
		listOfChains.add(chain);
		size++;
		this.updateMax(size);
	}

	@Override
	public Chain next() {
		size--;
		this.incrementNumNexts();
		return listOfChains.remove();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
