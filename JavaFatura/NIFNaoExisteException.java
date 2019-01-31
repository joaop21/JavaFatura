
/**
 * Escreva a descrição da classe FaturaNaoExisteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class NIFNaoExisteException extends Exception{
    
    public NIFNaoExisteException(){
        super();
    }
    
    public NIFNaoExisteException(String cod){
        super(cod);
    }
}
