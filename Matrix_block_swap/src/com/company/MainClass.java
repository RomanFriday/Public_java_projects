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
        try{
            if ( is_use_text.equals("") ) use_text(u);
            else not_use_text(u);
        }
        catch (NullObject ex) {
            System.out.println(ex.getMessage());
            return;
        }
        catch (FileIsOver ex) {
            System.out.println(ex.getMessage());
            return;
        }
        catch (NoUtl ex) {
            System.out.println(ex.getMessage());
            return;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        return;
    }

    public static void use_text(Utl u) throws IOException, FileIsOver, NoUtl, NegativeSizeMatrix, MaxSizeMatrix, NullObject{
        try{
        u = new Utl("C:\\Users\\Working\\IdeaProjects\\Public_java_projects\\Matrix_block_swap\\src\\input.txt",
                "C:\\Users\\Working\\IdeaProjects\\Public_java_projects\\Matrix_block_swap\\src\\output.txt");
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
            throw new IOException();
        }
        Matrix m=null, m1=null;
        try{
            m = create_2_pow_n_matrix_from_file(u);
            m.enter_matrix_from_file(u);
            u.out.write("Исходная матрица:\n");
            //m.print_matrix_to_file();
            u.out.write(m.toString());
            m1 = m.block_swap();
            u.out.write("\nНовая матрица:\n");
            u.out.write(m1.toString());
            if(m.equals(m1))
                u.out.write("\n Матрицы совпадают.\n");
            else
                u.out.write("\nМатрицы различаются.\n");
            u.out.close();
            u.in.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            throw new IOException();
        }
        catch (NullObject ex) {
            System.out.println(ex.getMessage());
            throw new NullObject();
        }
        catch (FileIsOver ex) {
            System.out.println(ex.getMessage());
            throw new FileIsOver();
        }
        catch (NoUtl ex) {
            System.out.println(ex.getMessage());
            throw new NoUtl();
        }
    }

    public static void not_use_text(Utl u) throws IOException, NoUtl, NegativeSizeMatrix, MaxSizeMatrix, NullObject{
        if(u == null) throw new NoUtl();
        Matrix m=null, m1=null;
        try{
            m = create_2_pow_n_matrix(u);
            System.out.println("Введите элементы матрицы ("+m.getLines()+"x"+m.getColumns()+")");
            m.enter_matrix(u);
        }
        catch (NoUtl ex){
            System.out.println(ex.getMessage());
            throw new NoUtl();
        }
        System.out.println("Исходная матрица:");
        System.out.println(m.toString());
        try {
            m1 = m.block_swap();
        } catch (NullObject ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Новая матрица:");
        System.out.println(m1.toString());
        if(m.equals(m1))
            System.out.println("\n Матрицы совпадают");
        else
            System.out.println("\n Матрицы различаются");
    }

    public static Matrix create_2_pow_n_matrix_from_file(Utl u) throws FileIsOver, NoUtl, NegativeSizeMatrix,MaxSizeMatrix{
        if(u == null) throw new NoUtl();
        int size = 0;
        Matrix m = new Matrix(1,1);
        boolean all_is_ok = false;
        while(!all_is_ok){
            try{
                size = u.get_int_from_file();
                m.setLines((int)Math.pow(2,size));
                m.setColumns((int)Math.pow(2,size));
                all_is_ok = true;
            }
            catch (FileIsOver ex){
                System.out.println(ex.getMessage());
                throw new FileIsOver();
            }
            catch(NegativeSizeMatrix ex){
                //System.out.println(ex.getMessage());
            }
            catch(MaxSizeMatrix ex){
                //System.out.println(ex.getMessage());
            }
        }
        return m;
    }

    public static Matrix create_2_pow_n_matrix(Utl u)throws NegativeSizeMatrix, MaxSizeMatrix, NoUtl{
        if(u == null) throw new NoUtl();
        int size = 0;
        Matrix m = new Matrix();
        boolean all_is_ok = false;
        System.out.println("Введите размер матрицы 2^n :");
        while(!all_is_ok){
            try{
                size = u.get_int();
                m = new Matrix((int)Math.pow(2,size));
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

