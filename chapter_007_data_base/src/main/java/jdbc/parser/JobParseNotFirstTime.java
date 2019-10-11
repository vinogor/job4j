package jdbc.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.List;

public class JobParseNotFirstTime implements Job {

    private static final Logger LOG = LogManager.getLogger(JobParseNotFirstTime.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        LOG.trace("парсим НЕ первый раз");

        SqlRuParser sqlRuParser = new SqlRuParser();
        sqlRuParser.parsePages("https://www.sql.ru/forum/job-offers/", false);
        sqlRuParser.printToLogVacancies();
    
        List<Vacancy> vacancies = sqlRuParser.getVacancies();
    
        ParserStore parserStore = new ParserStore();
        parserStore.connectDB();
        parserStore.save(vacancies);
        parserStore.close();

        LOG.trace("парсер завершил очередной проход");
    }
}