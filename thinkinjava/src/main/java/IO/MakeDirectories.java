package IO;

import java.io.File;

/**
 * Created by wangbo on 2018/9/3.
 */
public class MakeDirectories {

    private static void usage() {
        System.out.println();
        System.exit(1);
    }

    private static void fillData(File f) {
        System.out.println(f.getAbsolutePath());
        System.out.println(f.canRead());
        System.out.println(f.canWrite());
        System.out.println(f.getName());
        System.out.println(f.getParent());
        System.out.println(f.getPath());
        System.out.println(f.length());
        System.out.println(f.lastModified());

        if (f.isDirectory()) {
            System.out.println("its a directory");
        } else if (f.isFile()) {
            System.out.println("its a file");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) usage();
        if (args[0].equals("-r")) {
            if (args.length != 3) usage();
            File old = new File(args[1]),
                 rname = new  File(args[2]);
            old.renameTo(rname);
            fillData(old);
            fillData(rname);
            return;
        }
        int count = 0;
        boolean del = false;
        if (args[0].equals("-d")) {
            count++;
            del = true;
        }
        count --;
        while (++count < args.length) {
            File f = new File(args[count]);
            if (f.exists()) {
                System.out.println(f + "exits");
                if (del) {
                    System.out.println("deleting" +f);
                    f.delete();
                }
            } else {
                if (!del) {
                    f.mkdirs();
                    System.out.println("created" + f);
                }
            }
            fillData(f);
        }
    }
}
