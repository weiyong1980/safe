package com.ochain.provider.wheel.vo.coupon;

import java.io.Serializable;
import java.util.Date;

public class CouponLogAndGCouponVo implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long couponLogId;

    private Integer userId;

    private Integer couponId;

    private String userAccount;

    private String url;

    private Integer price;

    private Integer couponType;

    private Integer diamond;

    private Integer status;

    private Integer amount;

    private Date couponTime;

    private Date createTime;

    private Date updateTime;

    private String gCouponId;

    private String gName;

    private String gCityName;

    private String gAreaInfo;

	public Long getCouponLogId() {
		return couponLogId;
	}

	public void setCouponLogId(Long couponLogId) {
		this.couponLogId = couponLogId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getCouponTime() {
		return couponTime;
	}

	public void setCouponTime(Date couponTime) {
		this.couponTime = couponTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getgCouponId() {
		return gCouponId;
	}

	public void setgCouponId(String gCouponId) {
		this.gCouponId = gCouponId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgCityName() {
		return gCityName;
	}

	public void setgCityName(String gCityName) {
		this.gCityName = gCityName;
	}

	public String getgAreaInfo() {
		return gAreaInfo;
	}

	public void setgAreaInfo(String gAreaInfo) {
		this.gAreaInfo = gAreaInfo;
	}

	@Override
	public String toString() {
		return "CouponLogAndGCouponVo [couponLogId=" + couponLogId + ", userId=" + userId + ", couponId=" + couponId
				+ ", userAccount=" + userAccount + ", url=" + url + ", price=" + price + ", couponType=" + couponType
				+ ", diamond=" + diamond + ", status=" + status + ", amount=" + amount + ", couponTime=" + couponTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", gCouponId=" + gCouponId + ", gName="
				+ gName + ", gCityName=" + gCityName + ", gAreaInfo=" + gAreaInfo + "]";
	}

}