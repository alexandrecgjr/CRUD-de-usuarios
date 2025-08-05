package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("job")
    private String job;
    
    public UserRequest() {}
    
    public UserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getJob() {
        return job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    
    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
} 