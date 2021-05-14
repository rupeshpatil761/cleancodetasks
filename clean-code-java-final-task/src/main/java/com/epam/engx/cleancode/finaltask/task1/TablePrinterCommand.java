package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DatabaseManager;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.View;

import java.util.List;

public class TablePrinterCommand implements Command {

    private static final String COMMAND_START_WITH = "print ";
    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_NAME = 1;
    private static final int MAX_COMMAND_SIZE = 2;

    private View view;
    private DatabaseManager databaseManager;

    public TablePrinterCommand(View view, DatabaseManager manager) {
        this.view = view;
        this.databaseManager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith(COMMAND_START_WITH);
    }

    @Override
    public void process(String command) {
        String tableName = retrieveTableNameFromCommand(command);
        String table = createTable(tableName);
        writeToView(table);
    }

    private void writeToView(String table){
        view.write(table);
    }

    private String retrieveTableNameFromCommand(String command) {
        String[] commandParameters = command.split(COMMAND_SPLITTER);
        verifyNumberOfParametersInCommand(commandParameters.length);
        return commandParameters[COMMAND_NAME];
    }

    private void verifyNumberOfParametersInCommand(int commandParametersNumber) {
        if (commandParametersNumber != MAX_COMMAND_SIZE) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (commandParametersNumber - 1));
        }
    }

    private String createTable(String tableName){
        List<DataSet> tableData = databaseManager.getTableData(tableName);
        return tableData.isEmpty()
                ? new EmptyTableCreator(tableName).create()
                : new ContentTableCreator(tableData).create();
    }
}
