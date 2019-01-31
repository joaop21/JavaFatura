
/**
 * Escreva a descrição da classe Registo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

 

import java.util.Scanner;
import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.io.Serializable;


public class Main implements Serializable{
    
    /**
     * 
     * Método que procura um NIF na estrutura dos Contribuintes.
     * 
     * @param nif NIF.
     * @param td TodosContribuintes.
     * 
     * @return Boolean true ou false.
     */
    public static Boolean procuraNif(int nif, TodosContribuintes td){
        if(td.getContribuinte(nif) == null) return false;
        else return true;
    }
    
    
    /**
     * 
     * Método que procura um e-mail na estrutura dos Contribuintes.
     * 
     * @param mail String com o e-mail.
     * @param td TodosContribuintes.
     * 
     * @return Boolean true ou false.
     */
    public static Boolean procuraMail(String mail, TodosContribuintes td){
        for(Contribuinte ct : td.getContribuintes()){
            if(ct.getEmail() == mail) return true;
        }
        return false;
    }

    /** 
     * Método que regista um contribuinte no programa.
     * 
     * @param Estrutura TodosContribuintes.
     */
    public static void registo(TodosContribuintes td,TodosConcelhos tc){

        Scanner sc = new Scanner(System.in);
        
        int nif = 0;
        
        boolean existe = true;
        while(existe == true){
            System.out.print("NIF: ");
            nif = sc.nextInt();
            if (procuraNif(nif,td) == false)
                existe = false;
            else{
                System.out.println("NIF ja existente. Tente de novo.");
            }
        }
        
        String email = "";
        
        existe = true;
        while(existe == true){
            System.out.print("Email: ");
            email = sc.next();
            if (procuraMail(email,td) == false)
                existe = false;
            else{
                System.out.println("Email ja existente. Tente de novo.");
            }
        }
                
        System.out.print("Nome: ");
        String nome = sc.next();
                
        System.out.print("Morada: ");
        String morada = sc.next();
                
        System.out.print("Password: ");
        String pass = sc.next();
       
        
        System.out.println("É contribuinte individual(1) ou empresa(2)?");
        int resposta = sc.nextInt();

        // Contribuinte Individual
        if(resposta ==1){
                
            System.out.print("Quantos elementos tem o seu agregado familiar: ");
            int num_agregado = sc.nextInt();

            List<Integer> nifs = new ArrayList<>();
            nifs.add(nif);
            if(num_agregado > 1){
                System.out.println("Coloque o NIF de cada elemento do seu agregado: ");
                for(int i = 1 ; i < num_agregado ; i++){
                    System.out.print("O " + i + "º elemento: ");
                    int nif_agregado = sc.nextInt();
                    nifs.add(nif_agregado);
                }
            }
                
            System.out.println("Quantos códigos para deduções fiscais possui?");
            int num_codigos = sc.nextInt();
                
            System.out.println("Lista de códigos para deduções fiscais: ");

            List<String> codigos = new ArrayList<>();
            for(int i = 0 ; i < num_codigos ; i++){
                String codigo = sc.next();
                codigos.add(codigo);
            }
         
            if(num_agregado>=6){ 
                FamiliaNumerosa fn = new FamiliaNumerosa(nif,email,nome,morada,pass,num_agregado,nifs,num_agregado*0.01,codigos,0,num_agregado-2);
                td.addContribuinte(fn);
                System.out.println("É considerado como pertencendo a uma Familia Numerosa, possui bonificação de mais 5% por cada filho.");
            }
            else {
                ContIndividual ci = new ContIndividual(nif,email,nome,morada,pass,num_agregado,nifs,num_agregado*0.01,codigos,0);
                td.addContribuinte(ci);
            }

            System.out.println("Foi adicionado com sucesso. Se quiser aceder a sua conta faça login.");

        }
        // Contribuinte Empresa
        else{
            System.out.print("Quantas atividades económicas?");
            int num_codigos = sc.nextInt();
            
            System.out.println("Lista de códigos das atividades económicas: ");

            List<String> codigos = new ArrayList<>();
            for(int i = 0 ; i < num_codigos ; i++){
                String codigo = sc.next();
                codigos.add(codigo);
            }
        
            System.out.println("Em qual concelho atua?");
            String concelho = sc.next();
            
            Concelho c1 = new Concelho();
            for(Concelho c : tc.getConcelhos())
                if(concelho.equals(c.getNome()))
                    c1 = new Concelho (c.getNome(),c.getCoeficiente());
            
            if(tc.contains_(c1) == true){
                EmpresaInterior ei = new EmpresaInterior (nif, email, nome, morada, pass, codigos,0,c1.getNome(),c1.getCoeficiente());
                td.addContribuinte(ei);
                System.out.println("É considerado como sendo uma Empresa do Interior, possui bonificação de " + (c1.getCoeficiente())*100 + " % pela sua localização.");
            }
            else {
                ContEmpresa ce = new ContEmpresa(nif, email, nome, morada, pass, codigos,0);
                td.addContribuinte(ce);
            }

            System.out.println("Foi adicionado com sucesso. Se quiser aceder a sua conta faça login.");

        }
    }
    
    /**
     * 
     * Método de login no programa.
     * 
     * @param td TodosContribuintes.
     * @param tf TodasFaturas.
     * @param atividades Atividades económicas.
     * @param tc Todas as correções de atividades.
     * 
     * @return NIF do cliente/empresa que fez o login.
     * 
     */
    public static Integer acesso(TodosContribuintes td, TodasFaturas tf, AtividadesEcon atividades, TodasCorrecoesAtividade tc){
        Scanner sc = new Scanner(System.in);                               
        
        int nif = 0;
        
        boolean existe = false;
        while(existe == false){
            System.out.print("NIF: ");
            nif = sc.nextInt();
            if(procuraNif(nif,td) == true)
                existe = true;
            else System.out.println("NIF não existente. Tente de novo.");
        }
        
        int acesso = 0;
        while(acesso == 0){
            System.out.print("PASSWORD: ");
            String pass = sc.next();
            
            
            Contribuinte c = td.getContribuinte(nif);
            
            if((c.getPassword()).equals(pass)){
                System.out.println("Acesso Garantido");
                acesso=1;
                
                if(nif == 1) progAdmin(tf,atividades,td,tc);
                else{
                    if (c instanceof ContEmpresa) {
                         ContEmpresa cEmp = (ContEmpresa) c;
                         progEmpresa(cEmp,tf,td);
                    }
                    else{ // é contribuinte individual
                         ContIndividual cInd = (ContIndividual) c;
                         progIndividual(cInd,tf, atividades,td,tc);
                    }
                }
            }
            else System.out.println("Password errada");
        }
        // retorna o nif que se quer aceder
        return nif;
    }
     
    
    /**
     * Método que adiciona uma atividade económica à lista de atividades económicas.
     * 
     * @para atividades Atividades económicas.
     */
    
    ///////////////////////////////////// Programa Administrador
    public static void adicionaAtividade(AtividadesEcon atividades){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Qual atividade deseja adicionar:");
        String atividade = sc.next();
        
        System.out.println("Qual o coeficiente da atividade:");
        Double coeficiente = sc.nextDouble();
        
        AtividadeEcon ae = new AtividadeEcon(atividade, coeficiente);
        
        atividades.addAtividade(ae);
    }
    
    /**
     * 
     * Método com o programa do administrador que possibilita a execução de ações.
     * 
     * @param tf Todas as faturas.
     * @param atividades Atividades económicas.
     * @param td Todos os contribuintes.
     * 
     */
    
    public static void progAdmin(TodasFaturas tf, AtividadesEcon atividades, TodosContribuintes td, TodasCorrecoesAtividade tc){
        Scanner sc = new Scanner(System.in);
        
        int menu = 0;
        do{
            System.out.println("(1) Adicionar Atividade Economica  (2) 10 mais gastadores");
            System.out.println("(3) N empresas que mais faturam (4) Rastrear correções de faturas");
            System.out.println("(5) LogOut");
            
            switch(getOpcao()){
                case 1: adicionaAtividade(atividades);
                        break;
                
                case 2: Set<ContIndividual> dez = td.dezGastadores(tf);
                        for( ContIndividual i : dez) {
                            System.out.println("NIF do cliente: " + i.getNif());
                        }         
                        break;
                
                case 3: System.out.println("Número de empresas que pretende:");
                        int x = sc.nextInt();
                        try{
                            Map<Integer,Double> xempresas = td.xEmpresas(atividades,tf,x);
                            Set<Integer> keys= xempresas.keySet();
                            for(Integer nif : keys){
                                 Double deducao = xempresas.get(nif);
                                 System.out.println("NIF: " + nif);
                                 System.out.println("montante de deducao fiscal: " + deducao);
                            }
                        }
                        catch(AtividadeNaoExisteException e){System.out.println(e.getMessage());}
                        catch (Exception e){System.out.println(e.getMessage());}
                        break;
                
                case 4: Map<Integer,CorrecaoAtividade> correcoes = tc.getCorrecoes();
                        Set<Integer> keys= correcoes.keySet();
                        for(Integer id : keys){
                             CorrecaoAtividade ca = correcoes.get(id);
                             System.out.print(ca.toString());
                        }
                        break;
                        
                case 5: menu = 50;
                        break;
                        
                default: System.out.println("Ação não reconhecida");
                         break;
            }
        }while(menu == 0);
    }
    
    
    
    ///////////////////////////////////// Programa Individual
    /**
     * Método que devolve a opção escolhida pelo utilizador.
     * 
     * @return Opção.
     */
    public static Integer getOpcao(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    
    /**
     * 
     * Método que verifica as despesas individuais e de um agregado familiar.
     * 
     * @param tf Todas as faturas.
     * @param td Todos os contribuintes.
     * @param atividades Atividades económicas.
     * 
     */
    public static void verificacaoDespesas(TodasFaturas tf, TodosContribuintes td,AtividadesEcon atividades, ContIndividual c, int nif){
        int menu = 0;
        
        List<Integer> nifs = c.getNifs_Agregado();
        
        do{
            System.out.println("(1) Despesas Individuais (2) Despesas do Agregado Familiar  (3) Voltar ao menu");
            
            List<Fatura> despesas = new ArrayList<>();
            switch(getOpcao()){
                case 1: try{
                            despesas = tf.impressaoDespesas(nif,td);
                            for(Fatura f : despesas)
                                System.out.print(f.toString());
                        }
                        catch (NIFNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Valor total das despesas: ");
                        System.out.println(tf.despesas(nif));
                        System.out.print("Valor de dedução Fiscal: ");
                        System.out.println(tf.deducaoDespesas(nif, atividades,td));
                        break;

                case 2: try{
                            despesas = tf.impressaoDespesasAgregado(nifs,td);
                            for(Fatura f : despesas)
                                System.out.print(f.toString());
                        }
                        catch (NIFNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Valor total das despesas do agregado: ");
                        System.out.println(tf.despesasAgregado(nifs));
                        System.out.print("Valor de dedução Fiscal do agregado: ");
                        System.out.println(tf.deducaoDespesasAgregado(nifs, atividades,td));
                        break;
                        
                case 3: menu = 50;
                        break;
                        
                default: System.out.println("Ação não reconhecida");
                         break;
            }
        }while(menu == 0);
    }
    
    /**
     * 
     * Método que permite a validação da natureza de uma fatura.
     * 
     * @param tf Todos as faturas.
     * @param td Todos os contribuintes.
     * 
     */
    
    public static void validar(TodasFaturas tf, TodosContribuintes td, int nif){
        Scanner sc = new Scanner(System.in);


        System.out.print("########## Listagem Das Faturas ##########\n");
        List<Fatura> despesas = new ArrayList<>();
        try{
            despesas = tf.impressaoDespesas(nif,td);
            for(Fatura f : despesas)
                System.out.print(f.toString());
        }
        catch (NIFNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.print("ID da fatura a validar: ");
        int id = sc.nextInt();
        Fatura f = tf.getFatura(id);
        
        if(f == null){
            System.out.println("Não existe fatura com o ID mencionado.");
            return;
        }
        
        if(f.getNifCliente() != nif){
            System.out.println("Fatura não corresponde ao seu NIF.");
            return;
        }
        
        if(f.getValidez() == true){
            System.out.println("Fatura já validada.");
            return;
        }
        
        List<String> codigos = new ArrayList<>();
        try{
             codigos = td.codigos(f.getNifEmpresa(),td);
        }
        catch (NIFNaoExisteException e){
             System.out.println(e.getMessage());
        }
        catch (Exception e){
             System.out.println(e.getMessage());
        }
        
        System.out.println("Naturezas possíveis da Fatura:");
        for(String s : codigos) System.out.println(s);
        
        System.out.print("Natureza da Fatura: ");
        String natureza = sc.next();
        try{
            if(codigos.contains(natureza) == true) tf.validaFatura(f.getId(), natureza,tf);
            else System.out.println("Natureza nao associável.");
        }
        catch (IDNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Método que permite a correção da natureza de uma fatura.
     * 
     *
     *@param tf Todos as faturas.
     *@param td Todos os contribuintes.
     *@param tc Todos as correções de atividade(para registo).
     */
    
    public static void corrigir(TodasFaturas tf, TodosContribuintes td, TodasCorrecoesAtividade tc, int nif){
        Scanner sc = new Scanner(System.in);

        System.out.print("########## Listagem Das Faturas ##########\n");
        List<Fatura> despesas = new ArrayList<>();
        try{
            despesas = tf.impressaoDespesas(nif,td);
            for(Fatura f : despesas)
                System.out.print(f.toString());
        }
        catch (NIFNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        System.out.print("ID da fatura a alterar: ");
        int id = sc.nextInt();
        Fatura f = tf.getFatura(id);
        if(f == null){
            System.out.println("Não existe fatura com o ID mencionado.");
            return;
        }
        
        if(f.getNifCliente() != nif){
            System.out.println("Fatura não corresponde ao seu NIF.");
            return;
        }
        
        List<String> codigos = new ArrayList<>();
        try{
             codigos = td.codigos(f.getNifEmpresa(),td);
        }
        catch (NIFNaoExisteException e){
             System.out.println(e.getMessage());
        }
        catch (Exception e){
             System.out.println(e.getMessage());
        }
        
        if(codigos.size() == 1){
            System.out.println("Não é possível corrigir esta fatura uma vez que a empresa emitente só tem uma área de atividade.");
            return;
        }
            
        System.out.println("Naturezas possíveis da Fatura:");
        for(String s : codigos) System.out.println(s);
        
        System.out.print("Nova natureza da Fatura: ");
        String natureza = sc.next();
        try{
            if(codigos.contains(natureza) == true){
                String antiga = f.getNatureza();
                tf.validaFatura(f.getId(), natureza, tf);
                CorrecaoAtividade ca = new CorrecaoAtividade(f.getId(),antiga, natureza);
                tc.addCorrecaoAtividade(ca);
                System.out.println("Fatura atualizada: ");
                Fatura nova = tf.getFatura(f.getId());
                System.out.print(nova.toString());
            }
            else System.out.print("Natureza nao associável.");
        }
        catch (IDNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * Método que permite ao utilizador escolher validar ou corrigir a natureza de uma fatura.
     * 
     * @param tf Todos as faturas.
     * @param td Todos os contribuintes.
     * @param tc Todos as correções de atividade(para registo).
     */
    public static void validacaoFaturas(TodasFaturas tf, TodosContribuintes td, TodasCorrecoesAtividade tc, int nif){
        int menu = 0;
        
        do{
            System.out.println("(1) Validar (2) Corrigir Natureza (3) Voltar ao menu");
            
            switch(getOpcao()){
                case 1: validar(tf,td,nif);
                        break;
                
                case 2: corrigir(tf,td,tc,nif);
                        break;
                
                case 3: menu = 50;
                        break;
            }
        }while(menu == 0);
    }
    
    /**
     * 
     * Método com o menu do contribuinte individual e as possíveis ações.
     * 
     * @param c Contribuinte
     * @param tf Todas as faturas
     * @param atividades Atividades económicas.
     * @param td Todos os contribuintes.
     * @param tc Todos as correções de atividade(para registo).
     */
    public static void progIndividual(ContIndividual c, TodasFaturas tf, AtividadesEcon atividades, TodosContribuintes td, TodasCorrecoesAtividade tc) {
        Scanner sc = new Scanner(System.in);
        
        int nif = c.getNif();
        int menu = 0;
        
        List faturas = tf.getFaturas();
        
        do{
            System.out.println("O que pretende fazer?");
            System.out.println("(1) Verificar despesas  (2) Validação de Faturas");
            System.out.println("(3) LogOut");
            
            switch(getOpcao()){
                case 1: verificacaoDespesas(tf,td,atividades,c,nif);
                        break;
                        
                case 2: validacaoFaturas(tf,td,tc,nif);
                        break;
                
                case 3: menu = 50;
                        break;
                        
                default: System.out.println("Ação não reconhecida");
                         break;
            }
        } while(menu == 0);
    }
    
  
    
    /** 
     * Método com o menu e as possíveis ações de um contribuinte de empresa.
     * 
     * @param c Contribuinte Empresa.
     * @param tf Todos as faturas.
     * @param td Todos os contribuintes.
     * 
     */
    ///////////////////////////////////// Programa Empresa
     public static void progEmpresa(ContEmpresa c, TodasFaturas tf, TodosContribuintes td) {
        Scanner sc = new Scanner(System.in);
        
        int nif = c.getNif();
        int menu = 0;
    
        
        do{
            System.out.println("O que pretende fazer?");
            System.out.println("(1) Emitir Fatura\n(2) Total faturado num período\n(3) Lista das faturas da empresa\n(4) Lista das faturas de um cliente num intervalo\n(5) Lista das faturas de um cliente ordenadas por valor (ordem decrescente)\n(6) Logout");
            
            switch(getOpcao()){
                case 1: System.out.print("NIF do cliente: ");
                        int nifcliente = sc.nextInt();
                        System.out.print("Descrição referente à fatura: ");
                        String descricao = sc.next();
                        System.out.print("Valor da despesa: ");
                        double valorDespesa = 0;
                        try{
                            valorDespesa = sc.nextDouble();
                        }
                        catch (InputMismatchException e){
                            System.out.println(e.getMessage());
                        }
                        Fatura f = new Fatura();
                        try{
                            f = c.emiteFatura(nifcliente,descricao,valorDespesa,tf,td);
                            System.out.print(f.toString());
                        }
                        catch (NIFNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                        
                case 2 : System.out.print("Data de início (formato AAAA-MM-DD): ");
                         String dataInicio = sc.next();
                         System.out.print("Data de fim (formato AAAA-MM-DD): ");
                         String dataFim = sc.next();
                         System.out.print("Total faturado neste período: ");
                         double t = tf.totalFaturado(dataInicio,dataFim);
                         System.out.print(t + " €\n");
                         break;
                     
                case 3: System.out.print("Pretende a lista das faturas ordenada por (1) data ou por (2) valor? \n");         
                        int opcao = sc.nextInt();
                        if (opcao==1) {
                            TreeSet<Fatura> aux = tf.ordenaData(tf.getFaturas());
                            
                            System.out.print("IDs das faturas da mais recente para a mais antiga: \n");
                            for( Fatura fa : aux) {
                                System.out.print("ID da fatura: " + fa.getId() + "\n");
                            }
                        }
                        else { // ordena por valor
                            TreeSet<Fatura> aux = tf.ordenaValor(tf.getFaturas());
                            
                            System.out.print("IDs das faturas ordenado pelo valor (ordem decrescente): \n");
                            for( Fatura fa : aux) {
                                System.out.print("ID da fatura: " + fa.getId() + "\n");
                            }
                           
                        }  
                        break;
                        
                case 4: System.out.print("NIF do cliente: ");
                        int nifc = sc.nextInt();
                        System.out.print("Data de início (formato AAAA-MM-DD): ");
                        String dInicio = sc.next();
                        System.out.print("Data de fim (formato AAAA-MM-DD): ");
                        String dFim = sc.next();
                        System.out.println("Faturas do cliente da mais antiga para a mais recente: ");
                        try{
                            List<Fatura> aux = tf.faturasClienteSemOrd(nifc,dInicio,dFim,td);
                            for(Fatura fa : aux){
                                System.out.println("ID da fatura: " + fa.getId());
                            }
                        }
                        catch (NIFNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                        
                case 5: System.out.print("NIF do cliente: ");
                        int nifc1 = sc.nextInt();
                        System.out.println("Faturas do cliente ordenadas por valor (ordem decrescente): ");
                        try{
                            TreeSet<Fatura> aux2 = tf.faturasClienteComOrd(nifc1,td);
                            for(Fatura fa : aux2){
                                System.out.println("ID da fatura: " + fa.getId());
                            }
                        }
                        catch (NIFNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                        
                case 6: menu = 50;
                        break;
                        
                default: System.out.println("Ação não reconhecida");
                         break;
            }
        }while(menu == 0);
    }
    /**
     * Método que carrega informação para as estruturas para fins de testar o programa.
     * 
     * @param td Todos os contribuintes.
     * @param tf Todos as faturas.
     * @param ae Atividades económicas.
     * @param tca Todos as correções de atividade(para registo).
     */

    public static void carregaDados (TodosContribuintes td, TodasFaturas tf, AtividadesEcon ae, TodasCorrecoesAtividade tca, TodosConcelhos tc){
        
        
        List<String> ncodigos1 = new ArrayList<>();
        ncodigos1.add("Educação");
        ncodigos1.add("Saúde");
        ncodigos1.add("Alimentação");
        ncodigos1.add("Restauração");
        
        List<Integer> nifs_agregado_db = new ArrayList<>();
        nifs_agregado_db.add(2);
        List<Integer> nifs_agregado_js = new ArrayList<>();
        nifs_agregado_js.add(3);
        List<Integer> nifs_agregado_rc = new ArrayList<>();
        nifs_agregado_rc.add(4);
        
        // Contribuintes Base
        Contribuinte c1 = new ContIndividual(1,"admin@gmail.com","Administrador","---","admin",1,new ArrayList<>(),0.0,ncodigos1,0.0);
        Contribuinte c2 = new ContIndividual(2,"diogobraga@gmail.com","Diogo Braga","Rua Dos Moinhos","db",1,nifs_agregado_db,0.01,ncodigos1,1.30);
        Contribuinte c3 = new ContIndividual(3,"joaosilva@gmail.com","Joao Silva","Travessa Da Cha","js",1,nifs_agregado_js,0.01,ncodigos1, 0.80);
        Contribuinte c4 = new ContIndividual(4,"ricardocacador@gmail.com","Ricardo Cacador","Rua Dos Lusiadas","rc",1,nifs_agregado_rc,0.01,ncodigos1,1.20);
        
        // Empresa Soutivinhos
        List<String> ncodigosSoutivinhos = new ArrayList<>();
        ncodigosSoutivinhos.add("Alimentação");
       
        Contribuinte c5 = new ContEmpresa(5,"soutivinhos@gmail.com","Soutivinhos","Viana Do Castelo","souti",ncodigosSoutivinhos,0.0);
        
        // Empresa Montalegrense
        List<String> ncodigosMontalegrense = new ArrayList<>();
        ncodigosMontalegrense.add("Alimentação");
        
        Contribuinte c6 = new ContEmpresa(6,"montalegrense@gmail.com","Montalegrense","Braga","monta",ncodigosMontalegrense,3.30);
        
        // Empresa Hospital São João
        List<String> ncodigosHospitalSaoJoao = new ArrayList<>();
        ncodigosHospitalSaoJoao.add("Saúde");
        ncodigosHospitalSaoJoao.add("Educação");
        
        Contribuinte c17 = new ContEmpresa(17,"hospitalsaojoao@gmail.com","Hospital São João","Porto","saojoao",ncodigosHospitalSaoJoao,4030.0);
        
        // Empresa Danone (Empresa Interior)
        List<String> ncodigosDanone = new ArrayList<>();
        ncodigosDanone.add("Alimentação");
        ncodigosDanone.add("Transportes");   
        
        Contribuinte c18 = new EmpresaInterior(18,"danone@gmail.com","Danone Portugal, S.A.","Castelo Branco","danone",ncodigosDanone,0.0,"Castelo Branco",0.05);
        
        // Empresa Dura Automotive (Empresa Interior)
        List<String> ncodigosDuraAutomotive = new ArrayList<>();
        ncodigosDuraAutomotive.add("Reparação de Veículos");
        ncodigosDuraAutomotive.add("Transportes");  
        
        Contribuinte c19 = new EmpresaInterior(19,"duraautomotive@gmail.com","Dura Automotive Portuguesa, LDA.","Guarda","dura",ncodigosDuraAutomotive,140.0,"Guarda",0.05);
        
        // Familia Bastos (Familia Numerosa)
        List<Integer> nifs_agregado_familiabastos = new ArrayList<>();
        nifs_agregado_familiabastos.add(7);nifs_agregado_familiabastos.add(8);nifs_agregado_familiabastos.add(9);
        nifs_agregado_familiabastos.add(10);nifs_agregado_familiabastos.add(11);nifs_agregado_familiabastos.add(12);
        nifs_agregado_familiabastos.add(13);
        
        Contribuinte c7 = new FamiliaNumerosa(7,"joao_pai@gmail.com","João Bastos (Pai)","Alameda São Dâmaso","jb",7,nifs_agregado_familiabastos,0.07,ncodigos1,50.0,5);
        Contribuinte c8 = new FamiliaNumerosa(8,"andreia_mae@gmail.com","Andreia Bastos (Mãe)","Alameda São Dâmaso","ab",7,nifs_agregado_familiabastos,0.07,ncodigos1,50.0,5);
        Contribuinte c9 = new FamiliaNumerosa(9,"paulo_filho1@gmail.com","Paulo Bastos (Filho1)","Alameda São Dâmaso","pb",7,nifs_agregado_familiabastos,0.07,ncodigos1,2000.0,5);
        Contribuinte c10 = new FamiliaNumerosa(10,"andre_filho2@gmail.com","André Bastos (Filho2)","Alameda São Dâmaso","ab",7,nifs_agregado_familiabastos,0.07,ncodigos1,0.0,5);
        Contribuinte c11 = new FamiliaNumerosa(11,"tiago_filho3@gmail.com","Tiago Bastos (Filho3)","Alameda São Dâmaso","tb",7,nifs_agregado_familiabastos,0.07,ncodigos1,0.0,5);
        Contribuinte c12 = new FamiliaNumerosa(12,"ines_filho4@gmail.com","Inês Bastos (Filho4)","Alameda São Dâmaso","tb",7,nifs_agregado_familiabastos,0.07,ncodigos1,0.0,5);
        Contribuinte c13 = new FamiliaNumerosa(13,"maria_filho5@gmail.com","Maria Bastos (Filho5)","Alameda São Dâmaso","tb",7,nifs_agregado_familiabastos,0.07,ncodigos1,0.0,5);
        
        // Família Sousa
        List<Integer> nifs_agregado_familiasousa = new ArrayList<>();
        nifs_agregado_familiasousa.add(14);nifs_agregado_familiasousa.add(15);nifs_agregado_familiasousa.add(16);
        
        Contribuinte c14 = new ContIndividual(14,"alberto_pai@gmail.com","Alberto Sousa (Pai)","Avenida Central","as",3,nifs_agregado_familiasousa,0.03,ncodigos1,20.0);
        Contribuinte c15 = new ContIndividual(15,"mariana_mae@gmail.com","Mariana Sousa (Mãe)","Avenida Central","ms",3,nifs_agregado_familiasousa,0.03,ncodigos1,50.0);
        Contribuinte c16 = new ContIndividual(16,"adriana_filho1@gmail.com","Adriana Sousa (Filho1)","Avenida Central","as",3,nifs_agregado_familiasousa,0.03,ncodigos1,2000.0);
        
        // Adicionar Contribuintes
        if(td.containsValue_(c1) == false)td.addContribuinte(c1);
        if(td.containsValue_(c2) == false)td.addContribuinte(c2);
        if(td.containsValue_(c3) == false)td.addContribuinte(c3);
        if(td.containsValue_(c4) == false)td.addContribuinte(c4);
        if(td.containsValue_(c5) == false)td.addContribuinte(c5);
        if(td.containsValue_(c6) == false)td.addContribuinte(c6);
        if(td.containsValue_(c7) == false)td.addContribuinte(c7);
        if(td.containsValue_(c8) == false)td.addContribuinte(c8);
        if(td.containsValue_(c9) == false)td.addContribuinte(c9);
        if(td.containsValue_(c10) == false)td.addContribuinte(c10);
        if(td.containsValue_(c11) == false)td.addContribuinte(c11);
        if(td.containsValue_(c12) == false)td.addContribuinte(c12);
        if(td.containsValue_(c13) == false)td.addContribuinte(c13);
        if(td.containsValue_(c14) == false)td.addContribuinte(c14);
        if(td.containsValue_(c15) == false)td.addContribuinte(c15);
        if(td.containsValue_(c16) == false)td.addContribuinte(c16);
        if(td.containsValue_(c17) == false)td.addContribuinte(c17);
        if(td.containsValue_(c18) == false)td.addContribuinte(c18);
        if(td.containsValue_(c19) == false)td.addContribuinte(c19);
        
        // Faturas
        Fatura f1 = new Fatura(0,6,"Montalegrense",LocalDate.of(2018,05,20),2,"Croissant Simples","Alimentação",0.80,true);
        Fatura f2 = new Fatura(1,6,"Montalegrense",LocalDate.of(2018,05,20),2,"Garrafa Água","Alimentação",0.50,true);
        Fatura f3 = new Fatura(2,6,"Montalegrense",LocalDate.of(2018,05,23),4,"Pastel de Chaves","Alimentação",1.20,true);
        Fatura f4 = new Fatura(3,6,"Montalegrense",LocalDate.of(2018,05,21),3,"Nata","Alimentação",0.80,true);
        Fatura f5 = new Fatura(4,17,"Hospital São João",LocalDate.of(2017,05,21),9,"Propinas Finais","Educação",2000.0,false);
        Fatura f6 = new Fatura(5,17,"Hospital São João",LocalDate.of(2017,05,21),7,"Consulta de Rotina","Saúde",30.0,false);
        Fatura f7 = new Fatura(6,17,"Hospital São João",LocalDate.of(2017,05,22),16,"Propinas Finais","Educação",2000.0,false);
        Fatura f8 = new Fatura(7,19,"Dura Automotive Portuguesa, LDA.",LocalDate.of(2018,03,21),15,"Revisão Carro","Reparação de Veículos",50.0,false);
        Fatura f9 = new Fatura(8,19,"Dura Automotive Portuguesa, LDA.",LocalDate.of(2018,03,21),14,"Transporte","Transportes",20.0,false);
        Fatura f10 = new Fatura(9,19,"Dura Automotive Portuguesa, LDA.",LocalDate.of(2018,04,05),8,"Revisão Carro","Reparação de Veículos",50.0,false);
        Fatura f11 = new Fatura(10,19,"Dura Automotive Portuguesa, LDA.",LocalDate.of(2018,04,05),7,"Transporte","Transportes",20.0,false);
        
        // Adicionar Faturas
        if(tf.contains_(f1) == false) tf.addFatura(f1);
        if(tf.contains_(f2) == false) tf.addFatura(f2);
        if(tf.contains_(f3) == false) tf.addFatura(f3);
        if(tf.contains_(f4) == false) tf.addFatura(f4);
        if(tf.contains_(f5) == false) tf.addFatura(f5);
        if(tf.contains_(f6) == false) tf.addFatura(f6);
        if(tf.contains_(f7) == false) tf.addFatura(f7);
        if(tf.contains_(f8) == false) tf.addFatura(f8);
        if(tf.contains_(f9) == false) tf.addFatura(f9);
        if(tf.contains_(f10) == false) tf.addFatura(f10);
        if(tf.contains_(f11) == false) tf.addFatura(f11);
        
        // Atividades Econômicas
        AtividadeEcon ae1 = new AtividadeEcon("Alimentação",0.06);
        AtividadeEcon ae2 = new AtividadeEcon("Saúde",0.06);
        AtividadeEcon ae3 = new AtividadeEcon("Educação",0.13);
        AtividadeEcon ae4 = new AtividadeEcon("Restauração",0.23);
        AtividadeEcon ae5 = new AtividadeEcon("Transportes",0.23);
        AtividadeEcon ae6 = new AtividadeEcon("Imóveis",0.23);
        AtividadeEcon ae7 = new AtividadeEcon("Reparação de Veículos",0.23);
        AtividadeEcon ae8 = new AtividadeEcon("Beleza",0.23);
        
        // Adicionar Atividades Econômicas
        if(ae.contains_(ae1) == false) ae.addAtividade(ae1);
        if(ae.contains_(ae2) == false) ae.addAtividade(ae2);
        if(ae.contains_(ae3) == false) ae.addAtividade(ae3);
        if(ae.contains_(ae4) == false) ae.addAtividade(ae4);
        if(ae.contains_(ae5) == false) ae.addAtividade(ae5);
        if(ae.contains_(ae6) == false) ae.addAtividade(ae6);
        if(ae.contains_(ae7) == false) ae.addAtividade(ae7);
        if(ae.contains_(ae8) == false) ae.addAtividade(ae8);
        
        // Concelhos
        Concelho cc1 = new Concelho("Covilhã",0.20);
        Concelho cc2 = new Concelho("Guarda",0.10);
        Concelho cc3 = new Concelho("Pampilhosa da Serra",0.25);
        Concelho cc4 = new Concelho("Seia",0.20);
        Concelho cc5 = new Concelho("Fundão",0.25);
        Concelho cc6 = new Concelho("Montalegre",0.10);
        Concelho cc7 = new Concelho("Castelo Branco",0.05);
        Concelho cc8 = new Concelho("Bragança",0.05);
        
        // Adicionar Concelhos
        if(tc.contains_(cc1) == false) tc.addConcelho(cc1);
        if(tc.contains_(cc2) == false) tc.addConcelho(cc2);
        if(tc.contains_(cc3) == false) tc.addConcelho(cc3);
        if(tc.contains_(cc4) == false) tc.addConcelho(cc4);
        if(tc.contains_(cc5) == false) tc.addConcelho(cc5);
        if(tc.contains_(cc6) == false) tc.addConcelho(cc6);
        if(tc.contains_(cc7) == false) tc.addConcelho(cc7);
        if(tc.contains_(cc8) == false) tc.addConcelho(cc8);
        
        
    }
    
    /**
     * Método Main que faz todos os carregamentos e gravações necessáris do programa e contém o menu inicial que permite fazer login ou registar.
     * 
     */
    ///////////////////////////////////// Main
    public static void main() throws FileNotFoundException,IOException,ClassNotFoundException{
        TodosContribuintes td = new TodosContribuintes();
        TodasFaturas tf= new TodasFaturas();
        AtividadesEcon ae = new AtividadesEcon(); /// precisamos de carregar de ficheiro as atividades economicas
        TodasCorrecoesAtividade tca = new TodasCorrecoesAtividade();
        TodosConcelhos tc = new TodosConcelhos();
        
        carregaDados(td,tf,ae,tca,tc);
        
        // primeiro necessário carregar um estrutrura dum ficheiro e colocar em todos
        File fcont = new File("TodosContribuintes.obj");
        File ffat = new File("TodasFaturas.obj");
        File fativ = new File("TodasAtividades.obj");
        File fcorre = new File("TodasCorrecoesAtividade.obj");
        File fconc = new File("TodosConcelhos.obj");
        
        
        if(fcont.exists() && !fcont.isDirectory()) { 
            td =td.loadTD("TodosContribuintes.obj");
        }
        else {
            System.out.println("Erro a carregar dados dos contribuintes, ficheiro não existe. Classe vazia.");
        }
        
        if(ffat.exists() && !ffat.isDirectory()) { 
            tf = tf.loadTF("TodasFaturas.obj");
        }
        else {
            System.out.println("Erro a carregar dados das faturas, ficheiro não existe. Classe vazia.");
        }
        
        if(fativ.exists() && !fativ.isDirectory()) { 
            ae = ae.loadTA("TodasAtividades.obj");
        }
        else {
            System.out.println("Erro a carregar dados das atividades económicas, ficheiro não existe. Classe vazia.");
        }
        
        if(fcorre.exists() && !fcorre.isDirectory()) { 
            tca = tca.loadTCA("TodasCorrecoesAtividade.obj");
        }
        else {
            System.out.println("Erro a carregar dados das correções das atividades económicas, ficheiro não existe. Classe vazia.");
        }
        
        if(fconc.exists() && !fconc.isDirectory()) { 
            tc = tc.loadTC("TodosConcelhos.obj");
        }
        else {
            System.out.println("Erro a carregar dados dos concelhos, ficheiro não existe. Classe vazia.");
        }
        
        
        Scanner sc = new Scanner(System.in);

        int menu = 0;

        do{
            System.out.println("O que pretende fazer?");
            System.out.println("(1) Login  (2) Registo  (3) Sair da aplicação");
            
            switch(getOpcao()){
                case 1: acesso(td,tf,ae,tca);
                        break;
                        
                case 2: registo(td,tc);
                        break;
                        
                case 3: menu = 50;
                        td.guardaTD("TodosContribuintes.obj");
                        tf.guardaTF("TodasFaturas.obj");
                        ae.guardaTA("TodasAtividades.obj");
                        tca.guardaTCA("TodasCorrecoesAtividade.obj");
                        tc.guardaTC("TodosConcelhos.obj");
                        break;
                        
                default: System.out.println("Ação não conhecida");
                         break;
            }
        }while(menu == 0);
    }
}