package vsu.labs.crypto.algs.encryption.transposition;

import vsu.labs.crypto.algs.encryption.transposition.functions.TableAction;

import java.math.BigInteger;

import static vsu.labs.crypto.algs.encryption.transposition.functions.TableStepper.leftToRightStepper;
import static vsu.labs.crypto.algs.encryption.transposition.functions.TableStepper.upToDownStepper;

class Table {
    private final String[][] table;
    private final int size;
    private static final String EMPTY_CELL = "";
    static final String SPACING_SYMBOL = "_";

    private Table(BigInteger size) {
        this.size = size.intValue();
        table = new String[this.size][this.size];
        clear();
    }

    static Table withSize(BigInteger size) {
        return new Table(size);
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
        TableAction inputAction = createInputAction(message);
        leftToRightStepper.run(table, inputAction);
    }

    private void inputUpToDown(String message) {
        TableAction inputAction = createInputAction(message);
        upToDownStepper.run(table, inputAction);
    }

    private String readUpToDown() {
        StringBuilder sb = new StringBuilder();
        TableAction readAction = createReadAction(sb);
        upToDownStepper.run(table, readAction);
        return sb.toString();
    }

    private String readLeftToRight() {
        StringBuilder sb = new StringBuilder();
        TableAction readAction = createReadAction(sb);
        leftToRightStepper.run(table, readAction);
        return sb.toString();
    }

    private TableAction createInputAction(String message) {
        return (table, rowIndex, columnIndex) -> {
            int index = rowIndex * size + columnIndex;
            var symbol = index >= message.length() ? SPACING_SYMBOL : message.charAt(index);
            table[rowIndex][columnIndex] += symbol;
        };
    }

    private TableAction createReadAction(StringBuilder stringBuilder) {
        return (table, rowIndex, columnIndex) -> {
            String symbol = table[rowIndex][columnIndex];
            stringBuilder.append(symbol);
        };
    }
}
