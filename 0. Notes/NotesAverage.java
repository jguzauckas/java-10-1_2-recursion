public class NotesAverage {
    public static double average(int[] nums) {
        int sum = sum(nums);
        return (double) sum / nums.length;
    }

    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}