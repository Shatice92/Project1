import java.util.*;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	// Sözlükten Trie'ye kelime ekleme
	public void insert(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			current.children.putIfAbsent(ch, new TrieNode());
			current = current.children.get(ch);
		}
		current.isEndOfWord = true;
	}
	
	// Verilen prefix ile başlayan tüm kelimeleri bul
	public List<String> getWordsWithPrefix(String prefix) {
		List<String> result = new ArrayList<>();
		TrieNode current = root;
		
		for (char ch : prefix.toCharArray()) {
			if (!current.children.containsKey(ch)) {
				return result; // prefix yoksa boş liste dön
			}
			current = current.children.get(ch);
		}
		
		// bulunduğumuz noktadan itibaren tüm kelimeleri bul
		collectAllWords(current, prefix, result);
		
		return result;
	}
	
	private void collectAllWords(TrieNode node, String word, List<String> result) {
		if (node.isEndOfWord) {
			result.add(word);
		}
		// children.entrySet() kullanarak hem karakteri hem de düğümü alıyoruz.
		for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			collectAllWords(entry.getValue(), word + entry.getKey(), result);
		}
	}
}