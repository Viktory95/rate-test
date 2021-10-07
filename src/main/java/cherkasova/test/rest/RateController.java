package cherkasova.test.rest;

import cherkasova.test.manager.CbrService;
import cherkasova.test.model.Error;
import cherkasova.test.model.Valute;
import cherkasova.test.util.Either;
import cherkasova.test.util.ModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateController.class);
    private final CbrService cbrService;
    private final ModelConvertor modelConvertor;

    @Autowired
    public RateController(CbrService cbrService) {
        this.cbrService = cbrService;
        this.modelConvertor = new ModelConvertor();
    }

    @RequestMapping(value = {"/rate/{code}", "/rate/{code}/{date}"}, method = RequestMethod.GET)
    public ResponseEntity getRate(@PathVariable String code, @PathVariable Optional<String> date) {
        Date newDate;
        if(date.isPresent()) {
            try {
                newDate = CbrService.INPUT_OUTPUT_DATE_FORMAT.parse(date.get());
            } catch (ParseException e) {
                LOGGER.error(e.getMessage());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("BAD REQUEST. Please use 3 signs for code parameter and \"yyyy-MM-dd\" format for date parameter.");
            }
        } else {
            newDate = new Date();
        }

        Either<Valute, Error> either = cbrService.getRateByCode(code, newDate);
        if (either.isRight())
            return ResponseEntity
                    .status(either.getRight().get().getCode())
                    .body(either.getRight().get().getMessage());

        return ResponseEntity.ok().body(modelConvertor.convertValuteModelToRateModel(either.getLeft().get()));
    }

}
