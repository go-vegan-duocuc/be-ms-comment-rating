package cl.govegan.ms_comment_rating.models;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String recipeId;
    @NotNull
    private String username;
    private String content;
    private String creationDate; 
}

   

