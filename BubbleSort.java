/**
 * Created by vipul on 11/14/2016.
 */

import java.io.*;
import java.util.Scanner;

class BubbleSort {
    public static void main(String[] args) {
        try {
            String inputFile = "";
            String inputFilePath="";
            String outputFilePath="";

            if (args.length == 0) {
                System.out.println("Enter the name of input file");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                inputFile = br.readLine();

            } else {
                inputFile = args[0];
            }

            final String dir = System.getProperty("user.dir");
            if(dir.contains("java")){
                inputFilePath="\\"+inputFile;
            }
            else {
                 inputFilePath = "\\src\\main\\java\\" + inputFile;
            }

            if(dir.contains("java")){
                 outputFilePath = dir + "\\bubbleSort_out.txt";
            }
            else {
                 outputFilePath = dir + "\\src\\main\\java\\bubbleSort_out.txt";
            }


            int integerArray[] = readInputFile(dir + inputFilePath);

            long startTime = System.currentTimeMillis();
            int[] sortedArray = bubbleSort(integerArray);
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            writeToOutputFile(sortedArray, outputFilePath, elapsedTime);
        } catch (Exception ex) {
            System.out.println("An error occured while program execution. Exiting the program now...");
            ex.printStackTrace();
        }
    }


    private static int[] readInputFile(String filePath) throws Exception {
        Scanner in = new Scanner(System.in);
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
        String inputArrayInStringFormat[] = tempInput.split(",");
        finalInputArray = new int[inputArrayInStringFormat.length];

        for (int i = 0; i < inputArrayInStringFormat.length; i++) {
            finalInputArray[i] = Integer.parseInt(inputArrayInStringFormat[i]);
        }
        return finalInputArray;
    }

    static int[] bubbleSort(int arr[]) throws Exception {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    static void writeToOutputFile(int[] sortedArray, String outputFilePath, long elapsedTime) throws Exception {

        File file = new File(outputFilePath);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Input size: " + sortedArray.length);
        System.out.println("\nTime taken to sort: " + elapsedTime + " milliseconds \n");
        bw.write("\nTime taken to sort: " + elapsedTime + " milliseconds \n");
        for (int i=0;i<sortedArray.length;i++) {
            if (i == (sortedArray.length-1)) {
                bw.write(sortedArray[i]+"");
            } else {
                bw.write(sortedArray[i] + ",");
            }
        }
        bw.close();

        System.out.println("Output file created at location: " + outputFilePath);

    }

}
