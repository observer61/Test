package m.c.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ToString
@Getter
@Entity
public class CreditCard {
    // TODO test the annotations somehow??

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull(message = "Please provide a cardLimit")
    @DecimalMin(value = "0.0", message = "Please provide a non-negative cardLimit")
    private BigDecimal cardLimit;

    @NotNull(message = "Please provide a cardNumber")
    @Size(max = 19, message = "cardNumber length should be at most 19 digits")
    @Luhn10Constraint(message = "cardNumber failed Luhn10Check ")
    @Column(unique = true)
    private String cardNumber;

    @NotEmpty(message = "Please provide a name")
    private String name;

}
