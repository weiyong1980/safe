package com.cloud.provider.safe.rest.request.page;

import com.cloud.provider.safe.base.BaseRestRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostPageRequest extends BaseRestRequest {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "企业id")
    private Integer enterpriseId;

	@ApiModelProperty(value = "岗位名称")
    private String postName;

	@ApiModelProperty(value = "是否特殊岗位 0-否, 1-是")
    private Integer isSpecial;

}