public class NotesPrint1 {
    public static void printFromNto1(int n) {
        System.out.println(n);
        if (n > 1) {
            printFromNto1(n - 1);
        }
    }
}
