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

    public Matrix(){
    }

    public Matrix(int lines, int columns) throws NegativeSizeMatrix, MaxSizeMatrix{
        if( lines<=0 || columns<=0 ) throw new NegativeSizeMatrix();
        if( lines>max_size || columns>max_size ) throw new MaxSizeMatrix(max_size);
        this.lines = lines;
        this.columns = columns;
        M = new double[lines][columns];
    }

    //квадратная матрица
    public Matrix(int size) throws NegativeSizeMatrix, MaxSizeMatrix{
        if(size<= 0) throw new NegativeSizeMatrix();
        if(size > max_size) throw new MaxSizeMatrix(max_size);
        this.lines = this.columns = size;
        M = new double[size][size];
    }

    public Matrix(Matrix m) throws NullObject{
        if(m==null) throw new NullObject();
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
        if( lines>max_size ) throw new MaxSizeMatrix(getMax_size());
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
            this.lines = lines;
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
        this.columns = columns;
    }

    public void enter_matrix(Utl u) throws NoUtl{
        if(u == null) throw new NoUtl();
        for(int i=0; i<lines; i++)
            for(int j=0; j<columns; j++)
                M[i][j] = u.get_int();
    }

    public void enter_matrix_from_file(Utl u) throws FileIsOver, NoUtl{
        if(u == null) throw new NoUtl();
        for(int i=0; i<lines; i++)
            for(int j=0; j<columns; j++)
            {
                try {
                    M[i][j] = u.get_int_from_file();
                }
                catch (FileIsOver ex){
                    System.out.println(ex.getMessage());
                    throw new FileIsOver();
                }
            }
    }

    public Matrix block_swap() throws IOException, NoUtl, NullObject{
        Matrix new_m = null;
        try {
            new_m = new Matrix( this);
        }
        catch (NullObject ex){
            System.out.println(ex.getMessage());
            return null;
        }
        if(new_m ==null)
            throw new NullObject();
        double temp;
        // блоки главной диагонали
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                new_m.M[i][j] = M[i+(lines+1)/2][j+(columns+1)/2];
            }
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                new_m.M[i+(lines+1)/2][j+(columns+1)/2] = M[i][j];
            }
        // меняем блоки побочной диагонали
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                new_m.M[i+(lines+1)/2][j] = M[i][j+(columns+1)/2];
            }
        for(int i=0; i<lines/2; i++)
            for(int j=0; j<columns/2; j++){
                new_m.M[i][j+(columns+1)/2] = M[i+(lines+1)/2][j];
            }
        return new_m;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Matrix) )
            return false;
        Matrix o = (Matrix) obj;
        if(o.columns == this.columns && o.lines == this.lines) {
            for(int i=0; i<lines; i++)
                for(int j=0; j<columns; j++)
                    if(o.M[i][j] != M[i][j])
                        return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("");
        for(int i=0; i<lines; i++){
            for(int j=0; j<columns; j++){
                s.append(" " + M[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

}
