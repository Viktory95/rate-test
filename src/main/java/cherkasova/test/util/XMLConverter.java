package cherkasova.test.util;

import cherkasova.test.model.ValCurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XMLConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLConverter.class);

    public ValCurs convertXMLValCurs(String data) {
        ValCurs valCurs = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            valCurs = (ValCurs) jaxbUnmarshaller.unmarshal(new StringReader(data));
        } catch (JAXBException e) {
            valCurs.setError(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return valCurs;
    }
}
