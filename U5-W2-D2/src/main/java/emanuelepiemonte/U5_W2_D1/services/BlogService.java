package emanuelepiemonte.U5_W2_D1.services;

import emanuelepiemonte.U5_W2_D1.entities.Blog;
import emanuelepiemonte.U5_W2_D1.exceptions.NotFoundException;
import emanuelepiemonte.U5_W2_D1.payloads.NewBlogsPayload;
import emanuelepiemonte.U5_W2_D1.repositories.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {
    // private List<Blog> blogsDB = new ArrayList<>();

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Page<Blog> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogRepository.findAll(pageable);
    }

    public Blog saveBlog(NewBlogsPayload body) {
        Blog newBlog = new Blog(body.getCategory(), body.getTitle(), body.getCoverURL(), body.getContent(), body.getReadingTime());
        Blog saveBlog = this.blogRepository.save(newBlog);
        log.info("Il blog " + newBlog.getIdBlog() + " è stato creato correttamente");
        return newBlog;
    }

    public Blog findById(long blogId) {
        return this.blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }

    public Blog findByIdAndUpdate(long blogId, NewBlogsPayload body) {
        Blog found = this.findById(blogId);

        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());

        Blog updateBlog = this.blogRepository.save(found);
        log.info("Il blog " + updateBlog.getIdBlog() + " è stato modificato correttamente");
        return updateBlog;
    }

    public void findByIdAndDelete(long blogId) {
        Blog found = this.findById(blogId);
        this.blogRepository.delete(found);
    }

}



