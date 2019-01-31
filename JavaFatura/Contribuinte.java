/**
 * Classe Contribuinte que define as propriedades do Contribuinte.
 * 
 * @author cjb
 * @version 20180307
 */

import java.util.Arrays;
import java.lang.Object;
import java.io.Serializable;

public class Contribuinte implements Serializable{
    ////////////// Variáveis de instância
    private int nif;
    private String email;
    private String nome;
    private String morada;
    private String password;
    private double euros;

    ////////////// Construtores
    /**
    * Construtor vazio de Contribuinte.
    */
    public Contribuinte(){
        this.nif = 0;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.password = "";
        this.euros=0;
    }

    /**
    * Construtor parametrizado de Contribuinte.
    */
    public Contribuinte (int nnif, String nemail, String nnome, String nmorada, 
        String npassword,double neuros){
        this.nif = nnif;
        this.email = nemail;
        this.nome = nnome;
        this.morada = nmorada;
        this.password = npassword;
        this.euros=neuros;
    }

    /**
    * Construtor de cópia de Contribuinte.
    */
    public Contribuinte(Contribuinte umContribuinte){
        this.nif = umContribuinte.getNif();
        this.email = umContribuinte.getEmail();
        this.nome = umContribuinte.getNome();
        this.morada = umContribuinte.getMorada();
        this.password = umContribuinte.getPassword();
        this.euros = umContribuinte.getEuros();
    }

    //////////  Getters
    /**
    * Método que devolve o NIF(Número de Identificação Fiscal) do Contribuinte.
    * 
    * @return NIF do Contribuinte.
    */
    public int getNif(){
        return this.nif;
    }

    /**
    * Método que devolve o Email de contacto do Contribuinte.
    * 
    * @return Email de contacto do Contribuinte.
    */
    public String getEmail(){
        return this.email;
    }

    /**
    * Método que devolve o Nome do Contribuinte.
    * 
    * @return Nome do Contribuinte.
    */
    public String getNome(){
        return this.nome;
    }
    
    /**
    * Método que devolve a Morada do Contribuinte.
    * 
    * @return Morada do Contribuinte.
    */
    public String getMorada(){
        return this.morada;
    }

    /**
    * Método que devolve a Password de acesso do Contribuinte.
    * 
    * @return Password de acesso do Contribuinte.
    */
    public String getPassword(){
        return this.password;
    }
    
    
    /**
     * Método que devolve o dinheiro faturado ou gasto por um Contribuinte.
     * 
     * @return Dinheiro faturado/gasto do Contribuinte.
     * 
     */
    
    public double getEuros() {
        return this.euros;
    }
    /////////// Setters
    /**
    * Atualiza o Nome do Contribuinte.
    * 
    * @param n Novo Nome do Contribuinte
    */
    public void setNif(int n){
        this.nif = n;
    }
    
    /**
    * Atualiza o Email do Contribuinte.
    * 
    * @param n Novo Email do Contribuinte
    */
    public void setEmail(String n){
        this.email = n;
    }

    /**
    * Atualiza o Nome do Contribuinte.
    * 
    * @param n Novo Nome do Contribuinte
    */
    public void setNome(String n){
        this.nome = n;
    }

    /**
    * Atualiza a Morada do Contribuinte.
    * 
    * @param n Nova Morada do Contribuinte
    */
    public void setMorada(String n){
        this.morada = n;
    }
    
    /**
    * Atualiza a Password do Contribuinte.
    * 
    * @param n Nova Password do Contribuinte
    */
    public void setPassword(String n){
        this.password = n;
    }
    
    
    /**
     * Atualiza o valor gasto/faturado do Contribuinte.
     * 
     * @param n Novo valor gasto/faturado do Contribuinte
     */
    
    public void setEuros(double n) {
        this.euros = n;
    }
    ///////// Métodos Essenciais
    /**
    * Método de clonagem de um Contribuinte.
    * 
    * @return Objeto do tipo Contribuinte.
    */
    public Contribuinte clone(){
        return new Contribuinte(this);
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
            
        Contribuinte c = (Contribuinte) o;
        
        return (this.nif==(c.getNif())
                && this.email.equals(c.getEmail()) 
                && this.nome.equals(c.getNome())
                && this.morada.equals(c.getMorada()) 
                && this.password.equals(c.getMorada()));
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto Contribuinte.
    */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("NIF: "); sb.append(this.nif);
        sb.append(", Email: "); sb.append(this.email);
        sb.append(", Nome: "); sb.append(this.nome);
        sb.append(", Morada: "); sb.append(this.morada);
        sb.append(", Password: "); sb.append(this.password);
        return sb.toString();
    }
    /**
     * 
     * Método que acede a um contribuinte e atualiza o seu gasto ou faturação total.
     * 
     * @param nif NIF do cliente ou empresa.
     * @param tf Faturas.
     * 
     */
    public void atualizaEuros ( int nif, TodasFaturas tf) {
        double valor=0;
        
        for (Fatura f : tf.getFaturas()) {
            if ( nif == f.getNifCliente() || nif == f.getNifEmpresa()){
                valor += f.getValor();
            }
        }
        setEuros(valor);
    }
}