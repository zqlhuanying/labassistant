package com.labassistant.service;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;

/**
 * 生成PDF
 * @author zql
 * @date 2015/10/27
 */
public interface ToPDFService {

	public void toPdf(String pdfName, String myExpID) throws DocumentException, IOException;
	
	public List<Object> getPdfList();
}
