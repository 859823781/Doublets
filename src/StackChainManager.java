import java.util.Stack;

public class StackChainManager extends ChainManager {
	private int size = 0;
	private Stack<Chain> stackOfChains = new Stack<Chain>();

	@Override
	public void add(Chain chain) {
		stackOfChains.push(chain);
		size++;
		this.updateMax(size);
	}

	@Override
	public Chain next() {
		size--;
		this.incrementNumNexts();
		return stackOfChains.pop();
	}

	@Override
	public boolean isEmpty() {
		return stackOfChains.isEmpty();
	}

}
