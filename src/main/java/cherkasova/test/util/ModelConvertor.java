package cherkasova.test.util;

import cherkasova.test.manager.CbrService;
import cherkasova.test.model.Rate;
import cherkasova.test.model.Valute;

import java.text.ParseException;
import java.util.Date;

public class ModelConvertor {
    public Rate convertValuteModelToRateModel(Valute valute) {
        Rate rate = Rate
                .builder()
                .rate(valute.getCalculatedValue())
                .code(valute.getCharCode())
                .build();
        try {
            rate.setDate(CbrService.INPUT_OUTPUT_DATE_FORMAT.format(CbrService.XML_DATE_FORMAT.parse(valute.getDate())));
        } catch (ParseException e) {
            rate.setDate(CbrService.INPUT_OUTPUT_DATE_FORMAT.format(new Date()));
        }
        return rate;
    }
}
