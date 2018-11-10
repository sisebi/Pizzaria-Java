
package br.com.pizzaria.Http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @Eliezer
 */
public class PedidoHttp {
    private Integer id;
    private PessoaHttp cliente_id;
    private PessoaHttp entregador_id;
    private Double vlr_ttal;
    private Integer qtd_ttal;
    private Date dt_pedido;
    private String status_pedido;
    private List<Item_PedidoHttp> listaItens = new ArrayList<>();

    public PedidoHttp() {
    }

    public PedidoHttp(Integer id, PessoaHttp cliente_id, PessoaHttp entregador_id, Double vlr_ttal, Integer qtd_ttal, Date dt_pedido, String status_pedido) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.entregador_id = entregador_id;
        this.vlr_ttal = vlr_ttal;
        this.qtd_ttal = qtd_ttal;
        this.dt_pedido = dt_pedido;
        this.status_pedido = status_pedido;
    }
    
    public void addLista(Item_PedidoHttp i){
        i.setPedido_id(this.getId());
        this.listaItens.add(i);
    }
    public void deletarLista(int index){
        this.listaItens.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PessoaHttp getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(PessoaHttp cliente_id) {
        this.cliente_id = cliente_id;
    }

    public PessoaHttp getEntregador_id() {
        return entregador_id;
    }

    public void setEntregador_id(PessoaHttp entregador_id) {
        this.entregador_id = entregador_id;
    }

    public Double getVlr_ttal() {
        return vlr_ttal;
    }

    public void setVlr_ttal(Double vlr_ttal) {
        this.vlr_ttal = vlr_ttal;
    }

    public Integer getQtd_ttal() {
        return qtd_ttal;
    }

    public void setQtd_ttal(Integer qtd_ttal) {
        this.qtd_ttal = qtd_ttal;
    }

    public Date getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }

    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public List<Item_PedidoHttp> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Item_PedidoHttp> listaItens) {
        this.listaItens = listaItens;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final PedidoHttp other = (PedidoHttp) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PedidoModel{" + "id=" + id + ", cliente_id=" + cliente_id + ", entregador_id=" + entregador_id + ", vlr_ttal=" + vlr_ttal + ", qtd_ttal=" + qtd_ttal + ", dt_pedido=" + dt_pedido + ", status_pedido=" + status_pedido + ", listaItens=" + listaItens + '}';
    }
    
    
   
    
}
