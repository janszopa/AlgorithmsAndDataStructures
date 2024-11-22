package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
       validateParams(nums);

       int n = nums.length;

       int minValId = 0;

       for(int i = 0; i < n - 1; i++){ //dokończyć
           minValId = i;
<<<<<<< HEAD
           for(int j = i + 1; j < n; j++){
=======
           for(int j = i +  1; j < n; j++){
>>>>>>> b9c147a (sortowanie lab1)
               if(nums[j] < nums[minValId]){
                   minValId = j;
               }
           }
<<<<<<< HEAD
           swap(nums, minValId, i);
=======
>>>>>>> b9c147a (sortowanie lab1)
       }
    }

    private void validateParams (double[] nums) {
        if(nums == null){
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

    private void swap(double[] nums, int firstId, int secondId){
        if(firstId != secondId){
            double firstVal = nums[firstId];
<<<<<<< HEAD
            nums[firstId] = nums[secondId];
=======
            nums[firstId] = nums[firstId];
>>>>>>> b9c147a (sortowanie lab1)
            nums[secondId] = firstVal;
        }
    }

}
