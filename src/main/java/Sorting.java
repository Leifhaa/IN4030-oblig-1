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
     * Searches in a range of array for elements which is higher than the sortedFrom/sortedTo range. Adds such elements
     * to the sorted range and resorts it
     */
    public static void searchAndResort(int sortedFrom, int sortedTo, int readFrom, int readTo, int[] searchResults) {
        for (int i = readFrom; i < readTo - 1; i++) {
            if (searchResults[i] > searchResults[sortedTo]) {
                /* Found a number which is higher than our lowest */
                int tmp = searchResults[sortedTo];
                searchResults[sortedTo] = searchResults[i];
                searchResults[i] = tmp;

                /* Sort again */
                Sorting.sortElementDesc(sortedFrom, searchResults, sortedTo);
            }
        }
    }


    /**
     * Sorts element in descending order in array by using insertion sort
     * @param array The array which the element should be sorted in
     * @param index The current index of the element that should be sorted
     */
    public static void sortElementDesc(int sortedFrom, int[] array, int index) {
        int elementValue = array[index];
        for (int i = index; i > sortedFrom; i--){
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
