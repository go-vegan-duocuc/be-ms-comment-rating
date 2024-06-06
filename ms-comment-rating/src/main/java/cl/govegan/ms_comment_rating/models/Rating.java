package cl.govegan.ms_comment_rating.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ratings")
public class Rating {

   @Id
   private String id;
   private String recipeId;
   private String username;
   private int rating;
   private String createdAt;
}
