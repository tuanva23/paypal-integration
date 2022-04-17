package com.paypal.integration.manager.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class GCPFirestoreService {

    @Autowired
    private Firestore db;

    @Value("${google.cert}")
    private String certFileName;

    @Bean
    public Firestore firestore () throws IOException {
        File certFile = ResourceUtils.getFile("classpath:certs/" + certFileName);
        InputStream serviceAccount = new FileInputStream(certFile);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore();
    }

    public Map<String, Map<String, String>> getUserCollection () throws InterruptedException, ExecutionException {
        Map<String, Map<String, String>> userCollection = new HashMap<>();
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String documentId = document.getId();
            userCollection.put(documentId, new HashMap<>());
            userCollection.get(documentId).put("email", document.getString("email"));
            userCollection.get(documentId).put("image", document.getString("image"));
            userCollection.get(documentId).put("name", document.getString("name"));
            userCollection.get(documentId).put("plan", document.getString("plan"));
        }
        return userCollection;
    }
}
