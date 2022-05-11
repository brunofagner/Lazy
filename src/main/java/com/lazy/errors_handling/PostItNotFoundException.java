package com.lazy.errors_handling;

public class PostItNotFoundException extends RuntimeException{
      public PostItNotFoundException(Long id){
            super("Could not find Post-It: " + id);
      }
}
