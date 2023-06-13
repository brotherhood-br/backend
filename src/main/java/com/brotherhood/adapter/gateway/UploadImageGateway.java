package com.brotherhood.adapter.gateway;

import com.brotherhood.domain.dataprovider.UploadImageDataProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.Role;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.codec.binary.Base64;

import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Singleton
public class UploadImageGateway implements UploadImageDataProvider {

    public static final String BUCKET_AUTH = "src/main/resources/brotherhood-385823-6226dc88168b.json";
    public static final String VIEW_ROLE = "roles/storage.objectViewer";
    public static final String INLINE = "inline";
    public static final String STORAGE_BASE_URL = "https://storage.googleapis.com";
    public static final String BUCKET_NAME = "brotherhood-images";

    public void uploadObjectFromMemory(String bucketName, String extension, String objectName, byte[] contents) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(BUCKET_AUTH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        Policy policy = getPolicy(bucketName, storage);
        storage.setIamPolicy(bucketName, policy);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentDisposition(INLINE).setContentType(extension).build();
        storage.createFrom(blobInfo, new ByteArrayInputStream(contents));
    }

    private static Policy getPolicy(String bucketName, Storage storage) {
        Policy policy = storage.getIamPolicy(bucketName);
        policy = policy.toBuilder()
                .addIdentity(Role.of(VIEW_ROLE), Identity.allUsers())
                .build();
        return policy;
    }

    @Override
    public String uploadImage(String base64) {
        try {
            String[] strings = base64.split(",");
            String extension = getExtension(strings[0]);
            String fileName = UUID.randomUUID() + "." + extension;
            uploadObjectFromMemory(BUCKET_NAME, getExtension(strings[0]), fileName, Base64.decodeBase64(strings[1]));
            return String.format("%s/%s/%s", STORAGE_BASE_URL, BUCKET_NAME, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao tentar realizar o upload da imagem");
        }
    }

    private String getExtension(String contentType) {
        if (contentType.equals("data:image/jpeg;base64")) {
            return "jpeg";
        }
        if (contentType.equals("data:image/png;base64")) {
            return "png";
        }
        return "jpg";
    }

}
