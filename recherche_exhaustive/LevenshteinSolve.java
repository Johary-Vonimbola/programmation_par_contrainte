import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevenshteinSolve {

    private static class State{
        public String word;
        public List<String> histo;

        public State(String word){
            this.word = word;
            this.histo = new ArrayList<>();
        }

        public int length(){
            return this.word.length();
        }
    }

    public static boolean respectConstraints(String word, int lMin, int lMax){
        int m = word.length();
        return m >= lMin && m <= lMax;
    }

    public static Set<State> generateDelete(State word, int lMin, int lMax){
        Set<State> res = new HashSet<>();
        for(int i=0; i<word.length(); i++){
            String newWord = word.word.substring(0, i) + word.word.substring(i+1);
            if(respectConstraints(newWord, lMin, lMax)) {
                State st = new State(newWord);
                st.histo = new ArrayList<>(word.histo);
                st.histo.add("Del : "+word.word.substring(0, i)+" ["+word.word.charAt(i)+"] "+word.word.substring(i+1));
                res.add(st);
            }
        }
        return res;
    }

    public static Set<State> generateInsertion(State word, String alphabet, int lMin, int lMax){
        Set<State> res = new HashSet<>();
        for(int i=0; i<=word.length(); i++){
            for(int j=0; j<alphabet.length(); j++){
                String newWord = word.word.substring(0, i) + alphabet.charAt(j) + word.word.substring(i);
                if(respectConstraints(newWord, lMin, lMax)) {
                    State st = new State(newWord);
                    st.histo = new ArrayList<>(word.histo);
                    st.histo.add("Ins : "+word.word.substring(0, i)+" ["+alphabet.charAt(j)+"] "+word.word.substring(i));
                    res.add(st);
                }
            }
        }
        return res;
    }

    public static Set<State> generateReplace(State word, String alphabet, int lMin, int lMax){
        Set<State> res = new HashSet<>();
        for(int i=0; i<word.length(); i++){
            for(int j=0; j<alphabet.length(); j++){
                String newWord = word.word.substring(0, i) + alphabet.charAt(j) + word.word.substring(i+1);
                if(respectConstraints(newWord, lMin, lMax)) {
                    State st = new State(newWord);
                    st.histo = new ArrayList<>(word.histo);
                    st.histo.add("Repl : "+word.word.substring(0, i)+"["+word.word.charAt(i)+" -> "+alphabet.charAt(j)+"]"+word.word.substring(i+1));
                    res.add(st);
                }
            }
        }
        return res;
    }

    public static void generateWordsLevenshtein(int k, int step, Set<State> current, Set<State> results, String alphabet, int lMin, int lMax){
        if(step == k) return;

        Set<State> subResults = new HashSet<>();
        for(State word : current){
            subResults.addAll(generateDelete(word, lMin, lMax));
            subResults.addAll(generateInsertion(word, alphabet, lMin, lMax));
            subResults.addAll(generateReplace(word, alphabet, lMin, lMax));
        }
        results.addAll(subResults);
        
        generateWordsLevenshtein(k, step+1, subResults, results, alphabet, lMin, lMax);
    }


    public static void main(String[] args){

        String alphabet = "abdefghijklmnoprstvyz";
        String word = "vato";

        Set<State> results = new HashSet<>();
        
        Set<State> current = new HashSet<>();
        current.add(new State(word));

        int k=2;
        int lMin = 2, lMax = 10;

        generateWordsLevenshtein(k, 0, current, results, alphabet, lMin, lMax);

        for(State res : results){
            System.out.println("--------------------");
            System.out.println(res.word);
            System.out.println(res.histo);
            System.out.println("--------------------");
        }

        System.out.println("Total "+results.size());
    }

}
