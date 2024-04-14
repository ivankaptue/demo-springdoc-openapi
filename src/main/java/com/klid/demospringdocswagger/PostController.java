package com.klid.demospringdocswagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Operation(summary = "Get posts", description = "Load all posts from database and order by title asc", security = @SecurityRequirement(name = "bearer-key"))
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Post found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))),
    @ApiResponse(responseCode = "404", description = "Post not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    @ApiResponse(responseCode = "503", description = "Service unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping
  public ResponseEntity<Post> getPosts() {
    var post = new Post("Intelligence Artificielle", """
      L'intelligence artificielle (IA) est un processus d'imitation de l'intelligence humaine qui repose sur
      la création et l'application d'algorithmes exécutés dans un environnement informatique dynamique. 
      Son but est de permettre à des ordinateurs de penser et d'agir comme des êtres humains.
      """);
    return ResponseEntity.ok(post);
  }

  public record Post(String title, String body) {
  }
}
