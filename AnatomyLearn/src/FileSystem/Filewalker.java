package FileSystem;
import java.io.File;

public class Filewalker { 
	String str = "";
    public String walk( String path ) { 

        File root = new File( path ); 
        File[] list = root.listFiles(); 

        for ( File f : list ) { 
            if ( f.isDirectory() ) { 
                walk( f.getAbsolutePath() ); 
               str = str + f.getAbsoluteFile() + "\n";
            } 
            else { 
            	str = str + f.getAbsoluteFile() + "\n";
            } 
        } 
        return str;
    } 

 
} 