package com.cloud.provider.safe.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.common.enums.safe.SafeResultEnum;
import com.cloud.provider.safe.dao.PostAttachmentMapper;
import com.cloud.provider.safe.po.PostAttachment;
import com.cloud.provider.safe.po.PostAttachmentExample;
import com.cloud.provider.safe.service.IPostAttachmentService;
import com.cloud.provider.safe.util.Assert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 岗位附件 PostAttachmentService
 * @author wei.yong
 */
@Service
public class PostAttachmentServiceImpl implements IPostAttachmentService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //岗位附件 Mapper
    @Autowired
    private PostAttachmentMapper postAttachmentMapper;

    /**
	 * 分页查询
	 * @param page
	 * @param postAttachment
	 * @return List<PostAttachment>
	 */
	@Override
	public List<PostAttachment> selectPostAttachmentListByPage(Page<?> page, PostAttachment postAttachment) {
		logger.info("(PostAttachmentService-selectPostAttachmentListByPage)-分页查询-传入参数, page:{}, postAttachment:{}", page, postAttachment);
		PageHelper.startPage(page);
		PostAttachmentExample example = new PostAttachmentExample();
		example.setOrderByClause(" id desc ");
		PostAttachmentExample.Criteria criteria = example.createCriteria();
		if(postAttachment != null) {
		}
		List<PostAttachment> list = postAttachmentMapper.selectByExample(example);
		return list;
	}

	/**
	 * 不分页查询
	 * @param postAttachment
	 * @return List<PostAttachment>
	 */
	@Override
	public List<PostAttachment> selectPostAttachmentList(PostAttachment postAttachment) {
		logger.info("(PostAttachmentService-selectPostAttachmentList)-不分页查询-传入参数, postAttachment:{}", postAttachment);
		PostAttachmentExample example = new PostAttachmentExample();
		PostAttachmentExample.Criteria criteria = example.createCriteria();
		if(postAttachment != null) {
		}
		List<PostAttachment> list = postAttachmentMapper.selectByExample(example);
		return list;
	}

    /**
     * 根据id查询岗位附件
     * @param id
     * @return PostAttachment
     */
	@Override
	public PostAttachment selectPostAttachmentById(Integer id) {
    	logger.info("(PostAttachmentService-selectPostAttachmentById)-根据id查询岗位附件-传入参数, id:{}", id);
		PostAttachment postAttachment = postAttachmentMapper.selectByPrimaryKey(id);
		return postAttachment;
    }

    /**
     * 插入岗位附件
     * @param postAttachment
     * @return Integer
     */
	@Override
	public Integer insertPostAttachment(PostAttachment postAttachment) {
    	logger.info("(PostAttachmentService-insertPostAttachment)-插入岗位附件-传入参数, postAttachment:{}", postAttachment);
    	postAttachment.setCreateTime(new Date());
    	postAttachment.setUpdateTime(new Date());
    	int i = postAttachmentMapper.insertSelective(postAttachment);
//    	if(i<=0) {
//			throw new SafeException(SafeResultEnum.DATABASE_ERROR);
//		}
    	Assert.thanOrEqualZreo(i, SafeResultEnum.DATABASE_ERROR);
    	return i;
    }

 	/**
  	 * 根据id删除岗位附件
  	 * @param id
  	 * @return Integer
  	 */
	@Override
	public Integer deletePostAttachmentById(Integer id) {
  		logger.info("(PostAttachmentService-deletePostAttachmentById)-根据id删除岗位附件-传入参数, id:{}", id);
  		int i = postAttachmentMapper.deleteByPrimaryKey(id);
//  		if(i<=0) {
//  			throw new SafeException(SafeResultEnum.DATABASE_ERROR);
//  		}
  		Assert.thanOrEqualZreo(i, SafeResultEnum.DATABASE_ERROR);
  		return i;
  	}

    /**
     * 修改岗位附件
     * @param postAttachment
     * @return Integer
     */
	@Override
	public Integer modifyPostAttachment(PostAttachment postAttachment) {
    	logger.info("(PostAttachmentService-modifyPostAttachment)-修改岗位附件-传入参数, postAttachment:{}", postAttachment);
    	postAttachment.setUpdateTime(new Date());
    	int i = postAttachmentMapper.updateByPrimaryKeySelective(postAttachment);
//    	if(i<=0) {
//			throw new SafeException(SafeResultEnum.DATABASE_ERROR);
//		}
    	Assert.thanOrEqualZreo(i, SafeResultEnum.DATABASE_ERROR);
    	return i;
    }

}