package steps;

import dto.Simulacao;
import io.restassured.response.Response;


import static org.junit.Assert.fail;

public class ResponseValidation {
    public static void validaResponseCode(int responseCode, int expectedHTTPResponseCode, Response response) {
        response.prettyPrint();
        if (responseCode != expectedHTTPResponseCode) {
            fail("StatusCode esperado : " + expectedHTTPResponseCode + " e o StatusCode recebido : "
                    + responseCode + " não são o mesmo. Response: " + response.prettyPrint());
        }
    }

    public static Boolean alternaMensagem(String mensagem, Response response, Simulacao simulacao){
        switch (response.statusCode()){
            case 200:
                return validaMensagem200(response, simulacao, mensagem);
            case 201:
                return validaMensagem201(response, simulacao);
            case 204:
                return true;
            case 400:
                return validaMensagem400(mensagem, response);
            case 404:
                return validaMensagem404(mensagem, response, simulacao);
            case 409:
                return validaMensagem409(mensagem, response);
        }
    return false;

    }

    public static Boolean validaMensagem200(Response response, Simulacao simulacao, String mensagem) {
        if(response.body().path("valor")==null){
            if  (response.body().path("mensagem").toString().equalsIgnoreCase(mensagem)){
                return true;
            }
        }else {
            float valor = response.body().path("valor");
            if (response.body().path("nome").toString().equalsIgnoreCase(simulacao.getNome())
                && response.body().path("cpf").toString().equalsIgnoreCase(simulacao.getCpf().toString())
                && response.body().path("email").toString().equalsIgnoreCase(simulacao.getEmail())
                && (int)valor == (simulacao.getValor())
                && response.body().path("parcelas").toString().equalsIgnoreCase(simulacao.getParcelas().toString())) {
            return true;
            }
        }
        return false;
    }

    public static Boolean validaMensagem201(Response response, Simulacao simulacao) {
        if (response.body().path("nome").toString().equalsIgnoreCase(simulacao.getNome())
                && response.body().path("cpf").toString().equalsIgnoreCase(simulacao.getCpf().toString())
                && response.body().path("email").toString().equalsIgnoreCase(simulacao.getEmail())
                && response.body().path("valor").toString().equalsIgnoreCase(simulacao.getValor().toString())
                && response.body().path("parcelas").toString().equalsIgnoreCase(simulacao.getParcelas().toString())) {
            return true;
        }
        return false;
    }

    public static Boolean validaMensagem400(String mensagem, Response response) {

        if (response.body().path("erros").toString().contains("Parcelas")) {
            if (response.body().path("erros.parcelas").toString().equalsIgnoreCase(mensagem)) {
                return true;
            }
        }
        else if(response.body().path("erros").toString().contains("Valor")) {
            if (response.body().path("erros.valor").toString().equalsIgnoreCase(mensagem)) {
                return true;
            }
        }
        else if(response.body().path("erros").toString().contains("mail")) {
            if (response.body().path("erros.email").toString().equalsIgnoreCase(mensagem)) {
                return true;
            } else response.body().path("erros.email").toString().equalsIgnoreCase("não é um endereço de e-mail");
                {
                return true;
            }
        }
        else if(response.body().path("erros").toString().contains("Nome")) {
            if (response.body().path("erros.nome").toString().equalsIgnoreCase(mensagem)) {
                return true;
            }
        }
        else if(response.body().path("erros").toString().contains("CPF")) {
            if (response.body().path("erros.cpf").toString().equalsIgnoreCase(mensagem)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean validaMensagem404(String mensagem, Response response, Simulacao simulacao) {
        mensagem = mensagem.replace("{cpf}", simulacao.getCpf().toString());
        if (response.body().path("mensagem").toString().equalsIgnoreCase(mensagem)) {
            return true;
        }
        return false;
    }

    public static Boolean validaMensagem409(String mensagem, Response response) {
        if (response.body().path("mensagem").toString().equalsIgnoreCase(mensagem)) {
            return true;
        }
        return false;
    }

    public static Boolean validaLista(Response response) {
        if (!response.body().path("id").toString().isEmpty()
                && !response.body().path("nome").toString().isEmpty()
                && !response.body().path("cpf").toString().isEmpty()
                && !response.body().path("email").toString().isEmpty()
                && !response.body().path("valor").toString().isEmpty()
                && !response.body().path("parcelas").toString().isEmpty()) {
            return true;
        }
        return false;
    }

}