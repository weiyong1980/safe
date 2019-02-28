package com.ochain.provider.wheel.rest.request.baseUser;

import java.util.Date;

import com.ochain.provider.wheel.boot.BootRestRequest;

public class BaseUserOperateLogRequest extends BootRestRequest {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long baseUserOperateId;

    private Integer baseUserId;

    private Byte operationType;

    private Object content;

    private Date operationTime;

    private Date createTime;

    private Date updateTime;

    public Long getBaseUserOperateId() {
        return baseUserOperateId;
    }

    public void setBaseUserOperateId(Long baseUserOperateId) {
        this.baseUserOperateId = baseUserOperateId;
    }

    public Integer getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Integer baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Byte getOperationType() {
        return operationType;
    }

    public void setOperationType(Byte operationType) {
        this.operationType = operationType;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
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

	@Override
	public String toString() {
		return "BaseUserOperateLog [baseUserOperateId=" + baseUserOperateId + ", baseUserId=" + baseUserId
				+ ", operationType=" + operationType + ", content=" + content + ", operationTime=" + operationTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}