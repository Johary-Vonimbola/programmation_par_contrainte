public class MasterMind {

    public static String askUser(String goal, String comb){
        StringBuilder builder = new StringBuilder();
        System.out.println("Verifying : "+goal+" and "+comb);
        for(int i=0; i<goal.length(); i++){
            builder.append(goal.charAt(i) == comb.charAt(i) ? '1' : '0');
        }
        return builder.toString();
    }

    public static int ia(String goal, String S){
        return solve(goal, S, "", 0);
    }

    public static int solve(String goal, String S, String comb, int attempt){
        int n = goal.length();
        int m = comb.length();
        int k = S.length();
        if(n == 0) return attempt;
        if(m == n){
            String userFeedback = askUser(goal, comb);
            StringBuilder newGoal = new StringBuilder();
            int prune = 0;
            for(int i=0; i<userFeedback.length(); i++){
                prune += Integer.parseInt(userFeedback.charAt(i)+"");
                if(userFeedback.charAt(i) == '0'){
                    newGoal.append(goal.charAt(i));
                }
            }
            if(prune == 0) return Integer.MAX_VALUE;
            if(prune == userFeedback.length()-1) return attempt;
            return solve(newGoal.toString(), S, "", attempt+1);
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<k; i++){
            int otherResult = solve(goal, S, comb+S.charAt(i), attempt);
            System.out.println("Comparing "+min+" and "+otherResult);
            if(min > otherResult){
                min = otherResult;
                System.out.println("True min is "+min);
            }
        }
        return min;
    }

    public static void main(String[] args){

        System.out.println(ia("rrgrg", "rgb"));

    }
    
}
