import com.example.FileSearch;

public class App {
    public static void main(String[] args) throws Exception {
        FileSearch app = new FileSearch();
        switch (Math.min(args.length, 3)) {
            case 3: app.setZipFileName(args[2]);
            case 2: app.setZipFileName(args[1]);
            case 1: app.setZipFileName(args[0]);
            default:
                System.out.println("USAGE: FileSearchApp path [regex] [zipfile]");
                break;
        }

        try {
            app.walkDirectory(app.getPath());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
