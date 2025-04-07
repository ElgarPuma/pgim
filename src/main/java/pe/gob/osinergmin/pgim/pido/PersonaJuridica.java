
package pe.gob.osinergmin.pgim.pido;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosPrincipalesTYPE complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosPrincipalesTYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_dep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cod_dist" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cod_prov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_ciiu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_doble" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_fecact" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_fecalt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_fecbaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_flag22" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_identi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_inter1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_lllttt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_mclase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_nomvia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_nomzon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_numer1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_numreg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_numruc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_reacti" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_refer1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_secuen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_tamano" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_tipvia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_tipzon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_tpoemp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_ubigeo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ddp_userna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_ciiu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_dep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_dist" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_flag22" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_identi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_numreg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_prov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_tamano" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_tipvia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_tipzon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc_tpoemp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="esActivo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esHabido" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosPrincipalesTYPE", namespace = "http://soa.osinergmin.org.pe/schema/consultarucdatosprincipales/contribuyentes/consultar/1.0", propOrder = {
    "codDep",
    "codDist",
    "codProv",
    "ddpCiiu",
    "ddpDoble",
    "ddpEstado",
    "ddpFecact",
    "ddpFecalt",
    "ddpFecbaj",
    "ddpFlag22",
    "ddpIdenti",
    "ddpInter1",
    "ddpLllttt",
    "ddpMclase",
    "ddpNombre",
    "ddpNomvia",
    "ddpNomzon",
    "ddpNumer1",
    "ddpNumreg",
    "ddpNumruc",
    "ddpReacti",
    "ddpRefer1",
    "ddpSecuen",
    "ddpTamano",
    "ddpTipvia",
    "ddpTipzon",
    "ddpTpoemp",
    "ddpUbigeo",
    "ddpUserna",
    "descCiiu",
    "descDep",
    "descDist",
    "descEstado",
    "descFlag22",
    "descIdenti",
    "descNumreg",
    "descProv",
    "descTamano",
    "descTipvia",
    "descTipzon",
    "descTpoemp",
    "esActivo",
    "esHabido"
})
public class PersonaJuridica {

    @XmlElement(name = "cod_dep", required = true)
    protected String cod_dep;
    @XmlElement(name = "cod_dist", required = true)
    protected String cod_dist;
    @XmlElement(name = "cod_prov", required = true)
    protected String cod_prov;
    @XmlElement(name = "ddp_ciiu", required = true)
    protected String ddp_ciiu;
    @XmlElement(name = "ddp_doble", required = true)
    protected String ddp_doble;
    @XmlElement(name = "ddp_estado", required = true)
    protected String ddp_estado;
    @XmlElement(name = "ddp_fecact", required = true)
    protected String ddp_fecact;
    @XmlElement(name = "ddp_fecalt", required = true)
    protected String ddp_fecalt;
    @XmlElement(name = "ddp_fecbaj", required = true)
    protected String ddp_fecbaj;
    @XmlElement(name = "ddp_flag22", required = true)
    protected String ddp_flag22;
    @XmlElement(name = "ddp_identi", required = true)
    protected String ddp_identi;
    @XmlElement(name = "ddp_inter1", required = true)
    protected String ddp_inter1;
    @XmlElement(name = "ddp_lllttt", required = true)
    protected String ddp_lllttt;
    @XmlElement(name = "ddp_mclase", required = true)
    protected String ddp_mclase;
    @XmlElement(name = "ddp_nombre", required = true)
    protected String ddp_nombre;
    @XmlElement(name = "ddp_nomvia", required = true)
    protected String ddp_nomvia;
    @XmlElement(name = "ddp_nomzon", required = true)
    protected String ddp_nomzon;
    @XmlElement(name = "ddp_numer1", required = true)
    protected String ddp_numer1;
    @XmlElement(name = "ddp_numreg", required = true)
    protected String ddp_numreg;
    @XmlElement(name = "ddp_numruc", required = true)
    protected String ddp_numruc;
    @XmlElement(name = "ddp_reacti", required = true)
    protected String ddp_reacti;
    @XmlElement(name = "ddp_refer1", required = true)
    protected String ddp_refer1;
    @XmlElement(name = "ddp_secuen", required = true)
    protected String ddp_secuen;
    @XmlElement(name = "ddp_tamano", required = true)
    protected String ddp_tamano;
    @XmlElement(name = "ddp_tipvia", required = true)
    protected String ddp_tipvia;
    @XmlElement(name = "ddp_tipzon", required = true)
    protected String ddp_tipzon;
    @XmlElement(name = "ddp_tpoemp", required = true)
    protected String ddp_tpoemp;
    @XmlElement(name = "ddp_ubigeo", required = true)
    protected String ddp_ubigeo;
    @XmlElement(name = "ddp_userna", required = true)
    protected String ddp_userna;
    @XmlElement(name = "desc_ciiu", required = true)
    protected String desc_ciiu;
    @XmlElement(name = "desc_dep", required = true)
    protected String desc_dep;
    @XmlElement(name = "desc_dist", required = true)
    protected String desc_dist;
    @XmlElement(name = "desc_estado", required = true)
    protected String desc_estado;
    @XmlElement(name = "desc_flag22", required = true)
    protected String desc_flag22;
    @XmlElement(name = "desc_identi", required = true)
    protected String desc_identi;
    @XmlElement(name = "desc_numreg", required = true)
    protected String desc_numreg;
    @XmlElement(name = "desc_prov", required = true)
    protected String desc_prov;
    @XmlElement(name = "desc_tamano", required = true)
    protected String desc_tamano;
    @XmlElement(name = "desc_tipvia", required = true)
    protected String desc_tipvia;
    @XmlElement(name = "desc_tipzon", required = true)
    protected String desc_tipzon;
    @XmlElement(name = "desc_tpoemp", required = true)
    protected String desc_tpoemp;
    protected String esActivo;
    protected String esHabido;

