import com.sun.source.doctree.SystemPropertyTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

import java.awt.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public boardstate[][][] combinations = new boardstate[4][4][4];

    public MyPlayer() {
        columns = new int[10];
    }
    public void start(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 0; z < 4; z++){
                    combinations[i][j][z] = new boardstate(i,j,z);
                }
            }
        }
        combinations[1][0][0] = new boardstate(1,0,0);
        combinations[1][0][0].winning = false;
    }
    public ArrayList<boardstate> moves(int a, int b, int c){
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
            boardstate temp = new boardstate(i,hi1,hi2);
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
                       System.out.println("win or lose for " +  i + " " + j + " " + z);
                       System.out.println(combinations[i][j][z]. winning);
                       combinations[i][j][z].print();

                    }
                }
            }
        }
        System.out.println(count);
    }
    public boolean solve(int a, int b, int c){
        ArrayList<boardstate> current = new ArrayList<>();
        current = moves(a,b,c);
        for(int i = 0; i < current.size(); i++){
            int x,y,z;
            boardstate yes = current.get(i);
            x = yes.a;
            y = yes.b;
            z = yes.c;
       //     yes.print();
            if(combinations[x][y][z].winning == false){
                combinations[a][b][c].a = x;
                combinations[a][b][c].b = y;
                combinations[a][b][c].c = z;
       //         yes.print();
                return true;
            }

        }
        return false;

    }
    public void generate_losses(){
        for(int i = 2; i <4;i++) {
            combinations[i][0][0].winning = true;
        }
        combinations[1][1][0].winning = true;
        combinations[1][1][1].winning = true;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 1; z < 4; z++){
                    if(solve(z,j,i) == true){
                        combinations[z][j][i].winning = true;
                    }
                    else{
                        combinations[z][j][i].winning = false;
                    }
                }
            }
        }
    }
    public Point changestate(Chip[][] pBoard){
        int[] heights = new int[3];
        heights[0] =3;
        heights[1] = 3;
        heights[2] = 3;
        for(int i = 0; i < 3; i++){
           // System.out.println(pBoard[i][0].isAlive + " " + pBoard[i][1].isAlive + " " + pBoard[i][2].isAlive);
            for(int j = 0; j < 3;j++){
                if(pBoard[j][i].isAlive == false){
                    heights[i] = j;
                    break;
                }
            }
        }
 //       for(int i = 0; i < 3; i++){
 //           System.out.println(heights[i] + " " + i);
 //       }

        boardstate rip = combinations[heights[0]][heights[1]][heights[2]];
        rip.print();
        if(rip.a != heights[0]){
            Point myMove = new Point(rip.a, 0);
            return myMove;
        }
        if(rip.b != heights[1]){
            System.out.println("b");
    //        System.out.println(column);
            Point myMove = new Point(rip.b, 1);
            return myMove;
        }
        if(rip.c != heights[2]){
            System.out.println("c");
            Point myMove = new Point(rip.c, 2);
            return myMove;
        }
        int row = 0;
        int column = heights[0]-1;
    //    System.out.println(heights[0]);
    //    System.out.println(column);
        Point myMove = new Point(row, column);
        return myMove;
    }


    //add your code to return the row and the column of the chip you want to take.
    //you'll be returning a data type called Point which consists of two integers.
    public Point move(Chip[][] pBoard) {


        gameBoard = pBoard;

        start();
        generate_losses();
 //       generate(3);

        Point myMove = changestate(gameBoard);


        return myMove;
    }

}
