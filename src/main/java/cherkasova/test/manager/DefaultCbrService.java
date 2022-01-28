package cherkasova.test.manager;

import cherkasova.test.model.Error;
import cherkasova.test.model.ValCurs;
import cherkasova.test.model.Valute;
import cherkasova.test.util.Either;
import cherkasova.test.util.XMLConverter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultCbrService implements CbrService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCbrService.class);

    private final XMLConverter xmlConverter;

    @Autowired
    public DefaultCbrService() {
        this.xmlConverter = new XMLConverter();
    }

    @Override
    public Either<ValCurs, Error>  getRates(Date date) {
        String dateStr = CBR_DATE_FORMAT.format(date);
        return getRates(dateStr);
    }

    @Override
    public Either<ValCurs, Error> getRates(String date) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URI url;
        try {
            url = new URI("https://www.cbr.ru/scripts/XML_daily_eng.asp?date_req=" + date);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
            return Either.right(Error.builder().code(500).message("Internal Server Error").build());
        }

        HttpGet request = new HttpGet(url);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Either.right(Error.builder().code(500).message("Internal Server Error").build());
        }

        HttpEntity entity = response.getEntity();
        if (entity == null) return Either.right(Error.builder().code(404).message("Not Found").build());

        InputStream content;
        try {
            content = entity.getContent();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Either.right(Error.builder().code(500).message("Internal Server Error").build());
        }

        String data = new BufferedReader(
                new InputStreamReader(content, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        return Either.left(xmlConverter.convertXMLValCurs(data));
    }

    @Override
    public Either<Valute, Error> getRateByCode(String code, Date date) {
        Either<ValCurs, Error> either = getRates(date);

        if(either.isRight()) return Either.right(either.getRight().get());

        List<Valute> valutes = either.getLeft()
                .get()
                .getValutes();

        if(valutes == null || valutes.size() <= 0) return Either.right(Error.builder().code(404).message("Response was empty.").build());

        Valute valute = valutes.stream()
                .filter(v -> StringUtils.equals(v.getCharCode(), code))
                .findFirst()
                .orElse(Valute
                        .builder()
                        .build());
        valute.setDate(either.getLeft().get().getDate());
        return Either.left(valute);
    }
}
