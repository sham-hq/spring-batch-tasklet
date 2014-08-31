package spring.batch.tasklet.ex;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Shamsul Haque
 *
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration()
public class TaskletContinueEx 
{
	private static int i = 0;

	@Bean
	protected Tasklet tasklet() {
		return new Tasklet() {
			public RepeatStatus execute(StepContribution contribution,
					ChunkContext context) {
				i++;
				System.out.println("in execute: i value: " + i);
				if (i < 5) {
					return RepeatStatus.CONTINUABLE;
				} else {
					System.out.println("stopping execute");
					return RepeatStatus.FINISHED;
				}
			}
		};
	}

	@Bean
	public Job job(JobBuilderFactory jobs, Step s1) throws Exception {
		return jobs.get("testJob").start(s1).build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, Tasklet tasklet) {
		return stepBuilderFactory.get("testStep").tasklet(tasklet)
				.build();
	}

	public static void main(String[] args) {
		TaskletContinueEx ex = new TaskletContinueEx();
		System.exit(ex.startProcess(args));
	}
	
	public int startProcess(String[] args) {
		SpringApplication.exit(SpringApplication.run(
				TaskletContinueEx.class, args));
		return 0;
	}
}
