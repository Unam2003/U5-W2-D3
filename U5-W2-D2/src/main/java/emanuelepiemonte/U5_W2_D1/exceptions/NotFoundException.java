package emanuelepiemonte.U5_W2_D1.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Il record con id " + id + " non è stato trovato correttamente");
    }
}
