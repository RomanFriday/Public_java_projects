package com.company;

import java.io.IOException;
import java.util.Scanner;

import my_exceptions.FileIsOver;
import my_exceptions.MaxSizeMatrix;
import my_exceptions.NegativeSizeMatrix;
import my_exceptions.NoUtl;
import my_exceptions.NullObject;

public class MainClass {
    public static void main(String[] args) throws IOException, FileIsOver, NoUtl, NegativeSizeMatrix, MaxSizeMatrix, NullObject {
        System.out.println("Использовать текстовый файл? \n" +
                "Введите произвольную строку или " +
                "нажмите ENTER, чтобы продолжить");
        Utl u = new Utl();
        String is_use_text = u.s.nextLine();
        if ( is_use_text.equals("") ) use_text(u);
        else not_use_text(u);
        return;
    }

    public static void use_text(Utl u) throws IOException, FileIsOver, NoUtl, NegativeSizeMatrix, MaxSizeMatrix, NullObject{
        u = new Utl("C:\\Users\\Working\\IdeaProjects\\Public_java_projects\\Matrix_block_swap\\src\\input.txt",
                "C:\\Users\\Working\\IdeaProjects\\Public_java_projects\\Matrix_block_swap\\src\\output.txt");
        if(u==null) return;
        Matrix m = create_2_pow_n_matrix_from_file(u);
        m.enter_matrix_from_file();
        u.out.write("Исходная матрица:\n");
        //m.print_matrix_to_file();
        u.out.write(m.toString());
        Matrix m1 = null;
        try{
            m1 = m.block_swap();
        } catch (NullObject ex) {
            System.out.println(ex.getMessage());
            return;
        }
        u.out.write("\nНовая матрица:\n");
       //m1.print_matrix_to_file();
        u.out.write(m1.toString());
        u.out.close();
        u.in.close();
        return;
    }

    public static void not_use_text(Utl u) throws IOException, NoUtl, NegativeSizeMatrix, MaxSizeMatrix, NullObject{
        if(u == null) return;
        Matrix m = create_2_pow_n_matrix(u);
        System.out.println("Введите элементы матрицы ("+m.lines+"x"+m.columns+")");
        m.enter_matrix();
        System.out.println("Исходная матрица:");
        //m.print_matrix();
        System.out.println(m.toString());
        Matrix m1 = null;
        try {
            m1 = m.block_swap();
        } catch (NullObject ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Новая матрица:");
        //m1.print_matrix();
        System.out.println(m1.toString());
    }

    public static Matrix create_2_pow_n_matrix_from_file(Utl u) throws IOException, FileIsOver, NoUtl, NegativeSizeMatrix,MaxSizeMatrix{
        if(u == null) return null;
        int size = 0;
        Matrix m = null;
        boolean all_is_ok = false;
        while(!all_is_ok){
            try{
                size = u.get_int_from_file();
                m = new Matrix(u, (int)Math.pow(2,size));
                all_is_ok = true;
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
            catch(NegativeSizeMatrix ex){
                //System.out.println(ex.getMessage());
            }
            catch(MaxSizeMatrix ex){
                //System.out.println(ex.getMessage());
            }
            catch (NoUtl ex){
                System.out.println(ex.getMessage());
                all_is_ok = true;
            }
        }
        return m;
    }

    public static Matrix create_2_pow_n_matrix(Utl u)throws NegativeSizeMatrix, MaxSizeMatrix, NoUtl{
        if(u == null) return null;
        int size = 0;
        Matrix m = null;
        boolean all_is_ok = false;
        System.out.println("Введите размер матрицы 2^n :");
        while(!all_is_ok){
            try{
                size = u.get_int();
                m = new Matrix(u, (int)Math.pow(2,size));
                all_is_ok = true;
            }
            catch (NoUtl ex){
                System.out.println(ex.getMessage());
                all_is_ok = true;
            }
            catch (NegativeSizeMatrix ex){
                System.out.println(ex.getMessage());
            }
            catch (MaxSizeMatrix ex){
                System.out.println(ex.getMessage());
            }
        }
        return m;
    }
}

