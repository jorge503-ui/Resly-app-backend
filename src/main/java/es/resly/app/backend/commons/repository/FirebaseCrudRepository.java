package es.resly.app.backend.commons.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class FirebaseCrudRepository<T,ID> {

    protected Firestore dbFirestore = FirestoreClient.getFirestore();
    private String collection;

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    public void save(T t, String id){
        dbFirestore.collection(collection).document(id).set(t);
    }

    public <S extends T> Iterable<S> saveAll(Iterable<S> itrbl){return null;}

    public boolean existsById(ID id){return false;}

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

    public Iterable<T> findAllById(Iterable<ID> itrbl){return null;}

    public long count(){return 0;}

    public void delete(T t){}

    public void deleteAll(Iterable<? extends T> itrbl){}

    public void deleteAll(){}

    public T findById(ID id){return null;}

    public T update(T t){return null;}

    public void deleteById(ID id){}

    public void setCollection(String c){
        collection = c;
    }
}
