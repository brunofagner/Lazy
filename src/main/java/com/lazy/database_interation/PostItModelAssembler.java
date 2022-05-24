package com.lazy.database_interation;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.lang.reflect.Method;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostItModelAssembler implements RepresentationModelAssembler<PostIt, EntityModel<PostIt>>{

      @Override
      public EntityModel<PostIt> toModel(PostIt postIt) {
            
            return EntityModel.of(postIt,
                  linkTo(methodOn(PostItController.class).one(postIt.getId())).withSelfRel(),
                  linkTo(methodOn(PostItController.class).all()).withRel("tarefas"));
      }

      
}
