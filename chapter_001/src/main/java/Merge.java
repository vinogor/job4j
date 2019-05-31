
public class Merge {
    public int[] merge(int[] left, int[] right) {

        int lenLeft = left.length;
        int lenRight = right.length;
        int[] rsl = new int[lenLeft + lenRight];
        int indexLeft = 0;
        int indexRight = 0;

        for (int i = 0; i < lenLeft + lenRight; i++) {
            if ((indexLeft == lenLeft) && (indexRight == lenRight)) {
                return rsl;
            } else {
                if (indexLeft == lenLeft) {
                    rsl[i] = right[indexRight];
                    indexRight++;
                } else if (indexRight == lenRight) {
                    rsl[i] = left[indexLeft];
                    indexLeft++;
                } else {
                    if (left[indexLeft] < right[indexRight]) {
                        rsl[i] = left[indexLeft];
                        indexLeft++;
                    } else {
                        rsl[i] = right[indexRight];
                        indexRight++;
                    }
                }
            }
        }
        return rsl;
    }
}