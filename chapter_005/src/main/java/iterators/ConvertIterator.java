package iterators;

import java.util.Iterator;

public class ConvertIterator {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> tempIter1;
            // на случай если есть пустые итераторы 2го уровня
            {
                tempIter1 = it.next();
                while ((!tempIter1.hasNext()) && (it.hasNext()) ){
                    tempIter1 = it.next();
                }
            }
            @Override
            public boolean hasNext() {
                boolean result = true;
                if ( (!it.hasNext()) && !tempIter1.hasNext()) {
                    result = false;
                }
                return result;
            }

            @Override
            public Integer next() {

                if (!tempIter1.hasNext()) {
                    tempIter1 = it.next();
                }
                return tempIter1.next();
            }
        };
    }
}