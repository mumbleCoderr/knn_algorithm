import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t\t\tHello world!");

        System.out.println("provide the k-number of neighbours for the knn algorithm");
        int k = scanner.nextInt();

        System.out.println("insert 1 to:\tprint predictions from already loaded files");
        System.out.println("insert 2 to:\tinsert data and print a prediction");
        int choose = scanner.nextInt();

        ArtificialIntelligence AI = new ArtificialIntelligence(k, choose);
        AI.startProgram();
    }
}