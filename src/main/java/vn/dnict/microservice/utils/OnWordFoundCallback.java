package vn.dnict.microservice.utils;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFRun;

interface OnWordFoundCallback {
	void onWordFoundInRun(XWPFRun run);
    void onWordFoundInPreviousCurrentNextRun(List<XWPFRun> runs, int currentRun);
}
