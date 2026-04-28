package com.tideclient.Module.Config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import com.tideclient.Module.Module;
import java.util.Map;

public class CONFIG {
    private static final String FILE = "config.json";
    private static final Gson gson = new Gson();

    public static void save(List<Module> modules) {
        try {
            Map<String, Boolean> data = new HashMap<>();

            for (Module m : modules) {
                data.put(m.getName(), m.getStatus());
            }

            Files.write(Paths.get(FILE), gson.toJson(data).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(List<Module> modules) {
        try {
            File file = new File(FILE);
            if (!file.exists()) return;

            String json = new String(Files.readAllBytes(Paths.get(FILE)));

            Type type = new TypeToken<Map<String, Boolean>>(){}.getType();
            Map<String, Boolean> data = gson.fromJson(json, type);

            for (Module m : modules) {
                Boolean state = data.get(m.getName());

                if (state != null && state != m.getStatus()) {
                    m.toggle(); // минимально — без лишнего кода
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}