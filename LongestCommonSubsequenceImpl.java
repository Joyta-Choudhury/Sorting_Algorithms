import java.io.*;
import java.util.Scanner;

/**
 * Created by vipul on 11/28/2016.
 */
public class LongestCommonSubsequenceImpl {
    static final String dir = System.getProperty("user.dir");
    static String inputFile1Path="";
    static String inputFile2Path="";
    static String inputFile3Path="";
    static String outputFilePath="";

    static StringBuilder finalOutput=new StringBuilder();
    public static void main(String[] args) {
        finalOutput.append("*************************************");
        finalOutput.append("\n     Longest Common Sub-sequence");
        finalOutput.append("\n*************************************");
        finalOutput.append("");

        try {
            System.out.println("Enter the name of first input file");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            String inputFile1 = br1.readLine();

            System.out.println("Enter the name of second input file");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String inputFile2 = br2.readLine();

            System.out.println("Enter the name of third input file");
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            String inputFile3 = br3.readLine();

            final String dir = System.getProperty("user.dir");

            if(dir.contains("java")){
                inputFile1Path="\\"+inputFile1;
                inputFile2Path="\\"+inputFile2;
                inputFile3Path="\\"+inputFile3;
            }
            else{
                inputFile1Path = "\\src\\main\\java\\" + inputFile1;
                inputFile2Path = "\\src\\main\\java\\" + inputFile2;
                inputFile3Path = "\\src\\main\\java\\" + inputFile3;
            }

            int integerArrayForInputFile1[] = readInputFile(dir + inputFile1Path);
            int integerArrayForInputFile2[] = readInputFile(dir + inputFile2Path);
            int integerArrayForInputFile3[] = readInputFile(dir + inputFile3Path);

            findLongestCommonSubsequence(integerArrayForInputFile1, integerArrayForInputFile2, integerArrayForInputFile3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void findLongestCommonSubsequence(int[] array1, int[] array2, int[] array3) throws IOException {

        int lengthOfArray1 = array1.length;
        int lengthOfArray2 = array2.length;
        int lengthOfArray3 = array3.length;

        int[][][] arrayToHoldLCS = new int[lengthOfArray1 + 1][lengthOfArray2 + 1][lengthOfArray3 + 1];

        for (int i = 0; i <= lengthOfArray1; i++) {
            for (int j = 0; j <= lengthOfArray2; j++) {
                for (int k = 0; k <= lengthOfArray3; k++) {
                    if (i == 0 || j == 0 || k == 0)
                        arrayToHoldLCS[i][j][k] = 0;

                    else if ((array1[i - 1] == array2[j - 1]) && (array1[i - 1] == array3[k - 1]) && (array2[j - 1] == array3[k - 1]))
                        arrayToHoldLCS[i][j][k] = arrayToHoldLCS[i - 1][j - 1][k - 1] + 1;

                    else
                        arrayToHoldLCS[i][j][k] = max(arrayToHoldLCS[i - 1][j][k], arrayToHoldLCS[i][j - 1][k], arrayToHoldLCS[i][j][k - 1]);
                }
            }
        }

        int index = arrayToHoldLCS[lengthOfArray1][lengthOfArray2][lengthOfArray3];
        finalOutput.append("\n \n=================================================");
        finalOutput.append("\nLength of Longest Common Sub-sequence : "+index);
        finalOutput.append("\n=================================================");

        // Create a character array to store the lcs string
        int lcs[]=new int[index+1];

        // Start from the right-most-bottom-most corner and one by one store characters in lcs[]
        int i = lengthOfArray1, j = lengthOfArray2, k = lengthOfArray3;
        while (i > 0 && j > 0 && k > 0) {

            if ((array1[i - 1] == array2[j - 1]) && (array1[i - 1] == array3[k - 1]) && (array2[j - 1] == array3[k - 1])) {
                lcs[index-1] = array1[i-1]; // Put current character in result
                index--;
                i--;
                j--;
                k--;
            }

            // If not same, then find the larger of three and go in the direction of larger value
            else if ((arrayToHoldLCS[i - 1][j][k] > arrayToHoldLCS[i][j - 1][k]) && (arrayToHoldLCS[i - 1][j][k] > arrayToHoldLCS[i][j][k - 1])) {
                i--;
            } else if ((arrayToHoldLCS[i][j - 1][k] > arrayToHoldLCS[i - 1][j][k]) && (arrayToHoldLCS[i][j - 1][k] > arrayToHoldLCS[i][j][k - 1])) {
                j--;
            } else if ((arrayToHoldLCS[i][j][k - 1] > arrayToHoldLCS[i - 1][j][k]) && (arrayToHoldLCS[i][j][k - 1] > arrayToHoldLCS[i][j - 1][k])) {
                k--;
            } else if ((arrayToHoldLCS[i - 1][j][k] == arrayToHoldLCS[i][j - 1][k]) && (arrayToHoldLCS[i - 1][j][k] > arrayToHoldLCS[i][j][k - 1]) && (arrayToHoldLCS[i][j - 1][k] > arrayToHoldLCS[i][j][k - 1])) {
                i--;
                j--;
            } else if ((arrayToHoldLCS[i - 1][j][k] == arrayToHoldLCS[i][j][k - 1]) && (arrayToHoldLCS[i - 1][j][k] > arrayToHoldLCS[i][j - 1][k]) && (arrayToHoldLCS[i][j][k - 1] > arrayToHoldLCS[i][j - 1][k])) {
                i--;
                k--;
            } else if ((arrayToHoldLCS[i][j - 1][k] == arrayToHoldLCS[i][j][k - 1]) && (arrayToHoldLCS[i][j - 1][k] > arrayToHoldLCS[i - 1][j][k]) && (arrayToHoldLCS[i][j][k - 1] > arrayToHoldLCS[i - 1][j][k])) {
                j--;
                k--;
            }
        }
        for(int out=0; out<lcs.length-1;out++)
            if(out==lcs.length-2){
                finalOutput.append("\n"+lcs[out]+"");
            }
            else{
                finalOutput.append("\n"+lcs[out]+",");
            }

            writeOutputToFile(finalOutput);
    }

    static int max(int number1, int number2, int number3) {
        if (number1 > number2) {
            if (number1 > number3) {
                return number1;
            } else {
                return number3;
            }
        } else {
            if (number2 > number3) {
                return number2;
            } else {
                return number3;
            }
        }
    }

    private static int[] readInputFile(String filePath) throws Exception {
        StringBuilder input = new StringBuilder();
        int[] finalInputArray = null;
        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            input.append(line);
        }
        reader.close();


        String tempInput = input.toString();
        String tempInput2="";
        if(tempInput.contains("milliseconds")){
            tempInput2=tempInput.split("milliseconds ")[1];
        }
        else{
            tempInput2=tempInput;
        }

        String inputArrayInStringFormat[] = tempInput2.split(",");
        finalInputArray = new int[inputArrayInStringFormat.length];

        for (int i = 0; i < inputArrayInStringFormat.length; i++) {
            finalInputArray[i] = Integer.parseInt(inputArrayInStringFormat[i]);
        }
        return finalInputArray;
    }

    public static void writeOutputToFile(StringBuilder finalOutput) throws IOException {

        if(dir.contains("java")){
            outputFilePath=dir +"\\LCS_out.txt";
        }else{
            outputFilePath=dir + "\\src\\main\\java\\LCS_out.txt";
        }

        File file = new File(outputFilePath);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(finalOutput+"");
        bw.close();

        System.out.println("Output file created at location: " + outputFilePath);
    }
}
