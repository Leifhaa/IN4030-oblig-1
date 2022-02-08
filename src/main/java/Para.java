public class Para implements Runnable {

    private int[] searchResults;
    private int k;
    private int startIdx;
    private int endIdx;
    private int lastInserted = 0;

    public Para(int[] searchResults, int k, int startIdx, int nRead) {
        this.searchResults = searchResults;
        this.k = k;
        this.startIdx = startIdx;
        if (searchResults.length < nRead) {
            endIdx = searchResults.length;
        } else {
            endIdx = startIdx + nRead;
        }
    }

    @Override
    public void run() {
        for (int i = startIdx; i < endIdx; i++) {
            if (searchResults[k - 1] < searchResults[i]) {
                /**
                 * If the number is lower than the least significant search result (a[k-1]) we discard it without trying to achieve a locker
                 * If it's higher, we lock and attempt to update it
                 */
                tryUpdateFromIndex(i);
            }
        }
    }

    private synchronized void tryUpdateFromIndex(int index){
        if (searchResults[index] > searchResults[k - 1]){
            /* Found a number which is higher than our lowest */
            int tmp = searchResults[k - 1];
            searchResults[k - 1] = searchResults[index];
            searchResults[index] = tmp;

            /* Sort again */
            Sorting.sortElementDesc(searchResults, k - 1);
        }
    }
}
