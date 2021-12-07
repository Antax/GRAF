package main.pw3;

import main.m1graf2021.UndirectedGraf;

import java.util.Scanner;

public class testProgramPw3 {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path to a dot file");
        String path = input.next();
        try{
            ChinesePostman cp = new ChinesePostman(new UndirectedGraf(path));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
