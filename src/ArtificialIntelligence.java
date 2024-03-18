import java.util.Scanner;

public class ArtificialIntelligence {
    Database database;
    KNN knn;
    int choose;

    public ArtificialIntelligence(int k, int choose) {
        this.database = new Database();
        this.knn = new KNN(k);
        this.choose = choose;
        database.loadUserData("USER.csv");
        database.loadTrainingData("IRIS.csv");
        database.loadTestingData(SimulateInserts.generateData().toString());
    }

    public void startProgram() {
        switch (choose) {
            case 1:
                System.out.println("===============================TESTING DATA===============================");
                knn.algorithm(database.getTrainingData(), database.getTestingData());
                System.out.println("evaluation of testing data: " + knn.calculateEvaluation() + " %");
                System.out.println();
                System.out.println();

                knn.cleanCache();

                System.out.println("====================================USER DATA====================================");
                knn.algorithm(database.getTrainingData(), database.getUserData());
                System.out.println("\t\t\t\t  evaluation of user data: " + knn.calculateEvaluation() + " %");
                break;
            case 2:
                boolean isRunning = true;
                while (isRunning) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("insert: sepal_length");
                    Double sepal_length = scanner.nextDouble();
                    System.out.println("insert: sepal_width");
                    Double sepal_width = scanner.nextDouble();
                    System.out.println("insert: petal_length");
                    Double petal_length = scanner.nextDouble();
                    System.out.println("insert: petal_width");
                    Double petal_width = scanner.nextDouble();
                    System.out.println("insert: spec (Iris-setosa/Iris-versicolor/Iris-virginica)");
                    String spec = scanner.next();
                    String input = convertInput(sepal_length, sepal_width, petal_length, petal_width, spec);
                    database.loadInputData(input);
                    System.out.println("====================================INPUT DATA====================================");
                    knn.algorithm(database.getTrainingData(), database.getInputData());
                    System.out.println("\t\t\t\t  evaluation of input data: " + knn.calculateEvaluation() + " %");
                    System.out.println();
                    System.out.println("insert 1 to: close an application");
                    System.out.println("insert 2 to: insert more data");
                    int exit = scanner.nextInt();
                    if (exit == 1) isRunning = false;
                    while (exit != 1 && exit != 2) {
                        System.out.println("there is no such an option");
                        System.out.println("insert 1 to: close an application");
                        System.out.println("insert 2 to: insert more data");
                        exit = scanner.nextInt();
                        if (exit == 1) {
                            System.out.println("goodbye!");
                            isRunning = false;
                        } else if (exit == 2) {
                            break;
                        }
                    }
                    database.cleanInputData();
                }
                break;
        }
    }

    public String convertInput(Double sepal_length, Double sepal_width, Double petal_length, Double petal_width, String spec) {
        return sepal_length + "," + sepal_width + "," + petal_length + "," + petal_width + "," + spec;
    }
}
