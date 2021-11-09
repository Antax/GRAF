package main.m1graf2021;

public class Pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    /**
     * Creates a pair with two elements of any type
     *
     * @param first the first element of the pair
     * @param second the second element of the pair
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Sets the value of the first element
     *
     * @param first the future value of the first element
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Sets the value of the second element
     *
     * @param second the future value of the second element
     */
    public void setSecond(S second) {
        this.second = second;
    }

    /**
     * Returns the value of the first element
     *
     * @return the value of the first element
     */
    public F getFirst() {
        return first;
    }

    /**
     * Returns the value of the second element
     *
     * @return the value of the second element
     */
    public S getSecond() {
        return second;
    }
}
