package de.hsrm.mi.swtpro.pflamoehus.productapi;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import de.hsrm.mi.swtpro.pflamoehus.exceptions.ProductApiException;
import de.hsrm.mi.swtpro.pflamoehus.exceptions.ProductServiceException;
import de.hsrm.mi.swtpro.pflamoehus.product.Product;
import de.hsrm.mi.swtpro.pflamoehus.product.picture.Picture;
import de.hsrm.mi.swtpro.pflamoehus.productservice.ProductService;

/*
 * ProductRestApi for communication between front- and backend.
 * 
 * @author Svenja Schenk, Ann-Cathrin Fabian
 * @version 3
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductRestApi {

    @Autowired
    ProductService productService;

    Logger productRestApiLogger = LoggerFactory.getLogger(ProductRestApi.class);

    /**
     * Return a list of all products in the database.
     * 
     * @return list of products
     */
    @GetMapping("/products")
    public List<Product> allProducts() {
        return productService.allProducts();
    }

    /**
     * Return the product with the given id.
     * 
     * @param articleNr given articlenr
     * @return product
     */
    @GetMapping("/product/{articleNr}")
    public Product getProductWithID(@PathVariable long articleNr) {
        Optional<Product> found = productService.searchProductwithArticleNr(articleNr);
        return found.isEmpty() ? null : found.get();

    }

    /**
     * Delete a product with the given id.
     * 
     * @param articleNr product that should get deleted
     * @return true or false
     */
    @DeleteMapping(value="/product/{articleNr}")
    public boolean deleteProductWithArticleNr(@PathVariable long articleNr) {
        try{
            productService.deleteProduct(articleNr);
        }catch(ProductServiceException pse){
            return false;
        }
        
        return true;
    }

    /**
     * Create new product.
     * 
     * @param newProduct the new product that has du get saved
     * @param result shows errors from validating the given attributes
     * @return string new product
     */
    @PostMapping("/product/new")
    public String postNewProduct(@Valid @RequestBody Product newProduct, BindingResult result) {
        productRestApiLogger.info("Neues Produkt erhalten!");
        Product product = null;
        String message = "Message: ";
        if(result.hasErrors()){
            productRestApiLogger.info("Validationsfehler");
            message += "Validationsfehler -- "+result.getFieldErrors().toString();

        }else{
            try {
            product = productService.editProduct(newProduct);

        } catch (ProductServiceException pse) {
            productRestApiLogger.error("Failed to save the product.");
            message+= "saving_error";
            return message;
        }
            return product.toString()+message;
        }
        
        return message;
       

    }


    /**
     * Get all pictures of an product.
     * 
     * @param articleNr articlenr of the wanted product
     * @return all pictures
     */
    @GetMapping("/{articleNr}/pictures")
    public Set<Picture> getAllPicturesOfAProduct(@PathVariable long articleNr) {
        Set<Picture> allPictures = null;
        Optional<Product> found = productService.searchProductwithArticleNr(articleNr);
        try {
            allPictures = found.isPresent() ? found.get().getAllPictures() : null;

        } catch (ProductApiException pae) {
            productRestApiLogger.error("Failed to get the pictures.");
        }
        return allPictures;
    }

    
    /** 
     * Post picturedata.
     * 
     * @param image given image
     * @param articleNr given articleNr
     * @param path given path
     * @return boolean
     */
    @PostMapping(value="/product/{articleNr}/newpicture")
    public boolean postPicturedata(byte[] image, @PathVariable Long articleNr, @RequestParam("picturename") String path){

        //erst Bild speichern 
        FileOutputStream fileOutStream;
        try{
            fileOutStream = new FileOutputStream(path);
            fileOutStream.write(image);
            fileOutStream.close();

       }catch(FileNotFoundException fnoe){
           //TODO: exceptionhandling
       }catch(IOException ioe){
           //TODO: exceptionhandling
       }
       
        return false;
    }

    
    /** 
     * Init binder.
     * 
     * @param binder binder
     */
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
    binder.registerCustomEditor(byte[].class,
            new ByteArrayMultipartFileEditor());
}

}
