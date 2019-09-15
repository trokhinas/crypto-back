package vsu.labs.crypto.utils;

import org.modelmapper.ModelMapper;

public final class PropertyMapper {

    private PropertyMapper() { }

    public static <S, D>void map(S source, D destination) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(source, destination);
    }
}
