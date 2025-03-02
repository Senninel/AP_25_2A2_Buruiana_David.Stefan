import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        int n, k;
        int rdm = 0;
        Scanner sc = new Scanner(System.in);

        n = Integer.valueOf(args[0]);
        k = Integer.valueOf(args[1]);

        int[][] a = new int[n+1][n+1];
        if (2 * k > n) {
            for (int i = 1; i <= n; i++)
                for (int j = i + 1; j <= n; j++) {
                    rdm = (int) (Math.random() * 2);
                    a[i][j] = a[j][i] = rdm;
                }
        } else
        {
            Set<Integer> clique = new HashSet<Integer>();
            Set<Integer> stable = new HashSet<Integer>();
            while(clique.size() < k)
            {
                rdm = (int) (Math.random() * n) + 1;
                clique.add(rdm);
            }

            while(stable.size() < k) {
                rdm = (int) (Math.random() * n) + 1;
                if (!clique.contains(rdm))
                    stable.add(rdm);
            }

            Integer[] cliqueArr = clique.toArray(new Integer[clique.size()]);
            for(int i = 0; i < k; i++)
                for(int j = i + 1; j < k; j++)
                    a[cliqueArr[i]][cliqueArr[j]] = a[cliqueArr[j]][cliqueArr[i]] = 1;

            for(int i = 1; i <= n; i++)
                for(int j = i + 1; j <= n; j++)
                    if(!stable.contains(i) && !stable.contains(j))
                    {
                        rdm = (int) (Math.random() * 2);
                        a[i][j] = a[j][i] = rdm;
                    }
        }
        String[][] Matrix= new String[n+1][n+1];
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++) {
                if (a[i][j] == 1) Matrix[i][j] = "1";
                else Matrix[i][j] = "0";
            }
       System.out.printf("Matricea de adiacenta: \n");
      for(int i=1;i <= n; i++) {
          for (int j = 1; j <= n; j++)
              System.out.printf("%s ", Matrix[i][j]);
          System.out.printf("\n");
      }
      int m = 0;
      for(int i=1; i <= n; i++)
          for(int j = i + 1; j <= n; j++)
              if(a[i][j] == 1) m++;
      System.out.printf("Numar de muchii egal cu : %d", m);
      System.out.printf("\n");

      int maxDeg = 0;
      int minDeg = n;
      int totalDeg = 0;
      for(int i = 1; i <= n; i++)
      {
          int deg = 0;
          for(int j = 1; j <= n; j++)
              if(a[i][j] == 1) deg++;
          totalDeg += deg;
          if(deg > maxDeg) maxDeg = deg;
          if(deg < minDeg) minDeg = deg;
      }
      System.out.println("Δ(G) este: " + maxDeg + " si  δ(G) este: " + minDeg + "\n");
      if(totalDeg == 2 * m) System.out.println("2 * m este egal cu suma gradelor");
      else System.out.println("2 * m nu este egal cu suma gradelor");
    }

}
