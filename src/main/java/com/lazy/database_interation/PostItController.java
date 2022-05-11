package com.lazy.database_interation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import com.lazy.errors_handling.PostItNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostItController {
      private final PostItRepository repository;

      PostItController(PostItRepository repository) {
            this.repository = repository;
      }

      //Aggregate root
      //tag::get-aggregate-root[]
      @GetMapping("/tarefas")
      CollectionModel<EntityModel<PostIt>> all(){
            List<EntityModel<PostIt>> postIts = repository.findAll().stream()
                  .map(postIt -> EntityModel.of(postIt,
                  linkTo(methodOn(PostItController.class).one(postIt.getId())).withSelfRel(),
                  linkTo(methodOn(PostItController.class).all()).withRel("tarefas")))
                  .collect(Collectors.toList());
            
            return CollectionModel.of(postIts,
            linkTo(methodOn(PostItController.class).all()).withSelfRel());
      }
      //end::get-aggregate-root[]

      @PostMapping("/tarefas")
      PostIt newPostIt(@RequestBody PostIt newPostIt){
            return repository.save(newPostIt);
      }

      //Single item
      @GetMapping("/tarefas/{id}")
      EntityModel<PostIt> one(@PathVariable Long id) {

            PostIt postIt = repository.findById(id) //
                  .orElseThrow(() -> new PostItNotFoundException(id));

            return EntityModel.of(postIt, //
                  linkTo(methodOn(PostItController.class).one(id)).withSelfRel(),
                  linkTo(methodOn(PostItController.class).all()).withRel("tarefas"));
}

      @PutMapping("/tarefas/{id}")
      PostIt replacePostIt(@RequestBody PostIt newPostIt, @PathVariable Long id){
            return repository.findById(id).map(postIt ->{
                  postIt.setTitle(newPostIt.getTitle());
                  postIt.setDescription(newPostIt.getDescription());
                  postIt.setFinalData(newPostIt.getFinalData());
                  return repository.save(postIt);
            }).orElseGet(() -> {
                  newPostIt.setId(id);
                  return repository.save(newPostIt);
            });
      }

      @DeleteMapping("/tarefas/{id}")
      void deletePostIt(@PathVariable Long id){
            repository.deleteById(id);
      }

}
