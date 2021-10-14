package request;

import dto.Simulacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static Utils.Environments.Endpoints.*;
import static io.restassured.RestAssured.given;

public class RequestApi {

    public Response restAssuredResponse;

    public RequestApi() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    private String switchEndpoint(String consulta){
        String endpoint;
        if(consulta.equalsIgnoreCase("restricao")){
            endpoint = BASE_PATH_GET_RESTRICAO;
        }
            else {
            endpoint = BASE_PATH_GET_SIMULACAO;
        }

        return endpoint;
    }

    public Response doGet(String consulta, Object cpf) {
        try {
            restAssuredResponse = given().contentType(ContentType.JSON).
                    relaxedHTTPSValidation().
                    when().get(switchEndpoint(consulta) + cpf.toString());
            Assert.assertNotNull(restAssuredResponse);
            return restAssuredResponse;
        } catch (Exception e) {
            Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response: "
                    + restAssuredResponse.prettyPrint());
            throw e;
        }
    }

    public Response doGetAll(String consulta) {
        try {
            restAssuredResponse = given().contentType(ContentType.JSON).
                    relaxedHTTPSValidation().
                    when().get(switchEndpoint(consulta));
            Assert.assertNotNull(restAssuredResponse);
            return restAssuredResponse;
        } catch (Exception e) {
            Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response: "
                    + restAssuredResponse.prettyPrint());
            throw e;
        }
    }

    public Response doPost(String consulta, Simulacao simulacao) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(simulacao);
            RequestSpecification requestspecs = given().
                    contentType(ContentType.JSON).
                    relaxedHTTPSValidation();
            restAssuredResponse =
                    requestspecs.body(json).post(switchEndpoint(consulta));
            Assert.assertNotNull(restAssuredResponse);
            return restAssuredResponse;
        } catch (Exception e) {
            Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response: "
                    + restAssuredResponse.prettyPrint());
            throw e;
        }
    }

    public Response doPut(String consulta, Simulacao simulacao) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(simulacao);
            RequestSpecification requestspecs = given().
                    contentType(ContentType.JSON).
                    relaxedHTTPSValidation();
            restAssuredResponse =
                    requestspecs.body(json).put(switchEndpoint(consulta)+simulacao.getCpf());
            Assert.assertNotNull(restAssuredResponse);
            return restAssuredResponse;
        } catch (Exception e) {
            Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response: "
                    + restAssuredResponse.prettyPrint());
            throw e;
        }
    }

    public Response doDelete(String consulta, Simulacao simulacao) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(simulacao);
            RequestSpecification requestspecs = given().
                    contentType(ContentType.JSON).
                    relaxedHTTPSValidation();
            restAssuredResponse =
                    requestspecs.body(json).delete(switchEndpoint(consulta)+simulacao.getId().toString());
            Assert.assertNotNull(restAssuredResponse);
            return restAssuredResponse;
        } catch (Exception e) {
            Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response: "
                    + restAssuredResponse.prettyPrint());
            throw e;
        }
    }
}
