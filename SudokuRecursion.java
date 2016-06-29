import java.util.*;
import java.lang.*;
import java.io.*;

class Sud{
	/*
	static void createHintArray(int[][] puzzle){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(puzzle[i][j]==0){
					for(int k=0; k<9; k++){
						hintArray[i][j][k]=1;
					}
				}
			}
		}
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(puzzle[i][j]==0){
					for(int k=0; k<9; k++){
						if(puzzle[i][k]!=0){
							hintArray[i][j][puzzle[i][k]-1]=0;
						}
						if(puzzle[k][j]!=0){
							hintArray[i][j][puzzle[k][j]-1]=0;
						}
					}
					for(int k=i-i%3; k<i-i%3+3; k++){
						for(int l=j-j%3; l<j-j%3+3; l++){
							if(puzzle[k][l]!=0){
								hintArray[i][j][puzzle[k][l]-1]=0;
							}
						}
					}
				}
			}
		}
	}*/
	public static int findEmpty(int[][] arr){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(arr[i][j]==0){
					return (i*9+j);
				}
			}
		}
		return -1;
	}
	public static boolean checkFill(int[][] arr, int fill, int row, int col){
		for(int i=0; i<9; i++){
			if((arr[row][i]==fill && i!=col) || (arr[i][col]==fill && i!=row)){
				return false;
			}
		}
		for(int i=row-row%3; i<row-row%3+3; i++){
			for(int j=col-col%3; j<col-col%3+3; j++){
				if(arr[i][j]==fill && (i!=row && j!=col)){
					return false;
				}
			}
		}
		return true;	
	} 
	public static boolean fillOnce(int[][] arr){
		int row, col;
		int emp=findEmpty(arr);
		if(emp==-1){
			return true;
		}
		else{
			row=emp/9;
			col=emp%9;
		}
		for(int i=1; i<=9; i++){
			if(checkFill(arr, i, row, col)==true){
				arr[row][col]=i;
				/*for(int m=0; m<9; m++){
					for(int n=0; n<9; n++){
						System.out.print(arr[m][n]+" ");
					}
					System.out.println();
				}*/
				if(fillOnce(arr)==false){
					arr[row][col]=0;
				}
				else{
					return true;
				}
			}
		}
		return false;
	}
}

public class SudokuRecursion {
	
	public static void main (String args[]) {
		String fileName = "puz_sudoku.txt"; //read from file
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            // Number of test cases
            for (int test = 0; test < 50; test++)
            {

                int puz [][] = new int [9] [9];
                //enter Grid No.
                String Line = br.readLine();
                System.out.println(Line);
                // Entering Sudoku puzzle
                for (  int i = 0; i < 9; i++)
                {
                    for ( int j = 0; j < 9; j++)
                    {
                        puz[i][j]= Integer.parseInt(String.valueOf(br.read()))-48;
                    }
                    br.read();
                }
				Sud.fillOnce(puz);
				for(int i=0; i<9; i++){
					for(int j=0; j<9; j++){
						System.out.print(puz[i][j]+" ");
					}
					System.out.println();
				}
			}  // end of no of test cases loop

            br.close();
        }  //end of try

        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }

        catch(IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'"); 
        }
    }
}

