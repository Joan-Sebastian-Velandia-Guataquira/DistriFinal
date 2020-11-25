package RMI;

public class Transaccion{
    
    private int vacA;
    private int vacB;
    private int vacC;

    public int getVacA(){
        return vacA;
    }

    public void setVacA(int vacA){
        this.vacA = vacA;
    }

    public int getVacB(){
        System.out.println("mmm");
        return vacC;
        
    }

    public void setVacB(int vacB){
        this.vacB = vacB;
    }

    public int getVacC(){
        return vacC;
    }

    public void setVacC(int vacC){
        this.vacC = vacC;
    }

    public Transaccion(int vacA, int vacB, int vacC)
    {
        this.vacA = vacA;
        this.vacC = vacC;
        this.vacB = vacB;
    }
}
