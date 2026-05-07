import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static int[] getSimilarity(String guess, String otherPos){
        int n = guess.length();
        boolean[] guessBool = new boolean[n];
        boolean[] otherBool = new boolean[n];
        int correct = 0;
        int wrongPosition = 0;
        for(int i=0; i<n; i++){
            boolean cond = guess.charAt(i) == otherPos.charAt(i);
            guessBool[i]=cond;
            otherBool[i]=cond;
            correct += cond?1:0;
        }

        for(int i=0; i<n; i++){
            if(!otherBool[i]){
                for(int j=0; j<n; j++){
                    if(!guessBool[j] && guess.charAt(j) == otherPos.charAt(i)){
                        guessBool[j]=true;
                        wrongPosition++;
                        break;
                    }
                }
            }
        }
        return new int[]{correct, wrongPosition};
    }

    public static int ia(int number, String S){
        List<String> combinations = generateAll(number, S);
        List<String> queue = List.copyOf(combinations);
        Scanner sc = new Scanner(System.in);
        int attempt = 0;
        boolean found = false;
        while(!queue.isEmpty()){
            String guess = queue.get(0);
            System.out.println("Is the secret combination "+guess+" ?");
            System.out.print("Number of color at correct position: ");
            int correct = sc.nextInt();
            System.out.print("Number of color at the wrong position: ");
            int wrongPosition = sc.nextInt();
            if(correct == number){
                System.out.println("The secret combination was found "+guess+" with "+attempt+" attempt(s)");
                found = true;
                break;
            }
            List<String> nextQueue = new ArrayList<>();
            for(String el : queue){
                int[] data = getSimilarity(guess, el);
                if(data[0] == correct && data[1] == wrongPosition){
                    nextQueue.add(el);
                }
            }
            queue = nextQueue;
            System.out.println("The number of candidates is now "+queue.size());
            ++attempt;
        }
        if(!found){
            System.out.println("The secret cmobination cannot be found");
            return -1;
        }

        return attempt;
    }

    public static void main(String[] args){

        ia(3, "rgb");

    }
    
}
