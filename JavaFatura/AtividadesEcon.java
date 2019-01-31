
/**
 * Escreva a descrição da classe AtividadesEcon aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.lang.Iterable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class AtividadesEcon implements java.io.Serializable{
    private Map<String,AtividadeEcon> atividades;
    
    private static final long serialVersionUID = 3L;
    
    /**
    * Construtor vazio de AtividadesEcon.
    */
    
    public AtividadesEcon(){
        this.atividades = new HashMap<>();
    }
    
    
    /**
    * Construtor parametrizado de AtividadesEcon.
    */
    public AtividadesEcon(Map<String,AtividadeEcon> novos){
        this.atividades = new HashMap<>();
        for(Map.Entry<String,AtividadeEcon> par:novos.entrySet()){
            this.atividades.put(par.getKey(),(par.getValue()).clone());
        }
    }
    
    /**
    * Construtor de cópia de AtividadesEcon.
    */
    
    public AtividadesEcon(AtividadesEcon ae){
        this.atividades = ae.getAtividadesEcon();
    }
    
     /**
    * Método que devolve a atividade ecónomica com determinada natureza.
    * 
    * @param natureza Natureza da atividade.
    *  
    * @return Atividade económica
    */
    
    public AtividadeEcon getAtividadeEcon(String natureza){
        return this.atividades.get(natureza);
    }
    
      /**
    * Método que devolve uma lista das atividades económicas.
    * 
    * @param natureza Natureza da atividade.
    *  
    * @return Atividade económica
    */
    
    public List<AtividadeEcon> getAtividadeEcon(){
        List<AtividadeEcon> res = new ArrayList<AtividadeEcon>();
        for(AtividadeEcon ae : this.atividades.values())
            res.add(ae.clone());
        return res;
    }
    
     /**
    * Método que devolve um Map com a natureza e a atividade económica.
    * 
    *  
    * @return Mapa com natureza e atividade económica.
    */
    
    public Map<String,AtividadeEcon> getAtividadesEcon() {
        return this.atividades.values().stream().
        collect(Collectors.toMap((c) -> c.getAtividade(),(c) -> c.clone())); 
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto AtividadesEcon.
    */
    
    public String toString() {
     StringBuffer sb = new StringBuffer();
     for (AtividadeEcon c: this.atividades.values())
       sb.append(c.toString() + "\n");
     return sb.toString(); 
   }
   
   /**
    * Implementação do método de igualdade entre duas AtividadesEcon.
    * 
    * @param o AtividadesEcon que é comparada com o recetor
    * @return Booleano True ou False.
    */
   
   public boolean equals(Object o) {
      if (this == o) 
        return true;
      if ((o == null) || (this.getClass() != o.getClass()))
        return false;
      AtividadesEcon c = (AtividadesEcon) o;
      return this.atividades.equals(c.getAtividadeEcon());
       
   }    
   
   /**
    * Método de clonagem de uma AtividadesEcon.
    * 
    * @return Objeto do tipo AtividadesEcon.
    */
   
   public AtividadesEcon clone() {
     return new AtividadesEcon(this); 
   }
   
   
   //////////////////// Métodos
   
   /**
    * Método que adiciona uma atividade económica à lista de atividades.
    * 
    * @param ae AtividadeEcon
    */
   public void addAtividade(AtividadeEcon ae){
       this.atividades.put(ae.getAtividade(), ae.clone());
   }
   
   /**
    * 
    * Método que verifica se uma atividade económica pertence à lista. 
    * 
    * @param ae Atividade Económica a verificar.
    */
   
   public boolean contains_(AtividadeEcon ae){
       if(this.atividades.containsKey(ae)) return true; 
       else return false;
    }
   
   
   /**
    * Método que carrega a estrutura das atividades económicas.
    * 
    * @param nomeFicheiro Nome do ficheiro com a estrutura.
    * 
    * @return Estrutura AtividadesEcon
    */
   public static AtividadesEcon loadTA(String nomeFicheiro) throws FileNotFoundException,IOException,
                                                     ClassNotFoundException {
       FileInputStream f = new FileInputStream(nomeFicheiro);
       ObjectInputStream o = new ObjectInputStream(f);
       
       AtividadesEcon ae = (AtividadesEcon) o.readObject();
       o.close();
       return ae;                                    
    }
    
   /**
    * Método que guarda o estado da estrutura AtividadesEcon.
    * 
    * @param nomeFicheiro Nome do ficheiro com a estrutura AtividadesEcon.
    * 
    */ 
   public void guardaTA (String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
   }
}
