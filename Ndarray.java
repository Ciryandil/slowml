import java.util.Random;

class Ndarray
{
    private double arr[];
    public int num_inds;
    public int shape[];
    
    public Ndarray(int dims[])
    {
        shape = new int[dims.length];
        num_inds=1;
        for(int i = 0; i < dims.length; i++)
        {
            shape[i] = dims[i];
            num_inds *= dims[i];
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


    }
}