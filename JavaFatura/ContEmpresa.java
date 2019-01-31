/**
 * Classe ContEmpresa que define as propriedades dos Contribuintes Empresa.
 * 
 * @author cjb
 * @version 20180307
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class ContEmpresa extends Contribuinte implements java.io.Serializable{
    ////////////// Variáveis de instância
    private List<String> codigos; //informação sobre as atividades economicas nas quais atua a empresa
    
    ////////////// Construtores
    /**
    * Construtor vazio de ContEmpresa.
    */
    public ContEmpresa() {
        super();
        this.codigos = new ArrayList<>();
    }
    
    /**
    * Construtor parametrizado de ContEmpresa.
    * 
    * @param nnif NIF da empresa.
    * @param nemail E-mail da empresa.
    * @param nmorada Morada da empresa.
    * @param npassword Password da conta.
    * @param ncodigos Lista com os códigos das atividades económicas.
    * @param ndeducao Dedução fiscal da empresa.
    * @param neuros Total faturado pela empresa.
    * 
    */
    public ContEmpresa(int nnif, String nemail, String nnome, String nmorada, String npassword, List<String> ncodigos,double neuros) {
        super(nnif,nemail,nnome,nmorada,npassword,neuros);
        setCodigos(ncodigos);
    } 
    
    /**
    * Construtor de cópia de ContEmpresa.
    * 
    * @param umContEmpresa Contribuinte Empresa.
    */
    public ContEmpresa(ContEmpresa umContEmpresa) {
        super(umContEmpresa.getNif(), umContEmpresa.getEmail(),
              umContEmpresa.getNome(), umContEmpresa.getMorada(), 
              umContEmpresa.getPassword(), umContEmpresa.getEuros());
        this.codigos= umContEmpresa.getCodigos();
    }
    
    //////////  Getters
    /**
    * Método que devolve os códigos das atividades económicas para as
    * quais um determinado Contribuinte tem possibilidade de deduzir
    * despesas.
    * 
    * @return Códigos das atividades económicas do Contribuinte.
    */ 
    public List<String> getCodigos() {
        List<String> res = new ArrayList<>();
        for(String s : this.codigos) {
            res.add(s);
        }
        return res;
    }  
    
    /////////// Setters
    /**
    * Atualiza os códigos das atividades económicas para as quais um
    * determinado Contribuinte tem possibilidade de deduzir despesas.
    * 
    * @param n Novo código do Contribuinte
    * @param i Posição do Novo código a ser alterado
    */
    public void setCodigos(List<String> ncodigos) {
        this.codigos = new ArrayList<>();
        for(String codigo : ncodigos) {
            this.codigos.add(codigo);
        }
    }
    
    ///////// Métodos Essenciais   
    /**
    * Método de clonagem de um ContEmpresa.
    * 
    * @return Objeto do tipo ContEmpresa.
    */
    public ContEmpresa clone() {
        return new ContEmpresa(this);
    }
    
    /**
    * Implementação do método de igualdade entre dois ContribuintesEmpresa.
    * 
    * @param o ContEmpresa que é comparado com o recetor
    * @return Booleano True ou False.
    */
    public boolean equals (Object o) {
        if (this == o) 
            return true;
            
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        
        ContEmpresa c = (ContEmpresa) o;
        
        return (super.equals(c) &&
                this.codigos.equals(c.getCodigos()));
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto ContEmpresa.
    */
    public String toString() {
       StringBuilder sb = new StringBuilder();
       
       super.toString();
       sb.append(", Códigos: "); sb.append(this.codigos);
       
       return sb.toString();
    }
    
    ///////// Métodos
    public Fatura emiteFatura(int nifCliente, String descricao,
    double valorDespesa,TodasFaturas tf, TodosContribuintes td) throws InputMismatchException,NIFNaoExisteException{
        
        if(td.containsKey_(nifCliente) == false) throw new NIFNaoExisteException("NIF " +  nifCliente + " não existe");
        String natureza = null;
        boolean validez = false;
        if(this.codigos.size() == 1){
            natureza = this.codigos.get(0); // suponho que se so tiver um elemento ele está na posiçao 0
            validez = true;
        }
        
        atualizaEuros(nifCliente,tf);
        atualizaEuros(getNif(),tf);
        
        int id = tf.getFaturas().size();
        
        Fatura f = new Fatura(id, getNif(), getNome(), LocalDate.now(), 
        nifCliente, descricao, natureza, valorDespesa, validez);
        tf.addFatura(f);
        return f;
    }
    
}