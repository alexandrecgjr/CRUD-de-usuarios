package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    
    @JsonProperty("data")
    private UserData data;
    
    @JsonProperty("support")
    private Support support;
    
    public UserResponse() {}
    
    public UserData getData() {
        return data;
    }
    
    public void setData(UserData data) {
        this.data = data;
    }
    
    public Support getSupport() {
        return support;
    }
    
    public void setSupport(Support support) {
        this.support = support;
    }
    
    public static class UserData {
        @JsonProperty("id")
        private Integer id;
        
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("first_name")
        private String firstName;
        
        @JsonProperty("last_name")
        private String lastName;
        
        @JsonProperty("avatar")
        private String avatar;
        
        public UserData() {}
        
        public Integer getId() {
            return id;
        }
        
        public void setId(Integer id) {
            this.id = id;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getFirstName() {
            return firstName;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        
        public String getAvatar() {
            return avatar;
        }
        
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
        
        @Override
        public String toString() {
            return "UserData{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }
    
    public static class Support {
        @JsonProperty("url")
        private String url;
        
        @JsonProperty("text")
        private String text;
        
        public Support() {}
        
        public String getUrl() {
            return url;
        }
        
        public void setUrl(String url) {
            this.url = url;
        }
        
        public String getText() {
            return text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        @Override
        public String toString() {
            return "Support{" +
                    "url='" + url + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
    
    @Override
    public String toString() {
        return "UserResponse{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
} 