package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;



public class AllureRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                          FilterableResponseSpecification responseSpec,
                          FilterContext context) {
        
        // Captura detalhes da requisição
        String requestDetails = captureRequestDetails(requestSpec);
        Allure.addAttachment("Request Details", "text/plain", requestDetails);
        
        // Executa a requisição
        Response response = context.next(requestSpec, responseSpec);
        
        // Captura detalhes da resposta
        String responseDetails = captureResponseDetails(response);
        Allure.addAttachment("Response Details", "text/plain", responseDetails);
        
        return response;
    }
    
    @Attachment(value = "Request Details", type = "text/plain")
    private String captureRequestDetails(FilterableRequestSpecification requestSpec) {
        StringBuilder details = new StringBuilder();
        details.append("Method: ").append(requestSpec.getMethod()).append("\n");
        details.append("URI: ").append(requestSpec.getURI()).append("\n");
        details.append("Headers: ").append(requestSpec.getHeaders()).append("\n");
        
        if (requestSpec.getBody() != null) {
            details.append("Body: ").append(requestSpec.getBody().toString()).append("\n");
        }
        
        return details.toString();
    }
    
    @Attachment(value = "Response Details", type = "text/plain")
    private String captureResponseDetails(Response response) {
        StringBuilder details = new StringBuilder();
        details.append("Status Code: ").append(response.getStatusCode()).append("\n");
        details.append("Status Line: ").append(response.getStatusLine()).append("\n");
        details.append("Headers: ").append(response.getHeaders()).append("\n");
        
        if (response.getBody() != null) {
            details.append("Body: ").append(response.getBody().asPrettyString()).append("\n");
        }
        
        return details.toString();
    }
} 