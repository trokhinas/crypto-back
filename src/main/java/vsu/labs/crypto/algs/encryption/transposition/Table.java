package vsu.labs.crypto.algs.encryption.transposition;

import vsu.labs.crypto.algs.encryption.transposition.functions.TableAction;
import vsu.labs.crypto.algs.encryption.transposition.functions.TableStepper;
import vsu.labs.crypto.algs.encryption.transposition.utils.OrderedColumnsListBuilder;
import vsu.labs.crypto.algs.encryption.transposition.utils.StringWithCounter;
import vsu.labs.crypto.exceptions.LogicException;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.TooLongKeyException;

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
        if (key.toString().length() > 10)
            throw new TooLongKeyException();
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

    String getColumn(int index) {
        StringBuilder sb = new StringBuilder();
        TableAction readAction = (table, rowIndex, columnIndex) -> {
            if (columnIndex == index && !isDisabledCell(rowIndex, columnIndex))
                sb.append(table[rowIndex][columnIndex]);
        };
        leftToRightStepper.run(table, readAction);
        return sb.toString();
    }

    String getRow(int index) {
        StringBuilder sb = new StringBuilder();
        TableAction readAction = (table, rowIndex, columnIndex) -> {
            if (rowIndex == index && !isDisabledCell(rowIndex, columnIndex))
                sb.append(table[rowIndex][columnIndex]);
        };
        leftToRightStepper.run(table, readAction);
        return sb.toString();
    }

    void clear() {
        for (String[] row : table) {
            for (int i = 0; i < row.length; i++) {
                row[i] = EMPTY_CELL;
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getSize() {
        return size;
    }

    private void inputLeftToRight(String message) {
        TableAction inputAction = createInputAction(message);
        leftToRightStepper.run(table, inputAction);
    }

    private void inputUpToDown(String message) {
        TableStepper stepper = createColumnUpToDownStepper();
        TableAction inputAction = createInputAction(message);

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

    private TableAction createInputAction(String message) {
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
            return lastAccessedColumnIndex != -1 && columnIndex > lastAccessedColumnIndex;
        }
        return false;
    }
}
