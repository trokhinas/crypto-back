package vsu.labs.crypto.algs.encryption.transposition;

import vsu.labs.crypto.algs.encryption.transposition.functions.TableAction;
import vsu.labs.crypto.algs.encryption.transposition.functions.TableStepper;
import vsu.labs.crypto.algs.encryption.transposition.utils.OrderedColumnsListBuilder;
import vsu.labs.crypto.algs.encryption.transposition.utils.StringWithCounter;
import vsu.labs.crypto.exceptions.LogicException;

import java.math.BigInteger;
import java.util.List;

import static vsu.labs.crypto.algs.encryption.transposition.functions.TableStepper.leftToRightStepper;

final class Table {
    private final String[][] table;
    private final int rowCount;
    private final int columnCount;
    private final int size;

    private final List<Integer> orderedColumnsList;

    private static final String EMPTY_CELL = "";
    static final String SPACING_SYMBOL = "_";

    private Table(BigInteger size, BigInteger key) {
        this.size = size.intValue();
        this.orderedColumnsList = OrderedColumnsListBuilder.buildList(key);
        table = initTable(orderedColumnsList.size());

        this.rowCount = table.length;
        this.columnCount = orderedColumnsList.size();
        clear();
    }

    static Table withSize(BigInteger size, BigInteger key) {
        if (key.toString().length() < 2)
            throw new LogicException("Ключ должен содержать как минимум 2 цифры!");
        return new Table(size, key);
    }

    String encrypt(String message) {
        inputLeftToRight(message);
        return readUpToDown();
    }

    String decrypt(String message) {
        inputUpToDown(message);
        return readLeftToRight();
    }

    void clear() {
        for (String[] row : table) {
            for (int i = 0; i < row.length; i++) {
                row[i] = EMPTY_CELL;
            }
        }
    }

    private void inputLeftToRight(String message) {
        TableAction inputAction = createLeftToRightInputAction(message);
        leftToRightStepper.run(table, inputAction);
    }

    private void inputUpToDown(String message) {
        TableStepper stepper = createColumnUpToDownStepper();
        TableAction inputAction = createUpToDownInputAction(message);

        stepper.run(table, inputAction);
    }

    private String readUpToDown() {
        TableStepper stepper = createColumnUpToDownStepper();
        StringBuilder sb = new StringBuilder();
        TableAction readAction = createReadAction(sb);

        stepper.run(table, readAction);
        return sb.toString();
    }

    private String readLeftToRight() {
        StringBuilder sb = new StringBuilder();
        TableAction readAction = createReadAction(sb);
        leftToRightStepper.run(table, readAction);
        return sb.toString();
    }

    private TableAction createLeftToRightInputAction(String message) {
        StringWithCounter stringWithCounter = StringWithCounter.withString(message);
        return (table, rowIndex, columnIndex) -> {
            if (!isDisabledCell(rowIndex, columnIndex))
                table[rowIndex][columnIndex] += stringWithCounter.getOne();
        };
    }

    private TableAction createUpToDownInputAction(String message) {
        StringWithCounter stringWithCounter = StringWithCounter.withString(message);
        return (table, rowIndex, columnIndex) -> {
            if (!isDisabledCell(rowIndex, columnIndex))
                table[rowIndex][columnIndex] += stringWithCounter.getOne();
        };
    }

    private TableAction createReadAction(StringBuilder stringBuilder) {
        return (table, rowIndex, columnIndex) -> {
            String symbol = table[rowIndex][columnIndex];
            if (!isDisabledCell(rowIndex, columnIndex))
                stringBuilder.append(symbol);
        };
    }

    private TableStepper createColumnUpToDownStepper() {
        return (table, action) -> {
            for (int i = 0; i < orderedColumnsList.size(); i++) {
                int columnIndex = orderedColumnsList.get(i);
                for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                    action.act(table, rowIndex, columnIndex);
                }
            }
        };
    }

    private String[][] initTable(Integer columnsCount) {
        int fullRowsCount = size / columnsCount;
        if (size % columnsCount != 0)
            fullRowsCount ++;

        return new String[fullRowsCount][columnsCount];
    }

    private boolean isDisabledCell(int rowIndex, int columnIndex) {
        int lastRowIndex = rowCount - 1;

        if (rowIndex == lastRowIndex) {
            int lastAccessedColumnIndex = size % columnCount - 1;
            return columnIndex > lastAccessedColumnIndex;
        }
        return false;
    }
}
