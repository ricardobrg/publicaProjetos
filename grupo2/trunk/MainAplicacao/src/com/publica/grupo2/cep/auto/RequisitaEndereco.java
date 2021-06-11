package com.publica.grupo2.cep.auto;

	public class RequisitaEndereco {

	    String logradouro;
	    String bairro;
	    String localidade;
	    String uf;

	    public String getLogradouro() {
	        return logradouro;
	    }

	    public String getBairro() {
	        return bairro;
	    }

	    public String getLocalidade() {
	        return localidade;
	    }
	    
	    public String getUf() {
	        return uf;
	    }

	    @Override
	    public String toString() {
	        return "Endereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + '}';
	    }
}
