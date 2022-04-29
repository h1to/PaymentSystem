package kz.iitu.itse1908.murzaliev.ConvertFormat;

import kz.iitu.itse1908.murzaliev.database.Provider;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProviderFormatter implements Formatter<Provider> {

    @Override
    public Provider parse(String s, Locale locale) throws ParseException {
        if (s != null) {
            String[] attr =s.split(",");
            if (attr.length == 2) {
                Provider provider = new Provider();
                provider.setProviderId(Long.valueOf(attr[0]));
                provider.setProviderName(attr[1]);
                return provider;
            }
        }
        return null;
    }

    @Override
    public String print(Provider provider, Locale locale) {
        if(provider == null) {
            return "";
        }
        else {
            return String.format(locale, "%s, %s", provider.getProviderId(), provider.getProviderName());
        }
    }
}
