package emanuelepiemonte.U5_W2_D1.controllers;


import emanuelepiemonte.U5_W2_D1.entities.Blog;
import emanuelepiemonte.U5_W2_D1.payloads.NewBlogsPayload;
import emanuelepiemonte.U5_W2_D1.services.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public Page<Blog> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "surname") String sortBy) {
        return this.blogService.findAll(page, size, sortBy);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Blog createBlog(@RequestBody NewBlogsPayload body) {
        return this.blogService.saveBlog(body);
    }

    @GetMapping("/{blogId}")
    public Blog getAuthorById(@PathVariable long blogId) {
        return this.blogService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    public Blog getBlogByIdAndUpdate(@PathVariable long blogId, @RequestBody NewBlogsPayload body) {
        return this.blogService.findByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void getBlogByIdAndDelete(@PathVariable long blogId) {
        this.blogService.findByIdAndDelete(blogId);
    }
}
