package emanuelepiemonte.U5_W2_D1.services;

import emanuelepiemonte.U5_W2_D1.entities.Author;
import emanuelepiemonte.U5_W2_D1.exceptions.BadRequestException;
import emanuelepiemonte.U5_W2_D1.exceptions.NotFoundExceptionUUID;
import emanuelepiemonte.U5_W2_D1.payloads.AuthorDTO;
import emanuelepiemonte.U5_W2_D1.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class AuthorService {
    //private List<Author> authorsDB = new ArrayList<>();
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorRepository.findAll(pageable);
    }

    public Author saveAuthor(AuthorDTO body) {
        if (this.authorRepository.existsByEmail(body.email()))
            throw new BadRequestException("L'indirizzo email " + body.email() + " è già in uso!");
        Author newAuthor = new Author(body.name(), body.lastname(), body.email(), body.dateOfBirth());
        //this.authorsDB.add(newAuthor);
        Author saveAuthor = this.authorRepository.save(newAuthor);
        log.info("L'autore " + saveAuthor.getIdAuthor() + " è stato creato correttamente");
        return saveAuthor;
    }

    public Author findById(UUID authorId) {
//        Author found = null;
//
//        for (Author author : authorsDB) {
//            if (author.getId() == authorId) found = author;
//        }
//        if (found == null) throw new NotFoundException(authorId);
//        return found;
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundExceptionUUID(authorId));
    }

    public Author findByIdAndUpdate(UUID authorId, AuthorDTO body) {
        Author found = this.findById(authorId);

        if (!found.getEmail().equals(body.email())) {
            if (this.authorRepository.existsByEmail(body.email()))
                throw new BadRequestException("L'inditizzo email " + body.email() + " è già in uso");
        }

        found.setName(body.name());
        found.setLastname(body.lastname());
        found.setEmail(body.email());
        found.setDateOfBirth(body.dateOfBirth());

        Author updateAuthor = this.authorRepository.save(found);
        log.info("L'utente " + updateAuthor.getIdAuthor() + " è stato modificato correttamente");
        return updateAuthor;
    }

    public void findByIdAndDelete(UUID authorId) {
        Author found = this.findById(authorId);
        this.authorRepository.delete(found);
    }
}




