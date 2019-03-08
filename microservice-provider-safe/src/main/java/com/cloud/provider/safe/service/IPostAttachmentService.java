package com.cloud.provider.safe.service;

import java.util.List;

import com.cloud.provider.safe.po.PostAttachment;
import com.cloud.provider.safe.rest.request.page.PostAttachmentPageRequest;
import com.github.pagehelper.Page;

public interface IPostAttachmentService {

    /**
	 * 分页查询
	 * @param page
	 * @param param
	 * @return List<PostAttachment>
	 */
	public List<PostAttachment> selectPostAttachmentListByPage(Page<?> page, PostAttachmentPageRequest param);

	/**
	 * 不分页查询
	 * @param param
	 * @return List<PostAttachment>
	 */
	public List<PostAttachment> selectPostAttachmentList(PostAttachmentPageRequest param);

    /**
     * 根据id查询用户附件
     * @param id
     * @return PostAttachment
     */
	public PostAttachment selectPostAttachmentById(Integer id);

    /**
     * 插入用户附件
     * @param postAttachment
     * @return Integer
     */
	public Integer insertPostAttachment(PostAttachment postAttachment);

 	/**
  	 * 根据id删除岗位附件
  	 * @param id
  	 * @return Integer
  	 */
	public Integer deletePostAttachmentById(Integer id);

    /**
     * 修改用户附件
     * @param postAttachment
     * @return Integer
     */
	public Integer modifyPostAttachment(PostAttachment postAttachment);

}