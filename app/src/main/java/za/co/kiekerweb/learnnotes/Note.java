package za.co.kiekerweb.learnnotes;

public class Note {

    private String name = "";
    private String image = "";
    private String pos = "";



    public Note(String p_name, String p_image, String p_pos)
    {
        name = p_name;
        image = p_image;
        pos = p_pos;
    }
    public boolean verifyNote(String p_name,String p_pos)
    {
        if ((p_name == name) && (p_pos == pos))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public String getName()
    {
        return name;
    }
    public String getPos()
    {
        return pos;
    }
    public String getImage()
    {
        return image;
    }
}
