import java.util.Scanner;
import java.util.Set;

/**
 * @author Blake Mullalley
 */
public class Doublets {
	private LinksInterface links;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		System.out.println("Please wait while game initializes.");
		Links links = new Links("../DoubletsData/english.cleaned.all.35.txt");
		Doublets doub = new Doublets(links);
		System.out.println("Your playing Dublets");
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Enter starting word: ");
			String start = scan.next();
			System.out.println("Enter ending word: ");
			String end = scan.next();
			System.out.println("Enter chain manager (s:stack, q: queue, p: priorityqueue, e:exit): ");
			String manager = scan.next();
			if(manager.equals("e")) {
				break;
			}
			if(start.length()!=end.length()) {
				System.out.println("The starting and ending words must have the same length!");
			} else {
				ChainManager chainMan;
				if(manager.equals("s")) {
					chainMan = new StackChainManager();
				} else if(manager.equals("q")) {
					chainMan = new QueueChainManager();
				}else if(manager.equals("p")){
					chainMan = new PriorityQueueChainManager(end);
				} else {
					System.out.println("The chain manager must be one of the letters listed.");
					continue;
				}
				Chain foundChain = doub.findChain(start, end, chainMan);
				System.out.println("Chain: " + foundChain);
				System.out.println("Length: " + foundChain.length());
				System.out.println("Candidates: " + chainMan.getNumberOfNexts());
				System.out.println("Max size: " + chainMan.maxSize());
			}
			
		}
		System.out.println("Thanks for Playing");
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() != end.length()) {
			return null;
		}
		Chain chain = new Chain();
		manager.add(chain.addLast(start));
		while (!manager.isEmpty()) {
			chain = manager.next();
			if (chain.getLast().equals(end)) {  //check if is a solution
				return chain;
			}
			Set<String> nextOptions = this.links.getCandidates(chain.getLast()); //search for candidates of the current word
			if (nextOptions != null) {
				for (String val : nextOptions) {
					if(!chain.contains(val)){
						manager.add(chain.addLast(val));
					}
				}
			}
		}
		return null;
	}

}
