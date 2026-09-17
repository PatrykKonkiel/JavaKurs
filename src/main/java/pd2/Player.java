package pd2;

public class Player implements Comparable<Player> {
    private String name;
    private int[] score;

    public Player(String name, int[] score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns player name
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates player total score
     *
     * @return total score
     */
    public int sum() {
        int sum = 0;
        for (int scores : score) {
            sum += scores;
        }
        return sum;
    }

    /**
     * Calculates player average score
     *
     * @return average score
     */
    public double avg() {
        return (double) sum() / score.length;
    }

    /**
     * Finds minimal player score
     *
     * @return minimal player score
     */
    public int min() {
        int min = score[0];
        for (int scores : score) {
            if (scores < min) {
                min = scores;
            }
        }
        return min;
    }

    /**
     * Finds maximal player score
     *
     * @return maximal player score
     */
    public int max() {
        int max = score[0];
        for (int scores : score) {
            if (scores > max) {
                max = scores;
            }
        }
        return max;
    }

    /**
     * Print information about users name, total score, average score, minimal score, maximal score
     */

    public void printInfo() {
        System.out.print("Imię: " + getName() + " Suma: " + sum() + " Średnia: " +
                String.format("%.2f", avg()) + " Minimalna: " + min() + " Maksymalna: " + max());
    }

    /**
     * Compares this player with another player based on total score
     *
     * @param o the object to be compared.
     * @return a negative integer, zero or a positive integer if this player
     * has greater, equal or smaller total score
     */
    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.sum(), this.sum());
    }
}
