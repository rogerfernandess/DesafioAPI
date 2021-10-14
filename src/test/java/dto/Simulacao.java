package dto;

import Utils.Massa.GeraCpf;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Simulacao {

    @JsonIgnore
    public GeraCpf gerador = new GeraCpf();

    @JsonIgnore
    @JsonIgnoreProperties("nome")
    private Integer id;

    @JsonIgnoreProperties("nome")
    private String nome;

    @JsonIgnoreProperties("cpf")
    private Long cpf;

    @JsonIgnoreProperties("email")
    private String email;

    @JsonIgnoreProperties("valor")
    private Integer valor;

    @JsonIgnoreProperties("parcelas")
    private Integer parcelas;

    @JsonIgnoreProperties("seguro")
    private String seguro;

    public void setValorCampo(Simulacao simulacao, String cpf){
        if(cpf.equalsIgnoreCase("vazio") ) {
            simulacao.setCpf(null);
        }
        if(simulacao.getNome().equalsIgnoreCase("vazio")){
            simulacao.setNome(null);
        }
        if(simulacao.getValor()==0){
            simulacao.setValor(null);
        }
        if(simulacao.getParcelas()==0){
            simulacao.setParcelas(null);
        }
        if(simulacao.getEmail().equalsIgnoreCase("vazio")){
            simulacao.setEmail(null);
        }
        if(simulacao.getSeguro().equalsIgnoreCase("vazio")){
            simulacao.setSeguro(null);
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
