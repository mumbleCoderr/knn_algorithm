import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Iris> trainingData;
    private List<Iris> testingData;
    private List<Iris> userData;
    private List<Iris> inputData;

    public Database() {
        this.trainingData = new ArrayList<>();
        this.testingData = new ArrayList<>();
        this.userData = new ArrayList<>();
        this.inputData = new ArrayList<>();
    }

    private void loadData(String filename, List<Iris> data) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (Character.isDigit(line.charAt(0))) {
                    String[] split = line.split(",");
                    Double sepal_length = Double.parseDouble(split[0]);
                    Double sepal_width = Double.parseDouble(split[1]);
                    Double petal_length = Double.parseDouble(split[2]);
                    Double petal_width = Double.parseDouble(split[3]);
                    String spec = split[4];
                    data.add(new Iris(sepal_length, sepal_width, petal_length, petal_width, spec));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTrainingData(String filename) {
        loadData(filename, trainingData);
    }

    public void loadTestingData(String filename) {
        loadData(filename, testingData);
    }

    public void loadUserData(String filename) {
        loadData(filename, userData);
    }

    public void loadInputData(String data) {
        String[] split = data.split(",");
        Double sepal_length = Double.parseDouble(split[0]);
        Double sepal_width = Double.parseDouble(split[1]);
        Double petal_length = Double.parseDouble(split[2]);
        Double petal_width = Double.parseDouble(split[3]);
        String spec = split[4];
        inputData.add(new Iris(sepal_length, sepal_width, petal_length, petal_width, spec));
    }

    public void cleanInputData() {
        inputData = new ArrayList<>();
    }

    public List<Iris> getTrainingData() {
        return trainingData;
    }

    public List<Iris> getTestingData() {
        return testingData;
    }

    public List<Iris> getUserData() {
        return userData;
    }

    public List<Iris> getInputData() {
        return inputData;
    }

}
