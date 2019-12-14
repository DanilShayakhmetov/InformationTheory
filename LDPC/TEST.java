package LDPC;

import java.util.ArrayList;
import java.util.List;

public class TEST {
    public static void main(String[] args) {

        TEST test = new TEST();
        Integer[][] TestMatrix =
                {{1, 1, 0, 1, 0, 0},
                        {0, 1, 1, 0, 1, 0},
                        {1, 1, 1, 0, 0, 1}};

        Integer[][] TestBlock = {{1, 0, 1}};
        System.out.println(TEST.matrixMultiplication(TestBlock, TestMatrix));
    }


    public static List<List<Integer>> matrixMultiplication(Integer[][] a, Integer[][] b) {
        TEST test = new TEST();
        List<List<Integer>> resultArray = new ArrayList<>();
        for (int rowNum = 0; rowNum < a.length; rowNum++) {
            Integer[] row = a[rowNum];
            List<Integer> resultRow = new ArrayList<Integer>();
            for (int columnNum = 0; columnNum < b[0].length; columnNum++) {
                resultRow.add(TEST.elementCompute(row, b, columnNum));
            }
            resultArray.add(resultRow);
        }
        return resultArray;

    }


    public static int elementCompute(Integer[] row, Integer[][] b, int columnNum) {
        int result = 0;
        for (int i = 0; i < row.length; i++) {
            result += row[i] * b[i][columnNum];
        }
        return result % 2;
    }
}


