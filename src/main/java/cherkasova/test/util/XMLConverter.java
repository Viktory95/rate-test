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
    private static JAXBContext jaxbContext;
    private static Unmarshaller jaxbUnmarshaller;

    public XMLConverter() {
        try {
            jaxbContext = JAXBContext.newInstance(ValCurs.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public ValCurs convertXMLValCurs(String data) {
        ValCurs valCurs = null;
        try {
            valCurs = (ValCurs) jaxbUnmarshaller.unmarshal(new StringReader(data));
        } catch (JAXBException e) {
            valCurs.setError(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return valCurs;
    }
}
