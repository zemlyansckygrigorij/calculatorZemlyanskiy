package main;

import java.lang.NumberFormatException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mathematics {
    private String startStr = "(";
    private String finishStr = ")";

    private String [] symbols = {"*","/","-","+","(",")","0","1","2","3","4","5","6","7","8","9"};
    private String [] numbers = {"0","1","2","3","4","5","6","7","8","9"};

    private String enter;
    private List<String> list = new ArrayList<String>();;
//Заполнение строки
    public Mathematics(String str){
       if(checkExpress(str.trim())) {
           String s = str.trim();
           this.enter = s.replaceAll(" ", "");
       }else{
           System.out.println("you enter bad char");
       }
    }


//вычисление значения
    /*
    * Алгоритм вычисления
    * 1) Создать список(String) из чисел и знаков
    * 2) В списке найти первую ')'
    * 3) Двигаясь от нее влево найти '('
    * 4) Взять подсписок между '(' и ')'
    * 5) В подсписке найти знак арифметической операции и элементы справа и слева от него
    * 6) Сделать вычисление
    * 7) Продолжать вычисления пока в подсписке остаються арифметические знаки
    * 8) Пункты 2)- 7) продолжать пока есть знаки '(' и ')'
    * 9) Выполнить пункты 5)- 7)
    * 10) Вывести результат на экран
    *
    *
    *
    *
    * */
    public String getResult(){
        setListExpress(enter);

        if (list.indexOf(finishStr) == -1 && list.indexOf(startStr) == -1) {
            subStringExpress((list));
            return list.toString();
        }
        while(list.contains(startStr)) {
            int finish = list.indexOf(finishStr);
            if (finish != -1 && list.indexOf(startStr) == -1) {
                System.out.println("Есть '(' .Отсутствует ')'  " );
                System.exit(0);
            }
            List<String> sList = list.subList(0, finish);
            int start = sList.lastIndexOf(startStr);
            if (start == -1) {
                System.out.println("Есть ')' .Отсутствует '('  " );
                System.exit(0);
            }

            List<String> sendList = sList.subList(start + 1, finish);
            List<String> getList = subStringExpress(sendList);
            deleteNullElemFromList();
            list.remove(start + 2);
            list.remove(start);
        }
        subStringExpress((list));
        return list.toString();


    }
// Удаление из списка пустых элементов
    private void deleteNullElemFromList(){
        for(int i =0;i<list.size();i++){
            if(list.get(i).equals("")){
                list.remove(i);
            }
        }
    }

//  Вычисления
    private int getExpress(String Left,String Right,int numExpress){

        int intLeft = getNumber(Left);
        int intRight = getNumber(Right);
        int result = 0;
        switch (numExpress) {
            case 0:  result = intLeft * intRight ;
                break;
            case 1:  result = intLeft / intRight ;
                break;
            case 2:  result = intLeft - intRight ;
                break;
            case 3:  result = intLeft + intRight ;
                break;
        }
        return result;
    }

    //  Обработка Значенния окруженного скобками
    private List<String> subStringExpress(List<String> sendList){

        int index;
            for (int i = 0; i < 4; i++) {
                while (sendList.contains(symbols[i])) {

                    index = sendList.indexOf(symbols[i]);
                    if ((i == 0 || i == 1) && (index == 0 || index == sendList.size() - 1)) {
                        System.out.println("error subStringExpress 1");
                        System.exit(0);
                    }
                    if ((i == 2 || i == 3) && (index == sendList.size() - 1)) {
                        System.out.println("error subStringExpress 2");
                        System.exit(0);
                    }

                    int result = getExpress(sendList.get(index - 1), sendList.get(index + 1), i);

                    sendList.remove(index + 1);
                    sendList.remove(index);
                    sendList.remove(index - 1);

                    sendList.add(index - 1, Integer.toString(result));
                }
            }
        return sendList;
    }

// Заполнение списка числе и знаков
    private void setListExpress(String str){
        StringBuilder sb = new StringBuilder("");

        for(int i =0 ; i < str.length() ; i++){

            if(Arrays.asList(numbers).contains(Character.toString(str.charAt(i)))){
                sb = sb.append(str.charAt(i))  ;
            }else{
                list.add(sb.toString());
                String  s = Character.toString(str.charAt(i));
                if(!s.equals(" ")){
                    list.add(s);
                }
                sb = new StringBuilder("");
            }
        }
        list.add(sb.toString());
        deleteNullElemFromList();
    }
    //Проверка является ли данная строка числом
    private int getNumber(String str){
        try{
            int result = Integer.parseInt(str);
            return result;
        }
        catch(NumberFormatException e) {
            System.out.println("Bad Enter");
        }
        return 0;

    }

    //проверка на наличие посторонних символов . Кроме цифр и арифметических знаков
    private boolean checkExpress(String str){

        boolean bool = true;

        ArrayList <String> list = new ArrayList<String>(Arrays.asList(str.split("")));
        list.remove("");
        list.removeAll( Arrays.asList(symbols) );

        if (list.size() !=0){
            bool =false;
            System.out.println("Проверьте запись .Найдены посторонние знаки " );

        }
        return bool;
    }
}
