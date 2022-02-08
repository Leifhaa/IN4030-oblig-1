public class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        SearchEngine se = new SearchEngine(n);

        se.sortSearchResults(se.getSearchResults(), k);

        int[] sortedResults = se.getSearchResults();

        for (int i = 0; i < sortedResults.length; i++){
            System.out.println(sortedResults[i]);
        }
    }
}
