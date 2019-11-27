package cn.zeffect.task.schedu;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

	// 参考：https://blog.csdn.net/weixin_34346099/article/details/91537274
	// https://blog.csdn.net/cowbin2012/article/details/85219887
	// 注意，返回值应该都是void;
	// fixedDelay 上一次执行完毕时间点之后多长时间再执行
	// fixedRate 上一次开始执行时间点之后多长时间再执行
	// initialDelay 第一次延迟多长时间后再执行
	// initialDelay不能单独存在

	@Scheduled(fixedDelay = 1000)
	private void task1() {
		System.out.println("上一次执行完了之后，每过1秒执行task1");
	}

	@Scheduled(fixedRate = 5000)
	private void task2() {
		System.out.println("上一次开始执行之后，每过5秒执行task2");
	}

	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
	private void task3() {
		System.out.println("第一次延迟10秒执行，上一次开始执行后，每过5秒执行task3");
	}

	@Scheduled(cron = "*/5 * * * * ?")
	private void task4() {
		// cron表达式，更强大，比如，每天几点执行，或者指定什么时候执行都行。
		// cron表达式生成器：http://qqe2.com/cron
		System.out.println("每过5秒执行一次");
	}
}
