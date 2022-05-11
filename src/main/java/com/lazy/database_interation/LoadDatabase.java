package com.lazy.database_interation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
      private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

      @Bean
      CommandLineRunner initDatabase(PostItRepository repository){

            return args ->
            {
                  log.info("Preloading: "+ repository.save(new PostIt("Fazer Tarefa",
                                                                        "Fazer lista de quimica quantica",
                                                                        "10/10/2022")));

                  log.info("Preloading: "+ repository.save(new PostIt("Fazer Tarefa",
                                                                        "Fazer lista de quimica quantica",
                                                                        "10/11/2022")));
            };
      }

}