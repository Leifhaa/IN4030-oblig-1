import java.util.Random;
import java.util.concurrent.CyclicBarrier;

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
        for (int i = k; i < n - 1; i++) {
            if (searchResults[i] > searchResults[k - 1]) {
                /* Found a number which is higher than our lowest */
                int tmp = searchResults[k - 1];
                searchResults[k - 1] = searchResults[i];
                searchResults[i] = tmp;

                /* Sort again */
                Sorting.sortElementDesc(searchResults, k - 1);
            }
        }
    }

    public void sortSearchResultsAsync(int[] searchResults, int k) {
        Sorting.sortArrayDesc(searchResults, 0, k - 1);

        int numProcessors = Runtime.getRuntime().availableProcessors();
        Thread[] t = new Thread[numProcessors];
        int arrayElements = n - k - 1;
        int elementsPerProcessor = arrayElements / numProcessors;




        for (int i = 0; i < numProcessors; i++) {
            t[i] = new Thread(new Para(searchResults, k, k + elementsPerProcessor * i, elementsPerProcessor));
            t[i].start();
        }

        for (int i = 0; i < numProcessors; i++) {
            try {
                t[i].join();
            } catch (Exception e) {
                System.out.println("Exception : " + e);
                return;
            }
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
