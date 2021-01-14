package es.resly.app.backend.commons.repository;

import com.google.cloud.storage.*;
import es.resly.app.backend.auth.config.FirebaseConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FirebaseStorageRepository {

    @Autowired
    protected FirebaseConfig firebaseConfig = new FirebaseConfig();

    public String uploadObject(String objectName, byte[] object) throws IOException {
        Bucket bucket = firebaseConfig.getStorage();
        Storage storage = StorageOptions.newBuilder().setProjectId("api-project-445392307804").build().getService();
        BlobId blobId = BlobId.of(bucket.getName(), objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        return storage.create(blobInfo, object).getMediaLink();

    }

    public String uploadObject(String projectId, String bucketName, String objectName, byte[] object) {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        return storage.create(blobInfo, object).getMediaLink();
    }

    public byte[] downloadObject(String objectName) throws IOException {
        // The ID of your GCP project
        // String projectId = "your-project-id";

        // The ID of your GCS bucket
        // String bucketName = "your-unique-bucket-name";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to which the file should be downloaded
        // String destFilePath = "/local/path/to/file.txt";

        ByteArrayOutputStream buffer;
        OutputStream os = null;
            Bucket bucket = firebaseConfig.getStorage();

            Storage storage = StorageOptions.newBuilder().setProjectId("api-project-445392307804").build().getService();
            Blob blob = storage.get(BlobId.of(bucket.getName(), objectName));
            blob.downloadTo(os);

            buffer = (ByteArrayOutputStream) os;
        return buffer.toByteArray();
    }

    public byte[] downloadObject(String projectId, String bucketName, String objectName) {
        // The ID of your GCP project
        // String projectId = "your-project-id";

        // The ID of your GCS bucket
        // String bucketName = "your-unique-bucket-name";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to which the file should be downloaded
        // String destFilePath = "/local/path/to/file.txt";

        OutputStream os = null;
        ByteArrayOutputStream buffer;

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        Blob blob = storage.get(BlobId.of(bucketName, objectName));
        blob.downloadTo(os);
        buffer = (ByteArrayOutputStream) os;
        return buffer.toByteArray();
    }
}
