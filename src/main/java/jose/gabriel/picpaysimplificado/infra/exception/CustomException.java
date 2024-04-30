package jose.gabriel.picpaysimplificado.infra.exception;

public class CustomException extends RuntimeException {

    public CustomException(String mensagem) {
            super(mensagem);
        }
    }