    public String getCod_dep() {
		return cod_dep;
	}

	public void setCod_dep(String cod_dep) {
		this.cod_dep = cod_dep;
	}

	public String getCod_dist() {
		return cod_dist;
	}

	public void setCod_dist(String cod_dist) {
		this.cod_dist = cod_dist;
	}

	public String getCod_prov() {
		return cod_prov;
	}

	public void setCod_prov(String cod_prov) {
		this.cod_prov = cod_prov;
	}

	public String getDdp_ciiu() {
		return ddp_ciiu;
	}

	public void setDdp_ciiu(String ddp_ciiu) {
		this.ddp_ciiu = ddp_ciiu;
	}

	public String getDdp_doble() {
		return ddp_doble;
	}

	public void setDdp_doble(String ddp_doble) {
		this.ddp_doble = ddp_doble;
	}

	public String getDdp_estado() {
		return ddp_estado;
	}

	public void setDdp_estado(String ddp_estado) {
		this.ddp_estado = ddp_estado;
	}

	public String getDdp_fecact() {
		return ddp_fecact;
	}

	public void setDdp_fecact(String ddp_fecact) {
		this.ddp_fecact = ddp_fecact;
	}

	public String getDdp_fecalt() {
		return ddp_fecalt;
	}

	public void setDdp_fecalt(String ddp_fecalt) {
		this.ddp_fecalt = ddp_fecalt;
	}

	public String getDdp_fecbaj() {
		return ddp_fecbaj;
	}

	public void setDdp_fecbaj(String ddp_fecbaj) {
		this.ddp_fecbaj = ddp_fecbaj;
	}

	public String getDdp_flag22() {
		return ddp_flag22;
	}

	public void setDdp_flag22(String ddp_flag22) {
		this.ddp_flag22 = ddp_flag22;
	}

	public String getDdp_identi() {
		return ddp_identi;
	}

	public void setDdp_identi(String ddp_identi) {
		this.ddp_identi = ddp_identi;
	}

	public String getDdp_inter1() {
		return ddp_inter1;
	}

	public void setDdp_inter1(String ddp_inter1) {
		this.ddp_inter1 = ddp_inter1;
	}

	public String getDdp_lllttt() {
		return ddp_lllttt;
	}

	public void setDdp_lllttt(String ddp_lllttt) {
		this.ddp_lllttt = ddp_lllttt;
	}

	public String getDdp_mclase() {
		return ddp_mclase;
	}

	public void setDdp_mclase(String ddp_mclase) {
		this.ddp_mclase = ddp_mclase;
	}

	public String getDdp_nombre() {
		return ddp_nombre;
	}

	public void setDdp_nombre(String ddp_nombre) {
		this.ddp_nombre = ddp_nombre;
	}

	public String getDdp_nomvia() {
		return ddp_nomvia;
	}

	public void setDdp_nomvia(String ddp_nomvia) {
		this.ddp_nomvia = ddp_nomvia;
	}

	public String getDdp_nomzon() {
		return ddp_nomzon;
	}

	public void setDdp_nomzon(String ddp_nomzon) {
		this.ddp_nomzon = ddp_nomzon;
	}

	public String getDdp_numer1() {
		return ddp_numer1;
	}

	public void setDdp_numer1(String ddp_numer1) {
		this.ddp_numer1 = ddp_numer1;
	}

	public String getDdp_numreg() {
		return ddp_numreg;
	}

	public void setDdp_numreg(String ddp_numreg) {
		this.ddp_numreg = ddp_numreg;
	}

