import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TimeUsage;

import java.util.Arrays;

public class Tests_Task2 {

    @Test
    public void TestCorrectSorting() {
        int k = 20;
        int nSearchResults = 100_000_000;
        SearchEngine se = new SearchEngine(nSearchResults);
        se.sortSearchResultsAsync(se.getSearchResults(), k);

        for (int i = k; i < nSearchResults; i++){
            if (se.getSearchResult(k - 1) >= se.getSearchResult(i)){

                System.out.println("HEERORAS");
            }
            Assertions.assertTrue(se.getSearchResult(k - 1) >= se.getSearchResult(i));
        }
    }


    @Test
    public void TestSpeed() {
        int totalRuns = 7;
        int nSearchResults[] = new int[]{1000, 100_00, 100_000, 100_000_0, 100_000_00, 100_000_000};
        TimeUsage[] timeUsages = new TimeUsage[nSearchResults.length];

        for (int i = 0; i < nSearchResults.length; i++){
            TimeUsage timeUsage = recordTimeUsages(nSearchResults[i], totalRuns);
            timeUsages[i] = timeUsage;
        }
        for (int i = 0; i < timeUsages.length; i++){
            System.out.println("Results for array size " + nSearchResults[i] + ": " + timeUsages[i]);
        }
    }

    private TimeUsage recordTimeUsages(int n, int totalRuns){
        SearchEngine se = new SearchEngine(n);
        TimeUsage timeUsage = new TimeUsage();

        /* Retrieve runtime of A2 using K20 */
        int[] copy = se.getSearchResultsCopy();
        timeUsage.setA2_K20(getMedianSortTimeA2(se, copy, 20, totalRuns));

        /* Retrieve runtime of A2 using K100 */
        copy = se.getSearchResultsCopy();
        timeUsage.setA2_K100(getMedianSortTimeA2(se, copy, 100, totalRuns));

        return timeUsage;
    }


    private double getMedianSortTimeA2(SearchEngine se, int[] searchResults, int k, int totalRuns) {
        double[] times = new double[totalRuns];
        for (int i = 0; i < totalRuns; i++) {
            long time = System.nanoTime();
            se.sortSearchResultsAsync(searchResults, k);
            times[i] = (System.nanoTime() - time) / 1000000.0;
        }
        return times[(times.length) / 2];
    }
}
