
package br.com.pizzaria.Control;

import br.com.pizzaria.Http.Item_PedidoHttp;
import br.com.pizzaria.Http.PedidoHttp;
import br.com.pizzaria.Http.PessoaHttp;
import br.com.pizzaria.Util.ErroSistema;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @Eliezer
 */
public class PedidoControl {
    private Client cliente;
    private final String url = "http://192.168.0.197:8080/WS/v1"; 

    public PedidoControl() {
        ClientConfig clienteConfig = new DefaultClientConfig();
        clienteConfig.getClasses().add(JacksonJsonProvider.class);
        cliente = Client.create(clienteConfig);
    }
    
    public PedidoHttp getId(String id) throws ErroSistema{
         try {
            WebResource resource = cliente.resource(url+"/pedido/id/");
            PedidoHttp resposta = resource.path(id + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(PedidoHttp.class);            
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);            
        }        
    }
    
    public List<PedidoHttp> getCpfCliente(String cpf) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pedido/cliente/cpf/");
                List<PedidoHttp> respostas = resource.path(cpf + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<PedidoHttp>>() {});
            return respostas;
        } catch (Exception e) {
            throw new ErroSistema("Erro", e);
        }
    }
    
    public List<PedidoHttp> getCpfEntregador(String cpf) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pedido/entregador/cpf/");
            List<PedidoHttp> respostas = resource.path(cpf + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<PedidoHttp>>() {});
            return respostas;
        } catch (Exception e) {
            throw new ErroSistema("Erro", e);
        }
    }
    
    public void postPedido(PedidoHttp p) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pedido/");
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
    
    public void putPedido(PedidoHttp p) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pedido/");
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
    
    public void deletePedido(String id) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/pedido/");
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
//        PessoaControl pessoaControl = new PessoaControl();
        PedidoControl control = new PedidoControl();
//        PedidoHttp p = new PedidoHttp();
////        p.setId(4);
//        p.setCliente_id(pessoaControl.getId("6"));
//        p.setEntregador_id(pessoaControl.getId("4"));
//        p.setDt_pedido(new Date());
//        p.setStatus_pedido("ABERTO");
//        control.postPedido(p);        
        
//        p.setListaItens(listaItens);
//        p.setQtd_ttal(2);
//        p.setVlr_ttal(33.00);
//        System.out.println("PEDIDOOOO....."+p.getCliente_id().getEmail());
        
        PedidoHttp p = control.getId("6");       
        for (Item_PedidoHttp i : p.getListaItens()){
        System.out.println("ITEMSSS....."+i.getDescricao());           
            
        }

        
    }
    
    
    
}
