import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k;
        System.out.print("Enter grid size, k = ");
        k = scanner.nextInt();

        int[][] boardInitial = new int[k][k];
        ArrayList<Integer> values = new ArrayList<>(k*k);

        System.out.println("Enter initial configuration (0 for blank):");
        int i=0,j=0, blankX = -1, blankY = -1;
        while(scanner.hasNext()){
            int input = scanner.nextInt();

            if(input>=0 && input<(k*k)){
                if(input==0){
                    blankX = i;
                    blankY = j;
                }
                boardInitial[i][j++] = input;
                if(values.contains(input)){
                    System.out.println("Duplicate input!");
                    System.exit(1);
                }
                values.add(input);
            }else{
                System.out.println("Invalid input!");
                System.exit(1);
            }

            if(j==k){
                i++;
                j=0;
            }
            if(i==k)break;
        }

        int[][] boardFinal = new int[k][k];
        int val = 0;
        for(int p=0;p<k;p++){
            for(int q=0;q<k;q++){
                if(p==k-1 && q==k-1) boardFinal[p][q] = 0;
                else boardFinal[p][q] = ++val;
            }
        }

        show(boardInitial);
        show(boardFinal);

        if(!isSolvable(boardInitial, blankX, blankY)){
            System.out.println("This grid configuration is not solvable.");
        }else{
            int choice;
            System.out.println("Choose function:\n1.Hamming  2.Manhattan");
            choice = scanner.nextInt();
            if(choice==1){
                System.out.println("you chose hamming");
            }else if(choice==2){
                System.out.println("you chose manhattan");
            }else{
                System.out.println("Wrong input!");
            }
            
        }

        scanner.close();
    }

    public static void show(int[][] matrix){
        //System.out.println("Current grid configuration:");
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j]>0)System.out.print(matrix[i][j]+"  ");
                else System.out.print("*  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSolvable(int[][] mat, int blankX, int blankY) {
        int numOfInversions = 0;
        int idx = 0;
        int k = mat.length;
        int[] linearMat = new int[k*k];
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
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

        if(k%2==1 && numOfInversions%2 == 0) return true;
        
        if(k%2==0 && ((blankX%2==0 && numOfInversions%2==1) || (blankX%2==1 && numOfInversions%2==0))) return true;

        return false;
    }
}