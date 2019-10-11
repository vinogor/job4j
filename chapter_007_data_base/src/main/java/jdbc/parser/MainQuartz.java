package jdbc.parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class MainQuartz {

    public static Config config_parser;
    public static String lastStartDate = "";

    public MainQuartz(Config config) {
        config_parser = config;
    }

    private void start() {
        try {
            // создаём Планировщик
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            // запускаем Планировщик
            scheduler.start();
            
            // определим что будет делать Планировщик
            // в скобках - класс реализующий интерфейс Job
            JobDetail jobFirstTime = newJob(JobParseFirstTime.class)
                .withIdentity("job1", "group1")
                .build();
            
            JobDetail jobNotFirstTime = newJob(JobParseNotFirstTime.class)
                .withIdentity("job2", "group1")
                .build();
            
            // определим при каких условиях будет запускаться Планировщик
            Trigger triggerFirstTime = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .build();
            
            Trigger triggerNotFirstTime = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(config_parser.get("cron.time")))
                .startNow()
                .build();
            
            // сообщаем шедулеру что надо делать и когда
            scheduler.scheduleJob(jobFirstTime, triggerFirstTime);
            scheduler.scheduleJob(jobNotFirstTime, triggerNotFirstTime);
            
            // в спячку, а то Job не успеет отработать, а Шедулер уже завершиться!!
//            System.out.println("спим");
//            Thread.sleep(1000);
//            System.out.println("проснулись");
            
            // завершаем
//            scheduler.shutdown();
        
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    // добавить перед запуском в Program Arguments: app3.properties
    public static void main(String[] args) {
        new MainQuartz(new Config(args[0])).start();
    }
}
