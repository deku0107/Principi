package Test.Buisness;

import Buisness.AbstractFactory.AbstractFactoryUtente.FactoryProviderUtente;
import Buisness.Utente.LoginResult;
import Buisness.Utente.UtenteBusiness;
import org.junit.Test;

public class UtenteLoginTest {

    @Test
    public void loginTest(){
        String usr="Franco";
        String psw="12345";
        LoginResult result=UtenteBusiness.getInstance().login(usr, psw, FactoryProviderUtente.FactoryType.CLIENTE);
        System.out.println(result.getMessaggio());
        System.out.println(result.getResult());

        usr="Franc";
        psw="1234";
        result=UtenteBusiness.getInstance().login(usr, psw, FactoryProviderUtente.FactoryType.CLIENTE);
        System.out.println(result.getMessaggio());
        System.out.println(result.getResult());


    }
}
