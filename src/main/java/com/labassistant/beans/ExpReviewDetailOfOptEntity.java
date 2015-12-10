package com.labassistant.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zql on 2015/12/8.
 *
 * ExpReviewDetailOfOptID	            唯一ID
 * ExpReviewID	                        评论ID
 * ExpReviewOptID	                    评论项ID
 * ItemID	                            项目ID
 * ItemName	                            项目名称
 * SupplierID	                        供应商ID
 * ItemScore	                        明细项得分
 * ExpReviewDetailOfOptDesc	            描述
 *
 */
@Table(name = "t_expreviewdetailofopt")
@Entity
public class ExpReviewDetailOfOptEntity extends ToStringBase {

    private static final long serialVersionUID = -8778902612014630999L;

    private String expReviewDetailOfOptID;
    private String expReviewID;
    private String expReviewOptID;
    private String itemID;
    private String itemName;
    private String supplierID;
    private Integer itemScore;
    private String expReviewDetailOfOptDesc;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(length=32)
    public String getExpReviewDetailOfOptID() {
        return expReviewDetailOfOptID;
    }

    public void setExpReviewDetailOfOptID(String expReviewDetailOfOptID) {
        this.expReviewDetailOfOptID = expReviewDetailOfOptID;
    }

    public String getExpReviewID() {
        return expReviewID;
    }

    public void setExpReviewID(String expReviewID) {
        this.expReviewID = expReviewID;
    }

    public String getExpReviewOptID() {
        return expReviewOptID;
    }

    public void setExpReviewOptID(String expReviewOptID) {
        this.expReviewOptID = expReviewOptID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getItemScore() {
        return itemScore;
    }

    public void setItemScore(Integer itemScore) {
        this.itemScore = itemScore;
    }

    public String getExpReviewDetailOfOptDesc() {
        return expReviewDetailOfOptDesc;
    }

    public void setExpReviewDetailOfOptDesc(String expReviewDetailOfOptDesc) {
        this.expReviewDetailOfOptDesc = expReviewDetailOfOptDesc;
    }
}
