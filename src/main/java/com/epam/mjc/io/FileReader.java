package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class FileReader {

    public Profile getDataFromFile(File file) {
        var profile = new Profile();

        try (FileInputStream in = new FileInputStream(file)) {
//            FileReader.class.getResource("/Profile.txt")
            StringBuilder builder = new StringBuilder();
            int next = in.read();
            Flag flag = Flag.NAME;

            while (next != -1) {
                if (next == '\n') {
                    switch (flag) {
                        case NAME:
                            profile.setName(builder.toString());
                            break;
                        case AGE:
                            profile.setAge(Integer.valueOf(builder.toString()));
                            break;
                        case EMAIL:
                            profile.setEmail(builder.toString());
                            break;
                        case PHONE:
                            profile.setPhone(Long.valueOf(builder.toString()));
                            break;
                    }
                    builder.delete(0, builder.length());
                } else {
                    builder.append((char) next);
                    switch (builder.toString()) {
                        default:
                            break;
                        case "Name: ":
                            flag = Flag.NAME;
                            builder.delete(0, builder.length());
                            break;
                        case "Age: ":
                            flag = Flag.AGE;
                            builder.delete(0, builder.length());
                            break;
                        case "Email: ":
                            flag = Flag.EMAIL;
                            builder.delete(0, builder.length());
                            break;
                        case "Phone: ":
                            flag = Flag.PHONE;
                            builder.delete(0, builder.length());
                            break;
                    }
                }
                next = in.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }


    private enum Flag {
        NAME,
        AGE,
        EMAIL,
        PHONE
    }
}
