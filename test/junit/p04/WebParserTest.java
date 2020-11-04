package junit.p04;


import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebParserTest {
    private WebParser parser;

    public WebParserTest() {
    }

    @Before
    public void setUpUsingPageWithThreeImages() {
        this.parser = new WebParser((targetUrl) -> {
            return "<html> <meta content=a.png> <meta content=b.gif> <meta content=c.jpg> </html>";
        });
    }

    @Test
    public void countImageFromThreeImagePageStub() throws IOException {
        int actualResult = this.parser.countImageFromWebPage("http://google.com");
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(3)));
    }
}