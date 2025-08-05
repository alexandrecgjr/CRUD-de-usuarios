package tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class})
public class BaseTest {
    
    @BeforeClass
    @Step("Configurando ambiente de teste")
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.filters(new AllureRestAssuredFilter());
        System.out.println("Configuração de teste inicializada");
    }
    
    @AfterMethod
    @Step("Finalizando teste")
    public void tearDown() {
        System.out.println("Teste finalizado");
    }
    
    @Step("Aguardando {milliseconds} milissegundos")
    protected void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrompida durante espera");
        }
    }
    
    @Attachment(value = "Log de Teste", type = "text/plain")
    public String saveTextLog(String message) {
        return message;
    }
    
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }
} 