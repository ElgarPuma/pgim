package pe.gob.osinergmin.pgim.utils;

public class ClaveValorUtil<K, V> {

    K clave;
    V valor;

    public ClaveValorUtil() {

    }

    public ClaveValorUtil(K clave, V  valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
    public V getValor() {
        return valor;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }
    public K getClave() {
        return clave;
    }
    
}
