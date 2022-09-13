package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        // TODO Auto-generated method stub
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장 추가되었습니다. ");
    }


    @Override
    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void selectOne(int id) {
        // TODO Auto-generated method stub

    }

    public void listAll() {
        System.out.println("------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(word.contains(keyword)) {
                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
                idlist.add(i);
            }
        }
        System.out.println("------------------------------");
        return idlist;
    }

    public void listAll(int level){
        System.out.println("------------------------------");
        for(int i = 0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel == level) {
                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("------------------------------");
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 검색 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(id-1);
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 검색 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하시겠습니까?(y/n) : ");
        String answer = s.next();
        if(answer.equalsIgnoreCase("y")){
            list.remove(list.get(id-1));
            System.out.println("단어가 삭제되었습니다");
        }
        else{
            System.out.println("취소되었습니다");
        }
    }

    public void searchItem() {
        System.out.print("=> 검색할 단어 입력 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
    }

    public void searchItemByLevel() {
        System.out.print("=> 레벨(1:초급, 2:중급, 3: 고급) 선택 : ");
        int level = s.nextInt();
        listAll(level);
    }

    public void loadFile(){
            try {
                BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"));

                String line;
                int count = 0;
                while(true){
                    line = br.readLine();
                    if(line == null) break;
                    String data[] = line.split("\\|");
                    int level = Integer.parseInt(data[0]);
                    String word = data[1];
                    String meaning = data[2];
                    list.add(new Word(0,level,word,meaning));
                    count++;
                }
                br.close();
                System.out.println("==>" + count + "개 로딩완료!");
            } catch (IOException e){
                e.printStackTrace();
            }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("Dictionary.txt"));
            for(Word one : list){
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("데이터 저장 완료!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

