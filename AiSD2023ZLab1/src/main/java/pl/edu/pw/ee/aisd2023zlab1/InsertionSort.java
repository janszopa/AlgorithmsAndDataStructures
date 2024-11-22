package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        isInputNull(nums);

        int arrSize = nums.length;

        for (int i = 1; i < arrSize; i++) {
            int j = i - 1;
            double key = nums[i];

            while (j >= 0 && key < nums[j]) {
                nums[j + 1] = nums[j];
                nums[j] = key;
                j--;
            }
        }
    }

    private void isInputNull (double[] nums) {
        if(nums == null){
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

}
