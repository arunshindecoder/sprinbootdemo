package org.arun.spring.sprinbootdemo.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private UserJPARepository userJPARepository;
    private PostJPARepository postRepository;

    public UserJPAResource(UserJPARepository userJPARepository, PostJPARepository postRepository) {
        this.userJPARepository = userJPARepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<UserJPA> retrieveAllUsers() {
        return userJPARepository.findAll();
    }


    //http://localhost:8080/users

    //EntityModel
    //WebMvcLinkBuilder

    @GetMapping("/jpa/users/{id}")
    public EntityModel<UserJPA> retrieveUser(@PathVariable int id) {
        Optional<UserJPA> user = userJPARepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        EntityModel<UserJPA> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @GetMapping("/jpa/users/justuser/{id}")
    public ResponseEntity<UserJPA> retrieveOnlyUser(@PathVariable int id) {
        Optional<UserJPA> user = userJPARepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

//        ResponseEntity<UserJPA> userJPAResponseEntity = ResponseEntity.of(user);
//        return userJPAResponseEntity;
        //Arun the above commented and below is same.
        return ResponseEntity.status(HttpStatus.OK).body(user.get());

    }

    @GetMapping("/jpa/users/justuserone/{id}")
    public UserJPA retrieveOnlyUserReturnUser(@PathVariable int id) {
        Optional<UserJPA> user = userJPARepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        return user.get();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userJPARepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<UserJPA> createUser(@Valid @RequestBody UserJPA user) {

        UserJPA savedUser = userJPARepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<UserJPA> user = userJPARepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        return user.get().getPosts();

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,
                                                    @Valid @RequestBody Post post) {
        Optional<UserJPA> user = userJPARepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
        //Arun this adds a location header to the response headers.

    }

}