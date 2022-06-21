package com.google.sps.servlets;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Shows all of the images uploaded to Cloud Storage. */
@WebServlet("/list-images")
public class ListImageServlet extends HttpServlet{
    private static String projectId = "ysun-sps-summer22";
    private static String bucketName = "ysun-sps-summer22.appspot.com";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Page<Blob> blobs = getImages();
  
        // Output <img> elements as HTML.
        
        // response.setContentType("text/html;");
        response.getWriter().println("<html><head><style>img {height: auto; max-width: 540px; max-height: 540px; width: auto;}</style></head><body><p1>Here are all images you have uploaded</p1><br>");
        for (Blob blob : blobs.iterateAll()) {
            String imgTag = String.format("<a href=\"%s\" /><img src=\"%s\" /></a>", blob.getMediaLink(), blob.getMediaLink());
            response.getWriter().println(imgTag);
            response.getWriter().println("<br>");
        response.getWriter().println("</body></html>");
      }
    }

    // List all of the uploaded files.
    private Page<Blob> getImages() throws IOException {
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    Bucket bucket = storage.get(bucketName);
    Page<Blob> blobs = bucket.list();
    return blobs;
    }
}
