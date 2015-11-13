package com.labassistant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.labassistant.beans.ExpInstructionEntity;
import com.labassistant.beans.MyExpEntity;
import com.labassistant.beans.MyExpProcessAttchEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.constants.AppConfig;
import com.labassistant.service.exp.ExpInstructionsMainService;
import com.labassistant.service.myexp.MyExpConsumableService;
import com.labassistant.service.myexp.MyExpEquipmentService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.service.myexp.MyExpProcessAttchService;
import com.labassistant.service.myexp.MyExpProcessService;
import com.labassistant.service.myexp.MyExpReagentService;
import com.labassistant.utils.DateUtil;
import com.labassistant.utils.FileUtil;
import com.labassistant.utils.ToPDF;

/**
 * 生成PDF
 * @author zql
 * @date 2015/10/27
 */
@Service
public class ToPDFServiceImpl implements ToPDFService {

	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	@Autowired
	private MyExpReagentService myExpReagentService;
	@Autowired
	private MyExpConsumableService myExpConsumableService;
	@Autowired
	private MyExpEquipmentService myExpEquipmentService;
	@Autowired
	private MyExpProcessService myExpProcessService;
	@Autowired
	private MyExpProcessAttchService myExpProcessAttchService;
	
	private final ToPDF pdf = new ToPDF();
	
	public void toPdf(String pdfName, String myExpID) throws DocumentException, IOException{
		pdf.getDocument(pdfName);
		String experimentName = "";
		String experimentDesc = "";
		String experimentTheory = "";
		// 获取说明书名/技术简介/实验原理
		MyExpEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			ExpInstructionEntity expInstruction = expInstructionsMainService.get(myExp.getExpInstructionID());
			if(expInstruction != null){
				experimentName = expInstruction.getExperimentName();
				experimentDesc = expInstruction.getExperimentDesc();
				experimentTheory = expInstruction.getExperimentTheory();
			}
		}
		setTopic(experimentName + "实验报告");
		setExperimentDesc(experimentDesc);
		setExperimentTheory(experimentTheory);
		
		// 实验准备
		List<String> reagents = myExpReagentService.getAllReagentsName(myExpID);
		List<String> consumables = myExpConsumableService.getAllConsumablesName(myExpID);
		List<String> equipments = myExpEquipmentService.getAllEquipmentsName(myExpID);
		setExperimentReady(reagents, consumables, equipments);
		
		// 实验流程
		List<MyExpProcessEntity> steps = myExpProcessService.getList(myExpID);
		setExperimentSteps(steps);
		
		// 关闭文档
		pdf.close();
	}
	
	@Override
	public List<Object> getPdfList(){
		List<Object> object = new ArrayList<Object>();
		List<MyExpEntity> pdfs = myExpMainService.getPdfs();
		if(pdfs != null){
			for(MyExpEntity pdf : pdfs){
				Map<String, String> map = new HashMap<String, String>();
				String experimentName = expInstructionsMainService.get(pdf.getExpInstructionID()).getExperimentName();
				String pdfName = pdf.getReportName();
				pdfName = experimentName + DateUtil.formatDate("yyyy.MM.dd HH:mm:ss", new Date(Long.parseLong(pdfName.replace(".pdf", ""))));
				map.put("myExpID", pdf.getMyExpID());
				map.put("pdfName", pdfName);
				object.add(map);
			}
		}
		return object;
	}
	
	private void setTopic(String topic) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(topic)){
			pdf.add(pdf.paragraph(topic, pdf.setTitleFont(), ToPDF.ALIGN_CENTER));
		}
	}
	
	private void setExperimentDesc(String desc) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(desc)){
			pdf.add(pdf.paragraph("技术简介", pdf.setH1Font()));
			Paragraph p = pdf.paragraph(desc);
			p.setFirstLineIndent(12);
			pdf.add(p);
		}
	}
	
	private void setExperimentTheory(String theory) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(theory)){
			pdf.add(pdf.paragraph("技术原理", pdf.setH1Font()));
			Paragraph p = pdf.paragraph(theory);
			p.setFirstLineIndent(12);
			pdf.add(p);
		}
	}
	
	private void setExperimentReady(List<String> reagents, List<String> consumables, List<String> equipments) throws DocumentException, IOException{
		if(reagents != null ||
				consumables != null ||
				equipments != null){
			pdf.add(pdf.paragraph("实验准备", pdf.setH1Font()));
			
			if(reagents != null){
				pdf.add(pdf.paragraph("实验试剂"));
				Paragraph p = pdf.paragraph(reagents);
				p.setFirstLineIndent(12);
				pdf.add(p);
			}
			
			if(consumables != null){
				pdf.add(pdf.paragraph("实验耗材"));
				Paragraph p = pdf.paragraph(consumables);
				p.setFirstLineIndent(12);
				pdf.add(p);
			}
			
			if(equipments != null){
				pdf.add(pdf.paragraph("实验设备"));
				Paragraph p = pdf.paragraph(equipments);
				p.setFirstLineIndent(12);
				pdf.add(p);
			}
		}
	}
	
	private void setExperimentSteps(List<MyExpProcessEntity> steps) throws DocumentException, IOException{
		if(steps != null){
			pdf.add(pdf.paragraph("实验步骤", pdf.setH1Font()));
			int index = 1;
			for(MyExpProcessEntity step : steps){
				List<MyExpProcessAttchEntity> attches = myExpProcessAttchService.getMyExpProcessAttches(step.getMyExpID(), step.getExpStepID());
				setExperimentStep(step, attches, index);
				index ++;
			}
		}
	}
	
	private void setExperimentStep(MyExpProcessEntity step, List<MyExpProcessAttchEntity> stepAttches, int index) throws DocumentException, IOException{
		if(step != null){
			Paragraph pStepDesc = pdf.paragraph("Step" + index + ": " + step.getExpStepDesc());
			pStepDesc.setFirstLineIndent(12);
			pdf.add(pStepDesc);
			
			String memo = step.getProcessMemo();
			if(StringUtils.isNotBlank(memo)){
				Paragraph pMemo = pdf.paragraph(memo);
				pMemo.setFirstLineIndent(12);
				pdf.add(pMemo);
			}
			
			if(stepAttches != null && stepAttches.size() > 0){
				List<String> imgUrls = new ArrayList<String>();
				for(MyExpProcessAttchEntity stepAttch : stepAttches){
					String imgUrl = AppConfig.DOMAIN_PAGE + "/" + stepAttch.getAttchmentServerPath();
					imgUrl = FileUtil.toURLPath(imgUrl);
					imgUrls.add(imgUrl);
				}
				pdf.add(pdf.imageBlock(imgUrls, 1));
			}
		}
	}
}
