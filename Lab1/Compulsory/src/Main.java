//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello World!\n");

        String[] lanugages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1000000);

        n = n * 3;
        n = n + 0b10101;
        n = n + 0xFF;
        n = n * 6;

        while ( n > 9)
        {
            int n2 = n;
            n = 0;
            while(n2 > 0)
            {
                n+= n2 % 10;
                n2/=10;
            }
        }
        int result = n;
        System.out.printf("Willy-nilly, this semester i will learn " + lanugages[result]);

    }

}