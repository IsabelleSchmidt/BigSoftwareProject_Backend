package de.hsrm.mi.swtpro.pflamoehus.order.status;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import de.hsrm.mi.swtpro.pflamoehus.order.Order;
import de.hsrm.mi.swtpro.pflamoehus.order.orderdetails.OrderDetails;
import de.hsrm.mi.swtpro.pflamoehus.validation.order_db.ValidStatus;

/*
 * Status-Entity for its database.
 * 
 * @author Svenja Schenk
 * @version 1
 */
@Entity
@Validated
public class Status {

    @Id
    @GeneratedValue
    private long statusID;

    @Version
    @JsonIgnore
    private long version = 1;
    
    @OneToMany(mappedBy = "statusID")
    @JsonIgnore
    private Set<Order> allOrderNR = new HashSet<>();

    @OneToMany(mappedBy = "statusID")
    @JsonIgnore
    private Set<OrderDetails> allOrderDetailNR = new HashSet<>();

    @ValidStatus
    @Column(unique = true)
    private String statuscode;

    /**
     * Get statusId.
     * 
     * @return long
     */
    public long getStatusID() {
        return statusID;
    }

    /**
     * Add a new order.
     * 
     * @param newOrder to be added
     * @return boolean
     */
    public boolean addOrder(Order newOrder) {
        return allOrderNR.add(newOrder);
    }

    /**
     * Remove a order.
     * 
     * @param delOrder to be removed
     * @return boolean
     */
    public boolean removeOrder(Order delOrder) {
        return allOrderNR.remove(delOrder);
    }

    /**
     * Get statuscode.
     * 
     * @return String
     */
    public String getStatuscode() {
        return statuscode;
    }

    /**
     * Set statuscode.
     * 
     * @param statuscode to be set
     */
    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    /**
     * Get all orders.
     * 
     * @return Order
     */
    public Set<Order> getAllOrderNR() {
        return allOrderNR;
    }

    /**
     * Set all orders.
     * 
     * @param allOrderNR to be set
     */
    public void setAllOrderNR(Set<Order> allOrderNR) {
        this.allOrderNR = allOrderNR;
    }

    /**
     * Get all oderdetails.
     * 
     * @return OrderDetails
     */
    public Set<OrderDetails> getAllOrderDetailNR() {
        return allOrderDetailNR;
    }

    /**
     * Set all orderdetails.
     * 
     * @param allOrderDetailNR to be set
     */
    public void setAllOrderDetailNR(Set<OrderDetails> allOrderDetailNR) {
        this.allOrderDetailNR = allOrderDetailNR;
    }


    /**
     * Status to string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Status [allOrderDetailNR=" + allOrderDetailNR + ", allOrderNR=" + allOrderNR + ", statusID=" + statusID
                + ", statuscode=" + statuscode + ", version=" + version + "]";
    }

    // TODO: in allen Entities checken ob wir ein Set oder eine Liste brauchen
    // TODO: in allen Entities add und remove methoden die boolean zurueckgeben
}
