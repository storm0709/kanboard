package API.POJO.dataprovidersapi;

import org.testng.annotations.DataProvider;

public class NegativeUserCreds {
    @DataProvider(name="negativeUserCredentials")
    public static Object [][] userCredentialsDataProvider(){
        return new Object[][]{
                {"", "admin"},
                {"", ""},
                {"admin", "123456"}
        };
    }
}
