import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Tests_Task1 {

    @Test
    void testAscendingSort() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        Sorting.sortArrayDesc(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] >= arr[i + 1]);
        }
    }

    @Test
    void testAscendingSortRandom() {
        SearchEngine se = new SearchEngine(100);
        int[] results = se.getSearchResults();

        Sorting.sortArrayDesc(results, 0, results.length - 1);

        for (int i = 0; i < results.length - 1; i++) {
            Assertions.assertTrue(results[i] >= results[i + 1]);
        }
    }

    @Test
    void testSortingElement() {
        int[] arr = new int[]{6, 5, 4, 3, 2, 4};
        Sorting.sortElementDesc(arr, 5);

        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] >= arr[i + 1]);
        }
    }

    @Test
    void testSortingElementLastEqualToFirst() {
        int[] arr = new int[]{100, 5, 4, 3, 2, 100};
        Sorting.sortElementDesc(arr, 5);

        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] >= arr[i + 1]);
        }
    }

    @Test
    void testSortingElementLastHigherThanFirst() {
        int[] arr = new int[]{100, 5, 4, 3, 2, 101};
        Sorting.sortElementDesc(arr, 5);

        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] >= arr[i + 1]);
        }
    }


    @Test
    void testSortingComparedToArraySort_n1000_k20() {
        int k = 20;
        int n = 1000;

        SearchEngine se = new SearchEngine(n);
        int[] results = se.getSearchResults();
        int[] copy = se.getSearchResultsCopy();
        se.sortSearchResults(results, k);
        Arrays.sort(copy);
        compareAnswers(k, n, results, copy);

    }

    private void compareAnswers(int k, int n, int[] results, int[] copy) {
        //Compare answers to Array.sort
        for (int i = 0; i < k; i++) {
            int arraySortValue = copy[n - i - 1];
            int searchEngineSortValue = results[i];
            Assertions.assertEquals(arraySortValue, searchEngineSortValue);
        }
    }

    @Test
    void testSortingComparedToArraySort_n1000_k100() {
        int k = 100;
        int n = 1000;

        SearchEngine se = new SearchEngine(n);
        se.generateSearchResults();
        int[] results = se.getSearchResults();
        int[] copy = se.getSearchResultsCopy();
        se.sortSearchResults(results, k);
        Arrays.sort(copy);

        //Compare answers to Array.sort
        compareAnswers(k, n, results, copy);
    }


    @Test
    void testSortingComparedToArraySort_n10000_k20() {
        int k = 20;
        int n = 100_00;

        SearchEngine se = new SearchEngine(n);
        int[] copy1 = se.getSearchResultsCopy();
        int[] copy2 = se.getSearchResultsCopy();
        se.sortSearchResults(copy1, k);
        Arrays.sort(copy2);

        //Compare answers to Array.sort
        compareAnswers(k, n, copy1, copy2);
    }

    @Test
    void testSortingComparedToArraySort_n10000_k100() {
        int k = 100;
        int n = 100_00;

        SearchEngine se = new SearchEngine(n);
        int[] copy1 = se.getSearchResultsCopy();
        int[] copy2 = se.getSearchResultsCopy();
        se.sortSearchResults(copy1, k);
        Arrays.sort(copy2);

        //Compare answers to Array.sort
        compareAnswers(k, n, copy1, copy2);
    }


    @Test
    void testSortingComparedToArraySort_n100000000_k20() {
        int k = 20;
        int n = 100_000_000;

        SearchEngine se = new SearchEngine(n);
        int[] copy1 = se.getSearchResultsCopy();
        int[] copy2 = se.getSearchResultsCopy();
        se.sortSearchResults(copy1, k);
        Arrays.sort(copy2);

        //Compare answers to Array.sort
        compareAnswers(k, n, copy1, copy2);
    }

    @Test
    void testSortingComparedToArraySort_n100000000_k100() {
        int k = 100;
        int n = 100_000_000;

        SearchEngine se = new SearchEngine(n);
        int[] copy1 = se.getSearchResultsCopy();
        int[] copy2 = se.getSearchResultsCopy();
        se.sortSearchResults(copy1, k);
        Arrays.sort(copy2);

        //Compare answers to Array.sort
        compareAnswers(k, n, copy1, copy2);
    }



    @Test
    void testSpeedComparison() {
        int totalRuns = 7;
        int nSearchResults[] = new int[]{1000, 100_00, 100_000, 100_000_0, 100_000_00, 100_000_000};
        TimeUsage[] timeUsages = new TimeUsage[nSearchResults.length];

        for (int i = 0; i < nSearchResults.length; i++){
            TimeUsage timeUsage = recordTimeUsages(nSearchResults[i], totalRuns);
            timeUsages[i] = timeUsage;
        }

        for (int i = 0; i < timeUsages.length; i++){
            System.out.println(timeUsages[i]);
        }
    }


    private TimeUsage recordTimeUsages(int n, int totalRuns){
        SearchEngine se = new SearchEngine(n);
        TimeUsage timeUsage = new TimeUsage();

        /* Retrieve runtime of A1 */
        int[] copy = se.getSearchResultsCopy();
        timeUsage.setA1(getMedianTimeA1(copy, totalRuns));

        /* Retrieve runtime of A2 using K20 */
        copy = se.getSearchResultsCopy();
        timeUsage.setA2_K20(getMedianSortTimeA2(se, copy, 20, totalRuns));

        /* Retrieve runtime of A2 using K100 */
        copy = se.getSearchResultsCopy();
        timeUsage.setA2_K100(getMedianSortTimeA2(se, copy, 100, totalRuns));

        return timeUsage;
    }

    private double getMedianTimeA1(int[] searchResults, int totalRuns) {
        double[] times = new double[totalRuns];
        for (int i = 0; i < totalRuns; i++) {
            long time = System.nanoTime();
            Arrays.sort(searchResults);

            int[] reversed = new int[searchResults.length];
            int j = searchResults.length;
            for (int searchResult : searchResults) {
                reversed[j - 1] = searchResult;
                j = j - 1;
            }
            times[i] = (System.nanoTime() - time) / 1000000.0;
        }
        return times[(times.length) / 2];
    }

    private double getMedianSortTimeA2(SearchEngine se, int[] searchResults, int k, int totalRuns) {
        double[] times = new double[totalRuns];
        for (int i = 0; i < totalRuns; i++) {
            long time = System.nanoTime();
            se.sortSearchResults(searchResults, k);
            times[i] = (System.nanoTime() - time) / 1000000.0;
        }
        return times[(times.length) / 2];
    }

}
