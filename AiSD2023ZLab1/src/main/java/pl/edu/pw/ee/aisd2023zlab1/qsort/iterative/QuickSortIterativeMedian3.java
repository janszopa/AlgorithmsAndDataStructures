package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSortIterativeMedian3 implements Sorting {

    @Override
    public void sort(double[] nums) {
            if (nums == null) {
                throw new IllegalArgumentException("Input args (nums) cannot be null!");
            }

            quicksortMedian(nums);
    }

        private void quicksortMedian(double[] data) {
            List<Integer> starts = new ArrayList<>();
            List<Integer> ends = new ArrayList<>();

            Integer left = 0;
            Integer right = data.length - 1;

            starts.add(left);
            ends.add(right);

            int n = 1;
            int pivot;

            if (left < right) {

                while (n > 0) {
                    n--;
                    left = starts.remove(n);
                    right = ends.remove(n);
                    pivot = partition(data, left, right);

                    if (pivot > left) {
                        starts.add(left);
                        ends.add(pivot);
                        n++;
                    }

                    if (pivot + 1 < right) {
                        starts.add(pivot + 1);
                        ends.add(right);
                        n++;
                    }
                }
            }
        }

    private int partition(double[] nums, int start, int end) {
        int medianIndex = median(nums, start, end);
        swap(nums, start, medianIndex);
        double pivot = nums[medianIndex];


        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (nums[++left] < pivot) {
            }

            while (nums[--right] > pivot) {
            }

            if (left < right) {
                swap(nums, left, right);
            } else {
                break;
            }

        }

        return right;
    }

        private void swap(double[] data, int firstId, int secondId) {
            if (firstId != secondId) {
                double firstValue = data[firstId];
                data[firstId] = data[secondId];
                data[secondId] = firstValue;
            }
        }

        private int median(double[] nums, int left, int right){
            double[] temp = new double[3];

            int medianIndex = 0;

            temp[0] = nums[left];
            temp[2] = nums[right];

            Random random = new Random();
            int randomIndex = random.nextInt( right - left + 1) + left; //nie chcemy wylosować pierwszego i ostatniego

            temp[1] = nums[randomIndex];

            double[] temp2 = temp.clone();
            Arrays.sort(temp);

                if(temp[1] == temp2[0]){
                    medianIndex = left;
                } else if (temp[1] == temp2[1]) {
                    medianIndex = randomIndex;
                } else {
                    medianIndex = right;
                }

            return medianIndex;
        }

}
