
/**
 * Classe ContIndividual que define as propriedades do Contribuinte Individual.
 * 
 * @author cjb
 * @version 20180501
 */

import java.util.ArrayList;
import java.util.List;

public class ContIndividual extends Contribuinte implements java.io.Serializable {
    ////////////// Variáveis de instância
    private int agregado_familiar;
    private List<Integer> nifs_agregado;
    private double coeficiente_fiscal;
    private List<String> codigos;
    // preciso fazer HashMap que associe cada fatura a um coeficiente
    
    ////////////// Construtores
    /**
    * Construtor vazio de ContIndividual.
    */
    public ContIndividual(){
        super();
        this.agregado_familiar = 1;
        this.nifs_agregado = new ArrayList<>();
        this.coeficiente_fiscal = 0;
        this.codigos = new ArrayList<>();
    }
    
    /**
    * Construtor parametrizado de ContIndividual.
    */
    public ContIndividual(int nnif, String nemail, String nnome, String nmorada, 
        String npassword, int nagregado_familiar, List<Integer> nnifs, 
        double ncoeficiente, List<String> ncodigos,double neuros){
        super(nnif,nemail,nnome,nmorada,npassword,neuros);
        this.agregado_familiar = nagregado_familiar;
        setNifs_Agregado(nnifs);
        this.coeficiente_fiscal = ncoeficiente;
        setCodigos(ncodigos);
    }
    
    /**
    * Construtor de cópia de ContIndividual.
    */
    public ContIndividual(ContIndividual umContIndividual){
        super(umContIndividual.getNif(), umContIndividual.getEmail(),
              umContIndividual.getNome(), umContIndividual.getMorada(), 
              umContIndividual.getPassword(),umContIndividual.getEuros());
        this.agregado_familiar = umContIndividual.getAgregado_familiar();
        this.nifs_agregado = umContIndividual.getNifs_Agregado();
        this.coeficiente_fiscal = umContIndividual.getCoeficiente();
        this.codigos = umContIndividual.getCodigos();
    }

    //////////  Getters 
    /**
    * Método que devolve o Número de dependendentes do 
    * agregado familiar do Contribuinte.
    * 
    * @return Número de dependentes do agregado familiar do Contribuinte.
    */
    public int getAgregado_familiar(){
        return this.agregado_familiar;
    }

    /**
    * Método que devolve os NIF's do agregado familiar do Contribuinte.
    * 
    * @return NIF's do agregado familiar do Contribuinte.
    */  
    public List<Integer> getNifs_Agregado() {
        List<Integer> res = new ArrayList<>();
        for(Integer s : nifs_agregado) {
            res.add(s);
        }
        return res;
    }  

    /**
    * Método que devolve o Coeficiente Fiscal para efeitos de 
    * dedução do Contribuinte.
    * 
    * @return Coeficiente Fiscal do Contribuinte.
    */
    public double getCoeficiente(){
        return this.coeficiente_fiscal;
    }
    
    /**
    * Método que devolve os códigos das atividades económicas para as
    * quais um determinado Contribuinte tem possibilidade de deduzir
    * despesas.
    * 
    * @return Códigos das atividades económicas do Contribuinte.
    */ 
    public List<String> getCodigos() {
        List<String> res = new ArrayList<>();
        for(String s : codigos) {
            res.add(s);
        }
        return res;
    }     
    
    /////////// Setters
    /**
    * Atualiza o Número de dependentes do agregado
    * familiar do Contribuinte.
    * 
    * @param n Novo Número do agregado familiar do Contribuinte
    */
    public void setAgregado(int n){
        this.agregado_familiar = n;
    }

    /**
    * Atualiza o Número Fiscal do agregado familiar do Contribuinte.
    * 
    * @param n Novo Número fiscal do agregado familiar do Contribuite
    * @param i Posição do Número fiscal a ser alterado
    */
    public void setNifs_Agregado(List<Integer> nifs_agregado) {
        this.nifs_agregado = new ArrayList<>();
        for(Integer nif : nifs_agregado) {
            this.nifs_agregado.add(nif);
        }
    }

    /**
    * Atualiza o Coeficiente fiscal para efeitos de 
    * dedução do Contribuinte.
    * 
    * @param n Novo Coeficiente fiscal do Contribuinte
    */    
    public void setCoeficiente(double n){
        this.coeficiente_fiscal = n;
    }

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
    * Método de clonagem de um Contribuinte.
    * 
    * @return Objeto do tipo Contribuinte.
    */
    public ContIndividual clone(){
        return new ContIndividual(this);
    }
    
    /**
    * Implementação do método de igualdade entre dois Contribuintes.
    * 
    * @param o Contribuinte que é comparado com o recetor
    * @return Booleano True ou False.
    */
    public boolean equals (Object o){
       if (this == o) 
            return true;
            
       if ((o == null) || (this.getClass()!=o.getClass())) 
            return false;
            
      ContIndividual c = (ContIndividual) o;
        
      return (super.equals(c) 
            && this.agregado_familiar == c.getAgregado_familiar()
            && this.nifs_agregado.equals(c.getNifs_Agregado())
            && this.coeficiente_fiscal == c.getCoeficiente()
            && this.codigos.equals(c.getCodigos())); 
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto Contribuinte.
    */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        super.toString();
        sb.append(", Número de agregado familiar: "); sb.append(this.agregado_familiar);
        sb.append(", NIF dos agregados: "); sb.append(this.nifs_agregado);
        sb.append(", Coeficiente fiscal: "); sb.append(this.coeficiente_fiscal);
        sb.append(", Códigos das atividades fiscais: "); sb.append(this.codigos);
        return sb.toString();
    }
}
