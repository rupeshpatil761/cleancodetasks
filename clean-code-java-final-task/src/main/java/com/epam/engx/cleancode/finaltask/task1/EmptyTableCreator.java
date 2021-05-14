package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.levelboudary.LevelBoundary;

public class EmptyTableCreator extends  TableCreator {

    private static final String EMPTY_TABLE_TEXT = VERTICAL_BORDER
            + " Table '%s' is empty or does not exist " + VERTICAL_BORDER;
    public static final int SINGLE_COLUMN = 1;

    private String emptyTableText;

    @Override
    String create() {
        return createHorizontalRowBorder(LevelBoundary.TABLE_UPPER)
                + emptyTableText + NEXT_LINE
                + createHorizontalRowBorder(LevelBoundary.TABLE_BOTTOM);
    }

    public EmptyTableCreator(String tableName) {
        this.emptyTableText = createEmptyTableText(tableName);
        columnsCount = SINGLE_COLUMN;
        columnsSize = calculateColumnsSize();
    }

    private int calculateColumnsSize() {
        return emptyTableText.length() - INDENT_TWO;
    }

    private String createEmptyTableText(String tableName) {
        return String.format(EMPTY_TABLE_TEXT, tableName);
    }
}
