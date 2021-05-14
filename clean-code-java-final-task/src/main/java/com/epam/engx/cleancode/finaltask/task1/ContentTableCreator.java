package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.levelboudary.LevelBoundary;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContentTableCreator extends TableCreator {

    private List<DataSet> tableData;

    public ContentTableCreator(List<DataSet> tableData) {
        this.tableData = tableData;
        setup();
    }

    private void setup(){
        columnsCount = countColumns(tableData);
        int cellTextSize = calculateCellTextLength(tableData);
        columnsSize = calculateColumnSize(cellTextSize);
    }

    @Override
    String create() {
        return createTableHeader(tableData) + createTableBody(tableData);
    }

    private int calculateColumnSize(int cellTextSize) {
        return isEvenNumber(cellTextSize)
                ? cellTextSize + LEFT_PADDING
                : cellTextSize + LEFT_PADDING + INDENT_ONE;
    }

    private int calculateCellTextLength(List<DataSet> tableData) {
        int columnNameLength = calculateColumnLength(tableData);
        int columnValueLength = calculateColumnValueLength(tableData);
        return getMaxNumber(columnNameLength, columnValueLength);
    }

    private int getMaxNumber(int columnNameLength, int columnValueLength){
        return (columnNameLength > columnValueLength) ? columnNameLength : columnValueLength;
    }

    private int calculateColumnValueLength(List<DataSet> tableData) {
        int maxValueLength = 0;
        for (DataSet dataSet : tableData) {
            List<Object> values = dataSet.getValues();
            List<String> texts = convertObjectsIntoStrings(values);
            int valueTextSize = findTheLongestColumnLength(texts);
            if (valueTextSize > maxValueLength) {
                maxValueLength = valueTextSize;
            }
        }
        return maxValueLength;
    }

    private int calculateColumnLength(List<DataSet> tableData) {
        List<String> columnNames = tableData.get(FIRST_DATA_SET).getColumnNames();
        return findTheLongestColumnLength(columnNames);
    }

    private int findTheLongestColumnLength(List<String> columnNames) {
       return columnNames.stream().map(String::length)
               .max(Comparator.comparingInt(Integer::intValue)).orElse(0);
    }

    private int countColumns(List<DataSet> tableData){
        return retrieveColumnNamesFromDataSet(tableData).size();
    }

    private String createTableHeader(List<DataSet> tableData) {
        StringBuilder header = new StringBuilder();
        header.append(createHorizontalRowBorder(LevelBoundary.TABLE_UPPER));
        List<String> columnNames = retrieveColumnNamesFromDataSet(tableData);
        header.append(retrieveColumnsWithCellValues(columnNames));
        header.append(VERTICAL_BORDER).append(NEXT_LINE).
                append(createHorizontalRowBorder(LevelBoundary.TABLE_MIDDLE));
        return header.toString();
    }

    private String retrieveColumnsWithCellValues(List<String> columnNames){
        StringBuilder header = new StringBuilder();
        for (int col = 0; col < columnsCount; col++) {
            header.append(VERTICAL_BORDER).append(createTextWithCellIndex(col,columnNames));
        }
        return header.toString();
    }

    private String createTableBody(List<DataSet> tableData) {
        int rowsCount = tableData.size();
        StringBuilder body = new StringBuilder();
        for (int row = 0; row < rowsCount; row++) {
            body.append(createTableBodyWithCellValues(row,rowsCount));
        }
        body.append(createHorizontalRowBorder(LevelBoundary.TABLE_BOTTOM));
        return body.toString();
    }

    private String createTableBodyWithCellValues(int row, int rowsCount){
        StringBuilder body = new StringBuilder();
        List<Object> values = tableData.get(row).getValues();
        body.append(VERTICAL_BORDER);
        for (int c = 0; c < columnsCount; c++) {
            body.append(createTextWithCellIndex(c, convertObjectsIntoStrings(values)))
                    .append(VERTICAL_BORDER);
        }
        body.append(NEXT_LINE);
        if (row < rowsCount - 1) {
            body.append(createHorizontalRowBorder(LevelBoundary.TABLE_MIDDLE));
        }
        return body.toString();
    }

    private String createTextWithCellIndex(int cellTextIndex, List<String> rowText) {
        StringBuilder textWithPosition = new StringBuilder();
        int cellTextLength = retrieveCellText(cellTextIndex, rowText).length();
        textWithPosition.append(createPosition(columnsSize, cellTextLength))
                .append(retrieveCellText(cellTextIndex, rowText))
                .append(createPositionAfterText(columnsSize, cellTextLength));
        return textWithPosition.toString();
    }

    private List<String> convertObjectsIntoStrings(List<Object> objects) {
        return objects.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> retrieveColumnNamesFromDataSet(List<DataSet> dataSets){
        return dataSets.get(FIRST_DATA_SET).getColumnNames();
    }

    private String retrieveCellText(int cellTextIndex, List<String> rowText) {
        return rowText.get(cellTextIndex);
    }

    private String createPosition(int columnSize, int textLength) {
        return duplicateSymbolForGivenTimes(TABLE_EMPTY_SPACE, (columnSize - textLength) / HALF_SIZE_DIVIDER);
    }

    private String createPositionAfterText(int columnSize, int textLength) {
        StringBuilder position = new StringBuilder();
        if (isEvenNumber(textLength)) {
            position.append(createPosition(columnSize, textLength));
        } else {
            position.append(duplicateSymbolForGivenTimes(TABLE_EMPTY_SPACE,
                    ((columnSize - textLength) / HALF_SIZE_DIVIDER) + INDENT_ONE));
        }
        return position.toString();
    }

    private boolean isEvenNumber(int number) {
        return number % EVEN_LENGTH_DIVIDER == 0;
    }
}
