package emanuelepiemonte.U5_W2_D1.exceptions;

import java.util.UUID;

public class NotFoundExceptionUUID extends RuntimeException {
    public NotFoundExceptionUUID(UUID id) {
        super("Il record con id " + id + " non è stato trovato correttamente");
    }
}
