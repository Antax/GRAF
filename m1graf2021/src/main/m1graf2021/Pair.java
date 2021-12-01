package main.m1graf2021;

public class Pair<T1, T2> {
    private T1 first; //first member of pair
    private T2 second; //second member of pair

    /**
     * Creates a pair with two elements of any type
     *
     * @param first the first element of the pair
     * @param second the second element of the pair
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Sets the value of the first element
     *
     * @param first the future value of the first element
     */
    public void setFirst(T1 first) {
        this.first = first;
    }

    /**
     * Sets the value of the second element
     *
     * @param second the future value of the second element
     */
    public void setSecond(T2 second) {
        this.second = second;
    }

    /**
     * Returns the value of the first element
     *
     * @return the value of the first element
     */
    public T1 getFirst() {
        return first;
    }

    /**
     * Returns the value of the second element
     *
     * @return the value of the second element
     */
    public T2 getSecond() {
        return second;
    }
}
