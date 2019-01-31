
/**
 * Escreva a descrição da classe TodasFaturas aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.lang.Iterable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Collections;

public class TodasFaturas implements java.io.Serializable{
    private List<Fatura> faturas;
    
    private static final long serialVersionUID = 2L;
   
    //////////////////////////////// Métodos Essenciais
    
    /**
    * Construtor vazio de ContEmpresa.
    */
    
    public TodasFaturas() {
        this.faturas = new ArrayList<>();
    }
    
    /**
    * Construtor parametrizado de ContEmpresa.
    * 
    * @param novasFaturas Lista com Faturas.
    */
    public TodasFaturas(List<Fatura> novasFaturas) {
        setFaturas(novasFaturas);
    }
    
    /**
    * Construtor vazio de ContEmpresa.
    * 
    * @param Estrutura TodasFaturas.
    */
    public TodasFaturas(TodasFaturas todas) {
        this.faturas = todas.getFaturas();
    }
    
    
    //////////////////////////////// Gets
    
     /**
    * Método que devolve uma lista com todas as faturas.
    * 
    * @return Lista de faturas.
    */
    
    public List<Fatura> getFaturas() {
      return this.faturas.stream().map(Fatura::clone).collect(Collectors.toList());        
    }
    
     /**
    * Método que recebe o ID de uma fatura e devolve a mesma.
    * 
    * @param id ID da fatura.
    * 
    * @return Fatura com ID id.
    */
   
    public Fatura getFatura(int id){
        for(Fatura f : this.faturas){
            if(f.getId() == id) return f.clone();
        }
        return null;
    }
    
    
    //////////////////////////////// Sets
    
    /**
    * Atualiza a lista das Faturas da estrutura TodasFaturas.
    * 
    * @param todas Lista com as novas faturas.
    */
    
    public void setFaturas(List<Fatura> todas) {
        this.faturas = new ArrayList<>();
        for(Fatura fatura : todas) {
            this.faturas.add(fatura.clone());
        }
    }
    
    
    //////////////////////////////// Métodos Essenciais
    
    /**
    * Método de clonagem de uma TodasFaturas.
    * 
    * @return Objeto do tipo TodasFaturas.
    */
    public TodasFaturas clone() {
        return new TodasFaturas(this);
    }
    
     /**
    * Implementação do método de igualdade entre duas TodasFaturas.
    * 
    * @param o TodasFaturas que é comparado com o recetor
    * @return Booleano True ou False.
    */
    
    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;
        TodasFaturas e = (TodasFaturas)o;
        return this.faturas.equals(e.getFaturas()); 
    }
    
    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto TodasFaturas.
    */
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.faturas.toString());
        return sb.toString();
    }
    
    
    //////////////////////////////// Métodos
    /**
     * Método que adiciona uma Fatura f a TodasFaturas.
     * 
     * @param f Fatura.
     */

    public void addFatura(Fatura f){ // não precisa exceção
        this.faturas.add(f.clone());
    }
    
    public boolean contains_(Fatura f){
       if(this.faturas.contains(f)) return true; 
       else return false;
    }
    
    /**
     * Método que devolve as despesas de um cliente.
     * 
     * @param nif NIF do cliente.
     *
     * @return Valor das despesas do cliente.
     */
    
    public double despesas(int nif) { // não precisa exceção
        double total=0;
        for (Fatura f : this.faturas) {
            if(nif == f.getNifCliente()){
                total += f.getValor();
            }
        }
        return total;
    }
    
    /**
     * Método que devolve as despesas de um agregado familiar. 
     * 
     * @param nifs Lista dos NIFs do agregado familiar.
     * 
     * @return Valor das despesas do agregado familiar.
     */
    
    public double despesasAgregado(List<Integer> nifs){ // não precisa exceção
        double total = 0;
        for(Integer nif : nifs){
            total += despesas(nif);
        }
        return total;
    }
    
    /**
     * Método que devolve o valor total da dedução das despesas de um cliente.
     * 
     * @param nif NIF do cliente.
     * @param atividades Atividades económicas.
     * @param td Estrutura TodosContribuintes.
     * 
     * @return Valor total das deduções.
     */
    public double deducaoDespesas(int nif, AtividadesEcon atividades,TodosContribuintes td){
        double total = 0;
        for(Fatura f : this.faturas){
            if(nif == f.getNifCliente()){
                try{
                    total += f.deducaoDespesa(atividades,td);
                }
                catch(AtividadeNaoExisteException e){
                    total+=0;
                }
            }
        }
        return total;
    }
    
    /**
     * Método que devolve o valor total da dedução das despesas de um agregado familiar.
     * 
     * @param nifs Lista com os NIFs do agregado familiar.
     * @param atividades Atividades económicas.
     * @param td TodosContribuintes.
     * 
     * @return Valor das deduções do agregado.
     */
    public double deducaoDespesasAgregado(List<Integer> nifs, AtividadesEcon atividades, TodosContribuintes td){
        double total = 0;
        for(Integer nif : nifs){
            total += deducaoDespesas(nif, atividades,td);
        }
        return total;
    }
    
    /**
     * Método que devolve a lista de faturas de um cliente.
     * 
     * @param nif NIF do cliente.
     * @param td TodosContribuintes
     * 
     * @return Lista com as faturas do cliente.
     */
    
    public List<Fatura> impressaoDespesas(int nif,TodosContribuintes td) throws NIFNaoExisteException{
        if(td.containsKey_(nif) == false) throw new NIFNaoExisteException("NIF " +  nif + " não existe");
        List<Fatura> res = new ArrayList<>();
        for (Fatura f : this.faturas) {
            if(nif == f.getNifCliente()){
                res.add(f);
            }
        }
        return res;
    }
    
    /**
     * Método que devolve a lista das faturas de um agregado familiar.
     * 
     * @param nifs Lista dos NIFs.
     * @param td TodosContribuintes
     * 
     */
    public List<Fatura> impressaoDespesasAgregado(List<Integer> nifs,TodosContribuintes td) throws NIFNaoExisteException{
        List<Fatura> res = new ArrayList<>();
        for(Integer nif : nifs){
            List<Fatura> aux = impressaoDespesas(nif,td);
            for(Fatura f : aux){
                res.add(f);
            }
        }
        return res;
    }
    
    /**
     * Método que valida a natureza de uma fatura.
     * 
     * @param id ID da fatura.
     * @param natureza Natureza.
     * @param tf TodasFaturas.
     * 
     */
    
    public void validaFatura(int id, String natureza,TodasFaturas tf) throws IDNaoExisteException{
        if(tf.getFatura(id) == null) throw new IDNaoExisteException("NIF " +  id + " não existe");
        for(Fatura f : this.faturas){
            if(f.getId() == id){
                f.setNatureza(natureza);
                f.setValidez(true);
                break;
            }
        }
    }
    
    /**
     * Método que carrega o estado de TodasFaturas guardado num ficheiro.
     * 
     * @param nomeFicheiro Nome do ficheiro com o estado.
     * 
     * @return Estrutura TodasFaturas.
     */


    public static TodasFaturas loadTF(String nomeFicheiro) throws FileNotFoundException,IOException,
                                                     ClassNotFoundException {
       FileInputStream f = new FileInputStream(nomeFicheiro);
       ObjectInputStream o = new ObjectInputStream(f);
       
       TodasFaturas tf = (TodasFaturas) o.readObject();
       o.close();
       return tf;                                    
    }
    
    /**
     * Método que guarda o estado de TodasFatura num ficheiro.
     * 
     * @param nomeFicheiro Nome do ficheiro onde vai ser guardado.
     * 
     * 
     */
    
    public void guardaTF (String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    /**
     * Método que calcula o total faturado num período.
     * 
     * @param dataInicio Data inicial.
     * @param dataFim Data final
     * 
     * @return Total faturado.
     * 
     */
    public double totalFaturado (String dataInicio, String dataFim) { // não precisa exceção
        LocalDate dataI = LocalDate.parse(dataInicio);
        LocalDate dataF = LocalDate.parse(dataFim);
        double total=0;
        for (Fatura f : this.faturas) {
            if (f.getData().isAfter(dataI) && f.getData().isBefore(dataF))
                total += f.getValor();
        }
        return total;
    }
    
    public class ComparatorData implements Comparator<Fatura>{
        public int compare(Fatura f1, Fatura f2) {
            if(f1.getData().isBefore(f2.getData())) {
                return -1;
            }
            return 1;
        }
    }
    
    /**
     * Método que ordena um TreeSet com faturas por ordem da mais antiga para a mais recente.
     * 
     * @param tf TodasFaturas.
     * 
     * @return TreeSet ordenado.
     */
    public TreeSet<Fatura> ordenaData (List<Fatura> tf) {
        TreeSet<Fatura> aux = new TreeSet<Fatura>(new ComparatorData());
        for(Fatura f : tf){
            aux.add(f.clone());
        }
        
        return aux;
    }
    
    public class ComparatorValor implements Comparator<Fatura>{
        public int compare(Fatura f1, Fatura f2) {
            if(f1.getValor() > f2.getValor()) {
                return -1;
            }
            return 1;
        }
    }
    
    /**
     * Método que ordena um TreeSet com faturas por ordem descendente de valor.
     * 
     * @param tf TodasFaturas.
     * 
     * @return TreeSet ordenado.
     */
     public TreeSet<Fatura> ordenaValor (List<Fatura> tf) {
        
        TreeSet<Fatura> aux = new TreeSet<Fatura>(new ComparatorValor());
        for(Fatura f : tf){
            aux.add(f.clone());
        }
        
        return aux;
    }
    
    /**
     * 
     * Método que devolve uma lista de Faturas de um cliente num determinado período.
     * 
     * @param nifc NIF do cliente.
     * @param dataInicio Data inicial.
     * @param dataFim Data final.
     * @param tf TodasFaturas.
     *
     * @return Lista de faturas.
     */
     public List<Fatura> faturasClienteSemOrd (int nifc, String dataInicio, String dataFim,TodosContribuintes td) throws NIFNaoExisteException{
        if(td.getContribuinte(nifc) == null) throw new NIFNaoExisteException("NIF " +  nifc + " não existe");
        LocalDate dataI = LocalDate.parse(dataInicio);
        LocalDate dataF = LocalDate.parse(dataFim);
        List<Fatura> tf1 = new ArrayList<>();

        for (Fatura f : this.faturas) {
            if ((f.getData().isAfter(dataI) && f.getData().isBefore(dataF)) && ((f.getNifCliente())== nifc))
                tf1.add(f.clone());
        }
        
        return tf1;
    }
    
    /**
     * 
     * Método que devolve uma lista de Faturas de um cliente por ordem decrescente de valor.
     * 
     * @param nifc NIF do cliente.
     * @param tf TodasFaturas.
     * 
     * @return TreeSet com as faturas.
     */
    public TreeSet<Fatura> faturasClienteComOrd(int nifc,TodosContribuintes td) throws NIFNaoExisteException{
        if(td.getContribuinte(nifc) == null) throw new NIFNaoExisteException("NIF " +  nifc + " não existe");
        List<Fatura> tf1 = new ArrayList<>();
        for(Fatura f: this.faturas) {
            if((f.getNifCliente())==nifc) 
                tf1.add(f.clone());
        }
        TreeSet<Fatura> aux = ordenaValor(tf1);
        return aux;
    }
    
    
}


