public class NotesMerge1 {
    public static void main(String[] args) {
        int[] arr = new int[] {4, 6, 2, 3, 4, 5, 1, 2, 5};
        int[] sorted = mergeSort(arr, 0, arr.length - 1);
        for (int num : sorted) {
            System.out.print(num + " ");
        }
    }

    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int resultIndex = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (resultIndex < result.length) {
            if (aIndex == a.length) {
                result[resultIndex] = b[bIndex];
                bIndex++;
            } else if (bIndex == b.length) {
                result[resultIndex] = a[aIndex];
                aIndex++;
            } else if (a[aIndex] <= b[bIndex]) {
                result[resultIndex] = a[aIndex];
                aIndex++;
            } else {
                result[resultIndex] = b[bIndex];
                bIndex++;
            }
            resultIndex++;
        }
        return result;
    }

    public static int[] mergeSort(int[] arr, int from, int to) {
        if (from < to) {
            int middle = (from + to) / 2;
            int[] lower = mergeSort(arr, from, middle);
            int[] upper = mergeSort(arr, middle + 1, to);
            return merge(lower, upper);
        }
        return new int[] {arr[from]};
    }
}
