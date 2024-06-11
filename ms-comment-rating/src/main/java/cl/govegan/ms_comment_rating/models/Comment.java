package cl.govegan.ms_comment_rating.models;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generea setter getters
@AllArgsConstructor //contructor
@Builder//arquitectura de clases 
@NoArgsConstructor //const. sin argumentos 
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String recipeId;
    @NotEmpty
    private String username;
    private String content;
    private String creationDate; 
}

   

