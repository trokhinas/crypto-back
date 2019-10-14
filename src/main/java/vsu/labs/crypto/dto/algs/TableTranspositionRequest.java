package vsu.labs.crypto.dto.algs;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TableTranspositionRequest {
    private String text;
    private BigInteger key;
}
