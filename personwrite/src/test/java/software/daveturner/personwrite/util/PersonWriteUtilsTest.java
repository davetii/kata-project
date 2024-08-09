package software.daveturner.personwrite.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.apache.commons.lang3.StringUtils.*;

class PersonWriteUtilsTest {

    PersonWriteUtils utils  = new PersonWriteUtils();

    @Test
    void ensureThreeRandomLettersAreAlwaysAlpha() {
        Assertions.assertTrue(isAlpha(utils.threeRandomLetters()));
    }

    @Test
    void ensureThreeRandomNUmbersAreAlwaysNumeric() {
        Assertions.assertTrue(isNumeric(utils.threeRandomNumbers()));
    }
}