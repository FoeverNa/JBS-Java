package junit.p02;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
    private Account account;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public AccountTest() {
    }

    @BeforeClass
    public static void classSetUp() {
    }

    @Before
    public void setUpBySetBalanceOneHundred() {
        this.account = new Account(100);
    }

    @Test
    public void answerIsMinusWithNegativeBalance() {
        this.account.withdraw(150);
        boolean actualResult = this.account.isMinus();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(true)));
        Assert.assertThat(actualResult, CoreMatchers.not(CoreMatchers.equalTo(false)));
    }

    @Test
    public void answerIsNotMinusWithPositiveBalance() {
        this.account.withdraw(50);
        boolean actualResult = this.account.isMinus();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.not(CoreMatchers.equalTo(true))));
    }

    @Test
    public void checkPositiveBalanceAfterWithdrawal() {
        this.account.withdraw(80);
        int actualResult = this.account.getBalance();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(20)));
    }

    @Test
    @Ignore("This will be tested later.")
    public void checkNegativeBalanceAfterWithdrawal() {
        this.account.withdraw(120);
        int actualResult = this.account.getBalance();
        Assert.assertThat(actualResult, CoreMatchers.is(CoreMatchers.equalTo(-20)));
    }

    @Test(
            expected = ArithmeticException.class
    )
    public void checkExceptionByAnnotation() {
        this.account.throwExcept();
    }

    @Test
    public void checkExceptionByTryCatch() {
        try {
            this.account.throwExcept();
            Assert.fail();
        } catch (ArithmeticException var2) {
            Assert.assertThat(var2.getClass(), CoreMatchers.equalTo(ArithmeticException.class));
        }

    }

    @Test
    public void checkExceptionByRule() {
        this.thrown.expect(ArithmeticException.class);
        this.account.throwExcept();
    }
}
