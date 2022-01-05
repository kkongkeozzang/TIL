package kh.spring.advisor;

import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import kh.spring.dto.ContactDTO;

@Component
@Aspect
public class LogAdvisor {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
	@Before("within(kh.spring.controller.HomeController)")
	public void beforeLog(JoinPoint jp) {
		Signature sign = jp.getSignature();
		String logTime = sdf.format(System.currentTimeMillis());
		System.out.print(logTime+ " ");
		System.out.println(sign.getDeclaringTypeName() + "클래스에서 " + sign.getName() + "메서드 실행");
	}
	
	@After("execution(String kh.spring.controller.HomeController.*(*))")
	public void afterLog(JoinPoint jp) {
		Signature sign=jp.getSignature();
		String logTime = sdf.format(System.currentTimeMillis());
		System.out.print(logTime+ " ");
		System.out.println(sign.getDeclaringTypeName() + "클래스에서 " + sign.getName() + "메서드 종료");
	}
	
	@After("execution(String kh.spring.controller.HomeController.inputProc(*))")
	public void afterLog2(JoinPoint jp) {
		Signature sign=jp.getSignature();
		
		Object[] args = jp.getArgs();
		ContactDTO dto = (ContactDTO)args[0];
		System.out.println(dto.getName());
		
		String logTime = sdf.format(System.currentTimeMillis());
		System.out.print(logTime+ " ");
		System.out.println(sign.getDeclaringTypeName() + "클래스에서 " + sign.getName() + "메서드 종료");
	}
	
	@Around("execution(String kh.spring.controller.HomeController.outputProc(..))")
	public Object aroundLog(ProceedingJoinPoint pjp) {
		
		Object returnValue = null;
		System.out.println("Around Before 입니다.");
		try {
			returnValue = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Around After 입니다.");
		return returnValue;
	}
	
}
