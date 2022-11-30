import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;
        System.out.print("Enter size of puzzle(n*n), n = ");
        N = scanner.nextInt();

        int[][] matInitial = new int[N][N];
        ArrayList<Integer> values = new ArrayList<>(N*N);

        System.out.println("Enter initial configuration (0 for blank):");
        int i=0,j=0, blankX = -1, blankY = -1;
        while(scanner.hasNext()){
            int input = scanner.nextInt();

            if(input>=0 && input<(N*N)){
                if(input==0){
                    blankX = i;
                    blankY = j;
                }
                matInitial[i][j++] = input;
                if(values.contains(input)){
                    System.out.println("Duplicate input!");
                    System.exit(1);
                }
                values.add(input);
            }else{
                System.out.println("Invalid input!");
                System.exit(1);
            }

            if(j==N){
                i++;
                j=0;
            }
            if(i==N)break;
        }
        scanner.close();

        show(matInitial);
        System.out.println(blankX+" "+blankY);

        System.out.println(isSolvable(matInitial));
    }

    public static void show(int[][] matrix){
        System.out.println("Current configuration:");
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j]>0)System.out.print(matrix[i][j]+"  ");
                else System.out.print("*  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSolvable(int[][] mat) {
        int numOfInversions = 0;
        int idx = 0;
        int[] linearMat = new int[mat.length*mat.length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                linearMat[idx++] = mat[i][j];
            }
        }

        for(int i=0;i<linearMat.length-1;i++){
            for(int j=i+1;j<linearMat.length;j++){
                if(linearMat[i]!=0 && linearMat[j]!=0 && linearMat[i]>linearMat[j]){
                    numOfInversions++;
                }
            }
        }

        if(numOfInversions%2 == 1) return false;

        return true;
    }
}