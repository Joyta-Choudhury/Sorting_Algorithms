import java.io.BufferedWriter;
import java.io.*;
import java.util.Random;

/**
 * Created by vipul on 12/1/2016.
 */
public class RandomNumberGenerator {
    public static void main(String[] args) {
        try {

            System.out.println("How many random numbers do you want to generate?");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numberOfRandomNumbers = Integer.parseInt(br.readLine());
            int sizeOfEachLoop = numberOfRandomNumbers/4;

            Random random = new Random();
            StringBuilder randomNumbers = new StringBuilder();

            for (int i = 0; i < sizeOfEachLoop; i++)
                randomNumbers.append(random.nextInt(100000 - 1) + 1 + ",");

            for (int i = 0; i < sizeOfEachLoop; i++)
                randomNumbers.append(random.nextInt(10000 - 1) + 1 + ",");

            for (int i = 0; i < sizeOfEachLoop; i++)
                randomNumbers.append(random.nextInt(1000000 - 1) + 1 + ",");

            for (int i = 0; i < sizeOfEachLoop; i++)
                randomNumbers.append(random.nextInt(10000 - 1) + 1 + ",");

            final String dir = System.getProperty("user.dir");
            String outputFilePath = dir + "\\src\\main\\java\\randomInput.txt";

            File file = new File(outputFilePath);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            randomNumbers.deleteCharAt(randomNumbers.length()-1);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + randomNumbers);
            bw.close();
            System.out.println("Output file created at location: " + outputFilePath);
        } catch (Exception ex) {
            System.out.println("An error occurred while running te programm. The program will exit now.");
        }
    }
}