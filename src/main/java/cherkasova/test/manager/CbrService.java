package cherkasova.test.manager;

import cherkasova.test.model.Error;
import cherkasova.test.model.ValCurs;
import cherkasova.test.model.Valute;
import cherkasova.test.util.Either;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface CbrService {
    static final String CBR_DATE_PATTERN = "MM/dd/yyyy";
    static final SimpleDateFormat CBR_DATE_FORMAT = new SimpleDateFormat(CBR_DATE_PATTERN);
    static final String XML_DATE_PATTERN = "MM.dd.yyyy";
    static final SimpleDateFormat XML_DATE_FORMAT = new SimpleDateFormat(XML_DATE_PATTERN);
    static final String INPUT_OUTPUT_DATE_PATTERN = "yyyy-MM-dd";
    static final SimpleDateFormat INPUT_OUTPUT_DATE_FORMAT = new SimpleDateFormat(INPUT_OUTPUT_DATE_PATTERN);

    Either<ValCurs, Error> getRates(Date date);

    Either<ValCurs, Error> getRates(String date);

    Either<Valute, Error> getRateByCode(String code, Date date);
}
