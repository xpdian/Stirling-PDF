package stirling.software.SPDF.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

import io.github.pixee.security.Filenames;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.config.FileConfig;
import stirling.software.SPDF.domain.vo.FileHandlerResultVO;
import stirling.software.SPDF.enums.FileTypeEnum;
import stirling.software.SPDF.service.SysFileLogService;
import stirling.software.SPDF.utils.ProcessExecutor.ProcessExecutorResult;

@Component
@RequiredArgsConstructor
public class PDFToFile {

    private final FileConfig fileConfig;
    private final SysFileLogService fileLogService;

    private static final Logger logger = LoggerFactory.getLogger(PDFToFile.class);

    public ResponseEntity<byte[]> processPdfToHtml(MultipartFile inputFile)
            throws IOException, InterruptedException {
        if (!"application/pdf".equals(inputFile.getContentType())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Get the original PDF file name without the extension
        String originalPdfFileName = Filenames.toSimpleFileName(inputFile.getOriginalFilename());
        String pdfBaseName = originalPdfFileName;
        if (originalPdfFileName.contains(".")) {
            pdfBaseName = originalPdfFileName.substring(0, originalPdfFileName.lastIndexOf('.'));
        }

        Path tempInputFile = null;
        Path tempOutputDir = null;
        byte[] fileBytes;
        String fileName = "temp.file";

        try {
            // Save the uploaded file to a temporary location
            tempInputFile = Files.createTempFile("input_", ".pdf");
            inputFile.transferTo(tempInputFile);

            // Prepare the output directory
            tempOutputDir = Files.createTempDirectory("output_");

            // Run the pdftohtml command with complex output
            List<String> command =
                    new ArrayList<>(
                            Arrays.asList(
                                    "pdftohtml", "-c", tempInputFile.toString(), pdfBaseName));

            ProcessExecutorResult returnCode =
                    ProcessExecutor.getInstance(ProcessExecutor.Processes.PDFTOHTML)
                            .runCommandWithOutputHandling(command, tempOutputDir.toFile());

            // Get output files
            List<File> outputFiles = Arrays.asList(tempOutputDir.toFile().listFiles());

            // Return output files in a ZIP archive
            fileName = pdfBaseName + "ToHtml.zip";
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                for (File outputFile : outputFiles) {
                    ZipEntry entry = new ZipEntry(outputFile.getName());
                    zipOutputStream.putNextEntry(entry);
                    try (FileInputStream fis = new FileInputStream(outputFile)) {
                        IOUtils.copy(fis, zipOutputStream);
                    } catch (IOException e) {
                        logger.error("Exception writing zip entry", e);
                    }
                    zipOutputStream.closeEntry();
                }
            } catch (IOException e) {
                logger.error("Exception writing zip", e);
            }
            fileBytes = byteArrayOutputStream.toByteArray();

        } finally {
            // Clean up the temporary files
            if (tempInputFile != null) Files.deleteIfExists(tempInputFile);
            if (tempOutputDir != null) FileUtils.deleteDirectory(tempOutputDir.toFile());
        }

        return WebResponseUtils.bytesToWebResponse(
                fileBytes, fileName, MediaType.APPLICATION_OCTET_STREAM);
    }

