package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterResult {

    private int[] maxSumResults;
    private int[] solutions;

    public RodCutterResult(int maxSumResultsLength, int solutionsLength) {
        maxSumResults = new int[maxSumResultsLength];
        solutions = new int[solutionsLength];
    }

    public int getMaxSumResults(int id) {
        return maxSumResults[id];
    }

    public int[] getSolutions() {
        return solutions;
    }

    public void setMaxSumResults(int id, int value) {
        maxSumResults[id] = value;
    }

    public void setSolutions(int[] solutions) {
        this.solutions = solutions;
    }
}
