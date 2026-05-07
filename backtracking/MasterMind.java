import java.util.ArrayList;
import java.util.List;

public class MasterMind {

    public static List<String> generateAll(int number, String S){
        List<String> result = new ArrayList<>();
        if(number == 0) {
            result.add("");
            return result;
        }
        List<String> comb = generateAll(number-1, S);
        for(int i=0; i<S.length(); i++){
            for(String el : comb){
                result.add(el.concat(S.charAt(i)+""));
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args){

        System.out.println(generateAll(4, "abcd"));

    }
    
}
