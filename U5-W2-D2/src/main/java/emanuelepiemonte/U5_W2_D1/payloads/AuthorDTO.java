package emanuelepiemonte.U5_W2_D1.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Il nome proprio è obbligatorio e non può essere una stringa vuota")
        @Size(min = 2, max = 30, message = "Il nome proprio deve essere compreso tra i 2 e i 30 caratteri")
        String name,
        @NotBlank(message = "Il cognome è obbligatorio e non può essere una stringa vuota")
        @Size(min = 2, max = 30, message = "Il nome proprio deve essere compreso tra i 2 e i 30 caratteri")
        String lastname,
        @NotBlank(message = "L'email è obbligatorio e non può essere una stringa vuota")
        @Email(message = "L'email inserita non è del formato corretto")
        String email,
        @Past
        LocalDate dateOfBirth,
        String avatar) {
}

//@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z](?=.*[a-zA-Z]).{8,}$", message = "La password deve contenere almeno una maiuscola, una minuscola....")
//String password
