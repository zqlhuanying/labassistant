package com.labassistant.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 生成PDF
 * @author zql
 * @date 2015/10/27
 * iText默认不支持亚洲语言，导致iText生成中文出现问题，一般都是中文内容不能出现在pdf上，这是因为没有中文字体的原因.
 * 因此要想在pdf中出现中文，必须设置Font字体
 */
public class ToPDF {
	
	public static int ALIGN_LEFT = Element.ALIGN_LEFT;
	public static int ALIGN_CENTER = Element.ALIGN_CENTER;
	public static int ALIGN_RIGHT = Element.ALIGN_RIGHT;
	
	private Document document;

	// 设置段落
	public Paragraph paragraph() throws DocumentException, IOException{
		Font font = setDefaultFont();
		return paragraph("", font, ALIGN_LEFT);
	}
	
	public Paragraph paragraph(String string) throws DocumentException, IOException{
		Font font = setDefaultFont();
		return paragraph(string, font, ALIGN_LEFT);
	}
	
	public Paragraph paragraph(String string, Font font){
		//return paragraph(string, font, ALIGN_LEFT);
		return paragraph(string, font, ALIGN_LEFT);
	}
	
	public Paragraph paragraph(String string, int alignment) throws DocumentException, IOException{
		Font font = setDefaultFont();
		Paragraph p = new Paragraph(string, font);
		p.setAlignment(alignment);
		return p;
	}
	
	public Paragraph paragraph(String string, Font font, int alignment){
		Paragraph p = new Paragraph(string, font);
		p.setAlignment(alignment);
		return p;
	}
	
	public Paragraph paragraph(List<String> strings) throws DocumentException, IOException{
		Paragraph p = paragraph();
		for(String str : strings){
			p.add(str);
			p.add(",");
		}
		return p;
	}
	
	// 设置图片
	public Image image(String imgUrl) throws BadElementException, MalformedURLException, IOException{
		Image img = Image.getInstance(imgUrl);
		img.setSpacingBefore(5.0f);
		img.setSpacingAfter(5.0f);
		img.setIndentationLeft(5.0f);
		img.setIndentationRight(5.0f);
		img.setAlignment(Image.MIDDLE);
		if(img.getWidth() >= img.getHeight()) {
			img.scaleAbsolute(400.0f, 300.0f);
		} else {
			img.scaleAbsolute(300.0f, 400.0f);
		}
		return img;
	}
	
	// 设置图片在同一行
	public PdfPTable imageInline(List<String> imgUrls) throws BadElementException, MalformedURLException, IOException{
		float per_width = image(imgUrls.get(0)).getWidth();
		int columns = getColumns(getPageWidth(), per_width);
		PdfPTable pdfTable = new PdfPTable(columns);
		pdfTable.setWidthPercentage(100);
		
		int i = 1;
		for(String imgUrl : imgUrls){
			PdfPCell cell = new PdfPCell(image(imgUrl));
			// 居中显示
			cell.setHorizontalAlignment(ALIGN_CENTER);
			cell.setBorder(0);
			pdfTable.addCell(cell);
			
			if(i % columns == 0){
				pdfTable.completeRow();
			}
		}
		return pdfTable;
	}
	
	// 图片以块的方式呈现
	public PdfPTable imageBlock(List<Map<String, String>> imgs, int columns) throws MalformedURLException, IOException, DocumentException{
		PdfPTable pdfTable = new PdfPTable(columns);
		pdfTable.setWidthPercentage(100);
		
		for(Map<String, String> img : imgs){
			PdfPCell cell = new PdfPCell();
			Image im = null;
			Paragraph paragraph = paragraph();
			paragraph.setAlignment(ALIGN_CENTER);
			paragraph.setFont(setFont(null, 8, Font.NORMAL));
			paragraph.setSpacingBefore(-5.0f);
			try{
				im = image(img.get("imgUrl"));
			} catch (IOException e) {
				continue;
			}
			if(StringUtils.isNotBlank(img.get("title"))){
				paragraph.add(img.get("title") + ": ");
			}
			if(StringUtils.isNotBlank(img.get("desc"))){
				paragraph.add(img.get("desc"));
			}
			cell.addElement(im);
			cell.addElement(paragraph);
			// 居中显示
			cell.setHorizontalAlignment(ALIGN_CENTER);
			cell.setBorder(0);
			pdfTable.addCell(cell);
			pdfTable.completeRow();
		}
		return pdfTable;
	}
	
	// 获取表格的列数
	private int getColumns(float width, float per_width){
		float padding = 20.0f;				// 单元格内的间距
		per_width += padding;
		return (int)(width / per_width);
	}
	
	// 获取PDF Document
	public void getDocument(String pdfName) throws FileNotFoundException, DocumentException{
		this.document = new Document();
		PdfWriter.getInstance(this.document, new FileOutputStream(pdfName));
		this.document.open();
	}
	
	public void close(){
		this.document.close();
	}
	
	// Add Paragraph or Image
	public void add(Element element) throws DocumentException{
		this.document.add(element);
	}
	
	// 获取文档的宽度
	public float getPageWidth(){
		return this.document.getPageSize().getWidth();
	}
	
	// 设置默认字体
	public Font setDefaultFont() throws DocumentException, IOException{
		return setFont(null, 10, Font.NORMAL);
	}
	
	// 设置题目字体
	public Font setTitleFont() throws DocumentException, IOException{
		return setFont(null, 16, Font.BOLD);
	}
	
	// 设置一级标题字体
	public Font setH1Font() throws DocumentException, IOException{
		return setFont(null, 14, Font.BOLD);
	}
	
	// 设置二级标题字体
	public Font setH2Font() throws DocumentException, IOException{
		return setFont(null, 13, Font.BOLD);
	}
	
	public Font setFont(BaseFont baseFont, float size, int style) throws DocumentException, IOException{
		if(baseFont == null){
			baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		}
		return new Font(baseFont, size, style);
	}
	
	// test
	public static void main(String[] args){
		try {
			ToPDF pdf = new ToPDF();
			pdf.getDocument("f:\\1.pdf");
			Image img1 = pdf.image("f:\\1.jpg");
			pdf.add(img1);
			pdf.add(img1);
			pdf.add(new Paragraph("这是什么鬼",pdf.setDefaultFont()));
			
			ArrayList<String> imgUrls = new ArrayList<String>();
			imgUrls.add("F:\\1.jpg");
			imgUrls.add("F:\\1.jpg");
			imgUrls.add("F:\\1.jpg");
			imgUrls.add("F:\\1.jpg");
			pdf.add(pdf.imageInline(imgUrls));
			pdf.add(new Paragraph("In the previous example, you added a header and footer with the showTextAligned() method. This example demonstrates that it’s sometimes more interesting to use PdfPTable and writeSelectedRows(). You can define a bottom border for each cell so that the header is underlined. This is the most elegant way to add headers and footers, because the table mechanism allows you to position and align lines, images, and text"));
			pdf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
