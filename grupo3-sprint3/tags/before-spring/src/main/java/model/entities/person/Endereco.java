package model.entities.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import controller.ServicoCEP;
import utils.UtilsNumber;
import utils.validations.number.CEPValidation;

/***
 * Um objeto com informa��es de endere�o.
 * 
 * Este objeto cont�m informa��es sobre endere�o, com um cep,
 * localidade(cidade), bairro, logradouro e um complemento, todos
 * em String. Conta com uma API (com.publica.grupo1.services.ServicoCEP) 
 * para extrair as demais informa��es, exceto complemento. Tamb�m
 * � poss�vel definir essas informa��es com os setters do objeto.
 * a partir do CEP informado, 
 * 
 * @version 0.0.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */

@Entity
@Table(name="addresses")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String cep;
	private String localidade;
	private String bairro;
	private String logradouro;
	private String complemento;	
	
	/***
	 * Construtor que recebe somente o cep, e recebe as informa��es 
	 * de ServicoCEP.java. 
	 * 
	 * @param cep
	 */
	public Endereco (String cep) {
		if(CEPValidation.isCepValid(cep)) {
			this.cep = UtilsNumber.onlyNumbers(cep);
			atribuiPeloCEP();
		}
		else
			System.out.println("CEP inv�lido!");
	}
	
	/***
	 * Construtor que recebe o cep e um complemento, completando as informa��es
	 * com o cep, e atribuindo o complemento.
	 * 
	 * @param cep
	 * @param complemento
	 */
	public Endereco (String cep, String complemento) {
		if(CEPValidation.isCepValid(cep)) {
			this.cep = UtilsNumber.onlyNumbers(cep);
			atribuiPeloCEP();
		}
		this.complemento = complemento;
	}
	
	public Endereco () {
		
	}
	
	private void atribuiPeloCEP() {
		try {
			Endereco endereco = ServicoCEP.pegaEnderecoPelo(cep);
			setLocalidade(endereco.getLocalidade());
			setLogradouro(endereco.getLogradouro());
			setBairro(endereco.getBairro());
		}
		catch (Exception e) {
			//TODO: error message, invalid cep;
			e.printStackTrace();
		}
	}
	
	public String getCEP() {
		return cep;
	}
	
	public void setCEP(String cep) {
      if(CEPValidation.isCepValid(cep)) {
        this.cep = UtilsNumber.onlyNumbers(cep);
        atribuiPeloCEP();
      }
	}
	
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	public String getLocalidade() {
		return localidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getComplemento() {
		return complemento;
	}

	/***
	 * Override do m�todo toString para informar todos os campos deste
	 * objeto.
	 */
	@Override
	public String toString() {
		return "localidade: " + localidade + "\nbairro: " + bairro + 
			   "\nlogradouro: " + logradouro + "\ncomplemento: " + complemento;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
