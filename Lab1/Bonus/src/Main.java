import java.util.Scanner;

public class Main {
    static int st[]= new int[105];

    public static void main(String[] args) {
            int n = (int) (Math.random() * 15);
            System.out.println("n = " + n + "\n");
            int[][] a = new int[n+1][n+1];
            int rdm = 0;
            for(int i = 1; i <= n; i++)
                for(int j = i + 1; j<= n; j++) {
                rdm = (int) (Math.random() * 2);
                a[i][j] = a[j][i] = rdm;
                }
            printMatrix(a,n);
            int k;
            Scanner sc = new Scanner(System.in);
            k = sc.nextInt();
            int clica_a = Clique(a,n,k);
            if(clica_a == 0) System.out.println("Nu exista clica de lungimea minim k");
            else System.out.println("Exista o clica de lungime minim k");
            int[][] b = new int[n+1][n+1];
            for(int i=1; i <=n ; i++)
                for(int j=1 ; j <= n; j++)
                    b[i][j] = 1 - a[i][j];
            int clica_b = Clique(b,n,k);
            if(clica_b == 0) System.out.println("Nu avem un set stabil pentru graful a de lungime minim k");
            else System.out.println("Avem un set stabil pentru graful a de lungime minim k");
        }
    public static void printMatrix(int[][] a, int n)
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(a[i][j] + " ");
            System.out.printf("\n");
        }
    }
    public static int Valid(int top)
    {
        for(int i=1;i<top;i++)
            if(st[i] == st[top]) return 0;
        return 1;
    }
    public static int VerificareClique(int length, int[][] a)
    {
        for(int i=1;i<=length;i++)
            for(int j=i+1;j<=length;j++)
                if(a[st[i]][st[j]] == 0) return 0;
        return length;
    }
    public static int Clique(int[][] a,int n, int k)
    {
        int top, cand;
        int gasit = 0;
        top = 0;
        st[++top] = 0;

        while(top > 0 && gasit == 0)
        {
            cand = 0;
            while(cand == 0 && st[top] < n)
            {
                st[top]++;
                cand = Valid(top);
            }
            if(cand == 0) top--;
            else if(top >= k) gasit = VerificareClique(top,a);
            else st[++top] = 0;
        }
        if(gasit == 0) return 0;
        return gasit;
    }
    }
