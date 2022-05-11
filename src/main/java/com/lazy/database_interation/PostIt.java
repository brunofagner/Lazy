package com.lazy.database_interation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PostIt {
      private @Id @GeneratedValue long id;
      private String title;
      private String description;
      private String finalData;

      //Contructors
      PostIt(){};

      PostIt(String title, String description, String finalData){
            this.title = title;
            this.description = description;
            this.finalData = finalData;
      }

      //Getters and Setters
      public long getId() {
            return id;
      }
      public void setId(long id) {
            this.id = id;
      }
      public String getTitle() {
            return title;
      }
      public void setTitle(String title) {
            this.title = title;
      }

      public String getDescription() {
            return description;
      }
      public void setDescription(String description) {
            this.description = description;
      }

      public String getFinalData() {
            return finalData;
      }
      public void setFinalData(String finalData) {
            this.finalData = finalData;
      }

      @Override
      public boolean equals(Object o){
            if(this == o) return true;
            
            else if(!(o instanceof PostIt)) return false;
            
            PostIt employee = (PostIt) o;
            return Objects.equals(this.id, employee.id) && Objects.equals(this.title, employee.title) && 
                   Objects.equals(this.description, employee.description) && Objects.equals(this.finalData, employee.finalData);

      }

      @Override
      public int hashCode(){
            return Objects.hash(this.id, this.title, this.description, this.finalData);
      }

      @Override
      public String toString(){
            return "Post-it - { Id: "+ this.id +
                   ", title: "+ this.title +
                   ", Descrição: "+ this.description + 
                   ", Data final: "+ this.finalData +
                   " }";
      }

}
