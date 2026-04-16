package emanuelepiemonte.U5_W2_D1.controllers;

import emanuelepiemonte.U5_W2_D1.entities.Author;
import emanuelepiemonte.U5_W2_D1.exceptions.ValidationException;
import emanuelepiemonte.U5_W2_D1.payloads.AuthorDTO;
import emanuelepiemonte.U5_W2_D1.payloads.NewAuthorRespDTO;
import emanuelepiemonte.U5_W2_D1.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Page<Author> getUsers(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "name") String sortBy) {

        return this.authorService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public NewAuthorRespDTO createAuthor(@RequestBody @Validated AuthorDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }
        Author newAuthor = this.authorService.saveAuthor(body);
        return new NewAuthorRespDTO(newAuthor.getIdAuthor());
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable UUID authorId) {
        return this.authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author getAuthorByIdAndUpdate(@PathVariable UUID authorId, @RequestBody AuthorDTO body) {
        return this.authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void getAuthorByIdAndDelete(@PathVariable UUID authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }

}
