import java.util.Scanner;

public class Main{
    public static void show(int[][] array){
        System.out.println("Current configuration:");
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                if(array[i][j]>0)System.out.print(array[i][j]+"  ");
                else System.out.print("*  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;
        System.out.print("Enter size of puzzle(n*n), n = ");
        N = scanner.nextInt();

        int[][] a = new int[N][N];
        System.out.println("Enter initial configuration (0 for blank):");
        int i=0,j=0, blankX = -1, blankY = -1;
        while(scanner.hasNext()){
            int input = scanner.nextInt();

            if(input>=0 && input<(N*N)){
                if(input==0){
                    blankX = i;
                    blankY = j;
                }
                a[i][j++] = input;
            }else{
                System.out.println("Invalid input!");
                break;
            }

            if(j==N){
                i++;
                j=0;
            }
            if(i==N)break;
        }
        scanner.close();

        show(a);
        System.out.println(blankX+" "+blankY);
    }
}