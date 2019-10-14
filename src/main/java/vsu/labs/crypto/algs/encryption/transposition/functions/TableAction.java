package vsu.labs.crypto.algs.encryption.transposition.functions;

@FunctionalInterface
public interface TableAction {
    void act(String[][] table, int rowIndex, int columnIndex);
}