package de.hsrm.mi.swtpro.pflamoehus.payload.request;


import javax.validation.Valid;

import de.hsrm.mi.swtpro.pflamoehus.payload.response.JwtResponse;
import de.hsrm.mi.swtpro.pflamoehus.user.adress.Adress;
import de.hsrm.mi.swtpro.pflamoehus.user.paymentmethods.Bankcard;
import de.hsrm.mi.swtpro.pflamoehus.user.paymentmethods.Creditcard;
/*
 * UserOrderRequest for sending user information
 * 
 * @author Ann-Cathrin Fabian
 * @version 2
 */
public class UserOrderRequest {
    //TODO: überall gucken ob liste oder hashset
    @Valid
    private Adress adress;

    @Valid
    private Creditcard creditCard;

    @Valid
    private Bankcard bankCard;

    private JwtResponse token;

    
    /** 
     * Get adress.
     * 
     * @return Adress
     */
    public Adress getAdress() {
        return adress;
    }

    
    /** 
     * Set adress.
     * 
     * @param adress adress to be set
     */
    public void setAdress(Adress adress) {
        this.adress = adress;
    }


    
    /** 
     * Get token.
     * 
     * @return JwtResponse
     */
    public JwtResponse getToken() {
        return token;
    }

    
    /** 
     * Set token.
     * 
     * @param token to be set
     */
    public void setToken(JwtResponse token) {
        this.token = token;
    }

    
    /** 
     * Get creditcard.
     * 
     * @return Creditcard
     */
    public Creditcard getCreditcard() {
        return creditCard;
    }

    
    /** 
     * Set creditcard.
     * 
     * @param creditcard to be set
     */
    public void setCreditcard(Creditcard creditcard) {
        this.creditCard = creditcard;
    }

    
    /** 
     * Get bankcard.
     * 
     * @return Bankcard
     */
    public Bankcard getBankCard() {
        return bankCard;
    }

    
    /** 
     * Set bankcard.
     * 
     * @param bankCard to be set
     */
    public void setBankCard(Bankcard bankCard) {
        this.bankCard = bankCard;
    }

    
    /** 
     * UserOrderRequest to string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "UserOrderRequest [adress=" + adress + ", bankCard=" + bankCard + ", creditcard=" + creditCard
                + ", token=" + token + "]";
    }

    
    

}
