package Util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class TxtReaderTest {

    @Test
    void CheckIfReaderThrowsException() throws FileNotFoundException {
        assertThrows(FileNotFoundException.class, () -> {
            new TxtReader().reader("");
        });
    }

}