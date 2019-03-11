package com.cloud.consumer.safe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cloud.common.constants.PageConstants;
import com.cloud.common.constants.safe.RetSafeConstants;
import com.cloud.consumer.safe.base.BaseRestMapResponse;
import com.cloud.consumer.safe.page.PageVo;
import com.cloud.consumer.safe.rest.request.QualityAttachmentRequest;
import com.cloud.consumer.safe.rest.request.page.QualityAttachmentPageRequest;
import com.cloud.consumer.safe.service.IQualityAttachmentService;
import com.cloud.consumer.safe.validator.group.UpdateGroup;
import com.cloud.consumer.safe.vo.QualityAttachmentVo;
import com.cloud.consumer.safe.vo.base.BasePageResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资质附件管理 QualityAttachmentController
 * @author wei.yong
 * @ClassName: QualityAttachmentController
 */
@Api(tags = "资质附件")
@RestController
@RequestMapping("/quality/attachment")
public class QualityAttachmentController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	//资质附件 Service
	@Autowired
	private IQualityAttachmentService qualityAttachmentService;

	/**
	 * 分页查询
	 * @param req
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "分页查询资质附件列表")
	@RequestMapping(value="/getQualityAttachmentListByPage",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse getQualityAttachmentListByPage(
		@RequestBody QualityAttachmentPageRequest req) {
		logger.info("===step1:【分页查询】(QualityAttachmentController-getQualityAttachmentListByPage)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		JSONObject jsonQualityAttachment = qualityAttachmentService.getQualityAttachmentListByPage(req);
		logger.info("===step2:【分页查询】(QualityAttachmentController-getQualityAttachmentListByPage)-分页查询资质附件列表, jsonQualityAttachment:{}", jsonQualityAttachment);
		String dataListStr = JSONObject.toJSONString(jsonQualityAttachment.getJSONArray(PageConstants.DATA_LIST));
		String pageStr = JSONObject.toJSONString(jsonQualityAttachment.getJSONObject(PageConstants.PAGE));
		List<QualityAttachmentVo> qualityAttachmentVoList  = JSONObject.parseObject(dataListStr, new TypeReference<List<QualityAttachmentVo>>(){});
		PageVo pageVo = JSONObject.parseObject(pageStr, PageVo.class);

		BasePageResultVo result = new BasePageResultVo(pageVo, qualityAttachmentVoList);
		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, result);
	    logger.info("===step3:【分页查询】(QualityAttachmentController-getQualityAttachmentListByPage)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
	    return qualityAttachmentResponse;
	}

	/**
	 * 不分页查询
	 * @param req
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "不分页查询资质附件列表")
	@RequestMapping(value="/getQualityAttachmentList",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse getQualityAttachmentList(
		@RequestBody QualityAttachmentPageRequest req) {
		logger.info("===step1:【不分页查询】(QualityAttachmentController-getQualityAttachmentList)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		JSONObject jsonQualityAttachment = qualityAttachmentService.getQualityAttachmentListByPage(req);
		logger.info("===step2:【不分页查询】(QualityAttachmentController-getQualityAttachmentList)-不分页查询资质附件列表, jsonQualityAttachment:{}", jsonQualityAttachment);
		String dataListStr = JSONObject.toJSONString(jsonQualityAttachment.getJSONArray(PageConstants.DATA_LIST));
		List<QualityAttachmentVo> qualityAttachmentVoList  = JSONObject.parseObject(dataListStr, new TypeReference<List<QualityAttachmentVo>>(){});

		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, qualityAttachmentVoList);
		logger.info("===step3:【不分页查询】(QualityAttachmentController-getQualityAttachmentList)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
		return qualityAttachmentResponse;
	}

	/**
	 * 获取资质附件详情
	 * @param req
	 * @param request
	 * @param response
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "获取资质附件详情")
	@RequestMapping(value="/getQualityAttachment",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse getQualityAttachment(
		@RequestBody QualityAttachmentRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【获取资质附件】(QualityAttachmentController-getQualityAttachment)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		Integer qualityAttachmentId = req.getQualityAttachmentId();
		JSONObject jsonQualityAttachment = qualityAttachmentService.getQualityAttachmentById(qualityAttachmentId);
		logger.info("===step2:【获取资质附件】(QualityAttachmentController-getQualityAttachment)-根据qualityAttachmentId获取资质附件, jsonQualityAttachment:{}", jsonQualityAttachment);
		QualityAttachmentVo qualityAttachmentVo = JSONObject.toJavaObject(jsonQualityAttachment, QualityAttachmentVo.class);

		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, qualityAttachmentVo);
	    logger.info("===step3:【获取资质附件】(QualityAttachmentController-getQualityAttachment)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
	    return qualityAttachmentResponse;
	}

	/**
	 * 新增资质附件
	 * @param req
	 * @param bindingResult
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "新增资质附件")
	@RequestMapping(value="/addQualityAttachment",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse addQualityAttachment(
		@Validated @RequestBody QualityAttachmentRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【新增资质附件】(QualityAttachmentController-addQualityAttachment)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		JSONObject jsonQualityAttachment = qualityAttachmentService.addQualityAttachment(req);
		logger.info("===step2:【新增资质附件】(QualityAttachmentController-addQualityAttachment)-分页查询资质附件列表, jsonQualityAttachment:{}", jsonQualityAttachment);
		QualityAttachmentVo qualityAttachmentVo = JSONObject.toJavaObject(jsonQualityAttachment, QualityAttachmentVo.class);

		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, qualityAttachmentVo);
	    logger.info("===step3:【新增资质附件】(QualityAttachmentController-addQualityAttachment)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
	    return qualityAttachmentResponse;
	}

	/**
	 * 删除资质附件
	 * @param req
	 * @param bindingResult
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "删除资质附件")
	@RequestMapping(value="/deleteQualityAttachment",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse deleteQualityAttachment(
		@RequestBody QualityAttachmentRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【删除资质附件】(QualityAttachmentController-deleteQualityAttachment)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		Integer qualityAttachmentId = req.getQualityAttachmentId();
		JSONObject jsonQualityAttachment = qualityAttachmentService.deleteQualityAttachmentById(qualityAttachmentId);
		logger.info("===step2:【删除资质附件】(QualityAttachmentController-deleteQualityAttachment)-根据qualityAttachmentId删除资质附件, jsonQualityAttachment:{}", jsonQualityAttachment);
		QualityAttachmentVo qualityAttachmentVo = JSONObject.toJavaObject(jsonQualityAttachment, QualityAttachmentVo.class);

		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, qualityAttachmentVo);
		logger.info("===step3:【删除资质附件】(QualityAttachmentController-deleteQualityAttachment)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
		return qualityAttachmentResponse;
	}

	/**
	 * 修改资质附件
	 * @param req
	 * @param bindingResult
	 * @return BaseRestMapResponse
	 */
	@ApiOperation(value = "修改资质附件")
	@RequestMapping(value="/updateQualityAttachment",method={RequestMethod.POST})
	@ResponseBody
	public BaseRestMapResponse updateQualityAttachment(
		@Validated({ UpdateGroup.class }) @RequestBody QualityAttachmentRequest req,
		BindingResult bindingResult) {
		logger.info("===step1:【修改资质附件】(QualityAttachmentController-updateQualityAttachment)-请求参数, req:{}, json:{}", req, JSONObject.toJSONString(req));

		this.bindingResult(bindingResult);

		JSONObject jsonQualityAttachment = qualityAttachmentService.addQualityAttachment(req);
		logger.info("===step2:【修改资质附件】(QualityAttachmentController-updateQualityAttachment)-修改资质附件, jsonQualityAttachment:{}", jsonQualityAttachment);
		QualityAttachmentVo qualityAttachmentVo = JSONObject.toJavaObject(jsonQualityAttachment, QualityAttachmentVo.class);

		//返回信息
		BaseRestMapResponse qualityAttachmentResponse = new BaseRestMapResponse();
		qualityAttachmentResponse.put(RetSafeConstants.RESULT, qualityAttachmentVo);
		logger.info("===step3:【修改资质附件】(QualityAttachmentController-updateQualityAttachment)-返回信息, qualityAttachmentResponse:{}", qualityAttachmentResponse);
		return qualityAttachmentResponse;
	}

}