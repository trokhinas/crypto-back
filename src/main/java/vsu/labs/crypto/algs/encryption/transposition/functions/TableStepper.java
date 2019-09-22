package vsu.labs.crypto.algs.encryption.transposition.functions;

@FunctionalInterface
public interface TableStepper {
    void run(String[][] table, TableAction tableAction);

    TableStepper leftToRightStepper = (table, action) -> {
        for (int rowIndex = 0; rowIndex < table.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < table[rowIndex].length; columnIndex++) {
                action.act(table, rowIndex, columnIndex);
            }
        }
    };
    TableStepper upToDownStepper = (table, action) -> {
        for (int columnIndex = 0; columnIndex < table.length; columnIndex++) {
            for (int rowIndex = 0; rowIndex < table[columnIndex].length; rowIndex++) {
                action.act(table, rowIndex, columnIndex);
            }
        }
    };
}