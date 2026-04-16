package emanuelepiemonte.U5_W2_D1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id_author")
    private UUID idAuthor;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "avatar_url")
    private String avatarURL;


    public Author(String name, String lastname, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatarURL = "https://ui-avatars.com/api/?name=" + name + "+" + lastname;
    }
}


