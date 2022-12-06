import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class RockGenerator {
    public String generateRandomString(int n) {
        int count = 0;

        String alphaString = "01";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            if (count < 30) {
                int index = (int) (alphaString.length() * Math.random());
                
                if (alphaString.charAt(index) == '1')
                    count++;

                sb.append(alphaString.charAt(index));
            }
        }

        return sb.toString();
    }

    public void generateFile() {
        try {
            File rockFile = new File("RockFile.txt");
            if (rockFile.createNewFile()) {
                // Then, generate and write the randomized strings.
                FileWriter myWriter = new FileWriter("RockFile.txt");
                
                myWriter.write(generateRandomString(50) + "\n");
    
                myWriter.close();
            } else {
                if(rockFile.delete()) {
                    FileWriter myWriter = new FileWriter("RockFile.txt");
                    
                    myWriter.write(generateRandomString(50) + "\n");
        
                    myWriter.close();
                }
            }
        } catch (IOException e) {
            // If IO Exception occurs, abort.
            System.out.println("An error occurred. Please try again.");
            e.printStackTrace();
        }
    }
}
