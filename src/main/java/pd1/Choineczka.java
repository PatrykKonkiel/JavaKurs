package pd1;

public class Choineczka {
    public static void main(String[] args) {
        System.out.println("// Wynik:");
        for(int i=1;i<=5;i++){
            for (int j=1;j<=1;j++){
                System.out.print("//");
            }
            for (int j=1;j<=6-i;j++){
                System.out.print(" ");
            }
            for(int j=1; j<=2*i-1;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
