import org.junit.runner.manipulation.Sorter;

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

    public int[] getSearchResultsCopy() {
        return searchResults.clone();
    }

    public void sortSearchResults(int[] searchResults, int k){
        /* We then insert-sort a[0..k-1] in descending order */
        Sorting.sortArrayDesc(searchResults, 0, k - 1);

        /* Compare lowest number with each element in the rest of the array*/
        for (int i = k; i < n - 1; i++){
            if (searchResults[i] > searchResults[k - 1]){
                /* Found a number which is higher than our lowest */
                int tmp = searchResults[k - 1];
                searchResults[k - 1] = searchResults[i];
                searchResults[i] = tmp;

                /* Sort again */
                Sorting.sortElementDesc(searchResults, k - 1);
            }
        }
    }

    public void generateSearchResults() {
        /*  to get the same 'random' numbers in the array when the Random class if you want to redo the run several times, the
        constructor of the Random class must get a starting number that is the same as previous run */
        Random r = new Random(7363);

        int[] res = new int[n];
        for (int i = 0; i < n; i++){
            res[i] = r.nextInt(n);
        }
        this.searchResults = res;
    }
}