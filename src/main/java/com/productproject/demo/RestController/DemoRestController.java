package com.productproject.demo.RestController;
 
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.productproject.demo.Service.DemoService;
import com.productproject.demo.Service.RatingService;

import org.springframework.web.bind.annotation.*;
import com.productproject.demo.entity.Cart;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Ratings;
import com.productproject.demo.entity.Users;
import com.productproject.demo.repository.ProductsRepository;
import com.productproject.demo.repository.UsersRepository;
import jakarta.validation.Valid;
import com.productproject.demo.dto.Demodto;

@Validated
@RestController
@RequestMapping("/store")
public class DemoRestController {

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    private DemoService demoService;

     @Autowired
    public RatingService ratingService;



// get all prdts
    @GetMapping("/user/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> productAll = demoService.getAllproducts();
            return ResponseEntity.ok(productAll);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// // get all users
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> userAll = demoService.getAllUsers();
            return ResponseEntity.ok(userAll);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// // add prdt
    @PostMapping("/addproduct")
    public ResponseEntity<Object> addProducts(@Valid @RequestBody Products products) {
        try {
            ResponseEntity<Object> newProducts = demoService.save(products);
            return newProducts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
        }
    }


// // add cart
@PostMapping("/user/addcart")
public ResponseEntity<Cart> addcart(@RequestBody Demodto demodto) {
    try {
        Cart newcart = new Cart();
        newcart.setCid(demodto.getCid());
        newcart.setQty(demodto.getQty());
        newcart.setFavourite(demodto.isFavourite());

        Products products = productsRepository.findById(demodto.getPid()).get();
        Users users = usersRepository.findById(demodto.getUid()).get();
        newcart.setProducts(products);
        newcart.setUsers(users);
        Cart savedCart = demoService.save(newcart);

        // return ResponseEntity.ok(newcart);
        return ResponseEntity.ok(savedCart);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

// // get prdt by id
    @GetMapping("/user/products/{pid}")
    public ResponseEntity<Products> getProductById(@PathVariable("pid") int pid) {
        try {
            Products theProduct = demoService.getproductbyId(pid);
            if (theProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(theProduct);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// // get usr by id
    @GetMapping("/users/{uid}")
    public ResponseEntity<Users> getUserById(@PathVariable("uid") int uid) {
        try {
            Users theUser = demoService.getuserbyId(uid);
            if (theUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(theUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// // dlte prdt
    @DeleteMapping("/deleteproduct/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
        try {
            Products productDel = demoService.getproductbyId(pid);
            if (productDel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found");
            }
            demoService.deleteproduct(pid);
            return ResponseEntity.ok("Deleted the product");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }

// // dlte usr
    @DeleteMapping("/user/deleteUser/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable int uid) {
        try {
            Users userDel = demoService.getuserbyId(uid);
            if (userDel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
            }
            demoService.deleteuser(uid);
            return ResponseEntity.ok("Deleted the user");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }

// delete cart 
    @DeleteMapping("/user/deletecart/{cid}")
    public ResponseEntity<String> deleteCart(@PathVariable int cid) {
        try {
            Cart cartdel = demoService.getCartbyId(cid);
            if (cartdel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cart found");
            }
            demoService.deleteCart(cid);
            return ResponseEntity.ok("Deleted the cart");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete cart");
        }
    }

//  get prdt and its qty using user id
      @GetMapping("/getcartqty/{uid}")
      public List<Cart> getCartsByUserId(@PathVariable int uid) {
        try{
        return demoService.getproductqtywithuid(uid);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //get the count of products a user have
    @GetMapping("/user/getcount/{uid}")
    public int productCount(@PathVariable int uid){
        try{
            return demoService.productCount(uid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;  
    }

// get all cart
    @GetMapping("/carts")
    public ResponseEntity<Page<Cart>> getAllCarts(@RequestParam int page, @RequestParam int size) {  
        try {
            Page<Cart> cartPage = demoService.getAllCarts(page, size);
            // System.out.println("ur page no is....!!"+page);
            // System.out.println("ur page size is....!!"+size);
            return ResponseEntity.ok(cartPage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // get prdt and its details using uid
    @GetMapping("/getprdtbyuid/{uid}")
    public List<Products> getproducts(@PathVariable int uid){
        try{
        return demoService.getproducts(uid);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }
// get user by pid
    @GetMapping("/getuserbyprdt/{pid}")
    public List<Users> getUsersByProduct(@PathVariable int pid){
        try{
            return demoService.getUsersByProduct(pid);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
// get qty of prdt for production
    @GetMapping("/qtyforproduction/{pid}")
    public int getqtyforproduction(@PathVariable int pid){
        try{
            return demoService.getqtyforproduction(pid);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
// search products
    @GetMapping("/searchProduct")
    public List<Products> searchProducts(@RequestParam String pname){
             List<Products> productSearch=demoService.searchProducts(pname);
             return productSearch;
    }

    // filter products
    @GetMapping("/filterProducts")
    public List<Products> filterProducts(@RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating,String pname) {
    
    return demoService.filterProducts(minPrice, maxPrice, minRating, maxRating,pname);
}
 }











 // // total price
//     @GetMapping("/user/gettotalprice/{uid}")
//     public int totalprice(@PathVariable int uid){
//         try{
//          return demoService.gettotalprice(uid);
//         }
//         catch(Exception e){
//             System.out.println(e.getMessage());
//             return 0;
//         }
//     }

 // // update users by id
    // @PutMapping("/updateuser/{uid}")
    // public String updateUser(@PathVariable int uid){
    //     return null;
        
    // }

    /*
     * // // add usr
    @PostMapping("/user/addusers")
    public ResponseEntity<Users> addUsers(@Valid @RequestBody Users users) {
        try {
            Users newUsers = demoService.save(users);
            return ResponseEntity.ok(newUsers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
     */