package vn.dnict.microservice.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

public class TextReplacer extends WordFinder {
	private static int DEFAULT_TEXT_POS = 0;

    private String replacement;
    private String bookmark;
    public void replaceInText(XWPFDocument document, String bookmark, String replacement) {
        this.replacement = replacement;
        this.bookmark = bookmark;
        findWordsInText(document, bookmark);
    }

    public void replaceInTable(XWPFDocument document, String bookmark, String replacement) {
        this.replacement = replacement;
        this.bookmark = bookmark;
        findWordsInTable(document, bookmark);
    }

    @Override
    public void onWordFoundInRun(XWPFRun run) {
        replaceWordInRun(run);
    }

    @Override
    public void onWordFoundInPreviousCurrentNextRun(List<XWPFRun> runs, int currentRun) {
        replaceWordInPreviousCurrentNextRuns(runs, currentRun);
    }

    private void replaceWordInPreviousCurrentNextRuns(List<XWPFRun> runs, int currentRun) {
        boolean replacedInPreviousRun = replaceRunTextStart(runs.get(currentRun - 1));
        if (replacedInPreviousRun) {
            deleteTextFromRun(runs.get(currentRun));
        } else {
            replaceRunTextStart(runs.get(currentRun));
        }
        cleanRunTextStart(runs.get(currentRun + 1));
    }

    private void deleteTextFromRun(XWPFRun run) {
        run.setText("", DEFAULT_TEXT_POS);
    }

    //replaceAll() first parameter is used as regex pattern so normally special chars have to be escaped.
    //Pattern.quote() transforms given string into literal where special chars are ignored, thus can be used without escaping
    private void replaceWordInRun(XWPFRun run) {
    	if(replacement == null) {
    		replacement = "";
    	}
    	String replacedText = run.getText(DEFAULT_TEXT_POS).replaceAll(Pattern.quote(bookmark), replacement);
    	System.out.println("bookmark replaceWordInRun: " + bookmark);
		 if (replacedText.contains("*")) {
			 System.out.println("chạy xuống hàng");
		     String[] lines = replacedText.split("\\*");
		     run.setText(lines[0], 0); // set first line into XWPFRun
		     for(int i=1;i<lines.length;i++){
		         // add break and insert new text
		    	 if(1 <= i && i < lines.length -1) {
		    		run.addBreak();
		    		System.out.println("add break "  + i);
		    	 }
   		         run.setText(lines[i].trim());
		     }
		 } else {
			 System.out.println("chạy bình thường replaceWordInRun");
			 run.setText(replacedText, DEFAULT_TEXT_POS);
		 }
    }

    private boolean replaceRunTextStart(XWPFRun run) {
    	if(replacement == null) {
    		replacement = "";
    	}
        String text = run.getText(DEFAULT_TEXT_POS);
        String remainingBookmark = getRemainingBookmarkStart(text, bookmark);
        if (!remainingBookmark.isEmpty()) {
        	text = text.replace(remainingBookmark, replacement);
        	if (text.contains("\\*")) {
   			 System.out.println("chạy xuống hàng");
   		     String[] lines = text.split("\\*");
   		     System.out.println("lines : " + Arrays.toString(lines));
	   		 /* for (String s : lines) {
	              XWPFParagraph par = document.createParagraph();
	              par.setAlignment(ParagraphAlignment.BOTH);
	              run = par.createRun();
	              run.setText(s);
	              run.setFontFamily("Times New Roman");
	              run.setFontSize(14);
	          }*/
   		     run.setText(lines[0], 0); // set first line into XWPFRun
   		     for(int i=1;i<lines.length;i++){
   		         // add break and insert new text
   		    	 if(lines[i]!=null && lines[i].trim().length()>0) {
   		    		 if(1 <= i && i < lines.length -1) {
   		    			run.addBreak();
   		    		 }
   		    		 CTR ctr = run.getCTR();
   		    		 ctr.addNewTab();
   	   		         run.setText(lines[i].trim());
   		    	 }
   		     }
	   		} else {
	   			 System.out.println("chạy bình thường replaceRunTextStart");
	   			 run.setText(text, DEFAULT_TEXT_POS);
	   		}
            return true;
        }
        return false;
    }

    private void cleanRunTextStart(XWPFRun run) {
        String text = run.getText(DEFAULT_TEXT_POS);
        String remainingBookmark = getRemainingBookmarkEnd(text, bookmark);
        text = text.replace(remainingBookmark, "");
        run.setText(text, DEFAULT_TEXT_POS);
    }

    private String getRemainingBookmarkEnd(String text, String bookmark) {
        if (!text.startsWith(bookmark)) {
            return getRemainingBookmarkEnd(text, bookmark.substring(1, bookmark.length()));
        } else {
            return bookmark;
        }
    }

    private String getRemainingBookmarkStart(String text, String bookmark) {
        if (!text.endsWith(bookmark)) {
            return getRemainingBookmarkStart(text, bookmark.substring(0, bookmark.length() - 1));
        } else {
            return bookmark;
        }
    }
}
