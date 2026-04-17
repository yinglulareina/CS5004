package problem1;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Identifies trending topics on a social media platform by counting
 * the occurrences of each topic string in a given list.
 *
 * <p>This class uses functional programming elements — specifically
 * Java Streams and lambda expressions — to perform the counting.</p>
 *
 * @author Ying Lu
 */
public class TrendingTopics {

    /**
     * Counts the number of occurrences of every distinct String in the
     * input list and returns the result as a Map.
     *
     * <p>Each distinct String in the input list becomes a key in the
     * returned Map, and its associated value is the number of times
     * that String appears in the list.</p>
     *
     * <p>For example, given:
     * {@code ["Seattle", "NEU", "Seattle", "NEU", "NEU"]}
     * the method returns:
     * {@code {Seattle=2, NEU=3}}</p>
     *
     * <p>Implementation uses {@link java.util.stream.Collectors#groupingBy}
     * with {@link java.util.stream.Collectors#counting} to count occurrences
     * in a single stream pipeline, then casts the Long counts to Integer.</p>
     *
     * @param topics a list of topic Strings; must not be {@code null}
     * @return a {@code Map<String, Integer>} mapping each distinct topic
     *         to its occurrence count
     * @throws IllegalArgumentException if {@code topics} is {@code null}
     */
    public Map<String, Integer> countTopics(List<String> topics) {
        if (topics == null)
            throw new IllegalArgumentException("Topics list must not be null.");

        return topics.stream()
                // group identical strings together and count each group
                .collect(Collectors.groupingBy(
                        Function.identity(),          // key   = the string itself
                        Collectors.collectingAndThen(
                                Collectors.counting(),    // count occurrences as Long
                                Long::intValue            // convert Long → Integer
                        )
                ));
    }

    // ── Demo ─────────────────────────────────────────────────────────────────

    /**
     * Demonstrates {@link #countTopics(List)} with the example from the lab.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<String> topics = List.of(
                "Seattle", "wildfires", "DEFCON26", "NEU", "NEU",
                "Seattle", "Seattle", "NEU", "DEFCON26", "wildfires"
        );

        TrendingTopics tt = new TrendingTopics();
        Map<String, Integer> counts = tt.countTopics(topics);

        System.out.println("Topic counts:");
        counts.forEach((topic, count) ->
                System.out.println("  " + topic + " → " + count));
    }
}