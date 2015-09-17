package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 试剂表
 * @author zql
 * @date 2015/09/14
 * 
 * ReagentID			试剂ID	
 * ReagentName			试剂名称	
 * ReagentCommonName	试剂通用名称
 * SupplierID			供应商ID	
 * LevelOneSortID		一级分类ID
 * LevelTwoSortID		二级分类ID
 * OriginPlace			产地	
 * ProductNo			货号	
 * Agents				代理商	
 * Specification		规格
 * Price				价格	
 * ChemicalName			化学名
 * CASNo				CAS号码	
 * ArrivalDate			到货日期
 * Memo					说明	
 * 
 */
@Table(name = "t_reagent")
@Entity
public class ReagentEntity extends ToStringBase {

	private static final long serialVersionUID = 8730276703067341791L;
	
	private int reagentID;
	private String reagentName;
	private String reagentCommonName;
	private int supplierID;
	private int levelOneSortID;
	private int levelTwoSortID;
	private String originPlace;
	private String productNo;
	private String agents;
	private String specification;
	private int price;
	private String chemicalName;
	private String casNo;
	private Date arrivalDate;
	private String memo;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public int getReagentID() {
		return reagentID;
	}
	
	public void setReagentID(int reagentID) {
		this.reagentID = reagentID;
	}
	
	public String getReagentName() {
		return reagentName;
	}
	
	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}
	
	public String getReagentCommonName() {
		return reagentCommonName;
	}
	
	public void setReagentCommonName(String reagentCommonName) {
		this.reagentCommonName = reagentCommonName;
	}
	
	public int getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	public int getLevelOneSortID() {
		return levelOneSortID;
	}
	
	public void setLevelOneSortID(int levelOneSortID) {
		this.levelOneSortID = levelOneSortID;
	}
	
	public int getLevelTwoSortID() {
		return levelTwoSortID;
	}
	
	public void setLevelTwoSortID(int levelTwoSortID) {
		this.levelTwoSortID = levelTwoSortID;
	}
	
	public String getOriginPlace() {
		return originPlace;
	}
	
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	
	public String getProductNo() {
		return productNo;
	}
	
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	public String getAgents() {
		return agents;
	}
	
	public void setAgents(String agents) {
		this.agents = agents;
	}
	
	public String getSpecification() {
		return specification;
	}
	
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getChemicalName() {
		return chemicalName;
	}
	
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	
	public String getCasNo() {
		return casNo;
	}
	
	public void setCasNo(String casNo) {
		this.casNo = casNo;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
