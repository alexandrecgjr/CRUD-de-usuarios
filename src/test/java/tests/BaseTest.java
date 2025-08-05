package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        System.out.println("Configuração de teste inicializada");
    }
    
    @AfterMethod
    public void tearDown() {
        System.out.println("Teste finalizado");
    }
    
    protected void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrompida durante espera");
        }
    }
} 