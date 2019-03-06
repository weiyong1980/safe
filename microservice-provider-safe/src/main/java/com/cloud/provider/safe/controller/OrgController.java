package com.cloud.provider.safe.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constants.PageConstants;
import com.cloud.common.enums.safe.SafeResultEnum;
import com.cloud.provider.safe.base.BaseRestMapResponse;
import com.cloud.provider.safe.page.PageHelperUtil;
import com.cloud.provider.safe.po.Org;
import com.cloud.provider.safe.rest.request.OrgRequest;
import com.cloud.provider.safe.service.IOrgService;
import com.cloud.provider.safe.validator.group.ModifyGroup;
import com.cloud.provider.safe.vo.OrgVo;
import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组织机构 OrgController
 * @author wei.yong
 */
@Api(tags = "组织机构")
@RestController
@RequestMapping(value="/org")
public class OrgController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	//组织机构Service
	@Autowired
	private IOrgService orgService;

	/**
	 * 分页查询
	 * @param req
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "分页查询组织机构列表")
	@RequestMapping(value="/selectOrgListByPage",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse selectOrgListByPage(
		@RequestBody OrgRequest req) {
		logger.info("===step1:【分页查询组织机构列表】(OrgController-selectOrgListByPage)-传入参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		Integer pageNum = req.getPageNum();
		Integer pageSize = req.getPageSize();

		Org org = new Org();
		Page<?> page = new Page<>(pageNum, pageSize);
		List<Org> list = orgService.selectOrgListByPage(page, org);
		logger.info("===step2:【分页查询组织机构列表】(OrgController-selectOrgListByPage)-分页查询组织机构列表, list.size:{}", list == null ? null : list.size());
//		if(list == null || list.isEmpty()) {
//			return new BaseRestMapResponse(SafeResultEnum.ORDER_LIST_NOTEXIST);
//		}

		Map<String, Object> pageListMap = PageHelperUtil.INSTANCE.getPageListMap(list);
		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		orgResponse.putAll(pageListMap);
		logger.info("===step3:【分页查询组织机构列表】(OrgController-selectOrgListByPage)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}

	/**
	 * 不分页查询
	 * @param req
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "不分页查询组织机构列表")
	@RequestMapping(value="/selectOrgList",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse selectOrgList(
		@RequestBody OrgRequest req) {
		logger.info("===step1:【不分页查询组织机构列表】(OrgController-selectOrgList)-传入参数, req:{}, json:{}", req, JSONObject.toJSONString(req));
		Org org = new Org();
		List<Org> list = null;
		list = orgService.selectOrgList(org);
		logger.info("===step2:【不分页查询组织机构列表】(OrgController-selectOrgList)-不分页查询组织机构列表, list.size:{}", list == null ? null : list.size());
//		if(list == null || list.isEmpty()) {
//			return new BaseRestMapResponse(SafeResultEnum.ORDER_LIST_NOTEXIST);
//		}

		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		orgResponse.put(PageConstants.DATA_LIST, list);
		logger.info("===step3:【不分页查询组织机构列表】(OrgController-selectOrgList)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}

	/**
	 * 据id查询组织机构
	 * @param orgId
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "根据id查询组织机构")
	@RequestMapping(value="/selectOrgById/{id}",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse selectOrgById(
		@PathVariable(value="id",required=false) Integer orgId) {
		logger.info("===step1:【据id查询组织机构】(selectOrgById-selectOrgById)-传入参数, orgId:{}", orgId);

		if(orgId == null) {
			return new BaseRestMapResponse(SafeResultEnum.FIELD_EMPTY.getCode(), "orgId为空");
		}

		Org org = orgService.selectOrgById(orgId);
		logger.info("===step2:【据id查询组织机构】(OrgController-selectOrgById)-根据id查询组织机构, org:{}", org);
		if(org == null) {
			return new BaseRestMapResponse(SafeResultEnum.ORDER_SETTING_ENTITY_NOTEXIST);
		}
		OrgVo orgVo = new OrgVo().convertToOrgVo(org);

		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		orgResponse.putAll((JSONObject) JSONObject.toJSON(orgVo));
		logger.info("===step3:【据id查询组织机构】(OrgController-selectOrgById)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}

	/**
	 * 添加组织机构
	 * @param req
	 * @param bindingResult
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "添加组织机构")
	@RequestMapping(value="/insertOrg",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse insertOrg(
		@Validated @RequestBody OrgRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【添加组织机构】(OrgController-insertOrg)-传入参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		Org org = req.convertToOrg();
		int i = orgService.insertOrg(org);
		logger.info("===step2:【添加组织机构】(OrgController-insertOrg)-插入组织机构, i:{}", i);

		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		logger.info("===step3:【添加组织机构】(OrgController-insertOrg)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}

	/**
	 * 根据id删除组织机构
	 * @param orgId
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "根据id删除组织机构")
	@RequestMapping(value="/deleteOrgById/{id}",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse deleteOrgById(
		@PathVariable(value="id",required=false) Integer orgId) {
		logger.info("===step1:【根据id删除组织机构】(selectOrgById-deleteOrgById)-传入参数, orgId:{}", orgId);

		if(orgId == null) {
			return new BaseRestMapResponse(SafeResultEnum.FIELD_EMPTY.getCode(), "orgId为空");
		}

		int i = orgService.deleteOrgById(orgId);
		logger.info("===step2:【根据id删除组织机构】(OrgController-deleteOrgById)-根据id查询组织机构, i:{}", i);

		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		logger.info("===step3:【根据id删除组织机构】(OrgController-deleteOrgById)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}

	/**
	 * 修改组织机构
	 * @param req
	 * @param bindingResult
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "修改组织机构")
	@RequestMapping(value="/modifyOrg",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse modifyOrg(
		@Validated({ ModifyGroup.class }) @RequestBody OrgRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【修改组织机构】(OrgController-modifyOrg)-传入参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		Integer orgId = req.getOrgId();
		Org org = req.convertToOrg();
		org.setId(orgId);
		int i = orgService.modifyOrg(org);
		logger.info("===step2:【修改组织机构】(OrgController-modifyOrg)-修改组织机构, i:{}", i);

		BaseRestMapResponse orgResponse = new BaseRestMapResponse();
		logger.info("===step3:【修改组织机构】(OrgController-modifyOrg)-返回信息, orgResponse:{}", orgResponse);
		return orgResponse;
	}


}