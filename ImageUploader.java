package com.home;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

import com.google.auth.oauth2.GoogleCredentials;
 import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


public class ImageUploader {
    public static String upload(File file) throws Exception {
        // Load credentials manually (the same JSON used in FirebaseConfig)
       // FileInputStream serviceAccount = new FileInputStream("src/main/resources/glam-100a6-firebase-adminsdk-fbsvc-3d0f25dd37.json");
       FileInputStream serviceAccount = new FileInputStream("C:\\Users\\sneha\\Downloads\\glam-100a6-firebase-adminsdk-fbsvc-3d0f25dd37.json");


        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("glam-100a6") // ✅ match project ID in your JSON
                .build()
                .getService();

        String fileName = "products/" + System.currentTimeMillis() + "_" + file.getName();
        byte[] bytes = Files.readAllBytes(file.toPath());

        BlobInfo blobInfo = BlobInfo.newBuilder("glam-100a6", fileName)
                .setContentType("image/jpg")
                .build();

        storage.create(blobInfo, bytes);

        return "https://firebasestorage.googleapis.com/v0/b/glam-100a6.appspot.com/o/"
                + fileName.replace("/", "%2F")
                + "?alt=media";
    }
}