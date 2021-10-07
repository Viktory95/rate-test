package cherkasova.test.model;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
@ToString
@XmlRootElement(name="ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs {
    @XmlAttribute(name = "Date")
    String date;

    @XmlAttribute
    String name;

    @XmlElement(name = "Valute")
    List<Valute> valutes;

    String error;
}
