package cherkasova.test.model;

import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
@ToString
@XmlRootElement(name="Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {
    @XmlAttribute(name = "ID")
    String id;
    @XmlElement(name = "NumCode")
    String numCode;
    @XmlElement(name = "CharCode")
    String charCode;
    @XmlElement(name = "Nominal")
    Long nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    String value;
    String date;
}
