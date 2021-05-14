package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.levelboudary.LevelBoundary;

public abstract class TableCreator {

    protected static final String HORIZONTAL_BORDER_PART = "═";
    protected static final String VERTICAL_BORDER = "║";
    protected static final String TABLE_EMPTY_SPACE = " ";
    protected static final String NEXT_LINE = "\n";
    protected static final int FIRST_DATA_SET = 0;
    private static final int ONE_COLUMN = 1;
    protected static final int EVEN_LENGTH_DIVIDER = 2;
    protected static final int HALF_SIZE_DIVIDER = 2;
    protected static final int LEFT_PADDING = 2;
    protected static final int INDENT_ONE = 1;
    protected static final int INDENT_TWO = 2;

    int columnsCount;
    int columnsSize;
    abstract String create();

    String createHorizontalRowBorder(LevelBoundary levelBoundary) {
        StringBuilder rowBorder = new StringBuilder(levelBoundary.getLeftBoundary());
        String middleBorderPart = duplicateSymbolForGivenTimes(HORIZONTAL_BORDER_PART, columnsSize) + levelBoundary.getMiddleBoundary();
        if (columnsCount > 1) {
            rowBorder.append(duplicateSymbolForGivenTimes(middleBorderPart, columnsCount - ONE_COLUMN));
        }
        rowBorder
                .append(duplicateSymbolForGivenTimes(HORIZONTAL_BORDER_PART, columnsSize))
                .append(levelBoundary.getRightBoundary())
                .append(NEXT_LINE);
        return rowBorder.toString();
    }

    String duplicateSymbolForGivenTimes(String symbol, int times) {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < times; i++) {
            border.append(symbol);
        }
        return border.toString();
    }
}
