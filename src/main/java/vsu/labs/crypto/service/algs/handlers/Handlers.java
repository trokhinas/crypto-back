package vsu.labs.crypto.service.algs.handlers;

import org.springframework.util.StringUtils;
import vsu.labs.crypto.exceptions.LogicException;

public abstract class Handlers {

    public static Handler NOT_NULL_HANDLER = block -> {
        if (block == null)
            throw new IllegalStateException("block must not be null");
    };
    public static Handler NOT_EMPTY_VALUE = block -> {
        if (StringUtils.isEmpty(block.getValue())) {
            throw new LogicException("Не заполнено поле " + block.getName());
        }
    };
}
