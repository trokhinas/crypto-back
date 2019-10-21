package vsu.labs.crypto.service.algs.handlers;

import org.springframework.util.StringUtils;
import vsu.labs.crypto.exceptions.LogicException;

import java.util.Arrays;
import java.util.List;

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
    public static List<Handler> DEFAULT_HANDLERS = Arrays.asList(
            NOT_NULL_HANDLER,
            NOT_EMPTY_VALUE
    );
}
