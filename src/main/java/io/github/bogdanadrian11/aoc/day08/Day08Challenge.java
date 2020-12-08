package io.github.bogdanadrian11.aoc.day08;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day08Challenge implements Challenge {
    @Override
    public void solve(String inputPath) {
        try {
            List<Operation> operations = readFileLines(inputPath).stream()
                    .map(line -> line.split(" "))
                    .map(parts -> new Operation(parts[0], Integer.valueOf(parts[1])))
                    .collect(Collectors.toList());
            System.out.println(partA(operations));
            System.out.println(partB(operations));
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    private Integer partA(List<Operation> operations) {
        return start(operations, new boolean[operations.size()], 0);
    }

    private Integer start(List<Operation> operations, boolean[] visited, Integer index) {
        if (visited[index]) {
            return 0;
        }
        visited[index] = true;
        return switch (operations.get(index).name()) {
            case "jmp" -> start(operations, visited, index + operations.get(index).value());
            case "acc" -> operations.get(index).value() + start(operations, visited, index + 1);
            default -> start(operations, visited, index + 1);
        };
    }

    private Integer partB(List<Operation> operations) {
        return startB(operations, new boolean[operations.size()], new boolean[operations.size()], 0);
    }

    private Integer startB(List<Operation> operations, boolean[] visited, boolean[] changed, Integer index) {
        if (index >= operations.size()) {
            return 0;
        }
        if (visited[index]) {
            visited[index] = false;
            return -1;
        }
        visited[index] = true;
        Operation operation = operations.get(index);
        switch (operation.name()) {
            case "jmp" -> {
                int acc = startB(operations, visited, changed, index + operation.value());
                if (acc == -1) {
                    visited[index] = false;
                    if (changed[index]) {
                        changed[index] = false;
                        operations.set(index, new Operation("nop", operation.value()));
                        return -1;
                    }
                    if (hasChanged(changed)) {
                        return -1;
                    }
                    operations.set(index, new Operation("nop", operation.value()));
                    changed[index] = true;
                    return startB(operations, visited, changed, index);
                }
                return acc;
            }
            case "acc" -> {
                int acc = startB(operations, visited, changed, index + 1);
                if (acc == -1) {
                    visited[index] = false;
                    return -1;
                }
                return operation.value() + acc;
            }
            default -> {
                int acc = startB(operations, visited, changed, index + 1);
                if (acc == -1) {
                    visited[index] = false;
                    if (changed[index]) {
                        changed[index] = false;
                        operations.set(index, new Operation("jmp", operation.value()));
                        return -1;
                    }
                    if (hasChanged(changed)) {
                        return -1;
                    }
                    operations.set(index, new Operation("jmp", operation.value()));
                    changed[index] = true;
                    return startB(operations, visited, changed, index);
                }
                return acc;

            }
        }
    }

    private boolean hasChanged(boolean[] changed) {
        return IntStream.range(0, changed.length)
                .mapToObj(idx -> changed[idx])
                .filter(Boolean.TRUE::equals)
                .findAny()
                .orElse(false);

    }

}