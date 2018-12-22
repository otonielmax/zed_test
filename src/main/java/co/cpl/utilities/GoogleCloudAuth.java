package co.cpl.utilities;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;

public class GoogleCloudAuth {

  private Storage storage;
  private String pathJsonConfig;

  public GoogleCloudAuth() {
    setPathJsonConfig("C:\\Users\\otoni\\Downloads\\google_cloud.json");
  }

  public GoogleCloudAuth(String pathJsonConfig) {
    setPathJsonConfig(pathJsonConfig);
  }

  public static void authImplicit() {
    // If you don't specify credentials when constructing the client, the client library will
    // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
    Storage storage = StorageOptions.getDefaultInstance().getService();

    System.out.println("Buckets:");
    Page<Bucket> buckets = storage.list();
    for (Bucket bucket : buckets.iterateAll()) {
      System.out.println(bucket.toString());
    }
  }

  public void authExplicit() throws IOException {
    // You can specify a credential file by providing a path to GoogleCredentials.
    // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(getPathJsonConfig()))
            .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
    this.setStorage(StorageOptions.newBuilder().setCredentials(credentials).build().getService());
    /*
    System.out.println("Buckets:");
    Page<Bucket> buckets = storage.list();
    for (Bucket bucket : buckets.iterateAll()) {
      System.out.println(bucket.toString());
    }
    */
  }

  public Storage getStorage() {
    return storage;
  }

  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  public String getPathJsonConfig() {
    return pathJsonConfig;
  }

  public void setPathJsonConfig(String pathJsonConfig) {
    this.pathJsonConfig = pathJsonConfig;
  }
}
