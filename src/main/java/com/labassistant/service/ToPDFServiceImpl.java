package com.labassistant.service;

import java.io.*;
import java.util.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.labassistant.beans.*;
import com.labassistant.service.common.EquipmentService;
import com.labassistant.service.common.ReagentService;
import com.labassistant.service.exp.*;
import com.labassistant.service.myexp.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.labassistant.constants.AppConfig;
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
    private SysUserService sysUserService;
    @Autowired
    private ReagentService reagentService;
    @Autowired
    private EquipmentService equipmentService;
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	@Autowired
	private ExpReviewService expReviewService;
	@Autowired
	private ExpReviewDetailService expReviewDetailService;
	@Autowired
	private ExpReviewOptService expReviewOptService;
    @Autowired
    private ExpReviewDetailOfOptService expReviewDetailOfOptService;
    @Autowired
    private ExpProcessService expProcessService;
    @Autowired
    private ExpReagentService expReagentService;
    @Autowired
    private ExpConsumableService expConsumableService;
    @Autowired
    private ExpEquipmentService expEquipmentService;
	@Autowired
	private MyExpMainService myExpMainService;
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
    @Autowired
    private MyExpAttchService myExpAttchService;
	
	private final ToPDF pdf = new ToPDF();
	
	public void toPdf(String pdfName, String myExpID) throws DocumentException, IOException{			
		// 获取说明书名/技术简介/实验原理
		MyExpEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			pdf.getDocument(pdfName);
			String experimentName = "";
			String experimentDesc = "";
			String experimentTheory = "";
			ExpInstructionEntity expInstruction = expInstructionsMainService.get(myExp.getExpInstructionID());
			if(expInstruction != null){
				experimentName = expInstruction.getExperimentName();
				experimentDesc = expInstruction.getExperimentDesc();
				experimentTheory = expInstruction.getExperimentTheory();
			}
			setTopic(experimentName + "实验报告");

            // 设置实验基本信息
            setExpBasicInfo(myExp);
            setExperimentDesc(experimentDesc);
            setExperimentTheory(experimentTheory);

			// 实验准备
			List<MyExpReagentEntity> reagents = myExpReagentService.getMyExpReagents(myExpID);
			List<MyExpConsumableEntity> consumables = myExpConsumableService.getMyExpConsumables(myExpID);
			List<MyExpEquipmentEntity> equipments = myExpEquipmentService.getMyExpEquipments(myExpID);
			setExperimentReady(expInstruction.getExpInstructionID(), reagents, consumables, equipments);
			
			// 实验流程
			List<MyExpProcessEntity> steps = myExpProcessService.getList(myExpID);
			setExperimentSteps(steps);

            // 实验结果
            List<MyExpAttchEntity> myExpAttches = myExpAttchService.getMyExpAttches(myExpID);
            setExperimentResult(myExp, myExpAttches);

			// 实验评价
			ExpReviewEntity expReview = expReviewService.getReview(myExp.getUserID(), myExp.getExpInstructionID());
			setExperimentReview(expReview);
			
			// 关闭文档
			pdf.close();
		}
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

    private void setExpBasicInfo(MyExpEntity myExp) throws IOException, DocumentException {
        String nickName = sysUserService.get(myExp.getUserID()).getNickName();
        String start = DateUtil.formatDate(myExp.getCreateTime());
        String finish = "";
        if(myExp.getFinishTime() != null){
            finish = DateUtil.formatDate(myExp.getFinishTime());
        }
        pdf.add(pdf.paragraph("基本实验信息", pdf.setH2Font()));
        pdf.add(pdf.paragraph("实验者: " + nickName));
        pdf.add(pdf.paragraph("实验时间: " + start + "  -  " + finish));
        pdf.add(pdf.paragraph("实验归属"));

        // 设置研究课题等
        String projectName = myExp.getProjectName();
        String researchName = myExp.getResearchName();
        String taskName = myExp.getTaskName();
        setProjectName(projectName);
        setResearchName(researchName);
        setTaskName(taskName);
    }

	private void setTopic(String topic) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(topic)){
			pdf.add(pdf.paragraph(topic, pdf.setTitleFont(), ToPDF.ALIGN_CENTER));
		}
	}
	
	private void setExperimentDesc(String desc) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(desc)){
			pdf.add(pdf.paragraph("技术简介"));
			Paragraph p = pdf.paragraph(desc);
			p.setFirstLineIndent(12);
			pdf.add(p);
		}
	}
	
	private void setExperimentTheory(String theory) throws DocumentException, IOException{
		if(StringUtils.isNotBlank(theory)){
			pdf.add(pdf.paragraph("技术原理"));
			Paragraph p = pdf.paragraph(theory);
			p.setFirstLineIndent(12);
			pdf.add(p);
		}
	}

    private void setProjectName(String name) throws DocumentException, IOException{
        if(StringUtils.isBlank(name)){
            name = "无";
        }
        Paragraph p = pdf.paragraph("科研项目名称: " + name);
        p.setFirstLineIndent(12);
        pdf.add(p);
    }


    private void setResearchName(String name) throws DocumentException, IOException{
        if(StringUtils.isBlank(name)){
            name = "无";
        }
        Paragraph p = pdf.paragraph("研究课题名称: " + name);
        p.setFirstLineIndent(12);
        pdf.add(p);
    }

    private void setTaskName(String name) throws DocumentException, IOException{
        if(StringUtils.isBlank(name)){
            name = "无";
        }
        Paragraph p = pdf.paragraph("研究任务名称: " + name);
        p.setFirstLineIndent(12);
        pdf.add(p);
    }

	private void setExperimentReady(String expInstructionID, List<MyExpReagentEntity> reagents, List<MyExpConsumableEntity> consumables, List<MyExpEquipmentEntity> equipments) throws DocumentException, IOException{
		if(reagents != null ||
				consumables != null ||
				equipments != null){
			pdf.add(pdf.paragraph("实验材料与仪器", pdf.setH2Font()));

            List<List<String>> l = new ArrayList<List<String>>();
			if(reagents != null){
				pdf.add(pdf.paragraph("实验试剂"));
                l.clear();
                PdfPTable pdfTable = new PdfPTable(5);
                pdfTable.setWidthPercentage(100);
                pdfTable.setSpacingBefore(10.0f);
                pdfTable.setTotalWidth(new float[]{75.0f, 75.0f, 75.0f, 75.0f, 75.0f});
                pdfTable.setLockedWidth(true);
                pdfTable.setHorizontalAlignment(ToPDF.ALIGN_LEFT);
                pdfTable.setKeepTogether(true);

                pdf.pCell(pdfTable, pdf.paragraph("试剂名称"));
                pdf.pCell(pdfTable, pdf.paragraph("生产厂家"));
                pdf.pCell(pdfTable, pdf.paragraph("货号"));
                pdf.pCell(pdfTable, pdf.paragraph("配置方法"));
                pdf.pCell(pdfTable, pdf.paragraph("试剂用量"));

                for(MyExpReagentEntity reagent : reagents){
                    List<String> l_0 = new ArrayList<String>();
                    ExpReagentEntity r = expReagentService.getExpReagent(expInstructionID, reagent.getReagentID());
                    l_0.add(r.getReagentName());
                    l_0.add(r.getSupplierName());
                    l_0.add(reagentService.get(r.getReagentID()).getProductNo());
                    l_0.add(r.getCreateMethod());
                    l_0.add(String.valueOf(r.getUseAmount()));
                    l.add(l_0);
                }
                setExperimentReady_0(pdfTable, l);
                pdf.add(pdfTable);
			}
			
			if(consumables != null){
				pdf.add(pdf.paragraph("实验耗材"));
                l.clear();
                PdfPTable pdfTable = new PdfPTable(4);
                pdfTable.setWidthPercentage(100);
                pdfTable.setSpacingBefore(10.0f);
                pdfTable.setTotalWidth(new float[]{75.0f, 75.0f, 75.0f, 75.0f});
                pdfTable.setLockedWidth(true);
                pdfTable.setHorizontalAlignment(ToPDF.ALIGN_LEFT);
                pdfTable.setKeepTogether(true);

                pdf.pCell(pdfTable, pdf.paragraph("耗材名称"));
                pdf.pCell(pdfTable, pdf.paragraph("生产厂家"));
                pdf.pCell(pdfTable, pdf.paragraph("规格"));
                pdf.pCell(pdfTable, pdf.paragraph("耗材用量"));

                for(MyExpConsumableEntity consumable : consumables){
                    List<String> l_0 = new ArrayList<String>();
                    ExpConsumableEntity c = expConsumableService.getExpConsumable(expInstructionID, consumable.getConsumableID());
                    l_0.add(c.getConsumableName());
                    l_0.add(c.getSupplierName());
                    l_0.add(c.getConsumableType());
                    l_0.add(String.valueOf(c.getConsumableCount()));
                    l.add(l_0);
                }
                setExperimentReady_0(pdfTable, l);
                pdf.add(pdfTable);
			}
			
			if(equipments != null){
				pdf.add(pdf.paragraph("实验设备"));
                l.clear();
                PdfPTable pdfTable = new PdfPTable(3);
                pdfTable.setWidthPercentage(100);
                pdfTable.setSpacingBefore(10.0f);
                pdfTable.setTotalWidth(new float[]{75.0f, 75.0f, 75.0f});
                pdfTable.setLockedWidth(true);
                pdfTable.setHorizontalAlignment(ToPDF.ALIGN_LEFT);
                pdfTable.setKeepTogether(true);

                pdf.pCell(pdfTable, pdf.paragraph("设备名称"));
                pdf.pCell(pdfTable, pdf.paragraph("生产厂家"));
                pdf.pCell(pdfTable, pdf.paragraph("规格"));

                for(MyExpEquipmentEntity equipment : equipments){
                    List<String> l_0 = new ArrayList<String>();
                    ExpEquipmentEntity e = expEquipmentService.getExpEquipment(expInstructionID, equipment.getEquipmentID());
                    l_0.add(e.getEquipmentName());
                    l_0.add(e.getSupplierName());
                    l_0.add(equipmentService.get(equipment.getEquipmentID()).getEquipmentType());
                    l.add(l_0);
                }
                setExperimentReady_0(pdfTable, l);
                pdf.add(pdfTable);
			}
		}
	}

    private void setExperimentReady_0(PdfPTable table, List<List<String>> datas) throws IOException, DocumentException {
        Font font = pdf.setFont(null, 6, Font.NORMAL);
        for(List<String> row : datas){
            for(String col : row){
                pdf.pCell(table, pdf.paragraph(col, font));
            }
            table.completeRow();
        }
    }

	private void setExperimentSteps(List<MyExpProcessEntity> steps) throws DocumentException, IOException{
		if(steps != null){
			pdf.add(pdf.paragraph("实验步骤与过程记录", pdf.setH2Font()));
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
            pdf.add(pdf.paragraph("Step" + index + ": " + expProcessService.get(step.getExpStepID()).getExpStepText()));
			Paragraph pStepDesc = pdf.paragraph(step.getExpStepDesc());
			pStepDesc.setFirstLineIndent(12);
			pdf.add(pStepDesc);
			
			String memo = step.getProcessMemo();
			if(StringUtils.isNotBlank(memo)){
				Paragraph pMemo = pdf.paragraph("备注: " + memo);
				pMemo.setFirstLineIndent(12);
				pdf.add(pMemo);
			}
			
			if(stepAttches != null && stepAttches.size() > 0){
				List<Map<String, String>> imgs = new ArrayList<Map<String, String>>();
				for(MyExpProcessAttchEntity stepAttch : stepAttches){
					Map<String, String> imgDesc = new HashMap<String, String>();
					String imgUrl = AppConfig.DOMAIN_PAGE + "/" + stepAttch.getAttchmentServerPath();
					imgUrl = FileUtil.toURLPath(imgUrl);
					imgDesc.put("imgUrl", imgUrl);
					imgDesc.put("title", stepAttch.getTitle());
					imgDesc.put("desc", stepAttch.getDescription());
					imgs.add(imgDesc);
				}
				pdf.add(pdf.imageBlock(imgs, 1));
			}
		}
	}

    private void setExperimentResult(MyExpEntity myExp, List<MyExpAttchEntity> myExpAttches) throws IOException, DocumentException {
        String myExpResult = myExp.getMyExpResult();
        if(StringUtils.isNotBlank(myExpResult) || (myExpAttches != null && myExpAttches.size() > 0))
            pdf.add(pdf.paragraph("实验结果", pdf.setH2Font()));

        if(StringUtils.isNotBlank(myExpResult)){
            Paragraph p = pdf.paragraph(myExp.getMyExpResult());
            p.setFirstLineIndent(12);
            pdf.add(p);
        }

        if(myExpAttches != null && myExpAttches.size() > 0){
            List<Map<String, String>> imgs = new ArrayList<Map<String, String>>();
            for(MyExpAttchEntity myExpAttch : myExpAttches){
                Map<String, String> imgDesc = new HashMap<String, String>();
                String imgUrl = AppConfig.DOMAIN_PAGE + "/" + myExpAttch.getAttchmentServerPath();
                imgUrl = FileUtil.toURLPath(imgUrl);
                imgDesc.put("imgUrl", imgUrl);
                imgDesc.put("title", myExpAttch.getTitle());
                imgDesc.put("desc", myExpAttch.getDescription());
                imgs.add(imgDesc);
            }
            pdf.add(pdf.imageBlock(imgs, 1));
        }
    }

	private void setExperimentReview(ExpReviewEntity expReview) throws DocumentException, IOException{
		if(expReview != null){
			pdf.add(pdf.paragraph("实验评论", pdf.setH2Font()));

			PdfPTable pdfTable = new PdfPTable(2);
			pdfTable.setWidthPercentage(100);
			pdfTable.setSpacingBefore(10.0f);
			pdfTable.setTotalWidth(new float[]{300.0f, 75.0f});
			pdfTable.setLockedWidth(true);
			pdfTable.setHorizontalAlignment(ToPDF.ALIGN_LEFT);
            pdfTable.setKeepTogether(true);
//            pdfTable.setHeaderRows(1);

			Paragraph header1 = pdf.paragraph("评论项");
			header1.setAlignment(ToPDF.ALIGN_CENTER);
            PdfPCell header1Cell = pdf.pCell(header1);
            header1Cell.setBorderWidthRight(0);
            header1Cell.setBorderWidthBottom(0);
            pdf.pCell(pdfTable, header1Cell);

			Paragraph header2 = pdf.paragraph("评论项得分");
			header2.setAlignment(ToPDF.ALIGN_CENTER);
            PdfPCell header2Cell = pdf.pCell(header2);
            header2Cell.setBorderWidthBottom(0);
			pdf.pCell(pdfTable, header2Cell);
			pdfTable.completeRow();

            String expReviewID = expReview.getExpReviewID();
			List<ExpReviewDetailEntity> expReviewDetails = expReviewDetailService.getExpReviewDetails(expReviewID);
			for(ExpReviewDetailEntity expReviewDetail : expReviewDetails){
                String expReviewOptID = expReviewDetail.getExpReviewOptID();
                String expReviewOptName = expReviewOptService.get(expReviewOptID).getExpReviewOptName();
                PdfPCell cellName = pdf.pCell(pdf.paragraph(expReviewOptName));
                cellName.setBorderWidthRight(0);
                cellName.setBorderWidthBottom(0);
                pdf.pCell(pdfTable, cellName);

                PdfPCell cellScore = pdf.pCell(pdf.paragraph("  " + String.valueOf((expReviewDetail.getExpReviewOptScore()))));
                cellScore.setBorderWidthBottom(0);
                pdf.pCell(pdfTable, cellScore);

                List<ExpReviewDetailOfOptEntity> expReviewDetailOfOpts = expReviewDetailOfOptService.getExpReviewDetailOfOpts(expReviewID, expReviewOptID);
                if(expReviewDetailOfOpts != null){
                    if("实验步骤".equals(expReviewOptName)){
                        sortStep(expReviewDetailOfOpts);
                    }

                    for(ExpReviewDetailOfOptEntity expReviewDetailOfOpt : expReviewDetailOfOpts){
                        PdfPCell cellName1 = new PdfPCell();
                        Paragraph pName = pdf.paragraph("-  " + expReviewDetailOfOpt.getItemName());
                        pName.setIndentationLeft(12);
                        cellName1.addElement(pName);
                        cellName1.setBorderWidthTop(0);
                        cellName1.setBorderWidthBottom(0);
                        cellName1.setBorderWidthRight(0);
                        pdf.pCell(pdfTable, cellName1);

                        System.out.println("");
                        PdfPCell cellScore1 = pdf.pCell(pdf.paragraph("  " + String.valueOf(expReviewDetailOfOpt.getItemScore())));
                        cellScore1.setBorderWidthTop(0);
                        cellScore1.setBorderWidthBottom(0);
                        pdf.pCell(pdfTable, cellScore1);
                        System.out.println();
                        pdfTable.completeRow();
                    }
                }
				pdfTable.completeRow();
			}
			
			PdfPCell cell = pdf.pCell(pdf.paragraph("实验评论：\n    " + expReview.getReviewInfo()));
			cell.setColspan(2);
			pdf.pCell(pdfTable, cell);
			pdfTable.completeRow();
			
			pdf.add(pdfTable);
		}
	}

    private void sortStep(final List<ExpReviewDetailOfOptEntity> expReviewDetailOfOpts){
        final List<Integer> order = new LinkedList<Integer>();
        for(ExpReviewDetailOfOptEntity expReviewDetailOfOpt : expReviewDetailOfOpts){
            order.add(expProcessService.get(expReviewDetailOfOpt.getItemID()).getStepNum());
        }
        Collections.sort(expReviewDetailOfOpts, new Comparator<ExpReviewDetailOfOptEntity>() {
            @Override
            public int compare(ExpReviewDetailOfOptEntity o1, ExpReviewDetailOfOptEntity o2) {
                return order.get(expReviewDetailOfOpts.indexOf(o1)).compareTo(order.get(expReviewDetailOfOpts.indexOf(o2)));
            }
        });
    }
}
