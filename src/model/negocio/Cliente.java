/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.ICliente;
import model.iNegocio.IEndereco;

/**
 *
 * @author Danilo
 */
public class Cliente implements ICliente {
    
    private String nome;
    private char sexo;
    private String cpf;
    private String dataNascimento;
    private double renda;
    private String telefone;
    private String eMail;
    private IEndereco endereco; 

    public Cliente() {
    }

    public Cliente(String nome, char sexo, String cpf, String dataNascimento, double renda, String telefone, String eMail, IEndereco pEnd) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        this.telefone = telefone;
        this.eMail = eMail;
        this.endereco = pEnd;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public char getSexo() {
        return sexo;
    }

    @Override
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public double getRenda() {
        return renda;
    }

    @Override
    public void setRenda(double renda) {
        this.renda = renda;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String geteMail() {
        return eMail;
    }

    @Override
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public IEndereco getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(IEndereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return nome + "%" + cpf + "%" + dataNascimento + "%" + renda + "%" + telefone + "%" + eMail ;
    }
    
    
    
    
    
    
    
}
