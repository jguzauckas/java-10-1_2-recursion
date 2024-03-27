public class NotesBinarySearch1 {
    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(binarySearch(nums, 0, nums.length - 1, i));
        }
    }

    public static int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        if (startIndex <= endIndex) {
            int halfwayIndex = (endIndex + startIndex) / 2;
            if (target < nums[halfwayIndex]) {
                return binarySearch(nums, startIndex, halfwayIndex - 1, target);
            } else if (target > nums[halfwayIndex]) {
                return binarySearch(nums, halfwayIndex + 1, endIndex, target);
            }
            return halfwayIndex;
        }
        return -1;
    }
}