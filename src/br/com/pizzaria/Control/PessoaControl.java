package br.com.pizzaria.Control;


import br.com.pizzaria.Util.ErroSistema;
import br.com.pizzaria.Http.CepHttp;
import br.com.pizzaria.Http.PessoaHttp;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @Eliezer
 */
public class PessoaControl {

    private Client cliente;
    private String url = "http://192.168.0.197:8080/WS/v1";

    public PessoaControl() {
        ClientConfig clienteConfig = new DefaultClientConfig();
        clienteConfig.getClasses().add(JacksonJsonProvider.class);
        cliente = Client.create(clienteConfig);
    }
    
    public CepHttp getCep(String cep) throws ErroSistema{
        try {
            WebResource resource = cliente.resource("https://viacep.com.br/ws/");
            CepHttp resposta = resource.path(cep + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(CepHttp.class);            
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);
        }
    }

    public PessoaHttp getId(String id) throws ErroSistema {
        try {
            WebResource resource = cliente.resource(url+"/pessoa/id/");
            PessoaHttp resposta = resource.path(id + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(PessoaHttp.class);            
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);            
        }
    }
    
    public PessoaHttp getCpf(String cpf) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/cpf/");
            PessoaHttp resposta = resource.path(cpf + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(PessoaHttp.class);
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);
        }
    }
    
    public PessoaHttp getRg(String rg) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/rg/");
            PessoaHttp resposta = resource.path(rg + "/jason")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(PessoaHttp.class);            
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);
        }
    }
    
    public List<PessoaHttp> listaNome(String nome) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/listaNome");
            List<PessoaHttp> respostas = resource.queryParam("nome" , nome)
                    .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<PessoaHttp>>() {});
            return respostas;
        } catch (Exception e) {
            throw new ErroSistema("Erro", e);
        }
    }
    
    public void postPessoa(PessoaHttp p) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/");
            ClientResponse resultado = resource.type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, p);
            String retorno = resultado.getEntity(String.class);
            System.out.println("toString :"+resultado.toString());
            System.out.println("getStatus :"+resultado.getStatus());
            System.out.println("Retorno :"+retorno);
        } catch (Exception e) {
            throw new ErroSistema("ERRO :", e);
        }
    }
    
    public void putPessoa(PessoaHttp p) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/");
            ClientResponse resultado = resource.type(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class ,p);
            String retorno = resultado.getEntity(String.class);
            System.out.println("toString :"+resultado.toString());
            System.out.println("getStatus :"+resultado.getStatus());
            System.out.println("Retorno :"+retorno);
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);
        }
    }
    
    public void deletePessoa(String id) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pessoa/");
            ClientResponse resultado = resource.path(id)
                    .delete(ClientResponse.class);
            String retorno = resultado.getEntity(String.class);
            System.out.println("toString :"+resultado.toString());
            System.out.println("getStatus :"+resultado.getStatus());
            System.out.println("Retorno :"+retorno);
        } catch (Exception e) {
            throw new ErroSistema("ERRO :", e);
        }
    }

    public static void main(String[] args) throws ErroSistema {
        PessoaControl control = new PessoaControl();
//        PessoaHttp p = new PessoaHttp();
//        
//        control.deletePessoa("8");
        
//        p.setId(3);
//        p.setNome("Andre Bignati");
//        p.setCpf("999.999.999-49");
//        p.setRg("999.633");
//        p.setCep("13188-072");
//        p.setRua("Rua jose andrade");
//        p.setBairro("J.Amanda l");
//        p.setCidade("Hortolandia");
//        p.setEmail("andre@gmail.com");
//        p.setFone("(019)3819-2370");
//        p.setUf("sp");
//        p.setNr("500");
//        p.setComplemento("");
//        p.setTipo(TipoPessoa.ADMIN);

//        p = control.getId("3");
//        p.setNome("TUTINHA MIRANDA da silva");
//        control.putPessoa(p);

//        List<PessoaHttp> lista = control.listaNome("bignati");        
//        for (PessoaHttp p : lista){
//            System.out.println("EMAIL.....:"+p.getEmail());
//        }
        
        CepHttp cep = control.getCep("13188072");
        System.out.println("CEPSSSS   :" + cep.getLogradouro());
        
    }

}
