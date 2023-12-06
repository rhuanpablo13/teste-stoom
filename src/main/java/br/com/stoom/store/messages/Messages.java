package br.com.stoom.store.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Messages implements IMessages {
    
    private String entityClassName;
    private Logger log; 

    public Messages(Class classService, String entityClassName) {
        this.entityClassName = entityClassName;
        this.log = LoggerFactory.getLogger(classService);
    }


    /**
     * Buscando todo(a)s o(a)s registros
     */
    public String buscandoDados() {
        return printMessage("Buscando todo(a)s o(a)s registros");
    }
    
    /**
     * Buscando {} pelo id: {}
     */
    public String buscandoPorId(Object id) {
        return printMessage("Buscando {} pelo id: {}", entityClassName, id);
    }

    /**
     * Quantidade de registros encontrados: {}
     */
    public String quantidadeDados(Object quantidade) {
        return printMessage("Quantidade de registros encontrados: {}", quantidade);
    }

    /**
     * {} inserido com sucesso!
     */
    public String registroInseridoSucesso() {
        return printMessage("{} inserido com sucesso!", entityClassName);
    }

    /**
     * {} alterado com sucesso!
     */
    public String registroAlteradoSucesso() {
        return printMessage("{} alterado com sucesso!", entityClassName);
    }

    /**
     * {} deletado com sucesso pelo id: {}
     */
    public String registroDeletadoSucesso(Object id) {
        return printMessage("{} deletado com sucesso pelo id: {}", entityClassName, id);
    }


    /**
     * Falha ao inserir {}!
     */
    public String registroInseridoErro() {
        return printMessage("Falha ao inserir {}!", entityClassName);
    }

    /**
     * Falha ao alterar {}!
     */
    public String registroAlteradoErro() {
        return printMessage("Falha ao alterar {}!", entityClassName);
    }

    /**
     * Falha ao deletar {}!
     */
    public String registroDeletadoErro() {
        return printMessage("Falha ao deletar {}!", entityClassName);
    }


    /**
     * Informe um ID valido!
     */
    public String informeIdValido() {
        return printMessage("Informe um ID valido!");
    }


    /**
     * Nenhum registro encontrado!
     */
    public String nenhumRegistroEncontrado() {
        return printMessage("Nenhum registro encontrado!");
    }


    /**
     * Validando os dados em: {}
     */
    public String validandoDados() {
        return printMessage("Validando os dados em: {}", entityClassName);
    }

    /**
     * {} ja esta cadastrado!
     */
    public String registroJaCadastrado() {
        return printMessage("{} ja esta cadastrado!", entityClassName);
    }

    /**
     * {} não encontrado na base de dados
     * @param dado
     * @return
     */
    public String registroNaoEncontradoBaseDados(String dado) {
        return printMessage("{} não encontrado na base de dados", dado);
    }

    private String toMessage(String msg, String... args) {
        msg = msg.replaceAll("\\{\\}", "%s");
        return String.format(msg, args);
    }

    @Override
    public String printMessage(String msg, Object... args) {
        String[] strArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            strArgs[i] = args[i].toString();
        }

        msg = toMessage(msg, strArgs);
        log.info(msg);
        return msg;
    }

}

