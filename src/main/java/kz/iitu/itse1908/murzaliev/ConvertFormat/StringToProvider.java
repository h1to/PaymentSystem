package kz.iitu.itse1908.murzaliev.ConvertFormat;


import kz.iitu.itse1908.murzaliev.database.Provider;
import org.springframework.core.convert.converter.Converter;

public class StringToProvider implements Converter<String, Provider> {

    @Override
    public Provider convert(String source) {
        String[] attr = source.split(",");
        return new Provider(Long.valueOf(attr[0]), attr[1]);
    }
}
