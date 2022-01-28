package cherkasova.test.model;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
@ToString
@XmlRootElement(name = "Valute")
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
    String calculatedValue;

    public void setDate(String date) {
        this.date = date;
    }

    public String getCalculatedValue() {
        if (StringUtils.isNotBlank(value) && nominal != null)
            return Objects.toString(new BigDecimal(StringUtils.replace(value, ",", ".")).divide(BigDecimal.valueOf(nominal), 2));
        else
            return StringUtils.EMPTY;
    }
}
