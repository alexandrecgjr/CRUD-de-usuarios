package tests;

import client.UserClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import model.User;
import model.UserRequest;
import model.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.UserService;

@Epic("API de Usuários")
@Feature("Operações CRUD")
@Story("Gerenciamento completo de usuários")
public class UserCRUDTest extends BaseTest {
    
    private UserService userService;
    private UserClient userClient;
    private Integer createdUserId;
    private String originalJob;
    private String updatedJob;
    private String testVariable = "teste";
    
    @Test(description = "Teste completo CRUD: POST → GET → PUT → DELETE")
    @Description("Executa um ciclo completo de operações CRUD em um usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Ciclo completo CRUD")
    public void testCompleteUserCRUDCycle() {
        System.out.println("Iniciando teste do ciclo completo CRUD de usuários");
        saveTextLog("Iniciando teste do ciclo completo CRUD de usuários");
        
        userService = new UserService();
        userClient = new UserClient();
        
        String userName = "Ale Xandrinho";
        originalJob = "testador";
        updatedJob = "QA de automação";
        
        createUserStep(userName, originalJob);
        getUserStep();
        updateUserStep(updatedJob);
        deleteUserStep();
        verifyUserAfterDeleteStep();
        
        System.out.println("=== Ciclo CRUD completo executado com sucesso ===");
        saveTextLog("Ciclo CRUD completo executado com sucesso");
    }
    
    @Step("Criando usuário: {userName} com job: {job}")
    private void createUserStep(String userName, String job) {
        System.out.println("=== ETAPA 1: Criando usuário ===");
        createdUserId = userService.createUserAndGetId(userName, job);
        Assert.assertNotNull(createdUserId, "ID do usuário criado não deve ser nulo");
        System.out.println("Usuário criado com ID: " + createdUserId);
        saveTextLog("Usuário criado com ID: " + createdUserId);
    }
    
    @Step("Buscando dados do usuário criado")
    private void getUserStep() {
        System.out.println("=== ETAPA 2: Confirmando dados do usuário criado ===");
        UserResponse retrievedUser = userService.getUserById(1);
        
        Assert.assertNotNull(retrievedUser, "Resposta do usuário não deve ser nula");
        Assert.assertNotNull(retrievedUser.getData(), "Dados do usuário não devem ser nulos");
        Assert.assertNotNull(retrievedUser.getData().getId(), "ID do usuário não deve ser nulo");
        
        System.out.println("Dados do usuário confirmados: " + retrievedUser);
        saveTextLog("Dados do usuário confirmados: " + retrievedUser);
    }
    
    @Step("Atualizando job do usuário para: {newJob}")
    private void updateUserStep(String newJob) {
        System.out.println("=== ETAPA 3: Atualizando job do usuário ===");
        User updatedUser = userService.updateUserJob(1, newJob);
        
        Assert.assertNotNull(updatedUser, "Usuário atualizado não deve ser nulo");
        Assert.assertEquals(updatedUser.getJob(), newJob, "Job deve ser atualizado");
        Assert.assertNotNull(updatedUser.getUpdatedAt(), "Data de atualização não deve ser nula");
        
        System.out.println("Usuário atualizado com sucesso: " + updatedUser);
        saveTextLog("Usuário atualizado com sucesso: " + updatedUser);
    }
    
    @Step("Deletando usuário")
    private void deleteUserStep() {
        System.out.println("=== ETAPA 4: Deletando usuário ===");
        boolean deleteSuccess = userService.deleteUser(1);
        Assert.assertTrue(deleteSuccess, "Usuário deve ser deletado com sucesso");
        
        Response deleteResponse = userClient.deleteUser(1);
        Assert.assertEquals(deleteResponse.getStatusCode(), 204, 
                "Status code do DELETE deve ser 204");
        
        System.out.println("Usuário deletado com sucesso");
        saveTextLog("Usuário deletado com sucesso");
    }
    
    @Step("Verificando comportamento após deleção")
    private void verifyUserAfterDeleteStep() {
        System.out.println("Verificando comportamento da API após DELETE (simulado)");
        Response getAfterDeleteResponse = userClient.getUserById(1);
        Assert.assertEquals(getAfterDeleteResponse.getStatusCode(), 200, 
                "GET após DELETE retorna 200 (comportamento simulado da API)");
        
        boolean userExists = userService.userExists(1);
        Assert.assertTrue(userExists, "Usuário ainda existe após DELETE (simulado)");
        
        saveTextLog("Verificação pós-deleção concluída");
    }
    
    @Test(description = "Teste de criação de usuário")
    @Description("Testa a criação de um novo usuário via API")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Criação de usuário")
    public void testCreateUser() {
        System.out.println("Testando criação de usuário");
        saveTextLog("Testando criação de usuário");
        
        userClient = new UserClient();
        UserRequest userRequest = new UserRequest("Maria Santos", "Analista de QA");
        
        Response response = userClient.createUser(userRequest);
        
        Assert.assertEquals(response.getStatusCode(), 201, "Status code deve ser 201");
        System.out.println("Usuário criado com sucesso");
        saveTextLog("Usuário criado com sucesso - Status: " + response.getStatusCode());
    }
    
    @Test(description = "Teste de atualização de usuário")
    @Description("Testa a atualização de dados de um usuário existente")
    @Severity(SeverityLevel.NORMAL)
    @Story("Atualização de usuário")
    public void testUpdateUser() {
        System.out.println("Testando atualização de usuário");
        saveTextLog("Testando atualização de usuário");
        
        userClient = new UserClient();
        UserRequest userRequest = new UserRequest("Pedro Costa", "Tech Lead");
        
        Response response = userClient.updateUser(1, userRequest);
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code deve ser 200");
        System.out.println("Usuário atualizado com sucesso");
        saveTextLog("Usuário atualizado com sucesso - Status: " + response.getStatusCode());
    }
    
    @Test(description = "Teste de busca de usuário")
    @Description("Testa a busca de um usuário existente por ID")
    @Severity(SeverityLevel.NORMAL)
    @Story("Busca de usuário")
    public void testGetUser() {
        System.out.println("Testando busca de usuário");
        saveTextLog("Testando busca de usuário");
        
        userClient = new UserClient();
        Response response = userClient.getUserById(1);
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code deve ser 200");
        System.out.println("Usuário encontrado com sucesso");
        saveTextLog("Usuário encontrado com sucesso - Status: " + response.getStatusCode());
    }
} 