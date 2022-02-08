public class SorterThread implements Runnable {

    private final int[] searchResults;
    private final int k;
    private final int startIdx;
    private final int endIdx;

    public SorterThread(int[] searchResults, int k, int startIdx, int nRead) {
        this.searchResults = searchResults;
        this.k = startIdx + k;
        this.startIdx = startIdx;
        if (searchResults.length < nRead) {
            endIdx = searchResults.length;
        } else {
            endIdx = startIdx + nRead;
        }
    }

    @Override
    public void run() {
        Sorting.sortArrayDesc(searchResults, startIdx, endIdx - 1);

        /* Compare lowest number with each element in the rest of the array*/
        Sorting.searchAndResort(startIdx, k - 1, k, endIdx, searchResults);

    }



}
