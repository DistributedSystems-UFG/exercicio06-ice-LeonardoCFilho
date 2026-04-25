module Demo
{
    interface Printer
    {
        string printString(string s);
        string printUpperCase(string s);
    }

    interface Calculator
    {
        int soma(int a, int b);
        int subtrair(int a, int b);
        int multiplicar(int a, int b);
    }
}