	public String getDdp_numruc() {
		return ddp_numruc;
	}

	public void setDdp_numruc(String ddp_numruc) {
		this.ddp_numruc = ddp_numruc;
	}

	public String getDdp_reacti() {
		return ddp_reacti;
	}

	public void setDdp_reacti(String ddp_reacti) {
		this.ddp_reacti = ddp_reacti;
	}

	public String getDdp_refer1() {
		return ddp_refer1;
	}

	public void setDdp_refer1(String ddp_refer1) {
		this.ddp_refer1 = ddp_refer1;
	}

	public String getDdp_secuen() {
		return ddp_secuen;
	}

	public void setDdp_secuen(String ddp_secuen) {
		this.ddp_secuen = ddp_secuen;
	}

	public String getDdp_tamano() {
		return ddp_tamano;
	}

	public void setDdp_tamano(String ddp_tamano) {
		this.ddp_tamano = ddp_tamano;
	}

	public String getDdp_tipvia() {
		return ddp_tipvia;
	}

	public void setDdp_tipvia(String ddp_tipvia) {
		this.ddp_tipvia = ddp_tipvia;
	}

	public String getDdp_tipzon() {
		return ddp_tipzon;
	}

	public void setDdp_tipzon(String ddp_tipzon) {
		this.ddp_tipzon = ddp_tipzon;
	}

	public String getDdp_tpoemp() {
		return ddp_tpoemp;
	}

	public void setDdp_tpoemp(String ddp_tpoemp) {
		this.ddp_tpoemp = ddp_tpoemp;
	}

	public String getDdp_ubigeo() {
		return ddp_ubigeo;
	}

	public void setDdp_ubigeo(String ddp_ubigeo) {
		this.ddp_ubigeo = ddp_ubigeo;
	}

	public String getDdp_userna() {
		return ddp_userna;
	}

	public void setDdp_userna(String ddp_userna) {
		this.ddp_userna = ddp_userna;
	}

	public String getDesc_ciiu() {
		return desc_ciiu;
	}

	public void setDesc_ciiu(String desc_ciiu) {
		this.desc_ciiu = desc_ciiu;
	}

	public String getDesc_dep() {
		return desc_dep;
	}

	public void setDesc_dep(String desc_dep) {
		this.desc_dep = desc_dep;
	}

	public String getDesc_dist() {
		return desc_dist;
	}

	public void setDesc_dist(String desc_dist) {
		this.desc_dist = desc_dist;
	}

	public String getDesc_estado() {
		return desc_estado;
	}

	public void setDesc_estado(String desc_estado) {
		this.desc_estado = desc_estado;
	}

	public String getDesc_flag22() {
		return desc_flag22;
	}

	public void setDesc_flag22(String desc_flag22) {
		this.desc_flag22 = desc_flag22;
	}

	public String getDesc_identi() {
		return desc_identi;
	}

	public void setDesc_identi(String desc_identi) {
		this.desc_identi = desc_identi;
	}

	public String getDesc_numreg() {
		return desc_numreg;
	}

	public void setDesc_numreg(String desc_numreg) {
		this.desc_numreg = desc_numreg;
	}

	public String getDesc_prov() {
		return desc_prov;
	}

	public void setDesc_prov(String desc_prov) {
		this.desc_prov = desc_prov;
	}

	public String getDesc_tamano() {
		return desc_tamano;
	}

	public void setDesc_tamano(String desc_tamano) {
		this.desc_tamano = desc_tamano;
	}

	public String getDesc_tipvia() {
		return desc_tipvia;
	}

	public void setDesc_tipvia(String desc_tipvia) {
		this.desc_tipvia = desc_tipvia;
	}

	public String getDesc_tipzon() {
		return desc_tipzon;
	}

	public void setDesc_tipzon(String desc_tipzon) {
		this.desc_tipzon = desc_tipzon;
	}

	public String getDesc_tpoemp() {
		return desc_tpoemp;
	}

	public void setDesc_tpoemp(String desc_tpoemp) {
		this.desc_tpoemp = desc_tpoemp;
	}

	/**
     * Obtiene el valor de la propiedad esActivo.
     * 
     */
    public String isEsActivo() {
        return esActivo;
    }

    /**
     * Define el valor de la propiedad esActivo.
     * 
     */
    public void setEsActivo(String value) {
        this.esActivo = value;
    }

    /**
     * Obtiene el valor de la propiedad esHabido.
     * 
     */
    public String isEsHabido() {
        return esHabido;
    }

    /**
     * Define el valor de la propiedad esHabido.
     * 
     */
    public void setEsHabido(String value) {
        this.esHabido = value;
    }

}
