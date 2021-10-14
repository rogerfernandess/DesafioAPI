package steps;

import dto.Simulacao;
import Utils.Massa.GeraCpf;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import request.RequestOrchestrator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static steps.ResponseValidation.*;

public class StepDefinitions {

    public GeraCpf gerador = new GeraCpf();
    public Simulacao simulacao = new Simulacao();

    private final RequestOrchestrator requestOrchestrator = new RequestOrchestrator();
    private Response response;

    @Given("que eu possua um {string} com restrição")
    public void que_eu_possua_um_com_restrição(String cpf) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        simulacao = objectMapper.readValue(new File("src/test/resources/simulacao.json"), Simulacao.class);
        simulacao.setCpf((Long.parseLong(cpf)));
    }

    @Given("que eu possua um cpf sem restrição")
    public void que_eu_possua_um_cpf_sem_restrição() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        simulacao = objectMapper.readValue(new File("src/test/resources/simulacao.json"), Simulacao.class);
        simulacao.setCpf(Long.parseLong(gerador.cpf(false)));
        simulacao.setCpf(Long.parseLong(gerador.cpf(false)));
    }

    @When("executo um {string} para consultar {string} no CPF")
    public void executo_um_para_consultar_no_cpf(String httpMethod, String restricao) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, restricao);
    }

    @Given("que eu possua as informações {string} {string} {string} {int} {int} {string} para simulação")
    public void que_eu_possua_as_informações_para_simulação(String nome, String cpf, String email, Integer valor, Integer parcelas, String seguro) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        simulacao = objectMapper.readValue(new File("src/test/resources/simulacao.json"), Simulacao.class);
        simulacao.setNome(nome);
        simulacao.setCpf(Long.parseLong(gerador.cpf(false)));
        simulacao.setEmail(email);
        simulacao.setParcelas(parcelas);
        simulacao.setValor(valor);
        simulacao.setSeguro(seguro);
        simulacao.setValorCampo(simulacao, cpf);
    }

    @When("executo um {string} para cadastrar uma simulação")
    public void executo_um_para_cadastrar_uma_simulação(String httpMethod) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, "");

    }

    @When("executo um {string} para alterar uma simulação {string} {string} {string} {int} {int} {string}")
    public void executo_um_para_alterar_uma_simulação(String httpMethod, String nome, String cpf, String email, Integer valor, Integer parcelas, String seguro) {
        simulacao.setNome(nome);
        simulacao.setEmail(email);
        simulacao.setParcelas(parcelas);
        simulacao.setValor(valor);
        simulacao.setSeguro(seguro);
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, "");
    }


    @Then("sistema retorna {int} com a {string}")
    public void sistema_retorna_com_a(Integer code, String mensagem) {
        validaResponseCode(response.statusCode(), code, response);
        Assert.assertTrue(response.statusCode()==code);
        Assert.assertTrue(ResponseValidation.alternaMensagem(mensagem, response, simulacao));
    }

    @Then("sistema retorna {int}")
    public void sistema_retorna(Integer code) {
        validaResponseCode(response.statusCode(), code, response);
        Assert.assertTrue(response.statusCode() == code);
        Assert.assertTrue(ResponseValidation.alternaMensagem("", response, simulacao));
        MatcherAssert.assertThat(response.body().asString(), matchesJsonSchema(new File("src/test/resources/schema.json")));
    }

    @When("executo um {string} para cadastrar uma simulação de um cpf já cadastrado")
    public void executo_um_para_cadastrar_uma_simulação_de_um_cpf_já_cadastrado(String httpMethod) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, "");
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, "");
    }

    @Given("que eu possua um cpf")
    public void que_eu_possua_um_cpf() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        simulacao = objectMapper.readValue(new File("src/test/resources/simulacao.json"), Simulacao.class);
        simulacao.setCpf(Long.parseLong(gerador.cpf(false)));
    }

    @When("executo um {string} para cadastrar uma {string}")
    public void executo_um_para_cadastrar_uma(String httpMethod, String simula) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, simula);
        simulacao.setId(response.body().path("id"));
    }

    @When("executo um {string} para consultar essa {string}")
    public void executo_um_para_consultar_essa(String httpMethod, String simula) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, simula);
    }

    @Given("executo um {string} para consultar as {string} existentes")
    public void executo_um_para_consultar_as_existentes(String httpMethod, String simula) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, simula);
    }

    @Then("sistema retorna {int} e lista de simulações")
    public void sistema_retorna_e_lista_de_simulações(Integer code) {
        validaResponseCode(response.statusCode(), code, response);
        Assert.assertTrue(response.statusCode()==code);
        Assert.assertTrue(validaLista(response));
    }

    @When("executo um {string} para deletar essa {string}")
    public void executo_um_para_deletar_essa(String httpMethod, String simula) {
        response = requestOrchestrator.handleRequestAPI(httpMethod, simulacao, simula);
    }
}