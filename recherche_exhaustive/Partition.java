import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Partition {
    
    public static void partition(int target, List<Integer> current, Set<List<Integer>> result){
        if(target == 0){
            Collections.sort(current);
            result.add(new ArrayList<>(current));
            return;
        }
        if(target < 0) return;
        for(int n=1; n<=target; n++){
            int remaining = target - n;
            current.add(n);
            partition(remaining, current, result);
            current.remove(current.size() - 1);
        }
    }
    public static void partitionBest(int target, List<Integer> current, Set<List<Integer>> result, Map<Integer, Set<List<Integer>>> memo){
        if(target == 0){
            List<Integer> sub = new ArrayList<>(current);
            Collections.sort(sub);
            result.add(sub);
            return;
        }
        if(target < 0) return;
        if (memo.containsKey(target)) {
            result.addAll(memo.get(target));
            return;
        }
        Set<List<Integer>> found = new HashSet<>();
        for(int n=1; n<=target; n++){
            int remaining = target - n;
            current.add(n);
            partitionBest(remaining, current, found, memo);
            current.remove(current.size() - 1);
        }
        memo.put(target, found);
        result.addAll(found);
    }

    public static void main(String[] args) {
        Set<List<Integer>> results = new HashSet<>();
        Map<Integer, Set<List<Integer>>> memo = new HashMap<>();
        int target = 50;
        partitionBest(target, new ArrayList<>(), results, memo);
        for(List<Integer> sol : results){
            System.out.println(sol);
        }
    }

}
