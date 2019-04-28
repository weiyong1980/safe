package com.cloud.queue.safe.queue;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cloud.common.redis.keys.RedisKeysUtil;
import com.cloud.queue.safe.hook.ShutdownHandler;
import com.cloud.queue.safe.service.IBootRedisService;
import com.cloud.queue.safe.service.impl.DataBaseService;
import com.cloud.queue.safe.vo.user.UserLoginLogVo;
import com.ochain.common.exception.BootServiceException;

/**
 * @ClassName: DataUserLoginLogThread
 * @Description: 用户登录日志队列监听类
 * @author wei.yong
 * @date 2017年3月23日 下午15:37:58
 */
@Component
public class DataUserLoginLogThread implements Runnable {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String queueKey = RedisKeysUtil.QN_OCHAIN_WHEEL_USER_LOGIN_LOG;

	//数据 Service
	@Autowired
	private DataBaseService dataBaseService;

	//redis Service
	@Autowired
    private IBootRedisService redisService;

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.info("[DataUserLoginLogThread.run():用户登录日志数据队列-线程已启动:{}]: queueKey={}", threadName, queueKey);
		while(ShutdownHandler.INSTANCE.keepRunning()) {
			String value = null;
			try {
				value = redisService.brpop(queueKey);
				logger.info("[DataUserLoginLogThread.run():用户登录日志数据队列-redis获取数据]: value={}", value);
			} catch (BootServiceException e) {
				logger.error("[DataUserLoginLogThread.run():{} 线程-队列取值异常，进入下一循环] Exception = {}, message = {}", threadName, e, e.getMessage());
				continue ;
			}
			if (StringUtils.isBlank(value)) {
	    		continue;
		    }

			UserLoginLogVo userLoginLogVo = null;
			try {
				userLoginLogVo = JSONObject.parseObject(value, UserLoginLogVo.class);
				logger.info("[DataUserLoginLogThread.run():用户登录日志数据队列-json数据解析]: userLoginLogVo={}", userLoginLogVo);
			} catch (JSONException e) {
				logger.error("[DataUserLoginLogThread.run():{} 线程-队列值解析异常，进入下一循环] Exception = {}, message = {}", threadName, e, e.getMessage());
				continue ;
			}

			try {
				boolean flag = dataBaseService.insertUserLoginLog(userLoginLogVo);
				logger.info("[DataUserLoginLogThread.run():用户登录日志数据队列-返回信息, flag:{}", flag);
			} catch (Exception e) {
				logger.error("[DataUserLoginLogThread.run():用户登录日志数据队列{} 线程处理异常, 已结束] queueKey={}, Exception={}, message={}", threadName, queueKey, e, e.getMessage());
				continue ;
			}
		}
	}

	/**
	 * 设置当前线程名称
	 * @param threadName
	 */
	public void setCurrentThreadName(String threadName) {
		Thread.currentThread().setName(threadName);
	}

}