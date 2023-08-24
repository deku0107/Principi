package Buisness;

import Dao.Utenti.AmministratoreDao;
import Dao.Utenti.ManagerDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Dao.UtilityDao;

import javax.swing.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilityBuisness {

    private static final UtilityBuisness instance=new UtilityBuisness();
    public static UtilityBuisness getInstance(){return instance;}

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}$";
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public int getMax(String table, String idName){

        return UtilityDao.getInstance().getMax(table, idName);

    }

    public boolean checkFormattedEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();//torna falso se l'email e' corretta

    }

    public boolean checkEmail(String email){

        if(!UtenteAcquirenteDao.getInstance().controlloCredenziali(email)){
            if(!ManagerDao.getInstance().controlloCredenziali(email)){
                return AmministratoreDao.getInstance().controlloCredenziali(email);
            }else{
                return true;
            }
        }else{
            return true;
        }

    }

    public boolean checkFloat(String i){
        try {
            Float.parseFloat(i);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }

    }

    public boolean checkInteger(String i){
        try {
            Integer.parseInt(i);
            return true;
        }catch (NumberFormatException e ){
            return false;
        }

    }
    public boolean chekData(int yearMax, int yearMin, String dayS, String monthS, String yearS){

        try {
            int day = Integer.parseInt(dayS);
            int month = Integer.parseInt(monthS);
            int year = Integer.parseInt(yearS);

            boolean b = (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) || (month == 2 && ((day > 29 && (year % 4 == 0)) || (day > 28 && (year % 4 != 0))));

            if ((day > 31 || month > 12 || year < yearMin || year > yearMax || b)) {
                System.out.println("Data non valida " + yearS);
                return false;
            }

            return true;

        } catch (NumberFormatException ex) {
            // se entra nel catch vuol dire che il valore recuperato non è un intero e continua il ciclo
            JOptionPane.showMessageDialog(null, "Formato data errato!");
            return false;
        }

    }

    public String getRandomString(int j){
        StringBuilder randomString= new StringBuilder();
        Random random= new Random();
        for (int i = 0; i < j; i++) {
            // Genera un numero casuale
            int randomNumber = random.nextInt(alphabet.length());

            // Converte il numero casuale in un carattere
            char randomCharacter = alphabet.charAt(randomNumber);

            // Aggiungi il carattere alla stringa
            randomString.append(randomCharacter);
        }

        return randomString.toString();
    }
}
