package request;

import dto.Simulacao;
import io.restassured.response.Response;
import org.junit.Assert;


public class RequestOrchestrator {

    private final RequestApi requestApi = new RequestApi();

    public Response handleRequestAPI(String httpMethod, Simulacao simulacao, String consulta) {
        switch (httpMethod) {
            case "GET":
                return requestApi.doGet(consulta, simulacao.getCpf());
            case "GETALL":
                return requestApi.doGetAll(consulta);
            case "POST":
                try {
                    return requestApi.doPost(consulta, simulacao);
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
                break;
            case "PUT":
                try {
                    return requestApi.doPut(consulta, simulacao);
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
                break;
            case "DELETE":
                try {
                    return requestApi.doDelete(consulta, simulacao);
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
                break;
            default:
                Assert.fail("Esse método não é suportado: " + httpMethod);
        }
        return null;
    }

}