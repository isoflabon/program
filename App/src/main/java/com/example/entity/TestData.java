package com.example.entity;
 
import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity(name="item_master")
public class TestData {
 
    @Id
    private Long id;
    private Long value;
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Long getValue() {
        return value;
    }
 
    public void setValue(Long value) {
        this.value = value;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
}