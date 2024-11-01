package com.productproject.demo.Service;

// import org.apache.hc.core5.http.HttpEntity;
// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.google.api.client.http.HttpHeaders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.productproject.demo.entity.SignInRequest;
import com.productproject.demo.entity.SignUpRequest;
import com.productproject.demo.entity.Users;
import com.productproject.demo.repository.UsersRepository;

@Service
public class FirebaseAuthService {


    @Autowired
    private UsersRepository usersRepository;

   
    private final String FIREBASE_AUTH_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDpWDPVm-v7QvRqIuxD-pnsp9gKgN8VYbw";

    // Sign-up 
    public String signUp(SignUpRequest request) throws FirebaseAuthException {
       
        CreateRequest createRequest = new CreateRequest()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setDisplayName(request.getFname());
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);

        // Save the user to the database
        Users newUser = new Users();
        newUser.setFname(request.getFname());
        newUser.setLname(request.getLname());
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNum(request.getPhoneNum());
        newUser.setFid(userRecord.getUid());  

        usersRepository.save(newUser);

        return "User successfully created with Firebase ID: " + userRecord.getUid();
    }

    // Sign-in 
    public String signIn(SignInRequest request) {
        try{
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(FIREBASE_AUTH_URL, request, String.class);
        return response.getBody();
    }
        catch(Exception e ){
            System.out.println("u r not signed in");
        }
        return "u r not signed in";
    
    }

       
}




