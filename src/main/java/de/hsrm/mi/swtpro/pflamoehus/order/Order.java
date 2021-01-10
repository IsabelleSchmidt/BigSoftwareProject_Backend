package de.hsrm.mi.swtpro.pflamoehus.order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import de.hsrm.mi.swtpro.pflamoehus.order.status.Status;
import de.hsrm.mi.swtpro.pflamoehus.order.orderdetails.OrderDetails;
import de.hsrm.mi.swtpro.pflamoehus.user.User;
import de.hsrm.mi.swtpro.pflamoehus.validation.user_db.ValidEmail;



/*
 * Order-Entitiy for its database.
 * 
 * @author Ann-Cathrin Fabian
 * @version 1
 */
@Entity(name = "OrderTable")
@Validated
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderNR;

    @Version
    @JsonIgnore
    private long version = 1;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @Valid
    private User user; 

    @ValidEmail
    private String customerEmail;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "status")
    private Status statusID;

    @OneToMany(mappedBy = "orderID")
    private Set<@Valid OrderDetails> orderdetails = new HashSet<>();

    @FutureOrPresent
    private LocalDate deliveryDate;

    @Positive
    private double priceTotal;


   
    //private Status statusID;

    
    /**
     * Get orderNr.
     * 
     * @return long
     */
    public long getOrderNR() {
        return orderNR;
    }

    /**
     * Get version.
     * 
     * @return long
     */
    public long getVersion() {
        return version;
    }

    /**
     * Set version
     * 
     * @param version version that should be set.
     */
    public void setVersion(long version) {
        this.version = version;
    }


   

    /**
     * Get delivery date.
     * 
     * @return LocalDate
     */
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Set delivery date.
     * 
     * @param deliveryDate new date
     */
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Get status.
     * 
     * @return Status
     */
    public Status getStatus() {
        return statusID;
    }

    /**
     * Set status.
     * 
     * @param status new status
     */
    public void setStatus(Status status) {
        this.statusID = status;
    }

    /**
     * Order to string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Order [deliveryDate=" + deliveryDate + ", orderNR=" + orderNR + ", status=" + statusID + ", userID="
                +  ", version=" + version +"]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Set<OrderDetails> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Set<OrderDetails> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

}
