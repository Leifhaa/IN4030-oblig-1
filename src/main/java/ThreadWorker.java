public class ThreadWorker implements Runnable {

    private final int[] searchResults;
    private final int k;
    private final int startIdx;
    private final int endIdx;

    public ThreadWorker(int[] searchResults, int k, int startIdx, int endIdx) {
        this.searchResults = searchResults;
        this.k = startIdx + k;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public int getK() {
        return k;
    }

    @Override
    public void run() {
            Sorting.sortArrayDesc(searchResults, startIdx, k);

        /* Compare lowest number with each element in the rest of the array*/
            Sorting.searchAndResort(startIdx, k - 1, k, endIdx, searchResults);

    }

}
