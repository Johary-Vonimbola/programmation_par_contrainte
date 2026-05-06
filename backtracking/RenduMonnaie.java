import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenduMonnaie{

    public static Map<Integer, Integer> rendreMonnaie(int N, int[] S){
        List<Integer> minimum = new ArrayList<>();
        Map<Integer, List<Integer>> memo = new HashMap<>();
        minimum = solve(N, S, memo);
        Map<Integer, Integer> result = new HashMap<>();
        for(Integer a : minimum){
            result.putIfAbsent(a, 0);
            result.put(a, result.get(a)+1);
        }
        return result;
    }

    public static List<Integer> solve(int N, int[] S, Map<Integer, List<Integer>> memo){
        if(memo.containsKey(N)) return memo.get(N);
        if(N == 0) return new ArrayList<>();
        if(N < 0) return null;
        List<Integer> tempMin = null;
        for(Integer a : S){
            List<Integer> otherResult = solve(N-a, S, memo);
            if(otherResult != null){
                otherResult.add(a);
                if(tempMin == null || tempMin.size() > otherResult.size()){
                    tempMin = otherResult;
                }
            }
        }
        memo.put(N, tempMin);
        return new ArrayList<>(tempMin);
    }

    public static void main(String[] args){
        Map<Integer, Integer> result = rendreMonnaie(420, new int[]{1,2,5});
        System.out.println(result);
    }

}