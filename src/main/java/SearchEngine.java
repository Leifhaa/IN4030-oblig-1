import java.util.Random;

public class SearchEngine {

    private int n;

    public SearchEngine(int n) {
        this.n = n;
        generateSearchResults();
    }

    public int getN() {
        return n;
    }

    private int[] searchResults;

    public int[] getSearchResults() {
        return searchResults;
    }

    public int getSearchResult(int index) {
        return searchResults[index];
    }

    public int[] getSearchResultsCopy() {
        return searchResults.clone();
    }

    public void sortSearchResults(int[] searchResults, int k) {
        /* We then insert-sort a[0..k-1] in descending order */
        Sorting.sortArrayDesc(searchResults, 0, k - 1);

        /* Compare lowest number with each element in the rest of the array*/
        Sorting.searchAndResort(0, k - 1, k, n, searchResults);
    }

    public void sortSearchResultsAsync(int[] searchResults, int k) {
        int numProcessors = Runtime.getRuntime().availableProcessors();
        ThreadWorker[] tw = new ThreadWorker[numProcessors];
        Thread[] t = new Thread[numProcessors];
        int arrayElements = n - k - 1;
        int elementsPerProcessor = arrayElements / numProcessors;

        //Create threads where all should do sorting for a given range of elements.
        int readFrom;
        int readTo;
        for (int i = 0; i < numProcessors; i++) {
            readFrom = elementsPerProcessor * i;
            readTo = readFrom + elementsPerProcessor;
            if (i == numProcessors - 1) {
                readTo = n;
            }
            tw[i] = new ThreadWorker(searchResults, k, readFrom, readTo);
            t[i] = new Thread(tw[i]);
            t[i].start();

        }

        //Wait for threads to finish
        for (int i = 0; i < numProcessors; i++) {
            try {
                t[i].join();
            } catch (Exception e) {
                System.out.println("Exception : " + e);
                return;
            }
        }

        //All threads completed. Merge results for each sorting. Starting from thread 1, not 0 as thread 0 already has sorted it's elements from 0 to k
        for (int i = 1; i < tw.length; i++) {
            Sorting.searchAndResort(0, k - 1, tw[i].getStartIdx(), tw[i].getStartIdx() + k, searchResults);
        }
    }

    public void generateSearchResults() {
        /*  to get the same 'random' numbers in the array when the Random class if you want to redo the run several times, the
        constructor of the Random class must get a starting number that is the same as previous run */
        Random r = new Random(7363);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = r.nextInt(n);
        }
        this.searchResults = res;
    }
}
