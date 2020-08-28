import java.util.Random;

class Ndarray
{
    private double arr[];
    public int num_inds;
    public int shape[];
    private int multipliers[];
    public Ndarray(int dims[])
    {
        shape = new int[dims.length];
        multipliers = new int[dims.length];
        int num_inds=1, dimsize=1;
        for(int i = 0; i < dims.length; i++)
        {
            shape[i] = dims[i];
            num_inds *= dims[i];
        }
        for(int i=dims.length - 1; i >= 0; i--)
        {
            multipliers[i] = dimsize;
            dimsize *= dims[i];
        }
        arr = new double[num_inds];
        Random gen = new Random();
        for(int i = 0; i < num_inds; i++)
        {
            arr[i] = gen.nextDouble();
        }
    }

    public void fill(double x)
    {
        for(int i = 0; i < num_inds; i++)
            arr[i] = x;
    }

    private int find_index(int idx[])
    {
        int internal_idx = 0;
        for(int i = 0; i < idx.length; i++)
        {
            internal_idx = multipliers[i] * idx[i];
        }

        return internal_idx;
    }
    
    public double get(int idx[])
    {
        int index = find_index(idx);
        return arr[index];
    }

    public double get(int idx)
    {
        return arr[idx];
    }

    public void put(int idx[], double val)
    {
        int index = find_index(idx);
        arr[index] = val;
    }

    public void put(int idx, double val)
    {
        arr[idx] = val;
    }

    public Ndarray dot(Ndarray a, Ndarray b)
    {
        if(a.shape.length != 2 || b.shape.length != 2)
            throw new IllegalArgumentException();
        if(a.shape[1] != b.shape[0])
            throw new IllegalArgumentException();
        int res_shape[] = {a.shape[0], b.shape[1]};
        Ndarray res = new Ndarray(res_shape);
        res.fill(0.0);
        for(int i = 0; i < a.shape[0]; i++)
        {
            for(int j = 0; j < a.shape[1]; j++)
            {
                //double val = 0.0;
                for(int k = 0; k < b.shape[1]; k++)
                {
                    res.put(new int[] {i, j}, res.get(new int[] {i, j}) + a.get(new int[] {i, k}) * b.get(new int[] {k, j}));
                }
                
            }
        }

        return res;
    
    }

    public Ndarray elementp(Ndarray a, Ndarray b)
    {
        if(a.shape.length != b.shape.length)
            throw new IllegalArgumentException();
        int n = a.num_inds;
        for(int i = 0; i< a.shape.length; i++)
        {
            if(a.shape[i] != b.shape[i])
                throw new IllegalArgumentException();
        }
        Ndarray res = new Ndarray(a.shape);
        for(int i = 0; i < n; i++)
            res.put(i, a.get(i) * b.get(i)); 
            
        return res;
    }

    public void reshape(int new_shape[])
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
    }
}