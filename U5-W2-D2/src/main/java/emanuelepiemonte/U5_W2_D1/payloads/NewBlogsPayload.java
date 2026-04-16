package emanuelepiemonte.U5_W2_D1.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewBlogsPayload {
    private String category;
    private String title;
    private String coverURL;
    private String content;
    private double readingTime;

}
