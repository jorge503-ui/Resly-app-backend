package es.resly.app.backend.auth.config;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import es.resly.app.backend.auth.models.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Autowired
    private SecurityProperties secProps;

    @Primary
    @Bean
    public FirebaseApp getFirebaseApp() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl(secProps.getFirebaseProps().getDatabaseUrl())
                .setStorageBucket(secProps.getFirebaseProps().getStorageUrl())
                .setDatabaseAuthVariableOverride(null)
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }

    @Bean
    public FirebaseAuth getAuth() throws IOException {
        return FirebaseAuth.getInstance(getFirebaseApp());
    }

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        return FirebaseDatabase.getInstance();
    }

    @Bean
    public Firestore getDatabase() throws IOException {
        FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        return firestoreOptions.getService();
    }

    @Bean
    public Bucket getStorage() throws IOException {
        return StorageClient.getInstance().bucket();
    }

    @Bean
    public FirebaseMessaging getMessaging() throws IOException {
        return FirebaseMessaging.getInstance(getFirebaseApp());
    }
}
