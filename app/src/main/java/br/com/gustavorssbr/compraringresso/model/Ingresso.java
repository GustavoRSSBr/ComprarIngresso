package br.com.gustavorssbr.compraringresso.model;

public class Ingresso {
    private String codigo;
    private Float valor;
    private Boolean vip;

    public Ingresso(String codigo, Float valor, Boolean vip) {
        this.codigo = codigo;
        this.valor = valor;
        this.vip = vip;
    }

    public Float valorFinal(Float taxa){
        if (vip){
            return (float) ((valor + taxa) * 1.18);
        }

        return valor + taxa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }
}
