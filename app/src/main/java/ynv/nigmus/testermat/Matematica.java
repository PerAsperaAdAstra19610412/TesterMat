package ynv.nigmus.testermat;

import android.util.Log;

import androidx.preference.PreferenceManager;

import java.util.Random;
//Matematica - Математика на Итальянском.
class Matematica {
    //
    private static final int YES = R.drawable.ic_baseline_check_circle_24;
    private static final int NO = R.drawable.ic_baseline_cancel_24;

    private static int randMin;
    private static int randMax;

    private static int x;
    private static int y;
    private static int z;
    //private static boolean returnResult = true;// Пока нет ответа не генерировать новую задачу.
    private static boolean check = false;// Правельно ли ответил пользователь.
    private static long timeInMillis;
    private static final long TIME_8SEC_IN8000Millis = 8000;
    private static final int MAX_NUMBER_IN_STR = 8;

    private static int countYES = 0;

    public static void setTimeInMillis(long timeInMillis) {
        Matematica.timeInMillis = timeInMillis;
    }

    public static void setRandMin(String randMin) {
        Matematica.randMin = Integer.parseInt(randMin);
    }

    public static void setRandMax(String randMax) {
        Matematica.randMax = Integer.parseInt(randMax);
    }

    public static void setCountYES(int countYES) {
        Matematica.countYES = countYES;
    }

    public static boolean setСheck(final String s){

        if ( s.isEmpty() || s.length()>MAX_NUMBER_IN_STR ){ // Проверка на пустое поле при вводе или не больше 8 цифр.
            z = 0;
            return false;
        }

        z = Integer.parseInt(s);
        if ( x + y == z) {
            return check = true;
        }
        return check = false;
    }

    public static String getResult(){
        return Integer.toString(x) + " + " +  Integer.toString(y) + " = " + Integer.toString(z);
    }

    public static void getNewNumber(){
        if (randMin < randMax){
            x = randomNextInt(randMax,randMin);
            y = randomNextInt(randMax,randMin);
            } else  { //--> Если пользователь перепутал поля в настройках max min <--
                        x = randomNextInt(randMin,randMax);
                        y = randomNextInt(randMin,randMax);
                             }

    }

    public static String getxandy() {// Сколько будет?
        return Integer.toString(x) +" + " +  Integer.toString(y);
    }

    public static int getYESorNO(){// Giusto - правельно на Итальянском.
        if (check){
            if (timeInMillis < TIME_8SEC_IN8000Millis) {
                countYES++;
                if ( countYES >= 3){
                    randMax += 8;
                    //randMin += 8;
                    countYES = 0;
                }
            }
            check = false;
            return YES;
        }
        countYES = 0;
        return NO;
    }

    private static int randomNextInt(final int randMax, final int randMin){
        Random random = new Random();
        return random.nextInt((randMax + 1) - randMin) + randMin;
    }

}
