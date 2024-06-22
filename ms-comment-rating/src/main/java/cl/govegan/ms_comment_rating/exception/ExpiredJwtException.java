package cl.govegan.ms_comment_rating.exception;

public class ExpiredJwtException extends RuntimeException{

    public ExpiredJwtException(String msg){
        super(msg);
    }

}
