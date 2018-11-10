
package br.com.pizzaria.Control;

import br.com.pizzaria.Http.Item_PedidoHttp;
import br.com.pizzaria.Http.PedidoHttp;
import br.com.pizzaria.Util.ErroSistema;
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
public class Item_PedidoControl {
    private Client cliente;
    private final String url = "http://192.168.0.197:8080/WS/v1";

    public Item_PedidoControl() {
        ClientConfig clienteConfig = new DefaultClientConfig();
        clienteConfig.getClasses().add(JacksonJsonProvider.class);
        cliente = Client.create(clienteConfig);
    }
    
    public Item_PedidoHttp getId(String id) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/item/id/");
            Item_PedidoHttp resposta = resource.path(id + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(Item_PedidoHttp.class);            
            return resposta;
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);            
        }   
    }
    
    public List<Item_PedidoHttp> getItemPedidoId(String id) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/item/pedido/id/");
            List<Item_PedidoHttp> respostas = resource.path(id+"/json")
                    .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Item_PedidoHttp>>() {});
            return respostas;
        } catch (Exception e) {
            throw new ErroSistema("Erro", e);
        }        
    }
    
    public List<Item_PedidoHttp> getItemDescricao(String descricao) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/item/listaDescricao");
            List<Item_PedidoHttp> respostas = resource.queryParam("descricao" , descricao)
                    .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Item_PedidoHttp>>() {});
            return respostas;
        } catch (Exception e) {
            throw new ErroSistema("Erro", e);
        }
    }
    
    public void postItemPedido(Item_PedidoHttp i) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/item/");
            ClientResponse resultado = resource.type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, i);
            String retorno = resultado.getEntity(String.class);
            System.out.println("toString :"+resultado.toString());
            System.out.println("getStatus :"+resultado.getStatus());
            System.out.println("Retorno :"+retorno);
        } catch (Exception e) {
            throw new ErroSistema("ERRO :", e);
        }          
    }
    
    public void putItemPedido(Item_PedidoHttp i) throws ErroSistema{
        try {
            WebResource resource = cliente.resource(url+"/item/");
            ClientResponse resultado = resource.type(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class ,i);
            String retorno = resultado.getEntity(String.class);
            System.out.println("toString :"+resultado.toString());
            System.out.println("getStatus :"+resultado.getStatus());
            System.out.println("Retorno :"+retorno);
        } catch (Exception e) {
            throw new ErroSistema("ERRO :",e);
        }    
    }
    
    public void deleteItemPedido(String id) throws ErroSistema{
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
//        PedidoControl pedidoControl = new PedidoControl();
        Item_PedidoControl control = new Item_PedidoControl();
//        Item_PedidoHttp i = new Item_PedidoHttp();
//        i.setPedido_id(7);
//        i.setDescricao("pizza chocolate grande");
//        i.setQtd(1);
//        i.setVlr_un(76.99);
//        i.setVlr_un_ttal(76.99);
//        control.postItemPedido(i);

         List<Item_PedidoHttp> lista = control.getItemPedidoId("7");
         for (Item_PedidoHttp item : lista){
             System.out.println("ITEMSSSS....."+item.getDescricao());
             
         }
    }
    
    
}
