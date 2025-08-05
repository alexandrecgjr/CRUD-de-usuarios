package service;

import client.UserClient;
import io.restassured.response.Response;
import model.User;
import model.UserRequest;
import model.UserResponse;

public class UserService {
    
    private UserClient userClient;
    
    public UserService() {
        this.userClient = new UserClient();
    }
    
    public Integer createUserAndGetId(String name, String job) {
        System.out.println("Criando usuário: " + name + " - " + job);
        
        UserRequest userRequest = new UserRequest(name, job);
        Response response = userClient.createUser(userRequest);
        
        if (response.getStatusCode() == 201) {
            User createdUser = response.as(User.class);
            System.out.println("Usuário criado com sucesso. ID: " + createdUser.getId());
            return createdUser.getId();
        } else {
            System.out.println("Erro ao criar usuário. Status: " + response.getStatusCode());
            throw new RuntimeException("Falha ao criar usuário. Status: " + response.getStatusCode());
        }
    }
    
    public UserResponse getUserById(int userId) {
        System.out.println("Buscando usuário com ID: " + userId);
        
        Response response = userClient.getUserById(userId);
        
        if (response.getStatusCode() == 200) {
            UserResponse userResponse = response.as(UserResponse.class);
            System.out.println("Usuário encontrado: " + userResponse);
            return userResponse;
        } else {
            System.out.println("Erro ao buscar usuário. Status: " + response.getStatusCode());
            throw new RuntimeException("Falha ao buscar usuário. Status: " + response.getStatusCode());
        }
    }
    
    public User updateUserJob(int userId, String newJob) {
        System.out.println("Atualizando job do usuário " + userId + " para: " + newJob);
        
        UserRequest updateRequest = new UserRequest("Usuário Teste", newJob);
        
        Response response = userClient.updateUser(userId, updateRequest);
        
        if (response.getStatusCode() == 200) {
            User updatedUser = response.as(User.class);
            System.out.println("Usuário atualizado com sucesso: " + updatedUser);
            return updatedUser;
        } else {
            System.out.println("Erro ao atualizar usuário. Status: " + response.getStatusCode());
            throw new RuntimeException("Falha ao atualizar usuário. Status: " + response.getStatusCode());
        }
    }
    
    public boolean deleteUser(int userId) {
        System.out.println("Deletando usuário com ID: " + userId);
        
        Response response = userClient.deleteUser(userId);
        
        if (response.getStatusCode() == 204) {
            System.out.println("Usuário deletado com sucesso");
            return true;
        } else {
            System.out.println("Erro ao deletar usuário. Status: " + response.getStatusCode());
            throw new RuntimeException("Falha ao deletar usuário. Status: " + response.getStatusCode());
        }
    }
    
    public boolean userExists(int userId) {
        System.out.println("Verificando se usuário com ID " + userId + " existe");
        
        Response response = userClient.getUserById(userId);
        boolean exists = response.getStatusCode() != 404;
        
        System.out.println("Usuário " + userId + " existe: " + exists);
        return exists;
    }
} 