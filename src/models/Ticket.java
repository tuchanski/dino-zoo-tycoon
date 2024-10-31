package models;

import java.time.LocalDateTime;

public class Ticket {

    private static Long idCounter = 0L;

    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private Boolean isAvailable;

    public Ticket() {
        idCounter++;
        this.id = idCounter;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusDays(1);
        this.isAvailable = true;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    // Default is creation date + 1 day
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", creationDate=" + creationDate + ", expirationDate=" + expirationDate
                + ", isAvailable=" + isAvailable + "]";
    }

}