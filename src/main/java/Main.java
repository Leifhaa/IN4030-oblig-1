public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);


        SearchEngine se = new SearchEngine(n);

        se.sortSearchResults(se.getSearchResultsCopy(), k);
    }
}
