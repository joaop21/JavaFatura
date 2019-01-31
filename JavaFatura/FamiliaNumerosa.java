
/**
 * Escreva a descrição da classe FamiliaNumerosa aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.ArrayList;
import java.util.List;

public class FamiliaNumerosa extends ContIndividual implements ReducaoImposto{
    private int dependentes;
    
     /**
    * Construtor vazio de FamiliaNumerosa.
    */
    
    public FamiliaNumerosa(){
        super();
        this.dependentes = 4;
    }
    
     /**
    * Construtor parametrizado de FamiliaNumerosa.
    */
    
    public FamiliaNumerosa(int nnif, String nemail, String nnome, String nmorada, 
        String npassword, int nagregado_familiar, List<Integer> nnifs, 
        double ncoeficiente, List<String> ncodigos,double euros, int dependentes){
            super(nnif,nemail,nnome,nmorada,npassword,nagregado_familiar,nnifs,ncoeficiente,ncodigos,euros);
            this.dependentes = dependentes;
    }
     
    /**
    * Construtor de cópia de FamiliaNumerosa.
    */
    
    
    public FamiliaNumerosa(FamiliaNumerosa umFamiliaNumerosa){
        super(umFamiliaNumerosa.getNif(), umFamiliaNumerosa.getEmail(),
              umFamiliaNumerosa.getNome(), umFamiliaNumerosa.getMorada(), 
              umFamiliaNumerosa.getPassword(), umFamiliaNumerosa.getAgregado_familiar(),
              umFamiliaNumerosa.getNifs_Agregado(),umFamiliaNumerosa.getCoeficiente(),
              umFamiliaNumerosa.getCodigos(), umFamiliaNumerosa.getEuros());
        this.dependentes = umFamiliaNumerosa.getDependentes();
    }
    
    /**
    * Método que devolve o número de dependentes.
    * 
    * @return Dependentes de família.
    */
    
    public int getDependentes(){
        return this.dependentes;
    }
    
    /** 
     * Método que atualiza o número de dependentes.
     * 
     * @param nDependentes Novo valor.
     */
    public void setDependentes(int nDependentes){
        this.dependentes = nDependentes;
    }
    
    /**
     * 
     * Método que calcula o valor total para redução de imposto
     * 
     * @return Valor.
     */
    public double reducaoImposto(){
        return ((this.dependentes - 3)*0.05);
    }
}
