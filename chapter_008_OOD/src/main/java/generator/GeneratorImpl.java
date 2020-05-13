package generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorImpl implements Generator {

    // искомое начинается с ${ , экранируем через \\ чтобы воспринимались как простые символы
    // затем идёт любое кол-во любых символов .*
    // затем - ленивый режим квантификатора ?}, то есть первое найденный символ } будет концом вхождения
    private final Pattern KEYS = Pattern.compile("\\$\\{.*?}");

    @Override
    public String generate(String template, Map<String, String> map) {

        if (map.isEmpty()) {
            throw new RuntimeException("в карте нет ключей");
        }

        Matcher matcher = KEYS.matcher(template);
        StringBuffer resultSb = new StringBuffer();
        Set<String> uniqMatchCounter = new HashSet<>();

        while (matcher.find()) {
            // получаем то, что будем заменять, убрав с  краёв  ${  и  }
            String forReplace = template.substring(matcher.start() + 2, matcher.end() - 1);
            // альтернатива:
            // String forReplace = matcher.group().substring(2, matcher.group().length() - 1);

            // проверяем есть ли нужный ключ
            String replacement = map.get(forReplace);
            if (replacement == null) {
                throw new RuntimeException("в карте отсутствует необхожимый ключ: " + forReplace);
            }

            uniqMatchCounter.add(forReplace);

            // делаем "добавление-и-замена"
            // при нахождении вхождения - добавляет в resultSb всё что было ДО вхождения и
            // добавляет замену для вхождения (2й аргумент метода)
            // то что осталось после последнего вхождения - НЕ прибавляет в resultSb
            matcher.appendReplacement(resultSb, replacement);
        }

        // проверяем на лишние ключи
        if (map.size() != uniqMatchCounter.size()) {
            throw new RuntimeException("в карте есть лишние ключи");
        }

        // добавим остаточный хвост после последнего найдённого вхождения
        matcher.appendTail(resultSb);
        return resultSb.toString();
    }
}