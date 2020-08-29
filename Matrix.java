import java.util.Random;

class Matrix
{
    private double arr[][];
    public int num_inds;
    public int rows,cols;

    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        int num_inds=rows * cols;

        arr = new double[rows][cols];
        Random gen = new Random();
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                arr[i][j] = gen.nextDouble();
        }
    }

    public void fill(double x)
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                arr[i][j] = x;
        }
    }

    public double get(int i, int j)
    {
    
        return arr[i][j];
    }

    public void put(int i, int j, double val)
    {
        arr[i][j] = val;
    }

    public Matrix dot(Matrix b)
    {

        if(b.rows != this.cols)
            throw new IllegalArgumentException();
        
        Matrix res = new Matrix(this.rows, b.cols);
        res.fill(0.0);
        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                //double val = 0.0;
                for(int k = 0; k < b.cols; k++)
                {
                    
                    res.put(i, j, (res.get(i, j) + this.get(i, k) * b.get(k, j)));
                }
                
            }
        }

        return res;
    
    }

    public Matrix eproduct(Matrix b)
    {
        if(this.rows != b.rows || this.cols != b.cols)
            throw new IllegalArgumentException();
        Matrix res = new Matrix(this.rows, this.cols);
        for(int i = 0; i < this.rows; i++)
        {
            for( int j = 0; j < this.cols; j++)
            {
                res.put(i , j, (this.get(i,j) * b.get(i,j)));
            }
        }
            
        return res;
    }

    public Matrix eproduct(Matrix b, int axis)
    {
        Matrix res = new Matrix(this.rows, this.cols);
        if(axis == 0)
        {
        if(this.cols != b.cols || this.rows % b.rows != 0)
            throw new IllegalArgumentException();
        

        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                res.put(i , j, (this.get(i,j) * b.get(i % b.rows, j)));
            }
        }
        
        }

        if(axis == 1)
        {
        if(this.rows != b.rows || this.cols % b.cols != 0)
            throw new IllegalArgumentException();
        

        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                res.put(i , j, (this.get(i,j) * b.get(i, j % b.cols)));
            }
        }
        
        }        

        return res;
    }

    public Matrix eproduct(double val)
    {
        Matrix res = new Matrix(this.rows, this.cols);
        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                res.put(i , j, (this.get(i, j) * val));
            }
        }

        return res;
    }

    public Matrix slice(int r1, int r2, int c1, int c2)
    {
        if(r1 > r2 || c1 > c2 || r1 < 0 || r2 >= this.rows || c1 < 0 || c2 >= this.cols)
            throw new IllegalArgumentException();
        Matrix res = new Matrix(r2 - r1 + 1, c2 - c1 + 1);

        for(int i = r1; i <= r2; i++)
        {
            for(int j = c1; j <= c2; j++)
            {
                res.put((i - r1), (j - c1), this.get(i, j));
            }
        }

        return res;
    }

    public Matrix transpose()
    {
        Matrix res = new Matrix(this.cols, this.rows);

        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
                res.put(j, i, this.get(i, j));
        }

        return res;
    }

    //TODO: reshape(), eadd()
    /*public void reshape(int new_shape[])
    {
        int total = 1;
        for(int i = 0; i < new_shape.length; i++)
        {
            total *= new_shape[i];
        }
        if(total != num_inds)
            throw new IllegalArgumentException();
        shape = new int[new_shape.length];
        multipliers = new int[new_shape.length];
        int dims = 1;
        for(int i = 0; i < shape.length; i++)
        {
            shape[i] = new_shape[i];
        }

        for(int i = multipliers.length - 1; i >= 0; i--)
        {
            multipliers[i] = dims;
            dims *= new_shape[i];
        }
    }*/


}