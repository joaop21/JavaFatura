
/**
 * Escreva a descrição da classe Concelho aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Concelho implements java.io.Serializable{
    private String nome;
    private double coeficiente;
    
    public Concelho(){
        this.nome = "";
        this.coeficiente = 0.0;
    }
    
    public Concelho(String nome, double coeficiente){
        this.nome = nome;
        this.coeficiente = coeficiente;
    }
    
    public Concelho(Concelho umConcelho){
        this.nome = umConcelho.getNome();
        this.coeficiente = umConcelho.getCoeficiente();
    }
    
    public String getNome(){return this.nome;}
    
    public double getCoeficiente(){return this.coeficiente;}
    
    public void setNome(String nome){this.nome = nome;}
    
    public void setCoeficiente(double coeficiente){this.coeficiente = coeficiente;}
    
    public Concelho clone(){
        return new Concelho(this);
    }
    
    public boolean equals (Object o) {
        if (this == o) 
            return true;
            
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        
        Concelho c = (Concelho) o;
        
        return (this.nome.equals(c.getNome()) && this.coeficiente == c.getCoeficiente());
    }
    
    public String toString() {
       StringBuilder sb = new StringBuilder();
       
       sb.append("Nome do Concelho: "); sb.append("\n" + this.nome + "\n");
       sb.append("Benifício Fiscal: "); sb.append(this.coeficiente + "\n");
       
       return sb.toString();
    }
}
