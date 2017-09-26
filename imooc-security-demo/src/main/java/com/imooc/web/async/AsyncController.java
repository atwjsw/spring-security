package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	//同步执行请求
//	@RequestMapping("/order")
//	public String order() throws Exception {
//		logger.info("主线程开始");
//		Thread.sleep(1000);
//		logger.info("主线程完成");
//		return "success";
//	}
	
	//异步执行请求by callable
//	@RequestMapping("/order")
//	public Callable<String> order() throws Exception {
//		logger.info("主线程开始");		
//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程完成");				
//				return "success";
//			}			
//		};
//		logger.info("主线程完成");
//		return result;
//	}
	
	//异步执行请求by deferred result
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		logger.info("主线程开始");		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		
//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程完成");				
//				return "success";
//			}			
//		};
		logger.info("主线程完成");
		return result;
	}
}
