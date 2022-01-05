package kh.spring.advisor;

import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvisor {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
	@Before("within(kh.spring.dao.*)")
	public void beforeLog(JoinPoint jp) {
		Signature sign = jp.getSignature();
		String logTime = sdf.format(System.currentTimeMillis());
		System.out.print(logTime+ " ");
		System.out.println(sign.getDeclaringTypeName() + "클래스에서 " + sign.getName() + "메서드 실행");
	}
	
}
