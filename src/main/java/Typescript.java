import wci.frontend.TypeScriptParser;

public class Typescript
{
    public static void main(String[] args)
    {
        try {
            TypeScriptParser.main(args);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
