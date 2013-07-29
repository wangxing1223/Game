/**
 * Created by Administrator on 13-7-28.
 */
import com.sun.org.apache.xpath.internal.functions.FuncStringLength;

import java.util.Random;
import java.util.Scanner;

public class GameTest {

    private static final int S = 14;
    private static final int M = 21;
    private static final int L = 28;
    private static int[] COLOR = {0,1,2,3,4,5};
    private static int[][] board;
    private static boolean[][]booleans;
    private static int counter = 0;
    private static int [] ints;
    private static int n;
    private  static boolean b = false;


    public void setBoard(int length){
        board = new int[length][length];
        Random random = new Random();
        for (int k = 0; k < length ; k++) {
            for (int l = 0; l < length ; l++) {
                board[k][l] = random.nextInt(6);
//                System.out.print(board[k][l] + "\t");
            }
//            System.out.println();
        }


    }
    public void option(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("s/m/l :");
        String s = scanner.next();
        switch (s.toUpperCase()){
            case "S":
                setBoard(S);
                break;
            case "M":
                setBoard(M);
                break;
            case "L":
                setBoard(L);
                break;
            default:
                break;
        }

    }

    public void booleans(){
        booleans = new boolean[board.length+2][board.length+2];
        for (int i = 1; i < booleans.length-1; i++) {
            for (int j = 1; j < booleans[i].length-1; j++){
                if((i == 1)&&(j == 1)){
                    booleans[i][j]=true;
                } else {
                    booleans[i][j] = false;
                }
            }

        }
    }

    public int win(){
        int m = 0;
        for (int k = 1; k < booleans.length-1 ; k++) {
            for (int l = 1; l < booleans[k].length-1 ; l++) {
                if(booleans[k][l] == true){
                } else {
                    m  = -1;
                    break;
                }
            }
        }

        return m;
    }


    public void isExit(int n){
        for (int k = 1; k < booleans.length-1 ; k++) {
            for (int l = 1; l < booleans[k].length-1; l++) {
                if ((k ==1)&&(l==1)){
                    booleans[k][l] = true;
                }else if (board[k-1][l-1] == board[0][0]){
                    if (booleans[k][l-1]||booleans[k][l+1]||booleans[k+1][l]||booleans[k-1][l]){
                        booleans[k][l] = true;
                    }

                }

            }
        }

    }

    public void iuput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("input number:");
        n = scanner.nextInt();

        while ( b == false){
            if((n > 5)||(n < 0)){
                System.out.print("ERROR! input  number : ");
                n = scanner.nextInt();
            } else {
                b = true;
            }
        }
        for (int k = 1; k < booleans.length-1 ; k++) {
            for (int l = 1; l < booleans[k].length-1; l++) {
                if (booleans[k][l] == true){
                    board[k-1][l-1] = n;
                }

            }

        }

    }

    public void output(){
        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[k].length; l++) {
                System.out.print(board[k][l]+"\t");
            }
            System.out.println();
        }
        System.out.println("step:" + counter +"/" + (board.length*2-3));

    }

    public int scores(){
        int number = 0;
        for (int k = 1; k < booleans.length-1 ; k++) {
            for (int l = 1; l < booleans[k].length-1 ; l++) {
                if(booleans[k][l] == false){
                    number++;
                }
            }
        }
        return -(number* number);
    }



    public static void main(String[] args) {

        GameTest gameTest = new GameTest();
        gameTest.option();
        gameTest.booleans();
        gameTest.isExit(n);
        gameTest.output();
        gameTest.win();
        while ( counter < (board.length*2-3) ){
            if (gameTest.win() == 0){
                System.out.println("You are win!");
                System.out.println("Your scores is :" + (board.length*2-3-counter)*10);
            break;
        }

            gameTest.iuput();
            gameTest.isExit(n);
            counter++;
            gameTest.output();

        }
        if(counter > 10){
            System.out.println("You are lost!");
            System.out.println("Your scores is :" + gameTest.scores());
        }


    }

}
