package iban_valid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author rimid
 * @readFile reads data from file
 */
public class Client {

    public static void getCommands() throws IOException {
        try ( Scanner sc = new Scanner(System.in)) {

            boolean exit = true;
            String pas = null;
            int comm;
            while (exit) {
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("Insert command No:");
                try {
                    pas = sc.nextLine();
                } catch (NoSuchElementException ex) {
                    exit = false;
                }
                if (!Client.isNumeric(pas)) {
                    comm = 4;
                } else {
                    comm = Integer.parseInt(pas);
                }
                switch (comm) {
                    case 1:
                        System.out.println("");
                        System.out.println("Command 1 Interactive IBAN verification");
                        initiateInformationToConsole(sc);
                        break;
                    case 2:
                        System.out.println("");
                        System.out.println("Command 2 IBAN verification from file");
                        if (verifyIbanFromFile(sc)) {
                            System.out.println("Completed!");
                        } else {
                            exit = false;
                        }
                        break;
                    case 3:
                        exit = false;
                        System.out.println("Good bye!!!");
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static boolean initiateInformationToConsole(Scanner sc) throws MalformedURLException, IOException {
        String ibanNumber;
        boolean test;

        System.out.println("Please insert IBAN number (for example BE71096123456769)");
        ibanNumber = sc.nextLine().toUpperCase();
        System.out.println("");
        test = IbanCheck.ibanTest(ibanNumber);
        if (test) {
            System.out.println(ibanNumber + " Valid :) ");
        } else {
            System.out.println("!!!!!!");
            System.out.println("IBAN is not valid!!! Please insert correct IBAN");
        }
        return test;
    }

    public static void getMenu() {
        System.out.println("        MENIU");
        System.out.println("Command No.      Description");
        System.out.println("");
        System.out.println("1               Interactive IBAN verification");
        System.out.println("");
        System.out.println("2               IBAN verification from file");
        System.out.println("");
        System.out.println("3               Exit");
        System.out.println("____________________________________________________");
        System.out.println("");
    }

    private static boolean verifyIbanFromFile(Scanner sc) throws IOException {
        List<String> result = new ArrayList<>();
        List<String> filePathData = getFilePath(sc);
        String fileName;

        try {
            fileName = Objects.toString(filePathData.get(0));
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        try {
            List<String> list = readFile(fileName);
            for (int i = 0; i < list.size(); i++) {
                if (IbanCheck.ibanTest(list.get(i))) {
                    result.add(list.get(i) + ";true");
                } else {
                    result.add(list.get(i) + ";false");
                }
            }
        } catch (IOException e) {
        }
        writeToFile(result, filePathData);
        return true;
    }

    private static List<String> getFilePath(Scanner sc) throws IOException {
        String dir = null;
        String fileName;
        String textPath;
        List<String> pathData = new ArrayList<>();
        File f;

        System.out.println("Insert file directory, for example (C:\\Users\\user\\Desktop\\New folder)");
        try {
            dir = sc.nextLine();
        } catch (NoSuchElementException ex) {
            getCommands();
        }
        System.out.println("Insert file name, for example (fileName.txt}");
        fileName = sc.nextLine();

        textPath = dir + "\\" + fileName;
        if (textPath.equals("\\")) {
            getFilePath(sc);
        } else {
            try {
                f = new File(textPath);

                if (f.exists()) {
                    pathData.add(f.getPath());
                    pathData.add(dir);
                    pathData.add(fileName);
                } else {
                    throw new FileNotFoundException();
                }
            } catch (NullPointerException | FileNotFoundException ex) {
                System.out.println("File not found please try again");
                getCommands();
            }
        }
        return pathData;
    }

    /**
     * @param fileName
     * @return ArrayList from file
     * @throws java.io.FileNotFoundException
     */
    private static List<String> readFile(String fileName) throws FileNotFoundException, IOException {

        List<String> result = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            //Ignored
        }
        return result;
    }

    public static void writeToFile(List<String> result, List filePathData) throws IOException {

        String newFileName = changeFileExtension(filePathData.get(2));
        String newPath = filePathData.get(1) + "\\" + newFileName;
        List<String> s;

        try ( OutputStream os = new FileOutputStream(newPath);  Writer w = new OutputStreamWriter(os, "UTF-8");  BufferedWriter bw = new BufferedWriter(w)) {
            s = result;

            for (int i = 0; i < result.size(); i++) {
                bw.write(s.get(i));
                bw.newLine();
            }
        }
        System.out.println("Please find verified IBAN values in: " + newPath);
    }

    private static String changeFileExtension(Object fullFileName) {
        String newFileExtension = null;
        String ffn = fullFileName.toString();
        for (int i = ffn.length() - 1; i >= 0; i--) {
            if (ffn.charAt(i) == '.') {
                newFileExtension = ffn.substring(0, i) + ".out";
                break;
            }
        }
        return newFileExtension;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NullPointerException | NumberFormatException exception) {
            return false;
        }
    }
}
