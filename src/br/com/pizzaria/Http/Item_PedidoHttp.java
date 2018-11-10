
package br.com.pizzaria.Http;

import java.util.Objects;

/**
 *
 * @Eliezer
 */
public class Item_PedidoHttp {
    private Integer id;
    private Integer pedido_id;
    private String descricao;
    private int qtd;
    private Double vlr_un;
    private Double vlr_un_ttal;

    public Item_PedidoHttp() {
    }

    public Item_PedidoHttp(Integer id, Integer pedido_id, String descricao, int qtd, Double vlr_un, Double vlr_un_ttal) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.descricao = descricao;
        this.qtd = qtd;
        this.vlr_un = vlr_un;
        this.vlr_un_ttal = vlr_un_ttal;
    }

    public Double getVlr_un_ttal() {
        return vlr_un_ttal;
    }

    public void setVlr_un_ttal(Double vlr_un_ttal) {
        this.vlr_un_ttal = vlr_un_ttal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Integer pedido_id) {
        this.pedido_id = pedido_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Double getVlr_un() {
        return vlr_un;
    }

    public void setVlr_un(Double vlr_un) {
        this.vlr_un = vlr_un;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Item_PedidoHttp other = (Item_PedidoHttp) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item_Pedido{" + "id=" + id + ", pedido_id=" + pedido_id + ", descricao=" + descricao + ", qtd=" + qtd + ", vlr_un=" + vlr_un + ", vlr_un_ttal=" + vlr_un_ttal + '}';
    }
    
    
    
    
    
}
