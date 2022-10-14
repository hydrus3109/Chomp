import com.sun.source.doctree.SystemPropertyTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

import java.awt.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public int[][][] combinations = new int[4][4][4];

    public MyPlayer() {
        columns = new int[10];
    }
    public void start(){
        for(int i = 0 ; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 0; z < 4; z++){
                    combinations[i][j][z] = -1;
                }
            }
        }
        combinations[1][0][0] = 0;
    }
    public ArrayList<Integer> moves(int a, int b, int c){
        ArrayList<boardstate> arr = new ArrayList<>();
        for(int i = c-1;i>=0;i--){
            boardstate temp = new boardstate(a,b,i);
            arr.add(temp);
       //     System.out.println(a + " " + b + " " + i + "row1");
        }
        for(int i = b-1;i>=0;i--){

            int hi = c;
            if(i < c){
                hi = i;
            }
            boardstate temp = new boardstate(a,i,hi);
            arr.add(temp);
  //          System.out.println(a + " " + i + " " + hi + "row2");
        }
        for(int i = a-1;i>=0;i--){
            int hi1 = b;
            int hi2 = c;
            if(i < b) hi1 = i;
            if (i < c) hi2 = i;
            boardstate temp = new boardstate(a,b,c);
            arr.add(temp);
 //           System.out.println(i + " " + hi1 + " " + hi2 + "row3");
        }
        return arr;
    }
    public void generate(int n){
        int count = 0;
        for(int i =n; i >= 0; i--){
            for( int j = i;j >=0; j-- ){
                for(int z = j; z >=0; z--){
                    if(i == 0 && j == 0 && z == 0) {

                    }
                    else{
                        count++;
                        System.out.println("Moves for " +  i + " " + j + " " + z);
                        moves(i,j,z);
                    }
                }
            }
        }
        System.out.println(count);
    }
    public int solve(int a, int b, int c){
        ArrayList<Integer> current = new ArrayList<>();
        current = moves(a,b,c);
        for(int i = 0; i < current.size(); i++){
            int x,y,z;
            int yes = current.get(i);
            //System.out.println(yes);
            //algo for extracting is very scuffed cuz im stupid
            // flipped x and z
            x = yes%10;
            yes = yes - x; yes = yes/10;
            y = yes%10;
            yes = yes-y; yes = yes/10;
            z = yes %10;
    //        System.out.println(z + " " + y + " " + x);
            if(combinations[z][y][x] == 0){
      //          System.out.println(z + " " + y + " " + x);
                return yes;
            }

        }
        return 0;

    }
    public void generate_losses(){
        for(int i = 2; i <4;i++) {
            combinations[i][0][0] = 1;
        }
        combinations[1][1][0] = 1;
        combinations[1][1][1] = 1;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 1; z < 4; z++){
                    if(solve(z,j,i) != 0){
                        combinations[z][j][i] = solve(z,j,i);
                    }
                    else{
                        combinations[z][j][i] = 0;
                    }
                }
            }
        }
    }



    //add your code to return the row and the column of the chip you want to take.
    //you'll be returning a data type called Point which consists of two integers.
    public Point move(Chip[][] pBoard) {


        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 2;
        column = 4;

        Point myMove = new Point(row, column);
        start();
  //      generate(3);
        generate_losses();
        System.out.println(combinations[2][2][2]);


        return myMove;
    }

}
