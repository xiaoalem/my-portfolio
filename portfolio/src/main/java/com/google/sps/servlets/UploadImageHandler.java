package com.google.sps.servlets;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Takes an image submitted by the user and uploads it to Cloud Storage, and then displays it as
 * HTML in the response.
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadImageHandler extends HttpServlet{
    private static String projectId = "ysun-sps-summer22";
    private static String bucketName = "ysun-sps-summer22.appspot.com";
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
  
      // Get the file chosen by the user.
      Part filePart = request.getPart("image");
      String fileName = filePart.getSubmittedFileName() + System.currentTimeMillis();
      InputStream fileInputStream = filePart.getInputStream();
  
      // Upload the file and get its URL
      String uploadedFileUrl = uploadToCloudStorage(fileName, fileInputStream);
  
      // Output some HTML that shows the data the user entered.
      // You could also store the uploadedFileUrl in Datastore instead.
      PrintWriter out = response.getWriter();
      out.println("<p>Here's the image you uploaded:</p>");
      out.println("<a href=\"" + uploadedFileUrl + "\">");
      out.println("<img src=\"" + uploadedFileUrl + "\" />");
      out.println("</a>");
    }

    /** Uploads a file to Cloud Storage and returns the uploaded file's URL. */
    private static String uploadToCloudStorage(String fileName, InputStream fileInputStream) {
    Storage storage =
        StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    BlobId blobId = BlobId.of(bucketName, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

    // Upload the file to Cloud Storage.
    Blob blob = storage.create(blobInfo, fileInputStream);

    // Return the uploaded file's URL.
    return blob.getMediaLink();
  }
}
