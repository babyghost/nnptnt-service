package vn.dnict.microservice.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordReplacer {
	private XWPFDocument document;
    private TextReplacer replacer;

    /**
     * Creates WordReplacer with file to modify.
     *
     * @param docxFile file of type docx.
     * @throws IOException thrown if file is not found or is not required type.
     */
    public WordReplacer(File docxFile) throws IOException {
    	FileInputStream inputStream = new FileInputStream(docxFile);
        init(new XWPFDocument(inputStream));
    }

    /**
     * Creates WordReplacer with XWPFDocument to modify.
     * @param xwpfDoc to modify.
     */
    public WordReplacer(XWPFDocument xwpfDoc) {
        init(xwpfDoc);
    }

    private void init(XWPFDocument xwpfDoc) {
        if (xwpfDoc == null) throw new NullPointerException();
        document = xwpfDoc;
        replacer = new TextReplacer();
    }

    /**
     * Replaces all occurrences of a bookmark only in the text of the file with a replacement string.
     * @param bookmark word to replace.
     * @param replacement word of replacement.
     */
    public void replaceWordsInText(String bookmark, String replacement) {
        replacer.replaceInText(document, bookmark, replacement);
    }

    /**
     * Replaces all occurrences of a bookmark only in tables of the file with a replacement string.
     * @param bookmark word to replace.
     * @param replacement word of replacement.
     */
    public void replaceWordsInTables(String bookmark, String replacement) {
        replacer.replaceInTable(document, bookmark, replacement);
    }

    /**
     * Most of the time we want our template files untouched. Creates file from path, saves the modified document to it and returns it.
     * @param path filepath (dirs + filename).
     * @return modified file.
     * @throws Exception thrown if some issues while saving occur - mostly due to unavailable file or permissions.
     */
    public File saveAndGetModdedFile(String path) throws Exception {
        File file = new File(path);
        return saveToFile(file);
    }

    /**
     * Most of the time we want our template files untouched. Saves the modified document to the given file and returns it.
     * @param file to save to.
     * @return modified file.
     * @throws Exception thrown if some issues while saving occur - mostly due to unavailable file or permissions.
     */
    public File saveAndGetModdedFile(File file) throws Exception {
        return saveToFile(file);
    }

    public XWPFDocument getModdedXWPFDoc() {
        return document;
    }
   
    public void getModdedFile(XWPFDocument docx, String tenFile, Long id, HttpServletResponse response) throws IOException {
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		docx.write(outByteStream);
		byte[] outArray = outByteStream.toByteArray();

		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		response.addHeader("Content-Disposition", "attachment; filename=\""+ tenFile + "_" + id + ".docx" + "\";");

		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.flushBuffer();

		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

    private File saveToFile(File file) throws Exception {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, false);
            document.write(out);
            document.close();
            return file;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
