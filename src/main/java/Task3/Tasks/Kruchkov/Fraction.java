package Task3.Tasks.Kruchkov;

public class Fraction implements Fractionable {
    private int num;
    private int denum;
    public final FractionFT forTest;


    public Fraction(int num, int denum)    {
        this.num = num;
        this.denum = denum;
        forTest = new FractionFT();
        forTest.count=0;
        forTest.countR=0;
    }

    @Mutator
    public void setNum(int num)    {
        this.num = num;
    }
    @Mutator
    public void setDenum(int denum)     {
        this.denum = denum;
    }

    public int getDenum(){
        return this.denum ;
    }
    @Cashe
    public double doubleValue()    {
        //  System.out.println("выполнение расчета значения");
        forTest.count++;
        return (double) this.num/this.denum;
    }

    @Cashe
    public double reverseValue()    {
        //   System.out.println("выполнение расчета значения");
        forTest.countR++;
        return (double) this.denum/this.num;
    }

}


