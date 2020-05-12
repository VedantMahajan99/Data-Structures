
import java.util.*;
import java.io.*;

public class Main {
    // main function

    public static void main(String[] args) {
        boolean i = true;

        // check if the # of arguments is 2
        if (args.length == 2) {
            Scanner inputFile = null;
            PrintWriter outFile = null;
            File outputFile = new File(args[1]);

            String line = "";

            // try to open input and output file
            try {
                inputFile = new Scanner(new File(args[0]));

                // create output file if the file does not exist
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }

                outFile = new PrintWriter(outputFile);

                if(inputFile.hasNext() == false)
                {
                    outFile.print("NULL FILE !");
                    outFile.close();
                    System.exit(0);
                }

                line = inputFile.nextLine().trim();

                RedBlackTree<Integer> itree = new RedBlackTree<>();
                RedBlackTree<String> stree = new RedBlackTree<>();

                if (line.compareTo("Integer") == 0) {
                    i = true;
                } else if (line.compareTo("String") == 0) {
                    i = false;
                } else {

                    outFile.print("Only works for objects Integers and Strings");
                    outFile.close();
                    System.exit(0);

                }

                while (inputFile.hasNextLine()) {
                    line = inputFile.nextLine().trim();
                    String[] input = new String[line.length()];

                    // divide the command into two parts
                    if (line.contains(":")) {
                        input = line.split(":");
                    } else {
                        input[0] = line;
                    }

                    switch (input[0]) {

                        // calls the insert command of RedBlackTree class
                        case "Insert":
                            if (input[1] == null) {
                                outFile.println("Error in Line: Insert");
                            } else {
                                try {
                                    if (i) {
                                        boolean insert = itree.insert(Integer.parseInt(input[1]));
                                        outFile.println(insert);
                                    } else {
                                        boolean insert = stree.insert(input[1]);
                                        outFile.println(insert);
                                    }

                                } catch (IllegalArgumentException ex) {
                                    outFile.println("Error in insert: IllegalArgumentException raised");
                                }
                            }

                            break;

                        // calls the contains command of RedBlackTree class
                        case "Contains":
		            		try {
                            if (i) {
                                boolean con = itree.contains(Integer.parseInt(input[1]));
                                outFile.println(con);
                            } else {
                                boolean con = stree.contains(input[1]);
                                outFile.println(con);
                            }

                        } catch (Exception ex) {
                            outFile.println("Invalid input");
                        }

                        break;

                        // calls the PrintTree command of LazyBST class
                        case "PrintTree":
                            if (i) {
                                String str = itree.toString();
                                outFile.println(str);
                            } else {
                                String str = stree.toString();
                                outFile.println(str);
                            }
                            break;

                        case "":
                            break;

                        default:
                            outFile.println("Error in Line: " + input[0]);
                    }
                }

                // close the input and output files
                inputFile.close();
                outFile.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + inputFile + "'");

            } catch (IOException ex) {
                System.out.println("Error reading file '" + inputFile + "'");
            }
        } else {
            System.out.println("Please Input Valid Arguments in Command Line");
        }

    }

}
