package com.labassistant.constants;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public final class LabConstant {

	// 不支持实列化
	private LabConstant(){}
	
	// 实验状态
	public static final String DOING = "0";	// 进行中
	public static final String PAUSING = "1";	// 暂停中
	public static final String COMPLETED = "2";	// 已完成
	public static final String REPORTED = "3";	// 已生成实验报告
	
	// 实验中日期常用的格式
	public static final String DATEFORMAT = "yyyy-MM-dd"; 
	
	// 实验中为日期类型的字段
	public static final String MYEXP_CREATETIME = "createTime";
	public static final String MYEXP_FINISHTIME = "finishTime";
	public static final String MYEXPPLAN_PLANDATE = "planDate";

    // 实验评论项
    public static final String EXPREAGENTS = "实验试剂";
    public static final String EXPCONSUMABLES = "实验耗材";
    public static final String EXPEQUIPMENTS = "实验仪器";
    public static final String EXPSTEPS = "实验步骤";
}
