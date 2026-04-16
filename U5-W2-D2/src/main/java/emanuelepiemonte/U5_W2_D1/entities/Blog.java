package emanuelepiemonte.U5_W2_D1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id_blog")
    private long idBlog;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Column(name = "cover_url")
    private String coverURL;
    private String content;
    @Column(name = "reading_time")
    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;

    public Blog(String category, String title, String coverURL, String content, double readingTime) {
        this.category = category;
        this.title = title;
        this.coverURL = coverURL;
        this.content = content;
        this.readingTime = readingTime;
        Random random = new Random();
        this.idBlog = random.nextInt(1, 10000);
    }
}
