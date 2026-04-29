import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Levenshtein {

    public static int levenshtein(String a, String b){
        int ans = 0;
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<m; i++) dp[i][0]=i;
        for(int j=0; j<n; j++) dp[0][j]=j;
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                char ac = a.charAt(i-1);
                char bc = b.charAt(j-1);
                int dpVal = -1;
                if(ac == bc){
                    dpVal = dp[i-1][j-1];
                }else{
                    dpVal = Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j])) + 1;
                }
                dp[i][j] = dpVal;
            }
        }
        ans = dp[m-1][n-1];
        return ans;
    }

    public static int levenshteinNaif(String a, String b){
        if(a.isEmpty()) return b.length();
        if(b.isEmpty()) return a.length();

        int sup = levenshteinNaif(a.substring(1), b)+1;
        int ins = levenshteinNaif(a, b.substring(1))+1;

        int cost = 1;
        if((a.charAt(0) == b.charAt(0))) cost = 0;
        int rep = levenshteinNaif(a.substring(1), b.substring(1))+cost;
        
        return Math.min(sup, Math.min(ins, rep));
    }

    public static int levenshteinBest(String a, String b, Map<String, Integer> memo){
        String key = a+","+b;
        if(memo.containsKey(key)) return memo.get(key);
        if(a.isEmpty()) return b.length();
        if(b.isEmpty()) return a.length();

        int sup = levenshteinBest(a.substring(1), b, memo)+1;
        int ins = levenshteinBest(a, b.substring(1), memo)+1;

        int cost = 1;
        if((a.charAt(0) == b.charAt(0))) cost = 0;
        int rep = levenshteinBest(a.substring(1), b.substring(1), memo)+cost;
        
        memo.put(key, Math.min(sup, Math.min(ins, rep)));
        return memo.get(key);
    }

    public static int[] computeCurrentRow(String refWord, char current, int[] previouRow){
        int[] currentRow = new int[previouRow.length];
        currentRow[0] = previouRow[0] + 1;
        for(int i=1; i<currentRow.length; i++){
            if(current == refWord.charAt(i-1)) currentRow[i]=previouRow[i-1];
            else currentRow[i] = Math.min(currentRow[i-1], Math.min(previouRow[i-1], previouRow[i]))+1;
        }
        return currentRow;
    }

    public static int getMin(int[] arr){
        int min = arr[0];
        for(int i=0; i<arr.length; i++) min = Math.min(min, arr[i]);
        return min;
    }

    public static void generateWords(String alphabet, String current, int[] previous, String m, int k, Set<String> results){
        if(current.length() > m.length() + k) return;
        if(previous[m.length()] <= k) results.add(current); 
        if(getMin(previous) > k) return;

        for(char a : alphabet.toCharArray()){
            int[] currentRow = computeCurrentRow(m, a, previous);
            generateWords(alphabet, current+a, currentRow, m, k, results);
        }
    }

    public static Set<String> solve(String alphabet, String m, int k){
        int[] currentRow = new int[m.length()+1];
        for(int i=0; i<currentRow.length; i++) currentRow[i]=i;

        Set<String> results = new HashSet<>();
        generateWords(alphabet, "", currentRow, m, k, results);
        return results;
    } 

    public static void main(String[] args){
        String m = "kanto";
        int k = 2;
        String alphabet = "abdefghijklmnoprstvyz";
        Set<String> ans = solve(alphabet, m, k);
        for(String sol : ans){
            System.out.println(sol);
        }
        System.out.println("Total "+ans.size());
        // String a = "dhjssjkvdb";
        // String b = "aslkvaasjcgsidvnsjkdnvijsndvisjdnvslvasjkvnkds";
        // Map<String, Integer> memo = new HashMap<>();
        // System.out.println(levenshteinBest(a, b, memo));
    }

}
