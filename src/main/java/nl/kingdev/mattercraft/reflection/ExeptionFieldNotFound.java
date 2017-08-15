package nl.kingdev.mattercraft.reflection;

/**
 * Created by Jasper on 15-8-2017.
 */
public class ExeptionFieldNotFound extends Exception {

    private String fieldname;

     public ExeptionFieldNotFound(String fieldname) {
         this.fieldname = fieldname;
     }

    @Override
    public void printStackTrace() {
        System.err.println("[MatterCraft] [ReflectionHelper] Failed to find field : " + this.fieldname);
    }
}
