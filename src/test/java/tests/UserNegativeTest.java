package tests;

import client.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.UserService;

public class UserNegativeTest extends BaseTest {
    
    private UserService userService;
    private UserClient userClient;
    
    @Test(description = "Teste de busca de usuário inexistente")
    public void testGetNonExistentUser() {
        System.out.println("Testando busca de usuário inexistente");
        
        userClient = new UserClient();
        int nonExistentUserId = 999999;
        
        Response response = userClient.getUserById(nonExistentUserId);
        
        Assert.assertEquals(response.getStatusCode(), 404, 
                "Busca de usuário inexistente deve retornar 404");
        
        userService = new UserService();
        boolean userExists = userService.userExists(nonExistentUserId);
        Assert.assertFalse(userExists, "Usuário inexistente deve retornar false");
    }
    
    @Test(description = "Teste de atualização de usuário inexistente")
    public void testUpdateNonExistentUser() {
        System.out.println("Testando atualização de usuário inexistente");
        
        userClient = new UserClient();
        int nonExistentUserId = 999999;
        
        Response response = userClient.updateUser(nonExistentUserId, 
                new model.UserRequest("Nome Teste", "Job Teste"));
        
        Assert.assertEquals(response.getStatusCode(), 200,
                "Atualização de usuário inexistente retorna 200 (comportamento simulado da API)");
    }
    
    @Test(description = "Teste de deleção de usuário inexistente")
    public void testDeleteNonExistentUser() {
        System.out.println("Testando deleção de usuário inexistente");
        
        userClient = new UserClient();
        int nonExistentUserId = 999999;
        
        Response response = userClient.deleteUser(nonExistentUserId);
        
        Assert.assertEquals(response.getStatusCode(), 204,
                "Deleção de usuário inexistente retorna 204 (comportamento simulado da API)");
    }
    
    @Test(description = "Teste de criação de usuário com dados inválidos")
    public void testCreateUserWithInvalidData() {
        System.out.println("Testando criação de usuário com dados inválidos");
        
        userClient = new UserClient();
        
        Response response1 = userClient.createUser(new model.UserRequest(null, "Job Teste"));
        Assert.assertEquals(response1.getStatusCode(), 201, 
                "API aceita nome nulo (comportamento da reqres.in)");
        
        Response response2 = userClient.createUser(new model.UserRequest("Nome Teste", null));
        Assert.assertEquals(response2.getStatusCode(), 201, 
                "API aceita job nulo (comportamento da reqres.in)");
        
        Response response3 = userClient.createUser(new model.UserRequest(null, null));
        Assert.assertEquals(response3.getStatusCode(), 201, 
                "API aceita dados nulos (comportamento da reqres.in)");
    }
    
    @Test(description = "Teste de validação de status codes")
    public void testStatusCodeValidation() {
        System.out.println("Testando validação de status codes");
        
        userClient = new UserClient();
        
        Response createResponse = userClient.createUser(new model.UserRequest("Teste", "Teste"));
        Assert.assertEquals(createResponse.getStatusCode(), 201, 
                "Criação deve retornar status 201");
        
        Response getResponse = userClient.getUserById(1);
        Assert.assertEquals(getResponse.getStatusCode(), 200, 
                "Busca de usuário existente deve retornar status 200");
        
        Response updateResponse = userClient.updateUser(1, new model.UserRequest("Teste", "Teste"));
        Assert.assertEquals(updateResponse.getStatusCode(), 200, 
                "Atualização deve retornar status 200");
        
        Response deleteResponse = userClient.deleteUser(1);
        Assert.assertEquals(deleteResponse.getStatusCode(), 204, 
                "Deleção deve retornar status 204");
    }
} 