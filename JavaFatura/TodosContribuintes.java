
/**
 * Escreva a descrição da classe TodosContribuintes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class TodosContribuintes implements java.io.Serializable{
    private Map<Integer,Contribuinte> todos;
    
    private static final long serialVersionUID = 1L;
    
    /**
    * Construtor vazio de TodosContribuintes.
    */
    public TodosContribuintes(){
        this.todos = new HashMap<>();
    }
    
    /**
    * Construtor parametrizado de TodosContribuintes.
    * 
    * @param novos Estrutura com a chave e o contribuinte.
    * 
    * @return Estrutura TodosContribuintes.
    * 
    */
    
    public TodosContribuintes(Map<Integer,Contribuinte> novos){
        this.todos = new HashMap<>();
        for(Map.Entry<Integer,Contribuinte> par:novos.entrySet()){
            this.todos.put(par.getKey(),(par.getValue()).clone());
        }
    }
    /**
     * 
     * Construtor de cópia de TodosContribuintes.
     */
    
    public TodosContribuintes(TodosContribuintes tc){
        this.todos = tc.getTodosContribuintes();
    }
    
    /**
     * Método que devolve um contribuinte.
     * 
     * @param nif NIF do contribuinte.
     * 
     * @return Contribuinte.
     * 
     */
    
    public Contribuinte getContribuinte(int nif){
        for(Contribuinte c : this.todos.values()){
            if(c.getNif() == nif) return c.clone();
        }
        return null;
    }
    
    /**
     * Método que devolve uma lista com todos os contribuintes.
     * 
     * @return List com os contribuintes.
     */
    
    public List<Contribuinte> getContribuintes(){
        List<Contribuinte> res = new ArrayList<Contribuinte>();
        for(Contribuinte c : this.todos.values())
            res.add(c.clone());
        return res;
    }
    /**
     * 
     * Método que devolve todos os contribuintes com a sua chave.
     * 
     * @return Map com chave e contribuinte.
     */
    
    public Map<Integer,Contribuinte> getTodosContribuintes() {
        return this.todos.values().stream().
        collect(Collectors.toMap((c) -> c.getNif(),(c) -> c.clone())); 
    }

   
   
   //////////////////////////////// Métodos Essenciais
   /**
    * 
    * Implementação do método toString.
    * 
    * @return String com todos os contribuintes.
    */
   
   public String toString() {
     StringBuffer sb = new StringBuffer();
     for (Contribuinte c: this.todos.values())
     sb.append(c.toString() + "\n");
     return sb.toString(); 
   }
   
   /**
    * Implementação do método de igualdade entre duas estruturas TodosContribuintes.
    * 
    * @param o TodosContribuintes que é comparada com o recetor
    * @return Booleano True ou False.
    */
   
   public boolean equals(Object o) {
      if (this == o) 
        return true;
      if ((o == null) || (this.getClass() != o.getClass()))
        return false;
      TodosContribuintes c = (TodosContribuintes) o;
      return this.todos.equals(c.getContribuintes());
       
   }    
    /**
    * Método de clonagem de uma estrutura TodosContribuintes.
    * 
    * @return Objeto do tipo TodosContribuintes.
    */
   public TodosContribuintes clone() {
     return new TodosContribuintes(this); 
   }
   
   
   //////////////////////////////// Métodos
   /**
    * Método que adiciona um contribuinte à lista de todos os contribuintes.
    * 
    * @param cb Contribuinte.
    */
   
   public void addContribuinte(Contribuinte cb){
        this.todos.put(cb.getNif(), cb);
   }
   
   /**
    * Método que verifica se uma chave existe na estrutura.
    * 
    * @param key Chave.
    * 
    * @return Boolean true ou false.
    */
   public boolean containsKey_(Integer key){
       if(this.todos.containsKey(key)) return true; 
       else return false;
   }
   
   /** Método que verifica se um determinado Contribuinte existe na estrutura.
    * 
    * @param value Contribuinte.
    * ´
    * @return Boolean true ou false.
    */
   
   public boolean containsValue_(Contribuinte value){
       if(this.todos.containsValue(value)) return true; 
       else return false;
    }
    
    /**
     * Método que devolve os códigos de um contribuinte Empresa.
     * 
     * @param nif NIF da empresa.
     * @param td TodosContribuintes.
     * 
     * @return Lista de Strings com os códigos.
     */
    
   public List<String> codigos(int nif, TodosContribuintes td) throws NIFNaoExisteException{
       if(td.containsKey_(nif) == false) throw new NIFNaoExisteException("NIF " +  nif + " não existe");
       for(Contribuinte c : this.todos.values()){
           if(c.getNif() == nif){
               if(c instanceof ContEmpresa)
                    return ((ContEmpresa) c).getCodigos();
           }
       }
       return null;
   }
   
   
   
   public class ComparatorGasto implements Comparator<Contribuinte>{
        public int compare(Contribuinte c1, Contribuinte c2) {
            if(c1.getEuros() > c2.getEuros()) {
                return 1;
            }
            return -1;
        }
    }
   
   /**
    * Método que devolve um TreeSet com os  10 contribuintes individuais mais gastadores.
    * 
    * @param tf Estrutura com todas as faturas.
    * 
    * @return TreeSet com os contribuintes individuais.
    * 
    */
   public Set<ContIndividual> dezGastadores (TodasFaturas tf) {
       
       double gasto;
       
       TreeSet<ContIndividual> dez = new TreeSet<ContIndividual>(new ComparatorGasto());
       
       for (Contribuinte c : this.todos.values()) {
           if(c instanceof ContIndividual){ // se é individual calcula o seu gasto
                ContIndividual c1 = (ContIndividual) c;
                dez.add(c1.clone());
           }
        }
       return dez.stream().limit(10).collect(Collectors.toSet());
        
    }
   
    /**
     * Método que devolve as X empresas que mais faturam.
     * 
     * @param atividades Atividades económicas.
     * @param tf Todas as faturas.
     * @param x Número de empresas a devolver.
     * 
     * @return Map com o ID da empresa e o valor da faturação.
     */
    
   public Map<Integer,Double> xEmpresas (AtividadesEcon atividades, TodasFaturas tf,int x)
       throws AtividadeNaoExisteException {
       
       double gasto;
       
       TreeSet<ContEmpresa> empresas = new TreeSet<ContEmpresa>(new ComparatorGasto());
       
       for (Contribuinte c : this.todos.values()) {
           if(c instanceof ContEmpresa){ // se é individual calcula o seu gasto
                ContEmpresa c1 = (ContEmpresa) c;
                empresas.add(c1.clone());
           }
        }
       
       TreeSet<ContEmpresa> xempresas = (TreeSet) empresas.stream().limit(x).collect(Collectors.toSet());
       
       Map<Integer,Double> res = new HashMap<>();
       
       for(ContEmpresa ce : xempresas){
           double totaldeducao = 0.0;
           for(Fatura f : tf.getFaturas()){
               if(f.getNifEmpresa() == ce.getNif()){
                   totaldeducao += f.deducaoDespesa(atividades,this.clone());
               }
           }
           res.put(ce.getNif(),totaldeducao);
       }
       return res;
   }
   
   /**
    * 
    * Método que carrega o estado da estrutura TodosContribuintes.
    * 
    * @param nomeFicheiro String com o nome do ficheiro que contém o estado.
    * 
    * @return Estrutura TodosContribuintes.
    */
    public  static TodosContribuintes loadTD(String nomeFicheiro) throws FileNotFoundException,IOException,
                                                     ClassNotFoundException {
       FileInputStream f = new FileInputStream(nomeFicheiro);
       ObjectInputStream o = new ObjectInputStream(f);
       
       TodosContribuintes td = (TodosContribuintes) o.readObject();
       
       o.close();
       return td;                                    
   }
   
   /**
    * 
    * Método que guarda o estado atual de uma estrutura TodosContribuintes.
    * 
    * @param nomeFicheiro String com o nome do ficheiro onde vai ser guardado o estado.
    * 
    */
   public  void guardaTD (String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
   }
}