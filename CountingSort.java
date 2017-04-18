import java.io.*;
import java.util.*;

public class CountingSort {

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
                outputFilePath = dir + "\\countingSort_out.txt";
            }
            else {
                outputFilePath = dir + "\\src\\main\\java\\countingSort_out.txt";
            }

            Integer integerArray[] = readInputFile(dir + inputFilePath);

            long startTime = System.currentTimeMillis();
            List<Integer> sortedArray = countingSort(integerArray);
            Integer[] integerOutputArray = new Integer[sortedArray.size()];

            for (int i = 0; i < sortedArray.size(); i++) {
                integerOutputArray[i] = sortedArray.get(i);
            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            writeToOutputFile(integerOutputArray, outputFilePath, elapsedTime);
        } catch (Exception ex) {
            System.out.println("An error occured while program execution. Exiting the program now...");
            ex.printStackTrace();
        }
    }

    public static List<Integer> countingSort(Integer arr[]) {
        // array to be sorted in, this array is necessary
        // when we sort object datatypes, if we don't,
        // we can sort directly into the input array
        Integer[] aux = new Integer[arr.length];

        // find the smallest and the largest value
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        // init array of frequencies
        int[] counts = new int[max - min + 1];

        // init the frequencies
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - min]++;
        }

        // recalculate the array - create the array of occurences
        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }

    /*
      Sort the array right to the left
      1) Look up in the array of occurences the last occurence of the given value
      2) Place it into the sorted array
      3) Decrement the index of the last occurence of the given value
      4) Continue with the previous value of the input array (goto set1),
         terminate if all values were already sorted
    */
        for (int i = arr.length - 1; i >= 0; i--) {
            aux[counts[arr[i] - min]--] = arr[i];
        }
        List<Integer> list = Arrays.asList(aux);
        return list;
    }


    private static Integer[] readInputFile(String filePath) throws Exception {
        Scanner in = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        Integer[] finalInputArray = null;
        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            input.append(line);
        }
        reader.close();


        String tempInput = input.toString();
        String inputArrayInStringFormat[] = tempInput.split(",");
        finalInputArray = new Integer[inputArrayInStringFormat.length];

        for (int i = 0; i < inputArrayInStringFormat.length; i++) {
            finalInputArray[i] = Integer.parseInt(inputArrayInStringFormat[i]);
        }
        return finalInputArray;
    }

    static void writeToOutputFile(Integer[] sortedArray, String outputFilePath, long elapsedTime) throws Exception {

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