package LDPC;

import java.util.ArrayList;
import java.util.List;

public class BinaryMatrix {
    public static void main(String args[]){

    }
    public static Integer[][] checkMatrix =
                   {{1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
                    {0, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                    {1, 0, 0, 0, 1, 1, 0, 0, 1, 1},
                    {0, 1, 1, 1, 0, 1, 1, 0, 0, 0},
                    {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                    {0, 0, 0, 1, 0, 0, 1, 1, 1, 1}};

    public static Integer[][] genMatrix  =
                    {{1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 0, 1, 0},
                    {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}};

    public static Integer genMatrixWidth = genMatrix[0].length;

    public static Integer genMatrixHeight = genMatrix.length;


    public static List<List<Integer>> matrixMultiplication(Integer[][]a, Integer[][]b){
        List<List<Integer>> resultArray = new ArrayList<>();
        for (int rowNum = 0; rowNum < a.length; rowNum++){
            Integer[] row = a[rowNum];
            List<Integer> resultRow = new ArrayList<Integer>();
            for (int columnNum = 0; columnNum < b[0].length; columnNum++){
                resultRow.add(BinaryMatrix.elementCompute(row,b, columnNum));
            }
            resultArray.add(resultRow);
        }
        return resultArray;

    }



    public static int elementCompute(Integer[] row, Integer[][] b, int columnNum){
        int result = 0;
        for (int i = 0; i < row.length; i++){
            result += row[i] * b[i][columnNum];
        }
        return result%2;
    }

    public static Integer[][] matrixTranspose(Integer[][] a){
        Integer[][] transposedMatrix = new Integer[a[0].length][a.length];
        for (int columnIndex = 0; columnIndex < a[0].length; columnIndex++){
            for (int rowIndex = 0; rowIndex < a.length; rowIndex++){
                transposedMatrix[columnIndex][rowIndex] = a[rowIndex][columnIndex];
            }
        }
        return transposedMatrix;
    }


    public static Integer matrixSum(Integer[] a, Integer[] b){
        int sum = 0;
        for (int index = 0; index < a.length; index++){
            if (a[index]+b[index]==2){
                sum += 1;
            }
        }
        return sum%2;
    }

    public static Integer indexOfFirstMax(Integer[] failedChecks){
        int firstMax = 0;
        int indexOfFirstMax = 0;
        for (int i = 0; i < failedChecks.length; i++){
            if (failedChecks[i]!=null && failedChecks[i] > firstMax){
                indexOfFirstMax = i;
                firstMax = failedChecks[i];
            }
        }
        return indexOfFirstMax;
    }

    public static boolean equalNull(List<List<Integer>> matrix){
        int sum = 0;
        for (List<Integer>row : matrix){
            for (int element : row){
                sum += element;
            }
        }
        if (sum == 0){
            return true;
        }else{
            return false;
        }
    }


    public static String multiArrToString(Integer[][] input){
        String outputString = "";
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[0].length; j++){
                int val = (int) input[i][j];
                outputString += Integer.toString(val);
            }
            outputString += "\n";
        }
        return outputString;
    }




}

