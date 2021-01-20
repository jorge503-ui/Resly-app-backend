package es.resly.app.backend.commons.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class FirebaseCrudRepository<T extends Object,ID> {

    protected Firestore dbFirestore = FirestoreClient.getFirestore();
    private String collection;

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    @Transactional
    public void save(T t, String id){
        dbFirestore.collection(collection).document(id).set(t);
    }

    @Transactional
    public T save(T t) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> addedDocRef = dbFirestore.collection(collection).add(t);
        return (T) addedDocRef.get().get().get().toObject(Object.class);
    }

    @Transactional
    public <S extends T> Iterable<S> saveAll(Iterable<S> itrbl) throws ExecutionException, InterruptedException {
        List<S> it = new ArrayList <>();
        for (S s:itrbl) {
            String id = UUID.randomUUID().toString();
            ApiFuture<DocumentReference> addedDocRef = dbFirestore.collection(collection).add(s);
            it.add((S) addedDocRef.get().get().get().toObject(Object.class));
        }
        return it;
    }

    public boolean existsById(ID id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection(collection).document((String) id);
        return docRef.get().get().exists();
    }

    public Iterable<T> findAll(){
        ApiFuture<QuerySnapshot> query = dbFirestore.collection(collection).get();

        List<T> t = new ArrayList<>();
        // query.get() blocks on response
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            t = (List<T>) querySnapshot.toObjects(Object.class);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Ocurrio un problema",e);
        }
        return t;
    }

    public Iterable<T> findAllById(Iterable<ID> itrbl) throws ExecutionException, InterruptedException {
        List<T> list = new ArrayList <>();
        for (ID id:itrbl) {
            DocumentReference docRef = dbFirestore.collection(collection).document((String) id);
            list.add((T) docRef.get().get().toObject(Object.class));
        }
        return list;
    }

    public long count() throws ExecutionException, InterruptedException {
        return dbFirestore.collection(collection).get().get().size();
    }

    public void deleteAll(int batchSize){
        try {
            // retrieve a small batch of documents to avoid out-of-memory errors
            ApiFuture<QuerySnapshot> future = dbFirestore.collection(collection).limit(batchSize).get();
            int deleted = 0;
            // future.get() blocks on document retrieval
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                // retrieve and delete another batch
                deleteAll(batchSize);
            }
        } catch (Exception e) {
            logger.error("Error eliminado registros",e);
        }
    }

    public T findById(ID id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(collection).document((String) id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Object o = null;

        if(document.exists()) {
            o = document.toObject(Object.class);
            return (T) o;
        }else {
            return null;
        }
    }

    public T update(T t, String id){
        dbFirestore.collection(collection).document(id).set(t);
        return t;
    }

    public void deleteById(ID id){
        dbFirestore.collection(collection).document((String) id).delete();
    }

    public void setCollection(String c){
        collection = c;
    }

    public String getCollection(){
        return this.collection;
    }
}
