import java.io.*;
import java.util.Scanner;

/**
 * Created by vipul on 11/24/2016.
 */
public class MergeSort {
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
                outputFilePath = dir + "\\mergeSort_out.txt";
            }
            else {
                outputFilePath = dir + "\\src\\main\\java\\mergeSort_out.txt";
            }


            int integerArray[] = readInputFile(dir + inputFilePath);

            long startTime = System.currentTimeMillis();
            sort(integerArray,0,integerArray.length-1);
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            writeToOutputFile(integerArray, outputFilePath, elapsedTime);
        } catch (Exception ex) {
            System.out.println("An error occured while program execution. Exiting the program now...");
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


    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
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
