package LDPC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LDPC {
    public static void main(String args[]){
        //n - code word bit's ; m - check bit's
        Integer[][] checkMatrix =
                       {{1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
                        {0, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                        {1, 0, 0, 0, 1, 1, 0, 0, 1, 1},
                        {0, 1, 1, 1, 0, 1, 1, 0, 0, 0},
                        {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 0, 1, 1, 1, 1}};

        Integer[][] genMatrix  =
                        {{1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                         {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                         {0, 0, 1, 1, 1, 0, 0, 0, 1, 0},
                         {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}};

        Integer[][] block ={{1,0,1,1,}};

        LDPC code = new LDPC();
        Integer[][] encodedMSG = {{1, 1, 1, 0, 1, 0, 1, 0, 1, 1}};
        System.out.println("Message: " + Arrays.deepToString(block));
        System.out.println("Syndrom: " + BinaryMatrix.matrixMultiplication(checkMatrix, BinaryMatrix.matrixTranspose(encodedMSG)));
        System.out.println("Encode: " + code.encode(block));
        System.out.println("Syndrom null: "+ BinaryMatrix.equalNull(BinaryMatrix.matrixMultiplication(checkMatrix, BinaryMatrix.matrixTranspose(encodedMSG))));
        System.out.println("ErrMessage: " + Arrays.deepToString(encodedMSG));
        System.out.println("Decoded: " + Arrays.deepToString(code.decode(encodedMSG)));

        LDPC.incorrectBitIndex(encodedMSG);


    }



    public List<List<Integer>> encode(Integer[][] block){
        return BinaryMatrix.matrixMultiplication(block, BinaryMatrix.genMatrix);

    }

    public Integer[][] decode(Integer[][] block){
        List<List<Integer>> syndrome = BinaryMatrix.matrixMultiplication(BinaryMatrix.checkMatrix, BinaryMatrix.matrixTranspose(block));
        if (BinaryMatrix.equalNull(syndrome)){
            return block;
        }else{
            return fixBlockErr(block);
        }
    }

    public static Integer[][] fixBlockErr(Integer[][] block){
        for (int i = 0; i < 100; i++){
            List<List<Integer>> syndrome = BinaryMatrix.matrixMultiplication(BinaryMatrix.checkMatrix, BinaryMatrix.matrixTranspose(block));
            if (BinaryMatrix.equalNull(syndrome)){
                return block;
            }else{
                int incorrectBitIndex = LDPC.incorrectBitIndex(block);
                if (block[0][incorrectBitIndex] == 0){
                    block[0][incorrectBitIndex] = 1;
                }else{
                    block[0][incorrectBitIndex] = 0;
                }

            }
        }
        return block;
    }

    public static Integer incorrectBitIndex(Integer[][] decodedBlock){
        Integer[] failedChecks = new Integer[BinaryMatrix.genMatrixWidth];
        int n = BinaryMatrix.genMatrixWidth - BinaryMatrix.genMatrixHeight;
        for (int checkIndex = 0; checkIndex < n; checkIndex++){
            Integer[] mask = BinaryMatrix.checkMatrix[checkIndex];
            int parity = BinaryMatrix.matrixSum(mask, decodedBlock[0]);
            if (parity != 0){
                List<Integer> controlledBitIndex = new ArrayList<>();
                for (int i = 0; i < mask.length; i++){
                    if(mask[i] == 1){
                        controlledBitIndex.add(i);
                    }
                }
                for (int index : controlledBitIndex){
                    if (failedChecks[index] == null){
                        failedChecks[index] = 1;
                    }else{
                        failedChecks[index] += 1;
                    }
                }
            }
        }
        return BinaryMatrix.indexOfFirstMax(failedChecks);

    }

}
