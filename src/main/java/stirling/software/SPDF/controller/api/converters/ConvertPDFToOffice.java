package stirling.software.SPDF.controller.api.converters;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.annotation.FileLog;
import stirling.software.SPDF.domain.vo.FileHandlerResultVO;
import stirling.software.SPDF.model.api.PDFFile;
import stirling.software.SPDF.model.api.converters.PdfToPresentationRequest;
import stirling.software.SPDF.model.api.converters.PdfToTextOrRTFRequest;
import stirling.software.SPDF.model.api.converters.PdfToWordRequest;
import stirling.software.SPDF.utils.PDFToFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/convert")
@Tag(name = "Convert", description = "Convert APIs")
public class ConvertPDFToOffice {

    private final PDFToFile pdfToFile;

    @PostMapping(consumes = "multipart/form-data", value = "/pdf/presentation")
    @Operation(
            summary = "Convert PDF to Presentation format",
            description =
                    "This endpoint converts a given PDF file to a Presentation format. Input:PDF Output:PPT Type:SISO")
    public void processPdfToPresentation(
            @ModelAttribute PdfToPresentationRequest request, HttpServletResponse response)
            throws Exception {
        MultipartFile inputFile = request.getFileInput();
        String outputFormat = request.getOutputFormat();
        pdfToFile.processPdfToOfficeFormat(inputFile, outputFormat, "impress_pdf_import", response);
    }

    @PostMapping(consumes = "multipart/form-data", value = "/pdf/text")
    @Operation(
            summary = "Convert PDF to Text or RTF format",
            description =
                    "This endpoint converts a given PDF file to Text or RTF format. Input:PDF Output:TXT Type:SISO")
    public void processPdfToRTForTXT(
            @ModelAttribute PdfToTextOrRTFRequest request, HttpServletResponse response)
            throws Exception {
        MultipartFile inputFile = request.getFileInput();
        String outputFormat = request.getOutputFormat();
        if ("txt".equals(request.getOutputFormat())) {
            try (PDDocument document = Loader.loadPDF(inputFile.getBytes())) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                response.getOutputStream().write(text.getBytes());
                return;
            }
        } else {
            pdfToFile.processPdfToOfficeFormat(
                    inputFile, outputFormat, "writer_pdf_import", response);
        }
    }

    @PostMapping(consumes = "multipart/form-data", value = "/pdf/word")
    @Operation(
            summary = "Convert PDF to Word document",
            description =
                    "This endpoint converts a given PDF file to a Word document format. Input:PDF Output:WORD Type:SISO")
    @FileLog(opContent = "PDF转WORD")
    public FileHandlerResultVO processPdfToWord(
            @ModelAttribute PdfToWordRequest request, HttpServletResponse response)
            throws Exception {
        MultipartFile inputFile = request.getFileInput();
        String outputFormat = request.getOutputFormat();
        return pdfToFile.processPdfToOfficeFormat(
                inputFile, outputFormat, "writer_pdf_import", response);
    }

    @PostMapping(consumes = "multipart/form-data", value = "/pdf/xml")
    @Operation(
            summary = "Convert PDF to XML",
            description =
                    "This endpoint converts a PDF file to an XML file. Input:PDF Output:XML Type:SISO")
    public void processPdfToXML(@ModelAttribute PDFFile request, HttpServletResponse response)
            throws Exception {
        MultipartFile inputFile = request.getFileInput();

        pdfToFile.processPdfToOfficeFormat(inputFile, "xml", "writer_pdf_import", response);
        return;
    }
}
