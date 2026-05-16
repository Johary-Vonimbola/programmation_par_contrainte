package bioinformatics.lot2;

public class AlignmentChecker {
    
    
    /**
     * @implNote dynamic problem standard problem using 2d dp array
     * @param left
     * @param right
     * @return the longest common sub-sequence between the two strings with the score (the sub-seq length)
     */
    public static Overlapping lsbs(String left, String right){
        Overlapping overlapping = new Overlapping(left, right);
        int m=left.length(), n=right.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(left.charAt(i-1) == right.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (left.charAt(i - 1) == right.charAt(j - 1)) {
                overlapping.addPosition(i - 1, j - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        overlapping.setScore(dp[m][n]);
        return overlapping;
    }

}
