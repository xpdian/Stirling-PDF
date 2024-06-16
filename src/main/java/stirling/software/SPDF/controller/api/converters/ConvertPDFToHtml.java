package stirling.software.SPDF.controller.api.converters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.model.api.PDFFile;
import stirling.software.SPDF.utils.PDFToFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "Convert", description = "Convert APIs")
@RequestMapping("/api/v1/convert")
public class ConvertPDFToHtml {

    private final PDFToFile pdfToFile;

    @PostMapping(consumes = "multipart/form-data", value = "/pdf/html")
    @Operation(
            summary = "Convert PDF to HTML",
            description =
                    "This endpoint converts a PDF file to HTML format. Input:PDF Output:HTML Type:SISO")
    public ResponseEntity<byte[]> processPdfToHTML(@ModelAttribute PDFFile request)
            throws Exception {
        MultipartFile inputFile = request.getFileInput();
        return pdfToFile.processPdfToHtml(inputFile);
    }
}
