package org.example;

import java.util.Scanner;

public class WordManager {

    WordCRUD wordCRUD;
    Scanner s = new Scanner (System.in);

    public int selectOption() {

        System.out.print("*** 영단어 마스터 ***\n"
                + "******************\n"
                + "1. 모든 단어보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
                + "******************\n"
                + "=> 원하는 옵션은? ");

        return s.nextInt();
    }

    WordManager(){
        wordCRUD = new WordCRUD(s);
    }


    public void start() {
        while(true) {
            int option = selectOption();
            if(option == 0) break;
            if(option == 4) {
                wordCRUD.addWord();
            }
            else if(option == 1) {
                wordCRUD.listAll();
            }
            else if(option == 5){
                wordCRUD.updateItem();
            }
            else if(option == 6){
                wordCRUD.deleteItem();
            }
        }
//		System.out.println(option);
    }
}
