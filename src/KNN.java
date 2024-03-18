import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {
    private int k;
    private List<Double> results;
    private Double evaluationResult;
    private Double positiveEvaluationAttemps;
    private Double totalEvaluationAttemps;

    public KNN(int k) {
        this.k = k;
        this.results = new ArrayList<>();
        this.evaluationResult = 0.;
        this.positiveEvaluationAttemps = 0.;
        this.totalEvaluationAttemps = 0.;
    }

    public void algorithm(List<Iris> trainingData, List<Iris> testingData) {
        for (int i = 0; i < testingData.size(); i++) {
            results = new ArrayList<>();
            Iris vector1 = testingData.get(i);
            System.out.println("counting euklidean distances for vector: " + vector1.toString() + "...");
            for (int j = 0; j < trainingData.size(); j++) {
                Iris vector2 = trainingData.get(j);
                Double result = euklideanDistance(vector1, vector2);
                results.add(result);
            }

            List<Integer> lowestDistancesIndexes = findKLowestIndexes(results, k);
            System.out.println("found " + k + " closest neighbours: ");
            for (int j = 0; j < lowestDistancesIndexes.size(); j++) {
                System.out.println("training data vector " + lowestDistancesIndexes.get(j) + " = " + trainingData.get(lowestDistancesIndexes.get(j)).toString());
            }

            String prediction = makePrediction(lowestDistancesIndexes, trainingData);
            System.out.println("true spec: " + vector1.getSpec() + "\t\t\t\tprediction: " + prediction);
            boolean isEqual = isEqual(vector1.getSpec(), prediction);
            if (isEqual) System.out.println("\t\t\t\t\t\tCORRECT PREDICTION!");
            else System.out.println("\t\t\t\t\t\tWRONG PREDICTION!");
            evaluate(isEqual);
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

    public Double calculateEvaluation() {
        evaluationResult = (positiveEvaluationAttemps / totalEvaluationAttemps);
        Double evaluationInPercent = evaluationResult * 100;
        return Math.floor(evaluationInPercent * 10) / 10;
    }

    public void evaluate(boolean isEqual) {
        if (isEqual) {
            positiveEvaluationAttemps++;
            totalEvaluationAttemps++;
        } else {
            totalEvaluationAttemps++;
        }
    }

    public boolean isEqual(String trueSpec, String prediction) {
        return trueSpec.equals(prediction);
    }

    public String makePrediction(List<Integer> lowestDistancesIndexes, List<Iris> trainingData) {
        List<String> species = new ArrayList<>();
        for (int i = 0; i < lowestDistancesIndexes.size(); i++) {
            int index = lowestDistancesIndexes.get(i);
            Iris iris = trainingData.get(index);
            String spec = iris.getSpec();
            species.add(spec);
        }

        String prediction = "";
        Map<String, Integer> frequency = new HashMap<>();
        for (String str : species) {
            frequency.put(str, frequency.getOrDefault(str, 0) + 1);
        }
        int maxFrequency = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                prediction = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return prediction;
    }

    public List<Integer> findKLowestIndexes(List<Double> results, int k) {
        List<Integer> resultIndexes = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int minIndex = -1;
            double minValue = Double.MAX_VALUE;

            for (int j = 0; j < results.size(); j++) {
                double value = results.get(j);
                if (!resultIndexes.contains(j) && value < minValue) {
                    minIndex = j;
                    minValue = value;
                }
            }

            if (minIndex != -1) {
                resultIndexes.add(minIndex);
            }
        }

        return resultIndexes;
    }

    public Double euklideanDistance(Iris v1, Iris v2) {
        List<Double> vector1 = v1.getAttributes();
        List<Double> vector2 = v2.getAttributes();
        Double result = 0.;
        Double a = 0.;
        Double b = 0.;
        Double tmp = 0.;
        for (int i = 0; i < vector1.size(); i++) {
            a = vector1.get(i);
            b = vector2.get(i);
            tmp += Math.pow((a - b), 2);
        }

        result = Math.sqrt(tmp);

        return result;
    }

    public void cleanCache() {
        results = new ArrayList<>();
        evaluationResult = 0.;
        positiveEvaluationAttemps = 0.;
        totalEvaluationAttemps = 0.;
    }
}
