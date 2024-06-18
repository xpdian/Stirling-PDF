package stirling.software.SPDF.controller.api.converters;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;

import io.github.pixee.security.Filenames;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.config.FileConfig;
import stirling.software.SPDF.domain.vo.FileHandlerResultVO;
import stirling.software.SPDF.model.api.GeneralFile;
import stirling.software.SPDF.utils.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Convert", description = "Convert APIs")
@RequestMapping("/api/v1/convert")
public class ConvertOfficeController {

    private final FileConfig fileConfig;

    public FileHandlerResultVO convertToPdf(MultipartFile inputFile, HttpServletResponse response)
            throws Exception {
        // Check for valid file extension
        String originalFilename = Filenames.toSimpleFileName(inputFile.getOriginalFilename());
        if (originalFilename == null
                || !isValidFileExtension(FilenameUtils.getExtension(originalFilename))) {
            throw new IllegalArgumentException("Invalid file extension");
        }

        File outTempFile = null;
        try {
            outTempFile =
                    FileUtil.mkdirTempFile(
                            fileConfig.getPath()
                                    + SecurityUtil.getCurrentUserId()
                                    + "-"
                                    + FileUtil.genRandomFileName("pdf"));

            // 加载Word文档
            Document doc = new Document(inputFile.getInputStream());
            // 创建 PDF 保存选项对象
            PdfSaveOptions saveOptions = new PdfSaveOptions();
            // 将文档另存为 PDF
            if (fileConfig.getTmpSave()) {
                FileOutputStream os = new FileOutputStream(outTempFile);
                doc.save(os, saveOptions);
                byte[] fileContent = Files.readAllBytes(outTempFile.toPath());
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                response.getOutputStream().write(fileContent);
                FileHandlerResultVO result = new FileHandlerResultVO();
                result.setFileName(inputFile.getOriginalFilename());
                result.setFileSize(fileContent.length);
                result.setTmpFilePath(outTempFile.getPath());
                return result;
            } else {
                doc.save(response.getOutputStream(), saveOptions);
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                response.getOutputStream().flush();
                return null;
            }
        } finally {
            outTempFile.delete();
        }
    }

    private boolean isValidFileExtension(String fileExtension) {
        String extensionPattern = "^(?i)[a-z0-9]{2,4}$";
        return fileExtension.matches(extensionPattern);
    }

    @PostMapping(consumes = "multipart/form-data", value = "/file/pdf")
    @Operation(
            summary = "Convert a file to a PDF using LibreOffice",
            description =
                    "This endpoint converts a given file to a PDF using LibreOffice API  Input:ANY Output:PDF Type:SISO")
    public FileHandlerResultVO processFileToPDF(
            @ModelAttribute GeneralFile request, HttpServletResponse response) throws Exception {
        MultipartFile inputFile = request.getFileInput();
        // unused but can start server instance if startup time is to long
        // LibreOfficeListener.getInstance().start();
        return convertToPdf(inputFile, response);
    }
}
