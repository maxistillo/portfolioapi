package max.dev.portfolioapi.exceptions;

import lombok.Data;

@Data
public class GlobalException extends RuntimeException{

    public GlobalException(String message) { super(message); }

}
