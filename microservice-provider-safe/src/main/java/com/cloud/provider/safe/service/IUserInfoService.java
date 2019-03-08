package com.cloud.provider.safe.service;

import java.util.List;

import com.cloud.provider.safe.po.UserInfo;
import com.cloud.provider.safe.rest.request.page.UserInfoPageRequest;
import com.github.pagehelper.Page;

public interface IUserInfoService {

    /**
	 * 分页查询
	 * @param page
	 * @param param
	 * @return List<UserInfo>
	 */
	public List<UserInfo> selectUserInfoListByPage(Page<?> page, UserInfoPageRequest param);

	/**
	 * 不分页查询
	 * @param param
	 * @return List<UserInfo>
	 */
	public List<UserInfo> selectUserInfoList(UserInfoPageRequest param);

    /**
     * 根据id查询用户信息
     * @param id
     * @return UserInfo
     */
	public UserInfo selectUserInfoById(Integer id);

    /**
     * 插入用户信息
     * @param userInfo
     * @return Integer
     */
	public Integer insertUserInfo(UserInfo userInfo);

 	/**
  	 * 根据id删除用户信息
  	 * @param id
  	 * @return Integer
  	 */
	public Integer deleteUserInfoById(Integer id);

    /**
     * 修改用户信息
     * @param userInfo
     * @return Integer
     */
	public Integer modifyUserInfo(UserInfo userInfo);

}