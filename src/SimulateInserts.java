import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimulateInserts {
    public static File generateData() {
        File data = new File("TEST.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(data))) {
            bw.write("sepal_length,sepal_width,petal_length,petal_width,species");
            bw.newLine();

            double sepalLength;
            double sepalWidth;
            double petalLength;
            double petalWidth;
            String species;

            species = "Iris-setosa";
            for (int i = 0; i < 25; i++) {
                sepalLength = 4. + Math.random() * (5.5 - 4.);
                sepalLength = Math.floor(sepalLength * 10) / 10;
                sepalWidth = 2.3 + Math.random() * (4.4 - 2.3);
                sepalWidth = Math.floor(sepalWidth * 10) / 10;
                petalLength = 1. + Math.random() * (1.9 - 1.);
                petalLength = Math.floor(petalLength * 10) / 10;
                petalWidth = 0.1 + Math.random() * (0.6 - 0.1);
                petalWidth = Math.floor(petalWidth * 10) / 10;
                bw.write(sepalLength + "," + sepalWidth + "," + petalLength + "," + petalWidth + "," + species);
                bw.newLine();
            }

            species = "Iris-versicolor";
            for (int i = 0; i < 25; i++) {
                sepalLength = 4.9 + Math.random() * (6.9 - 4.9);
                sepalLength = Math.floor(sepalLength * 10) / 10;
                sepalWidth = 2. + Math.random() * (3.4 - 2.);
                sepalWidth = Math.floor(sepalWidth * 10) / 10;
                petalLength = 3. + Math.random() * (5 - 3.);
                petalLength = Math.floor(petalLength * 10) / 10;
                petalWidth = 1. + Math.random() * (1.7 - 1.);
                petalWidth = Math.floor(petalWidth * 10) / 10;
                bw.write(sepalLength + "," + sepalWidth + "," + petalLength + "," + petalWidth + "," + species);
                bw.newLine();
            }

            species = "Iris-virginica";
            for (int i = 0; i < 25; i++) {
                sepalLength = 5.7 + Math.random() * (7.9 - 5.7);
                sepalLength = Math.floor(sepalLength * 10) / 10;
                sepalWidth = 2.5 + Math.random() * (3.8 - 2.5);
                sepalWidth = Math.floor(sepalWidth * 10) / 10;
                petalLength = 4.5 + Math.random() * (6.7 - 4.5);
                petalLength = Math.floor(petalLength * 10) / 10;
                petalWidth = 1.4 + Math.random() * (2.5 - 1.4);
                petalWidth = Math.floor(petalWidth * 10) / 10;
                bw.write(sepalLength + "," + sepalWidth + "," + petalLength + "," + petalWidth + "," + species);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
