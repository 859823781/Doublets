import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Links implements LinksInterface {
	HashMap<String, HashSet<String>> set;
	HashMap<Integer, HashSet<String>> lengths; // classified by length

	public Links(String string) {
		File file = new File(string);
		this.lengths = new HashMap<Integer, HashSet<String>>();
		Scanner scan;
		this.set = new HashMap<String, HashSet<String>>();
		try {
			scan = new Scanner(file);
			while(scan.hasNext()){
				String current = scan.nextLine();
				int currentLength = current.length();
				if(this.lengths.containsKey(currentLength)){
					this.lengths.get(currentLength).add(current);
				}else{
					HashSet<String> lengthSet = new HashSet<String>();
					lengthSet.add(current);
					this.lengths.put(currentLength, lengthSet);
				}
			}
			for(int key:lengths.keySet()){
				for(String currentString:this.lengths.get(key)){
					HashSet<String> currentSet = new HashSet<String>();
					//this.lengths.get(key).remove(currentString); // for double side adding(Now I only add to set once)
					for(String toCompare:this.lengths.get(key)){
						if(!currentString.equals(toCompare)&&this.isCandidate(currentString, toCompare)){
							currentSet.add(toCompare);
						}
					}if (!currentSet.isEmpty()) {
						this.set.put(currentString, currentSet);
					}
					
//					HashSet<String> currentSet = new HashSet<String>();
//					this.lengths.get(key).remove(currentString); // for double side adding
//					for(String toCompare:this.lengths.get(key)){
//						if(this.isCandidate(currentString, toCompare)){
//							currentSet.add(toCompare);
//							if(this.set.containsKey(toCompare)){
//								this.set.get(toCompare).add(currentString);
//							}else{
//							HashSet<String> secondSet = new HashSet<String>();
//							secondSet.add(currentString);
//							this.set.put(toCompare, secondSet);
//							}
//						}
//					}if (!currentSet.isEmpty()) {
//						this.set.put(currentString, currentSet);
//					}
				}
				}
			}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Set<String> getCandidates(String string) {
		if (this.exists(string)) {
			return this.set.get(string);
		}
		return null;
	}

	@Override
	public boolean exists(String word) {
		return this.set.containsKey(word);
	}
	
	public boolean isCandidate(String current,String toCompare){
		int diff = 0;
		for (int i = 0; i < current.length(); i++) {
			if (current.charAt(i) != toCompare.charAt(i)) {
				diff++;
			}
			if (diff > 1) {
				break;
			}
		}
		if (diff == 1) {
			return true;
		}
		return false;
	}
}
