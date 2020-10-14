package com.company;

import java.io.IOException;

import my_exceptions.FileIsOver;
import my_exceptions.MaxSizeMatrix;
import my_exceptions.NegativeSizeMatrix;
import my_exceptions.NoUtl;
import my_exceptions.NullObject;

public class Matrix{
    int lines = 1, columns = 1;
    final int max_size = 32;
    double[][] M = new double[lines][columns];
    Utl u = null;
    public Matrix(Utl u) throws NoUtl{
        if( u == null) throw new NoUtl();
        this.u = u;
    }

    public Matrix(Utl u, int lines, int columns) throws NegativeSizeMatrix, MaxSizeMatrix, NoUtl {
        if( u == null) throw new NoUtl();
        if( lines<=0 || columns<=0 ) throw new NegativeSizeMatrix();
        if( lines>max_size || columns>max_size ) throw new MaxSizeMatrix(max_size);
        this.u = u;
        this.lines = lines;
        this.columns = columns;
        M = new double[lines][columns];
    }

    //квадратная матрица
    public Matrix(Utl u, int size) throws NegativeSizeMatrix, MaxSizeMatrix, NoUtl {
        if( u == null) throw new NoUtl();
        if( size<=0 || size<=0 ) throw new NegativeSizeMatrix();
        if( size>max_size || size>max_size ) throw new MaxSizeMatrix(max_size);
        this.u = u;
        this.lines = this.columns = size;
        M = new double[size][size];
    }

    public Matrix(Utl u, Matrix m) throws NoUtl, NullObject{
        if( u == null ) throw new NoUtl();
        if( m == null ) throw new NullObject();
        this.u = u;
        lines = m.lines;
        columns = m.columns;
        M = new double[lines][columns];
        for(int i=0; i<lines; i++)
            for(int j=0; j<columns; j++)
                M[i][j] = m.M[i][j];
    }

    public int getColumns() {
        return columns;
    }

    public int getLines() {
        return lines;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setLines(int lines) throws NegativeSizeMatrix, MaxSizeMatrix{
        if( lines<=0 ) throw new NegativeSizeMatrix();
        if( lines>max_size ) throw new MaxSizeMatrix(max_size);
        double[][] copy = new double[this.lines][this.columns];
        // запоминаем матрицу
        for(int i=0; i<this.lines; i++)
            for(int j=0; j<columns; j++)
                copy[i][j] = M[i][j];
        M = new double[lines][this.columns];
        // заполняем все имеющиеся строки
        for(int i=0; i<Math.min(lines, this.lines); i++)
            for(int j=0; j<columns; j++)
                copy[i][j] = M[i][j];
    }

    public void setColumns(int columns) throws NegativeSizeMatrix, MaxSizeMatrix{
        if( columns<=0 ) throw new NegativeSizeMatrix();
        if( columns>max_size ) throw new MaxSizeMatrix(max_size);
        double[][] copy = new double[this.lines][this.columns];
        // запоминаем матрицу
        for(int i=0; i<lines; i++)
            for(int j=0; j<this.columns; j++)
                copy[i][j] = M[i][j];
        M = new double[lines][columns];
        // заполняем все имеющиеся столбцы
        for(int i=0; i<lines; i++)
            for(int j=0; j<Math.min(columns,this.columns); j++)
                copy[i][j] = M[i][j];
    }

    public void enter_matrix(){
        for(int i=0; i<lines; i++)
            for(int j=0; j<columns; j++)
                M[i][j] = u.get_int();
    }

    public void enter_matrix_from_file() throws FileIsOver, IOException{
        for(int i=0; i<lines; i++)
            for(int j=0; j<columns; j++)
                M[i][j] = u.get_int_from_file();
    }

    /*public void print_matrix(){
        for(int i=0; i<lines; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(" "+M[i][j]);
            System.out.println();
        }
    }*/

    /*public void print_matrix_to_file() throws IOException{
        for(int i=0; i<lines; i++) {
            for (int j = 0; j < columns; j++)
                u.out.write(" "+M[i][j]);
            u.out.write('\n');
        }
    }*/

    public void block_swap(){
        double temp;
        // меняем блоки главной диагонали
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                temp = M[i][j];
                M[i][j] = M[i+(lines+1)/2][j+(columns+1)/2];
                M[i+(lines+1)/2][j+(columns+1)/2] = temp;
            }
        // меняем блоки побочной диагонали
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                temp = M[i+(lines+1)/2][j];
                M[i+(lines+1)/2][j] = M[i][j+(columns+1)/2];
                M[i][j+(columns+1)/2] = temp;
            }
    }

    @Override
    public String toString(){
        String s = "";
        for(int i=0; i<lines; i++){
            for(int j=0; j<columns; j++)
                s += " "+M[i][j];
            s += "\n";
        }
        return s;
    }

}
