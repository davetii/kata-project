package software.daveturner.personwrite.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PersonWriteUtils {

    public String threeRandomLetters() {
        Random r = new Random();
        char[] letters = {
                (char) (r.nextInt(26) + 'a'),
                (char) (r.nextInt(26) + 'a'),
                (char) (r.nextInt(26) + 'a')
        };
        return new String(letters);
    }

    public String threeRandomNumbers() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        return new String(sb);
    }
}
