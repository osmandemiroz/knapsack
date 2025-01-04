package edu.estu;

public record Item(int id, long profit, long weight) {

    double unitValue() {
        return (double) profit / weight;
    }
}
