package com.publica.grupo2;
import java.time.LocalDate;

// Essa classe foi criada para testar alguns métodos feitos

public class Colaborador {
	static String nome;
	static LocalDate dataNasc; 
	static String telefone; 
	static String cargo; 
	static int salario; 
	static String email; 
	static String cpf;
	static String cnpj;
	static String usuario;
	static String senha;
	
	
	
	public static String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		Colaborador.nome = nome;
	}


	public static LocalDate getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(LocalDate dataNasc) {
		Colaborador.dataNasc = dataNasc;
	}


	public static String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		if (validate.ValidarTelefone.validarTelefone(telefone)){
			Colaborador.telefone = telefone;
		}	
	}


	public static String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		Colaborador.cargo = cargo;
	}


	public static int getSalario() {
		return salario;
	}


	public void setSalario(Integer salario) {
		Colaborador.salario = salario;
	}


	public static String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		if (com.publica.grupo2.Email.ValidadorEmail.validarEmail(email) == true){
			Colaborador.email = email;}
		}
		
	//}
	
	public static String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		if (validate.ValidarCpf.validateCpf(cpf)==true) {
			Colaborador.cpf = cpf;
		}
	
	}
	
	public static String getCpnj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		if (augusto.publica.Implementacoes.validarCNPJ(cnpj) == true) {
			Colaborador.cnpj = cnpj;
		}
	}


	public Colaborador(String nome, LocalDate dataNasc, String cargo, int salario, String email, String cpf, String cnpj) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setEmail(email);
		this.setCpf(cpf);
		this.setCnpj(cnpj);
	}
	
	
	public static void main(String[] args) {
		Colaborador colab1 = new Colaborador("Ana", LocalDate.now(), "Dev", 3000, "carolsantos2002@publica.com.br", "12419231910", "11444777000100");
		System.out.println(colab1);
		

	}
	
	public String toString() {
		return " Nome: " + Colaborador.getNome() + " \n Data Nasc " + Colaborador.getDataNasc() + "\n Cargo "  + Colaborador.getCargo() + "\n Sal�rio " + Colaborador.getSalario() + "\n E-mail " + Colaborador.getEmail() + "\n CPF " + Colaborador.getCpf() + " \n CNPJ " +Colaborador.getCpnj();
		
	}

}
