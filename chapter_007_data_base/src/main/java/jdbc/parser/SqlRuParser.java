package jdbc.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdbc.parser.MainQuartz.lastStartDate;

public class SqlRuParser {

    private static final Logger LOG = LogManager.getLogger(JobParseFirstTime.class.getName());
    
    // Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
    private Map<String, Vacancy> vacancies = new LinkedHashMap<>();
    private String localLastStartDate = null;
    
    public void parsePages(String url, boolean isFirstParse) {
        
        Document document = null;
        try {
            document = Jsoup
                .connect(url + "1")
                .get();
        } catch (IOException e) {
            LOG.error(e.getStackTrace());
        }
        
        // парсим кол-во страниц
        int numOfPages = Integer.parseInt(
            document
                .getElementsByAttributeValue("class", "sort_options")
                .get(1)
                .getElementsByTag("a")
                .get(9)
                .text()
        );
        
        LOG.trace("всего страниц: " + numOfPages);
        
        for (int i = 1; i < numOfPages + 1; i++) {
            LOG.trace("парсим страницу номер: " + i);
            // если парвый раз не текущий год, то стоп
            boolean b = parsePage(url + i, isFirstParse);
            if (isFirstParse && !b) {
                LOG.trace("Док, это НЕ ТОТ ГОД!! ");
                break;
            }
            // если НЕ первый раз и дата равна самой первой дате предыдущего поиска, то стоп
            if (!isFirstParse && !b) {
                LOG.trace("последнюю выведенную вакансию и остальные мы уже парсили в прошлый раз");
                break;
            }
        }
        lastStartDate = localLastStartDate;
        LOG.trace("найдено новых вакансий: " + vacancies.size());
    }
    
    private boolean parsePage(String url, boolean isFirstParse) {
        LOG.trace("ссылка на страницу: " + url);
        Document document = null;
        try {
            document = Jsoup
                .connect(url)
                .get();
        } catch (IOException e) {
            LOG.error(e.getStackTrace());
        }
        
        Elements lines = document
            .getElementsByAttributeValue("class", "forumTable")
            .get(0)
            .getElementsByTag("tr");
        
        for (int i = 4; i < lines.size(); i++) {
            LOG.trace("строка " + i);
            Element el = lines.get(i);
            
            Element nameAndLink = el.getElementsByTag("a").get(0);
            String link = nameAndLink.attr("href");
            String name = nameAndLink.text();
            
            // будем считать что это - дата создания вакансии
            String date = el.getElementsByAttributeValue("class", "altCol").get(1).text();
            
            if (date.startsWith("сегодня")) {
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MMM yy");
                String dateNow = formatForDateNow.format(new Date()).replace(".", "");
                date = dateNow + date.substring(7);
            }
            
            if (date.startsWith("вчера")) {
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MMM yy");
                String dateNow = formatForDateNow.format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)).replace(".", "");
                date = dateNow + date.substring(5);
            }
            
            // так как даты в порядке убывания, то сохраняем самую первую
            if (localLastStartDate == null) {
                localLastStartDate = date;
            }

            LOG.trace("    " + name);
            LOG.trace("    " + link);
            LOG.trace("    " + date);
            
            // спарсим текст и дату создания вакансии
            try {
                document = Jsoup.connect(link).get();
            } catch (IOException e) {
                LOG.error(e.getStackTrace());
            }
            
            // текст вакансии
            String text = document.getElementsByAttributeValue("class", "msgBody").get(1).text();
            
            // настоящая же дата создания вакансии вот
            // но даты расположены не в порядке убывания =((
//            String dateAndTime = document.getElementsByAttributeValue("class", "msgFooter").get(0).toString();
//            dateAndTime = dateAndTime.substring(dateAndTime.indexOf('>') + 2, dateAndTime.indexOf('&'));

            LOG.trace("    " + text);
            
            // если парсим первый раз, то как только наталкивается на прошлый год - стоп всего парсинга
            if (isFirstParse && !isCurrentYear(date)) {
                return false;
            }
            
            // если парсим не первый раз, то останавливаемся после того как наткнёмся
            // на дату, которая уже была в прошлом парсинге
            if (!isFirstParse && (lastStartDate.equals(date) )) {
                return false;
            }
            
            if (checkNameVacancy(name)) {
                vacancies.put(name, new Vacancy(name, text, link));
            }
        }
        return true;
    }
    
    public void printToLogVacancies() {
        for (Map.Entry entry : vacancies.entrySet()) {
            LOG.trace(entry.getValue());
        }
        LOG.trace("дата самой свежей вакансии: " + lastStartDate);
    }
    
    private boolean isCurrentYear(String date) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        int index = date.indexOf(',');
        String year = date.substring(index - 2, index);
        return Integer.parseInt(year) == currentYear;
    }
    
    private boolean checkNameVacancy(String name) {
        return
            (name.contains("Java") || name.contains("java"))
                && !name.contains("JavaScript")
                && !name.contains("Java Script");
    }
    
    public List<Vacancy> getVacancies() {
        return new ArrayList<>(vacancies.values());
    }
}