package com.epam.engx.cleancode.finaltask.task1.levelboudary;

public enum LevelBoundary {

    TABLE_UPPER("╔","╦","╗"),
    TABLE_BOTTOM("╚","╩","╝"),
    TABLE_MIDDLE("╠","╬","╣");

    private final String leftBoundary;
    private final String middleBoundary;
    private final String rightBoundary;

    public String getLeftBoundary() {
        return leftBoundary;
    }

    public String getMiddleBoundary() {
        return middleBoundary;
    }

    public String getRightBoundary() {
        return rightBoundary;
    }

    LevelBoundary(String leftBoundary,String middleBoundary,String rightBoundary) {
        this.leftBoundary = leftBoundary;
        this.middleBoundary = middleBoundary;
        this.rightBoundary = rightBoundary;
    }
}


