
package br.com.pizzaria.Http;

import java.util.Objects;

/**
 *
 * @Eliezer
 */
public class PessoaHttp {    
    private Integer id;    
    private String nome;    
    private String cpf;    
    private String rg;
    private String tipo;
    private String fone;
    private String celular;
    private String email;
    private String cep;
    private String rua;
    private String nr;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;    

    public PessoaHttp() {
    }

    public PessoaHttp(Integer id) {
        this.id = id;
    }

    public PessoaHttp(Integer id, String nome, String cpf, String rg, String tipo, String fone, String celular, String email, String cep, String rua, String nr, String complemento, String bairro, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.tipo = tipo;
        this.fone = fone;
        this.celular = celular;
        this.email = email;
        this.cep = cep;
        this.rua = rua;
        this.nr = nr;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular.toUpperCase().trim();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase().trim();
    }
    

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf.toUpperCase().trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.toUpperCase().trim();
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg.toUpperCase().trim();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone.toUpperCase().trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase().trim();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.toUpperCase().trim();
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua.toUpperCase().trim();
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.toUpperCase().trim();
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.toUpperCase().trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade.toUpperCase().trim();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoaHttp other = (PessoaHttp) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PessoaHttp{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", tipo=" + tipo + ", fone=" + fone + ", celular=" + celular + ", email=" + email + ", cep=" + cep + ", rua=" + rua + ", nr=" + nr + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + '}';
    }

    
    
}
