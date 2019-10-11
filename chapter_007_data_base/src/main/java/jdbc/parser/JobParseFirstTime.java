package jdbc.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.List;

public class JobParseFirstTime implements Job {

    private static final Logger LOG = LogManager.getLogger(JobParseFirstTime.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        LOG.trace("запуск задачи первый раз");

        SqlRuParser sqlRuParser = new SqlRuParser();
        sqlRuParser.parsePages("https://www.sql.ru/forum/job-offers/", true);
        sqlRuParser.printToLogVacancies();
        
        List<Vacancy> vacancies = sqlRuParser.getVacancies();
        
        ParserStore parserStore = new ParserStore();
        parserStore.connectDB();
        parserStore.createTable();
        parserStore.save(vacancies);
        parserStore.close();

        LOG.trace("парсер завершил первый проход");
    }
}
