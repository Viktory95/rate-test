package cherkasova.test.model;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Rate {
    @XmlElement String code;
    @XmlElement String rate;
    @XmlElement String date;
}
