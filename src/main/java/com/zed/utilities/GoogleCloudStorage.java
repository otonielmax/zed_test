package com.zed.utilities;

import com.google.api.client.http.InputStreamContent;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GoogleCloudStorage {

  private Storage storage;

  public GoogleCloudStorage(Storage storage) {
    this.storage = storage;
  }

  /**
   * Extracts the file payload from an HttpServletRequest, checks that the file extension
   * is supported and uploads the file to Google Cloud Storage.
   */
  public String getImageUrl(HttpServletRequest req, HttpServletResponse resp,
                            final String bucket, String directory) throws IOException, ServletException {
    Part filePart = req.getPart("file");
    final String fileName = filePart.getSubmittedFileName();
    String imageUrl = req.getParameter("imageUrl");
    // Check extension of file
    if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
      final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
      String[] allowedExt = { "jpg", "jpeg", "png", "gif" };
      for (String s : allowedExt) {
        if (extension.equals(s)) {
          return this.uploadFile(filePart, bucket, directory);
        }
      }
      throw new ServletException("file must be an image");
    }
    return imageUrl;
  }

  /**
   * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
   * environment variable, appending a timestamp to end of the uploaded filename.
   */
  @SuppressWarnings("deprecation")
  public String uploadFile(Part filePart, final String bucketName, String directory) throws IOException {
    DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
    DateTime dt = DateTime.now(DateTimeZone.UTC);
    String dtString = dt.toString(dtf);
    final String fileName = filePart.getSubmittedFileName() + dtString;

    // the inputstream is closed by default, so we don't need to close it here
    BlobInfo blobInfo =
            storage.create(
                    BlobInfo
                            .newBuilder(bucketName, directory + "/" + fileName)
                            // Modify access list to allow all users with link to read file
                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(),
                    filePart.getInputStream());
    // return the public download link
    return blobInfo.getMediaLink();
  }

  public String generateNameFile(String nameFile) {
    DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
    DateTime dt = DateTime.now(DateTimeZone.UTC);
    String dtString = dt.toString(dtf);

    return nameFile + dtString + ".jpg";
  }

  public String uploadFile(
          byte[] bytes,
          final String bucketName,
          String contentType,
          final String fileName,
          String directory) throws IOException {
    /*
    DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
    DateTime dt = DateTime.now(DateTimeZone.UTC);
    String dtString = dt.toString(dtf);

    final String fileName = nameFile + dtString + ".jpg";
    */
    InputStreamContent inputStreamContent = new InputStreamContent(contentType, new ByteArrayInputStream(bytes));

    // the inputstream is closed by default, so we don't need to close it here
    BlobInfo blobInfo =
            storage.create(
                    BlobInfo
                            .newBuilder(bucketName, directory + "/" + fileName)
                            // Modify access list to allow all users with link to read file
                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(),
                    inputStreamContent.getInputStream());
    // return the public download link
    return blobInfo.getMediaLink();
  }
}
