package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterWithSolution {
    public RodCutterResult cutRod(int[] prices, int rodLength) {
        //validateInput(prices, rodLength);
        RodCutterResult rodCutterResult = new RodCutterResult(rodLength + 1, rodLength + 1);

        int result;

        for (int i = 1; i <= rodLength; i++) {
            result = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                result = Integer.max(result, prices[j - 1] + rodCutterResult.getMaxSumResults(i - j));
            }

            rodCutterResult.setMaxSumResults(i, result);
        }

        return rodCutterResult;
    }
}
