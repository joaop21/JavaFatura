/**
 * Classe Fatura que alberga despesas que podem ser feitas pelos contribuintes.
 * 
 * @author cjb
 * @version 20180307
 */

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fatura implements java.io.Serializable{
    ////////////// Variáveis de instância
    private int id;
    private int nif_empresa;
    private String nome;
    private LocalDate data;
    private int nif_cliente;
    private String descricao;
    private String natureza; //atividade economica a que diz respeito
    private double valor;
    private boolean validez;
    
    ////////////// Construtores
    /**
    * Construtor vazio de Fatura.
    */
    public Fatura() {
        this.id = 0;
        this.nif_empresa = 0;
        this.nome = "";
        this.data = LocalDate.now();
        this.nif_cliente = 0;
        this.descricao = "";
        this.natureza = "";
        this.valor = 0;
        this.validez = false;
    }
    
    /**
    * Construtor parametrizado de Fatura.
    */
    public Fatura(int id, int nnif_empresa, String nnome, LocalDate ndata, int nnif_cliente, String ndescricao,
                  String nnatureza, double nvalor, boolean validez) {
        this.id = id;
        this.nif_empresa = nnif_empresa;
        this.nome = nnome;
        this.data = ndata;
        this.nif_cliente= nnif_cliente;
        this.descricao=ndescricao;
        this.natureza = nnatureza;
        this.valor = nvalor;
        this.validez = validez;
    }
    
    /**
    * Construtor de cópia de Fatura.
    */
    public Fatura(Fatura umaFatura) {
        this.id=umaFatura.getId();
        this.nif_empresa=umaFatura.getNifEmpresa();
        this.nome=umaFatura.getNome();
        this.data=umaFatura.getData();
        this.nif_cliente=umaFatura.getNifCliente();
        this.descricao=umaFatura.getDescricao();
        this.natureza=umaFatura.getNatureza();
        this.valor=umaFatura.getValor();
        this.validez=umaFatura.getValidez();
    } 
    
    //////////  Getters
    /**
    * Método que devolve o ID da fatura.
    * 
    * @return ID da fatura.
    */
    public int getId() {
        return this.id;
    }
    
    /**
    * Método que devolve o NIF(Número de Identificação Fiscal) do emitente.
    * 
    * @return NIF do emitente.
    */
    public int getNifEmpresa() {
        return this.nif_empresa;
    }
    
    /**
    * Método que devolve o Nome do emitente.
    * 
    * @return Nome do emitente.
    */
    public String getNome() {
        return this.nome;
    }
    
    /**
    * Método que devolve a Data da despesa.
    * 
    * @return Data da despesa.
    */
    public LocalDate getData() {
        return this.data;
    }
    
    /**
    * Método que devolve o NIF do cliente.
    * 
    * @return NIF do cliente.
    */
    public int getNifCliente() {
        return this.nif_cliente;
    }
    
    /**
    * Método que devolve a Descrição da despesa.
    * 
    * @return Descrição da despesa.
    */
    public String getDescricao() {
        return this.descricao;
    }
    
    /**
    * Método que devolve a Natureza da despesa, isto é, a atividade 
    * económica a que diz respeito.
    * 
    * @return Natureza da despesa.
    */
    public String getNatureza() {
        return this.natureza;
    }
    
    /**
    * Método que devolve o Valor da despesa.
    * 
    * @return Valor da despesa.
    */
    public double getValor() {
        return this.valor;
    }
    
    /**
    * Método que devolve a validez da fatura.
    * 
    * @return Validez da fautura.
    */
    public boolean getValidez(){
        return this.validez;
    }
    
    /////////// Setters
    /**
    * Atualiza o ID da fatura.
    * 
    * @param id Novo id da fatura
    */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
    * Atualiza o NIF(Número de Identificação Fiscal) do emitente.
    * 
    * @param nnif_empresa Novo número fiscal do emitente
    */
    public void setNifEmpresa(int nnif_empresa) {
        this.nif_empresa=nnif_empresa;
    }
    
    /**
    * Atualiza o Nome do emitente.
    * 
    * @param nnome Novo nome do emitente.
    */
    public void setNome(String nnome) {
        this.nome=nnome;
    }
    
    /**
    * Atualiza a Data da despesa.
    * 
    * @param ndata Nova data da despesa.
    */
    public void setData(LocalDate ndata) {
        this.data=ndata;
    }
    
    /**
    * Atualiza o NIF do cliente.
    * 
    * @param nnif_cliente Novo NIF do cliente.
    */
    public void setNifCliente(int nnif_cliente) {
        this.nif_cliente=nnif_cliente;
    }
    
    /**
    * Atualiza a Descrição da despesa.
    * 
    * @param ndescricao Nova descrição da despesa.
    */
    public void setDescricao(String ndescricao) {
        this.descricao=ndescricao;
    }
    
    /**
    * Atualiza Natureza da despesa, isto é, a atividade 
    * económica a que diz respeito.
    * 
    * @return nnatureza Nova natureza da despesa.
    */
    public void setNatureza(String nnatureza) {
        this.natureza=nnatureza;
    }
    
    /**
    * Atualiza o Valor da despesa.
    * 
    * @param nvalor Novo valor da despesa.
    */
    public void setValor (double nvalor) {
        this.valor=nvalor;
    }
    
    /**
    * Atualiza a validez da fatura.
    * 
    * @param validez Novo estado de validade da fatura.
    */
    public void setValidez(boolean validez){
        this.validez=validez;
    }
    
    ///////// Métodos Essenciais    
    /**
    * Método de clonagem de uma Fatura.
    * 
    * @return Objeto do tipo Fatura.
    */
    public Fatura clone() {
        return new Fatura(this);
    }
    
    /**
    * Implementação do método de igualdade entre duas Faturas.
    * 
    * @param o Fatura que é comparada com o recetor
    * @return Booleano True ou False.
    */
    public boolean equals (Object o) {
        if (this == o) 
            return true;
            
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        
        Fatura f = (Fatura) o;
        
        return (this.id == f.getId() &&
                    this.nif_empresa == f.getNifEmpresa() && 
                    this.nome.equals(f.getNome()) &&
                    this.data.equals(f.getData()) &&
                    this.nif_cliente == f.getNifCliente() &&
                    this.descricao.equals(f.getDescricao()) &&
                    this.natureza.equals(f.getNatureza()) &&
                    this.valor == f.getValor() &&
                    this.validez == f.getValidez());
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto Fatura.
    */
    public String toString() {
       StringBuilder sb = new StringBuilder();
       
       sb.append("################# Fatura #################\n");
       sb.append("ID: "); sb.append(this.id + "\n");
       sb.append("NIF da Empresa: "); sb.append(this.nif_empresa + "\n");
       sb.append("Nome da Empresa: "); sb.append(this.nome + "\n");
       sb.append("Data de Emissão: "); sb.append(this.data + "\n");
       sb.append("NIF do cliente: "); sb.append(this.nif_cliente + "\n");
       sb.append("Descrição: "); sb.append(this.descricao + "\n");
       sb.append("Natureza da transação: "); sb.append(this.natureza + "\n");
       sb.append("Valor da transação: "); sb.append(this.valor + " €\n");
       sb.append("Validez: "); sb.append(this.validez + "\n");
       sb.append("##########################################\n\n");
       
       return sb.toString();
    }
    
    ///////// Métodos
    
    /**
     * Método que deduz a despesa de uma determinada fatura.
     * 
     * @param atividades Atividades económicas
     * @param td Estrutura com os contribuintes.
     * 
     * @return Valor da dedução fiscal.
     */
    public double deducaoDespesa(AtividadesEcon atividades, TodosContribuintes td) throws AtividadeNaoExisteException{
        AtividadeEcon a = atividades.getAtividadeEcon(this.natureza);
        if(a == null) throw new AtividadeNaoExisteException("Atividade " +  a + " não existe");
        
        if(this.validez == false) return 0;
        else{
            ContIndividual cliente = (ContIndividual) td.getContribuinte(this.nif_cliente);
            List<String> cods = cliente.getCodigos();
            ContEmpresa empresa = (ContEmpresa) td.getContribuinte(this.nif_empresa);
            
            if(cods.contains(this.natureza) == true) {
                double coeficiente_total = a.getCoeficiente()-cliente.getCoeficiente();
                
                if(cliente instanceof FamiliaNumerosa){
                    FamiliaNumerosa numeroso = (FamiliaNumerosa) cliente;
                    coeficiente_total -= numeroso.reducaoImposto();
                }
                
                if(empresa instanceof EmpresaInterior){
                    EmpresaInterior interior = (EmpresaInterior) empresa;
                    coeficiente_total -= interior.reducaoImposto();
                }
                
                if(coeficiente_total < 0 ) return 0;
                else return this.valor * (coeficiente_total);
            }
            else return 0;
        }
    }
    
}