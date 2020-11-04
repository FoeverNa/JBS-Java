package junit.p05;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RomanConverterTest {
    RomanConverter converter;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public RomanConverterTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.converter = new RomanConverter();
    }

    @Test
    public void exceptionWhenRomanNotSet() {
        this.thrown.expect(ArithmeticException.class);
        int actualResult = this.converter.transform();
    }

    @Test
    public void convertI() {
        this.converter.setRoman("I");
        int actualResult = this.converter.transform();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(1)));
    }

    @Test
    public void convertX() {
        this.converter.setRoman("X");
        int actualResult = this.converter.transform();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(10)));
    }

    @Test
    public void convertIII() {
        this.converter.setRoman("III");
        int actualResult = this.converter.transform();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(3)));
    }

    @Test
    public void convertIV() {
        this.converter.setRoman("IV");
        int actualResult = this.converter.transform();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(4)));
    }

    @Test
    public void convertVI() {
        this.converter.setRoman("VI");
        int actualResult = this.converter.transform();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(6)));
    }
}
