package emanuelepiemonte.U5_W2_D1.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewAuthorPayload {
    private String name;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private String avatar;
}
