package stirling.software.SPDF.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import io.github.pixee.security.Filenames;
import stirling.software.SPDF.domain.Result;

public class WebResponseUtils {

    public static Result boasToWebResponse(
            ByteArrayOutputStream baos, String docName) throws IOException {
        return WebResponseUtils.bytesToWebResponse(baos.toByteArray(), docName);
    }

    public static Result boasToWebResponse(
            ByteArrayOutputStream baos, String docName, MediaType mediaType) throws IOException {
        return WebResponseUtils.bytesToWebResponse(baos.toByteArray(), docName, mediaType);
    }

    public static Result multiPartFileToWebResponse(MultipartFile file)
            throws IOException {
        String fileName = Filenames.toSimpleFileName(file.getOriginalFilename());
        MediaType mediaType = MediaType.parseMediaType(file.getContentType());

        byte[] bytes = file.getBytes();

        return bytesToWebResponse(bytes, fileName, mediaType);
    }

    public static Result bytesToWebResponse(
            byte[] bytes, String docName, MediaType mediaType) throws IOException {

        // Return the PDF as a response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(bytes.length);
        String encodedDocName =
                URLEncoder.encode(docName, StandardCharsets.UTF_8.toString())
                        .replaceAll("\\+", "%20");
        headers.setContentDispositionFormData("attachment", encodedDocName);
        return Result.ok().data(Base64.getEncoder().encodeToString(bytes));
    }

    public static Result bytesToWebResponse(byte[] bytes, String docName)
            throws IOException {
        return bytesToWebResponse(bytes, docName, MediaType.APPLICATION_PDF);
    }

    public static Result pdfDocToWebResponse(PDDocument document, String docName)
            throws IOException {

        // Open Byte Array and save document to it
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        // Close the document
        document.close();

        return boasToWebResponse(baos, docName);
    }
}
