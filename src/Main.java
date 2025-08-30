import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Lütfen sözlük dosyasını parametre olarak giriniz. Örn: java MyTree sozluk.txt");
			return;
		}
		
		String fileName = args[0];
		Trie trie = new Trie();
		
		System.out.println("Sözlük Yükleniyor. Lütfen Bekleyin...");
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				trie.insert(line.trim().toUpperCase()); // büyük harfe çeviriyoruz
			}
		} catch (IOException e) {
			System.out.println("Dosya okunamadı: " + e.getMessage());
			return;
		}
		
		System.out.println("Sözlük Yüklendi.");
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("Bir Kelime Yazıp Enter Tuşuna Basınız (çıkış için EXIT yazın):");
			String input = scanner.nextLine().toUpperCase();
			
			if (input.equals("EXIT")) break;
			
			List<String> matches = trie.getWordsWithPrefix(input);
			
			if (matches.isEmpty()) {
				System.out.println("Hiç eşleşme bulunamadı.");
			} else {
				System.out.println("Olası Kelimeler:");
				for (String word : matches) {
					System.out.println(word);
				}
			}
		}
	}
}