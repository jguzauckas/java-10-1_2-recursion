public class NotesTraverse1 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5};
        System.out.println(sum(nums, 0));   
    }

    public static int sum(int[] nums, int index) {
        int sum = nums[index];
        if (index < nums.length - 1) {
            sum += sum(nums, index + 1);
        }
        return sum;
    }
}
