package max.dev.portfolioapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "projects")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String additionalInfo;
    @Column(columnDefinition = "TEXT")
    private String githubUrl;
    @Column(columnDefinition = "TEXT")
    private String projectUrl;
    @Column(columnDefinition = "TEXT")
    private String imgUrl;
    @Column(columnDefinition = "TEXT")
    private String imgUrl2;


}
