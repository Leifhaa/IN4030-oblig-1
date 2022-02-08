public class Sorting {

    /**
     * This sorts an array in ascending order with the insertion algorithm
     * */
    public static void sortArrayDesc(int [] array, int sortFromIndex, int sortToIndex) {
        int i, tmp;
        for (int j = sortFromIndex; j < sortToIndex; j++) {
            tmp = array[j + 1];
            i = j;
            while (i >= sortFromIndex && array[i] < tmp) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = tmp;
        }
    }


    /**
     * Sorts element in descending order in array by using insertion sort
     * @param array The array which the element should be sorted in
     * @param index The current index of the element that should be sorted
     */
    public static void sortElementDesc(int [] array, int index) {
        int elementValue = array[index];
        for (int i = index; i > 0; i--){
            if (elementValue > array[i - 1]){
                int tmp = array[i - 1];
                array [i - 1] = array[i];
                array[i] = tmp;
            }
            else{
                break;
            }
        }
    }

}
