package cherkasova.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Error {
    @XmlElement
    int code;
    @XmlElement String message;
}
