import java.util.HashSet;
import java.util.Set;

public class LevenshteinSolve {
    
    public static Set<String> generateDelete(String word){
        Set<String> res = new HashSet<>();
        for(int i=0; i<word.length(); i++){
            String newWord = word.substring(0, i) + word.substring(i+1);
            res.add(newWord);
        }
        return res;
    }

    public static Set<String> generateInsertion(String word, String alphabet){
        Set<String> res = new HashSet<>();
        for(int i=0; i<=word.length(); i++){
            for(int j=0; j<alphabet.length(); j++){
                String newWord = word.substring(0, i) + alphabet.charAt(j) + word.substring(i);
                res.add(newWord);
            }
        }
        return res;
    }

    public static Set<String> generateReplace(String word, String alphabet){
        Set<String> res = new HashSet<>();
        for(int i=0; i<word.length(); i++){
            for(int j=0; j<alphabet.length(); j++){
                String newWord = word.substring(0, i) + alphabet.charAt(j) + word.substring(i+1);
                res.add(newWord);
            }
        }
        return res;
    }

    public static void generateWordsLevenshtein(int k, int step, Set<String> current, Set<String> results, String alphabet){
        if(step == k) return;

        Set<String> subResults = new HashSet<>();
        for(String word : current){
            subResults.addAll(generateDelete(word));
            subResults.addAll(generateInsertion(word, alphabet));
            subResults.addAll(generateReplace(word, alphabet));
        }
        results.addAll(subResults);
        
        generateWordsLevenshtein(k, step+1, subResults, results, alphabet);
    }


    public static void main(String[] args){

        String alphabet = "abdefghijklmnoprstvyz";
        String word = "vato";

        Set<String> results = new HashSet<>();
        
        Set<String> current = new HashSet<>();
        current.add(word);

        int k=2;

        generateWordsLevenshtein(k, 0, current, results, alphabet);

        for(String res : results){
            System.out.println(res);
        }

        System.out.println("Total "+results.size());
    }

}
