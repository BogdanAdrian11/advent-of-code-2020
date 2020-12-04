package io.github.bogdanadrian11.aoc.day04;

public enum EyeColor {
    AMB("amb"),
    BLU("blu"),
    BRN("brn"),
    GRY("gry"),
    GRN("grn"),
    HZL("hzl"),
    OTH("oth");

    private final String color;

    EyeColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