    /**
     * pdf转office类型文件
     *
     * @param inputFile 用户上传的文件
     * @param outputFormat 输出文件格式
     * @param libreOfficeFilter libreOffice过滤器
     * @param response 响应
     * @throws IOException
     * @throws InterruptedException
     */
    public FileHandlerResultVO processPdfToOfficeFormat(
            MultipartFile inputFile,
            String outputFormat,
            String libreOfficeFilter,
            HttpServletResponse response)
            throws IOException, InterruptedException {

        if (!"application/pdf".equals(inputFile.getContentType())) {
            ResultUtil.setErrorResponse(707, "上传的文件类型不正确，无法转换", response);
            return null;
        }

        // Get the original PDF file name without the extension
        String originalPdfFileName = Filenames.toSimpleFileName(inputFile.getOriginalFilename());

        if (originalPdfFileName == null || "".equals(originalPdfFileName.trim())) {
            originalPdfFileName = "output.pdf";
        }
        // Assume file is pdf if no extension
        String pdfBaseName = originalPdfFileName;
        if (originalPdfFileName.contains(".")) {
            pdfBaseName = originalPdfFileName.substring(0, originalPdfFileName.lastIndexOf('.'));
        }
        // Validate output format
        List<String> allowedFormats =
                Arrays.asList("doc", "docx", "odt", "ppt", "pptx", "odp", "rtf", "xml", "txt:Text");
        if (!allowedFormats.contains(outputFormat)) {
            ResultUtil.setErrorResponse(707, "不支持该文件类型，无法执行转换", response);
            return null;
        }

        // 临时用户上传的文件
        File inputTempFile =
                FileUtil.mkdirTempFile(
                        fileConfig.getPath()
                                + SecurityUtil.getCurrentUserId()
                                + "-"
                                + FileUtil.genRandomFileName(FileTypeEnum.PDF.getValue()));
        // 转化后的文件
        File outTempDir = null;

        // 如果是docx转换，使用这个
        if (outputFormat.equals("docx") || outputFormat.equals("doc")) {
            String wordPath = fileConfig.getPath() + FileUtil.genRandomFileName(outputFormat);
            File file = new File(wordPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inputFile.getInputStream());
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            if (outputFormat.equals("docx")) {
                doc.save(os, SaveFormat.DocX);
            } else {
                doc.save(os, SaveFormat.Doc);
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.getOutputStream().write(fileContent);
            response.getOutputStream().flush();
            os.close();
            inputTempFile.delete();
            FileHandlerResultVO result = new FileHandlerResultVO();
            result.setFileSize(fileContent.length);
            result.setFileName(inputFile.getOriginalFilename());
            result.setTmpFilePath(file.getPath());
            return result;
        }

        outTempDir =
                FileUtil.mkdirTempFileDir(
                        fileConfig.getPath()
                                + SecurityUtil.getCurrentUserId()
                                + "-"
                                + FileUtil.genRandomFileName(""));

        byte[] fileBytes;

        try {
            inputFile.transferTo(inputTempFile);

            // Run the LibreOffice command
            List<String> command =
                    new ArrayList<>(
                            Arrays.asList(
                                    "soffice",
                                    "--infilter=" + libreOfficeFilter,
                                    "--convert-to",
                                    outputFormat,
                                    "--outdir",
                                    outTempDir.getPath(),
                                    inputTempFile.getPath()));
            ProcessExecutorResult returnCode =
                    ProcessExecutor.getInstance(ProcessExecutor.Processes.LIBRE_OFFICE)
                            .runCommandWithOutputHandling(command);

            // Get output files
            List<File> outputFiles = Arrays.asList(outTempDir.listFiles());

            FileHandlerResultVO result = new FileHandlerResultVO();

            result.setFileName(inputFile.getOriginalFilename());

            if (outputFiles.size() == 1) {
                File outputFile = outputFiles.get(0);
                if ("txt:Text".equals(outputFormat)) {
                    outputFormat = "txt";
                }
                result.setTmpFilePath(outputFile.getPath());
                fileBytes = FileUtils.readFileToByteArray(outputFile);
            } else {
                // Return output files in a ZIP archive
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                    for (File outputFile : outputFiles) {
                        ZipEntry entry = new ZipEntry(outputFile.getName());
                        zipOutputStream.putNextEntry(entry);
                        try (FileInputStream fis = new FileInputStream(outputFile)) {
                            IOUtils.copy(fis, zipOutputStream);
                        } catch (IOException e) {
                            logger.error("Exception writing zip entry", e);
                        }

                        zipOutputStream.closeEntry();
                    }
                } catch (IOException e) {
                    logger.error("Exception writing zip", e);
                }
                result.setTmpFilePath(outTempDir.getPath());
                fileBytes = byteArrayOutputStream.toByteArray();
            }
            System.out.println("fileBytes=" + fileBytes.length);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();

            result.setFileSize(fileBytes.length);
            return result;
        } finally {
            inputTempFile.deleteOnExit();
        }
    }
}
