package io.github.bogdanadrian11.aoc;

public interface Challenge<T, U> {
    void solve(String inputPath);
    U partA(T input);
    U partB(T input);
}
